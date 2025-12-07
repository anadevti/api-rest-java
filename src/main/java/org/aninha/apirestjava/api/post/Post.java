package org.aninha.apirestjava.api.post;

import jakarta.persistence.*;
import lombok.*;
import org.aninha.apirestjava.api.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private UUID uuid;
    private String title;
    private String body;
    @Column(name = "createddate")
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

}
