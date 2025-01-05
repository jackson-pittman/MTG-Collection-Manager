package com.mtg.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo
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
    /*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;

     */
}
