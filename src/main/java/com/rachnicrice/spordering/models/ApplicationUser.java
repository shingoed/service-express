package com.rachnicrice.spordering.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ApplicationUser implements UserDetails {
    //instance variables
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long user_id;

    String username;
    String password;
    String email;
    String phone;
    String firstName;
    String lastName;

    @OneToOne(mappedBy = "user")
    Cart cart;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ApplicationUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(String username, String password, String email, String phone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
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
