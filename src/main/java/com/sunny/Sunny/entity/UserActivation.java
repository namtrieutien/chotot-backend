package com.sunny.Sunny.entity;

import com.sunny.Sunny.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_activation")
@Entity
public class UserActivation extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name= "user_id")
    User user;

    public UserActivation(String token, LocalDateTime expiredAt, LocalDateTime confirmedAt, User user) {
        this.token = token;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.user = user;
    }
}
