/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Role.Role;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class UserAccountDirectory {

    ArrayList<UserAccount> useraccountlist;

    public UserAccountDirectory() {

        useraccountlist = new ArrayList();

    }

    public UserAccount newUserAccount(String un, String pw) {
        if (findUserAccount(un) != null) {
            return null;
        }
        UserAccount ua = new UserAccount(un, pw);
        useraccountlist.add(ua);
        return ua;
    }

    public UserAccount findUserAccount(String un) {

        for (UserAccount ua : useraccountlist) {

            if (ua.isMatch(un)) {
                return ua;
            }
        }
        return null; //not found after going through the whole list
    }

    public UserAccount AuthenticateUser(String un, String pw) {

        for (UserAccount ua : useraccountlist) {

            if (ua.IsValidUser(un, pw)) {
                return ua;
            }
        }

        return null; //not found after going through the whole list
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return useraccountlist;
    }

    public ArrayList<UserAccount> removeUser(UserAccount userAccount) {
        useraccountlist.remove(userAccount);
        return useraccountlist;
    }

}
