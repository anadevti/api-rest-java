package org.aninha.apirestjava.api;
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
    private String name;
    private String email;
    private LocalDate dob;
}