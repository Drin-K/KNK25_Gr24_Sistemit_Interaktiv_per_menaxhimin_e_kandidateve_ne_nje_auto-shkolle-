package models.Enums;

public enum LoginStatus {
    SUCCESS("Logimi ishte i suksesshëm."),
    USER_NOT_FOUND("Përdoruesi nuk u gjet."),
    INVALID_PASSWORD("Fjalëkalimi është i pasaktë."),
    FAILURE("Ka ndodhur një gabim gjatë logimit."),
    SESSION_CREATION_FAILED(" Dështim në krijimin e sesionit");
    private final String message;

    LoginStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

