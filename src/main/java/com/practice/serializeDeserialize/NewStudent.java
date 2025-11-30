package com.practice.serializeDeserialize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewStudent implements Serializable {
    private int id;
    private String name;
    @JsonIgnore
    private LocalDate date;
    private transient LocalDate pastDate; // for jackson this will still be included in JSON string
    // and deserialized object if getter and setter is present for this field


    // transient: Don’t save this anywhere, useful for Java native serialization not for jackson, for jackson use @JsonIgnore
    // transient does not stop JSON serialization if getter is present and deserialization if setter is present.

    // JsonIgnore: Don't expose this in JSON
    // JsonIgnoreProperties(ignoreUnknown=true): Don't break if extra fields come

    public NewStudent(int id, String name, LocalDate date, LocalDate pastDate){
        this.id = id;
        this.name = name;
        this.date = date;
        this.pastDate = pastDate;
    }

}
