package org.oka.microservices.microsender.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class Notification implements Serializable {
    private String user;
    private String message;

    public Notification() {
    }
}
