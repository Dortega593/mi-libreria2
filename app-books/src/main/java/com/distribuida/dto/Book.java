package com.distribuida.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "SELECT a FROM Book a"),
        @NamedQuery(name = "Book.findById", query = "SELECT a FROM Book a WHERE a.id = :id"),
})
public class Book implements Serializable {
    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private String isbn;
    private String title;
    private double price;
}
