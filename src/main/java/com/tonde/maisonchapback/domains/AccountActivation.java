package com.tonde.maisonchapback.domains;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "_accountKey")
public class AccountActivation  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "activation_key", nullable = false)
    private String key;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;


    @Column(name = "user_id", nullable = false)
    private Integer userId;



    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "expiration_at", nullable = false)
    private LocalDateTime expirationAt;


    public AccountActivation(String key, Integer userId) {
        this.key = key;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.expirationAt = LocalDateTime.now().plusMinutes(10);
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(expirationAt);
    }



}
