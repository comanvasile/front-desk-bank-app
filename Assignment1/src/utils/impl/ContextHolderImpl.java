package utils.impl;

import model.User;

public class ContextHolderImpl {
    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ContextHolderImpl.currentUser = currentUser;
    }
}
