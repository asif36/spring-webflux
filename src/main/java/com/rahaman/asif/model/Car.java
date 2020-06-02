package com.rahaman.asif.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {

    private String id;
    private String modelName;
    private String category;

    @Override
    public String toString() {
        return "Car{id=" + id + ", modelName=" + modelName + ", category=" + category + '}';
    }
}