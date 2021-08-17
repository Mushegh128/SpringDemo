package com.example.springdemo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Lesson {
    @Id
    private int id;
    private String title;
}
