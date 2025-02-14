package com.mtg.collection.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "collection_cards")
@NoArgsConstructor
@AllArgsConstructor
public class CollectionCard implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    private static final long serialVersionUID = 1L;
}
