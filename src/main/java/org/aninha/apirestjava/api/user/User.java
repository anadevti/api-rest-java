package org.aninha.apirestjava.api.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // o Jpa interpreta disso tudo pra baixo como sendo colunas no db.
    private UUID uuid;
    @NotBlank
    private String name;
    @Email
    private String email;
    @Past
    private LocalDate dob;

    public User(UUID uuid, String name, String email, LocalDate dob) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
}