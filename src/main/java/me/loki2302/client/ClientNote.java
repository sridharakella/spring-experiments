package me.loki2302.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ClientNote {
    @Id
    @GeneratedValue
    public Long id;
    public String text;
}
