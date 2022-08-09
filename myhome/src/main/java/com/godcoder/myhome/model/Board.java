package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data // getter setter
public class Board
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db
    private Long id;
    @NotNull
    @Size(min=2, max=30)
    private String title;
    private String content;
}
