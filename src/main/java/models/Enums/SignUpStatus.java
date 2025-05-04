package models.Enums;

public enum SignUpStatus {
    SUCCESS("Përdoruesi u krijua me sukses."),
    EMAIL_EXISTS("Ky email tashmë është i regjistruar."),
    PASSWORDS_DO_NOT_MATCH("Fjalëkalimet nuk përputhen."),
    INVALID_ROLE("Roli është i pasaktë."),
    FAILURE("Ka ndodhur një gabim gjatë regjistrimit.");

    private final String message;

    SignUpStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
