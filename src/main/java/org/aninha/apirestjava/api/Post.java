package org.aninha.apirestjava.api;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Post {

    private UUID uuid;
    private String title;
    private String body;
    private LocalDateTime createdDate;
    private String author;

}
