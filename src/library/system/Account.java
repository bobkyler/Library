/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

/**
 *
 * @author Kyle
 */
public class Account {
    private String id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String telephone;
    private String username;
    private String password;
    
    public Account(){}
    public Account (String id, String firstname, String middlename, String lastname, String telephone, 
            String username, String password){
        
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.telephone = telephone;
        this.username = username;
        this.password = password;

    }
    public Account(String id,String firstname,String middlename,String lastname,String telephone){
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.telephone = telephone;
    }
    //getters
    public String getID(){
        return id;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public String getLastname(){
        return lastname;
    }
    public String getTelephone(){
       return telephone;
    }
    public String getUsername(){
       return username;
    }
    public String getPassword(){
       return password;
    }
}
