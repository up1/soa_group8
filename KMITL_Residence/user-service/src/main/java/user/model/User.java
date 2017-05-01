package user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Adisorn on 2/4/2560.
 */
public class User {

    private String username;
    private int role;
    private String titleNameTh;
    private String fullNameTh;
    private String titleNameEn;
    private String fullNameEn;
    private String email;

    @JsonIgnore
    private String password;

    public User(String username, String password, int role, String titleNameTh, String fullNameTh, String titleNameEn, String fullNameEn, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.titleNameTh = titleNameTh;
        this.fullNameTh = fullNameTh;
        this.titleNameEn = titleNameEn;
        this.fullNameEn = fullNameEn;
        this.email = email;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getTitleNameTh() {
        return titleNameTh;
    }

    public void setTitleNameTh(String titleNameTh) {
        this.titleNameTh = titleNameTh;
    }

    public String getFullNameTh() {
        return fullNameTh;
    }

    public void setFullNameTh(String fullNameTh) {
        this.fullNameTh = fullNameTh;
    }

    public String getTitleNameEn() {
        return titleNameEn;
    }

    public void setTitleNameEn(String titleNameEn) {
        this.titleNameEn = titleNameEn;
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
