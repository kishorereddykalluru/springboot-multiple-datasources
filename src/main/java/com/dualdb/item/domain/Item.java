package com.dualdb.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="item")
public class Item {

    @Id
    @Column(name = "item_id")
    private int id;
    @Column(name = "item_name")
    private String name;
}
