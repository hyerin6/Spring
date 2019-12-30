package net.skhu.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String author;

    int price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    Category category;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    Publisher publisher;

    int available;

}
