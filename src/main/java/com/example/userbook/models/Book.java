package com.example.userbook.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private int id;

    private  String name;

    private Date created;

    private int authorId;

    private User author;

    private int page;

    public Book(String name, int page, Date created) {
        this.name = name;
        this.page = page;
        this.created = created;
    }
}
