package com.tonde.maisonchapback.domains;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_messages")
@Schema(
        name = "Message",
        description = "Message model",
        requiredMode = Schema.RequiredMode.REQUIRED,
        implementation = Message.class,
        example = """
                {
                  "id": "integer",
                  "receiver": "User",
                  "sender": "User",
                  "content": "string",
                  "subject": "string",
                  "status": "string",
                  "response": "string",
                  "dateResponse": "LocalDateTime",
                  "sentAt": "LocalDateTime"
                }""",
        requiredProperties = {"id", "receiver", "sender", "content", "subject", "status", "sentAt"}
)
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
    private String content;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String subject;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'unread'")
    private String status;

    @Column(columnDefinition = "TEXT", name = "response")
    private String response;

    @Column(columnDefinition = "TIMESTAMP", name = "date_response")
    private Instant dateResponse;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "sent_at")
    private Instant sentAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Instant getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(Instant dateResponse) {
        this.dateResponse = dateResponse;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }
}
