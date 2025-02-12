package com.mtg.collection.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@NamedQuery(name = "UserInfo.getAllUsers", query = "select new com.mtg.collection.entity.UserInfo(u.id, u.username, u.email, u.status) from UserInfo u where u.isDeletable = true and u.email not in (:email)")
@NamedQuery(name = "UserInfo.updateUser", query = "update UserInfo u set u.status = :status where u.id = :id and isDeletable = true")
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor


public class UserInfo implements Serializable
{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false,unique=true)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(nullable = false)
    private String email;



    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status;

    @Column(nullable=false)
    private boolean isDeletable;

    private static final long serialVersionUID = 1L;
    /*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;

     */

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public UserInfo(Long id, String username, String email, Status status)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeletable() {
        return isDeletable;
    }

    public void setDeletable(boolean deletable) {
        isDeletable = deletable;
    }
}
