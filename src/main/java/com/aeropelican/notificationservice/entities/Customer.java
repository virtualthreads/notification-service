package com.aeropelican.notificationservice.entities;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.persistence.*;
@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_code", nullable = false, unique = true, length = 50)
    private String customerCode;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "email_enabled", nullable = false)
    private Boolean emailEnabled = true;

    @Column(name = "whatsapp_enabled", nullable = false)
    private Boolean whatsappEnabled = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}