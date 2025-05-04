package services;

import models.Enums.LoginStatus;

public class LoginResponse {
    private LoginStatus status;
    private String role;
    private int userId;

    public LoginResponse(LoginStatus status, String role, int userId) {
        this.status = status;
        this.role = role;
        this.userId = userId;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public int getUserId() {
        return userId;
    }
}
//Vendosja e LoginResponse në service është më e përshtatshme,
//sepse mund të menaxhojë logjikën e biznesit si remember me dhe të centralizojë
//përgjigjet e logimit, duke ndihmuar
//në ruajtjen dhe përpunimin e informacionit të përdoruesit pa e ngarkuar modelin me detaje të tilla.
