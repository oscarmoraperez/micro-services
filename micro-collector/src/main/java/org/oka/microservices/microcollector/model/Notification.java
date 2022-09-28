package org.oka.microservices.microcollector.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Notification implements Serializable {
    private long id;
    private String user;
    private String message;

    public Notification() {
    }
}
