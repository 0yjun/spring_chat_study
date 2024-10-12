package com.chatflatform.domain.auth.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long t_id;

    @Column(nullable = false)
    private String name;

    private Timestamp created_at;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private UserCredentials userCredentials;

    public void setCredentials(UserCredentials credentials){
        this.userCredentials = credentials;
    }
}
