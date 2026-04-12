/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Role.Role;
import java.util.UUID;

/**
 *
 * @author kal bugrara
 */
public class UserAccount {

    String id;
    String username;
    String password;

    public UserAccount(String un, String pw) {
        id = UUID.randomUUID().toString();
        username = un;
        password = pw;
    }

    public String getId() {
        return id;
    }

    public String getUserLoginName() {
        return username;
    }

    public boolean isMatch(String id) {
        if (getUserLoginName().equals(id)) {
            return true;
        }
        return false;
    }

    public boolean IsValidUser(String un, String pw) {

        if (username.equalsIgnoreCase(un) && password.equals(pw)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {

        return getUserLoginName();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
