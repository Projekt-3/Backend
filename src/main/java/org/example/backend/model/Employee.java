package org.example.backend.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int ID;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String mail;
    @Column(unique = true)
    private int phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;




    //empty constructor
    public Employee() {
    }

    public Employee(int ID, Role role, String password, int phone, String mail, String username, String lastname, String firstname) {
        this.ID = ID;
        this.role = role;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
        this.username = username;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
