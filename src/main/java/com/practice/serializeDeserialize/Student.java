package com.practice.serializeDeserialize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
//@NoArgsConstructor - if your fields aren't final
// otherwise use @JsonCreater to tell which constructor to call during deserialization
// and which JSON property maps to which parameter using @JsonProperty
// or simply use records
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID =1L;
    private  final int id;
    private  final String name;





    @JsonCreator
    public Student(@JsonProperty("id") int id,
                   @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }


    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
