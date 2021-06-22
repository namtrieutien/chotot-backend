package com.sunny.Sunny.entity;

import com.sun.istack.NotNull;
import com.sunny.Sunny.model.AuditModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.expression.spel.ast.LongLiteral;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull
    @Column(name = "buyer_id")
    private Integer buyer_id;

    @Column(name = "total_price")
    private Double totalPrice;

}