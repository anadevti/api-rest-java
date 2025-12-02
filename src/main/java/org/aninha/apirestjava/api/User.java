package org.aninha.apirestjava.api;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private UUID uuid;
    @NotBlank
    private String name;
    @Email
    private String email;
    @Past
    private LocalDate dob;
}