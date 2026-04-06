/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Person;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class PersonDirectory {
    
      ArrayList<PersonAccount> personlist ;
    
      public PersonDirectory (){
          
       personlist = new ArrayList();

    }

    public PersonAccount newPerson(String id) {

        PersonAccount p = new PersonAccount(id);
        personlist.add(p);
        return p;
    }

    public PersonAccount findPerson(String id) {

        for (PersonAccount p : personlist) {

            if (p.isMatch(id)) {
                return p;
            }
        }
            return null; //not found after going through the whole list
         }
    
}
