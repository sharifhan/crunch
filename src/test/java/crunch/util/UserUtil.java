package crunch.util;

import java.util.ArrayList;
import java.util.List;

import crunch.domain.User;

public class UserUtil {
	/*
	 * Tests utils for storing user into database
	 * 
	 * */
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String TAXYEAR = "taxyear";
    private static final String GROSS = "12";
    private static final String NET = "12";
    private static final String REQUEST = "request";

    private UserUtil() {
    }

    public static User createUser() {
        return new User(EMAIL, TAXYEAR, GROSS, NET, REQUEST);
    }

    public static List<User> createUserList(int howMany) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            userList.add(new User(EMAIL, TAXYEAR, GROSS, NET, REQUEST));
        }
        return userList;
    }

}
