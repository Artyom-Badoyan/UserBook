package com.example.userbook.models;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private int id;

    private String name;

    private String imageName;

    private String surname;

    private String email;

    private String password;

    private int age;

    private List<Book> list;



}
