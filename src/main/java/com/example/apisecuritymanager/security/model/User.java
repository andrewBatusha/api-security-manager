package com.example.apisecuritymanager.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="users")
public class User {

    @Id
    private ObjectId id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;
}
