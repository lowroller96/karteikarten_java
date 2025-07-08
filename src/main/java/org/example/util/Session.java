package org.example.util;

public class Session {
    private static int userId=-1;
    public static void setUserId(int id) {
        userId=id;
    }
    public static int getUserId() {
        return userId;
    }
}
