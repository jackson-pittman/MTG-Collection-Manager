package com.mtg.collection.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cards")

public class Card
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
    private int collectorNumber;
    private int convertedManaCost;
}
