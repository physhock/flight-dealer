package gui;

import user.User;

class CurrentUser {
    private static CurrentUser ourInstance = new CurrentUser();
    private static User user;

    private CurrentUser() {
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }

    public static CurrentUser getInstance() {
        return ourInstance;
    }
}
