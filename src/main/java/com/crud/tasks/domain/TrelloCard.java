package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;

}