package com.mtg.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Card implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String type;
    private String rarity;
    @Column(length=1000)
    private String description;
    private String imageUrl;
    private Integer collectorNumber;
    private Integer convertedManaCost;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectionCard> collectionCards = new ArrayList<>();

    private static final long serialVersionUID = 1L;


}
