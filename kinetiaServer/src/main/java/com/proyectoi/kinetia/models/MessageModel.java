package com.proyectoi.kinetia.models;

import com.proyectoi.kinetia.domain.Message;
import jakarta.persistence.*;

import java.time.Instant;

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
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserModel receiver;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant sentAt;

    @Column(nullable = false)
    private Boolean isRead = false;

    @Column(nullable = false)
    private Boolean senderHasDeleted  = false;

    @Column(nullable = false)
    private Boolean receiverHasDeleted = false;

    public MessageModel() {}

    public MessageModel(Message message){
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
    }

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

    public UserModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserModel recipient) {
        this.receiver = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean read) {
        isRead = read;
    }

    public Boolean getSenderHasDeleted() {
        return senderHasDeleted;
    }

    public void setSenderHasDeleted(Boolean senderHasDeleted) {
        this.senderHasDeleted = senderHasDeleted;
    }

    public Boolean getReceiverHasDeleted() {
        return receiverHasDeleted;
    }

    public void setReceiverHasDeleted(Boolean recipientHasDeleted) {
        this.receiverHasDeleted = recipientHasDeleted;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "id=" + id +
                ", sender=" + sender.fullName() +
                ", recipient=" + receiver.fullName() +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                ", isRead=" + isRead +
                '}';
    }
}
