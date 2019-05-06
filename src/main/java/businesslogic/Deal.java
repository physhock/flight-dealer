package businesslogic;

import user.Administrator;

public class Deal {

    private Ask ask;
    private Bet bet;
    private Administrator administrator;

    public String status;

    public Deal(Ask ask, Bet bet, Administrator administrator) {
        this.ask = ask;
        this.bet = bet;
        this.administrator = administrator;
        this.status = "Opened";
    }




}
