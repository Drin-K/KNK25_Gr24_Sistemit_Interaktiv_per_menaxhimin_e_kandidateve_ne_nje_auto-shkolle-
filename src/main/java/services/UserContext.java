package services;


// UserContext vendoset më mirë te services, sepse menaxhon gjendjen dinamike të përdoruesit
// të autentifikuar, përmban logjikë të brendshme,
//  dhe nuk përfaqëson një entitet të dhënash si User, Stafi, Admin apo Kandidat
// Kjo e bën atë tipike për një service.
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
//UserContext është statik për të mundësuar qasje globale
//dhe të thjeshtë për gjendjen e përdoruesit gjatë sesionit,
//pa krijuar instanca. Funksionon mirë për aplikacione desktop me një përdorues aktiv,
//por nuk është i sigurt për aplikacione web me shumë përdorues, sepse të dhënat statike
//ndahen mes të gjithë përdoruesve.