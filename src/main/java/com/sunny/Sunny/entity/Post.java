package com.sunny.Sunny.entity;

import com.sunny.Sunny.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "size")
    private String size;

    @ManyToOne
    @JoinColumn(name = "categorize_id", referencedColumnName = "id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    public Post(String title, String description, Long price, String size, Category category, User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.size = size;
        this.category = category;
        this.user = user;
    }
}
