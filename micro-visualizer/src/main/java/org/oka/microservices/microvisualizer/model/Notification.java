package org.oka.microservices.microvisualizer.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table
@ToString
public class Notification implements Serializable {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CUSTOMER")
    private String user;

    @Column(name = "MESSAGE")
    private String message;

    public Notification() {
    }
}
