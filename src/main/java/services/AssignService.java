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

        ArrayList<Administrator> administrators = UserRepository.getUsers("Administrator").stream()
                .map(Administrator.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        // Find admin without deals or with minimal deals count
        administrators.stream().filter(administrator -> administrator.getDeals().isEmpty()).findFirst()
                .orElse(administrators.stream().min(Comparator.comparingInt(x -> x.getDeals().size())).get());

        return null;
    }


    private static Administrator findFirstOnline() {

        return (Administrator) UserRepository.getUsers("Administrator").stream()
                .filter((x -> x.getUserStatus().equals(User.UserStatus.ONLINE)))
                .findFirst().get();
    }

    private static boolean hasAdministratorsOnline() {
        return UserRepository.getUsers("Administrator").stream()
                .anyMatch(x -> x.getUserStatus().equals(User.UserStatus.ONLINE));
    }

}
