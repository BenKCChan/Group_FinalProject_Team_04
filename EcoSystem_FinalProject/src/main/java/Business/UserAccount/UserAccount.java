/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccount;

import Business.Role.Role;



/**
 *
 * @author kal bugrara
 */
public class UserAccount {
    
    Role role;
    String username;
    String password;
    
    public UserAccount (Role role, String un, String pw){
        username = un;
         password = pw;
         this.role = role;

    }

    public String getPersonId(){
        return role.getPerson().getPersonId();
    }
    public String getUserLoginName(){
        return username;
    }

        public boolean isMatch(String id){
        if(getPersonId().equals(id)) return true;
        return false;
    }
        public boolean IsValidUser(String un, String pw){
        
            if (username.equalsIgnoreCase(un) && password.equals(pw)) return true;
            else return false;
        
        }
        public String getRole(){
            return role.getRole();
        }
        
        public Role getAssociatedPersonProfile(){
            return role;
        }
        
    @Override
        public String toString(){
            
            return getUserLoginName();
        }
        
}

