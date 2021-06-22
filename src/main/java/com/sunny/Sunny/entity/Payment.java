package com.sunny.Sunny.entity;

import com.sun.istack.NotNull;
import com.sunny.Sunny.model.AuditModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_payment_info")
@NoArgsConstructor
public class Payment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "method")
    private String method;

    @Column(name = "intent")
    private String intent;

    @Column(name = "description")
    private String description;

    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "payerId")
    private String payerId;

    public Payment(Integer user_id,
                   Double totalPrice,
                   String currency,
                   String method,
                   String intent,
                   String description,
                   String paymentId,
                   String payerId,
                   Order order) {
        this.user_id = user_id;
        this.totalPrice = totalPrice;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
        this.paymentId = paymentId;
        this.payerId = payerId;
        this.order = order;
    }
}