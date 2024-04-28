package com.proyectoi.kinetia.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserModel sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserModel recipient;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime sentAt;

    @Column(nullable = false)
    private Boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserModel recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", sender=" + sender.fullName() +
                ", recipient=" + recipient.fullName() +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                ", isRead=" + isRead +
                '}';
    }
}
