package com.rateyourmedia.rym_entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class User implements UserDetails{
    @Id
    @Column(length = 20)
    private String username;

    private String password;
    private Long chatID;
    private String surname;
    private String first_name;
    private Date date_of_birth;
    private boolean chat_registered;
    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;
    @OneToMany(mappedBy = "creator", orphanRemoval = true)
    private Collection<Review> reviewsU;

    public User() {}

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getChatID() {
        return chatID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getDate_of_birth(){
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_Birth){
        this.date_of_birth = date_of_Birth;
    }

    public void setAccountType(AccountType accountType){
        this.accountType=accountType;
    }

    public boolean isChat_registered(){
        return chat_registered;
    }

    public void setChat_registered(boolean chat_registered){
        this.chat_registered=chat_registered;
    }


    //Security implementation of userdetails
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

    //TODO: Bitte besser machen eher ungünstig
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority(){
            @Override
            public String getAuthority(){
                return User.this.accountType.name();
            }
        });
    }
}
