package com.mtg.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collections")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Collection implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserInfo owner;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectionCard> collectionCards = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}
