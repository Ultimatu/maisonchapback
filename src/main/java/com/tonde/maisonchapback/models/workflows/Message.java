package com.tonde.maisonchapback.models.workflows;


import com.tonde.maisonchapback.models.workflows.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_messages")

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Message"))
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_ID_Message_Sender"))
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String subject;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'unread'")
    private String status;

    @Column(columnDefinition = "TEXT", name = "response")
    private String response;

    @Column(columnDefinition = "TIMESTAMP", name = "date_response")
    private LocalDateTime dateResponse;

    @Column(nullable = false , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "sent_at")
    private LocalDateTime sentAt;

}
