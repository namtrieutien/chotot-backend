package com.sunny.Sunny.entity;

import com.sun.istack.NotNull;
import com.sunny.Sunny.model.AuditModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private int auth;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        super();
    }

    public User(String name, String email, String phone, String password, int auth, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.auth = auth;
        this.address = address;
    }
}
