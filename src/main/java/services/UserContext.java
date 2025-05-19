package services;


public class UserContext {
    private static String currentRole;
    private static int currentUserId;
    private static String currentEmail;

    public static void setUser(String role, int userId,String email) {
        currentRole = role;
        currentUserId = userId;
        currentEmail = email;
    }

    public static String getRole() {
        return currentRole;
    }

    public static int getUserId() {
        return currentUserId;
    }

    public static void clear() {
        currentRole = null;
        currentUserId = 0;
    }
    public static String getEmail(){
        return  currentEmail;
    }
}
