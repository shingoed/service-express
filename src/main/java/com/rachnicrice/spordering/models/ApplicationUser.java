package com.rachnicrice.spordering.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {
    //instance variables
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long user_id;

    String username;
    String spCustomer_id;
    String password;
    String email;
    String phone;
    String firstName;
    String lastName;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

//    Default Constructor
    public ApplicationUser() {
    }


    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ApplicationUser(String username, String password, String email, String spCustomer_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.spCustomer_id = spCustomer_id;
    }


    public ApplicationUser(String username, String password, String email, String phone, String firstName, String lastName, String spCustomer_id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.spCustomer_id = spCustomer_id;
    }


    //instance methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Order> getOrders() {
        return orders;
    }


    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
