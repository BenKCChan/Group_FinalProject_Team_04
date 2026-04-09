/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Role;

/**
 *
 * @author lindq
 */
import java.util.ArrayList;

public class RoleDirectory {

    private ArrayList<Role> roles = new ArrayList<>();

    public void addRole(Role r) {
        roles.add(r);
    }

    public Role findRoleByAccountId(String accountId) {
        for (Role r : roles) {
            if (r.getPerson().getId().equals(accountId)) {
                return r;
            }
        }
        return null;
    }

}
