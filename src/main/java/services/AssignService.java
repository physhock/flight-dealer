package services;

import businesslogic.Deal;
import storage.UserRepository;
import user.Administrator;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AssignService {

    public static Deal assignAdministratorToDeal(Deal deal) {

        deal.setAdministrator(
                hasAdministratorsOnline() ? findFirstOnline() : chooseFromOfflineList());

        return deal;

    }

    private static Administrator chooseFromOfflineList() {

        ArrayList<Administrator> administrators = UserRepository.getUsers().stream()
                .filter(user -> user.getClass().getSimpleName().equals("Administrator"))
                .map(Administrator.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        // Find admin without deals or with minimal deals count
        administrators.stream().filter(administrator -> administrator.getDeals().isEmpty()).findFirst()
                .orElse(administrators.stream().min(Comparator.comparingInt(x -> x.getDeals().size())).get());

        return null;
    }


    private static Administrator findFirstOnline() {
        return UserRepository.getUsers().stream()
                .filter((x -> x.getClass().getSimpleName().equals("Administrator") && x.getUserStatus().equals(User.UserStatus.ONLINE)))
                .map(Administrator.class::cast)
                .findFirst().get();
    }

    private static boolean hasAdministratorsOnline() {
        return UserRepository.getUsers().stream()
                .anyMatch((x -> x.getClass().getSimpleName().equals("Administrator") && x.getUserStatus().equals(User.UserStatus.ONLINE)));
    }

}
