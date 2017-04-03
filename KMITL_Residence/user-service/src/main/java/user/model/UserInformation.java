package user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class UserInformation {

    private String username;
    @JsonIgnore
    private String hashPassword;
    private String role;
    private String fullNameTh;
    private String fullNameEn;
    private String email;

    public UserInformation() {

    }

    public UserInformation(String username, String hashPassword, String role, String fullNameTh, String fullNameEn, String email) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.role = role;
        this.fullNameTh = fullNameTh;
        this.fullNameEn = fullNameEn;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullNameTh() {
        return fullNameTh;
    }

    public void setFullNameTh(String fullNameTh) {
        this.fullNameTh = fullNameTh;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
