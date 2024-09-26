package com.ReadCorner.Library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description", columnDefinition = "TEXT")
    @Lob
    private String description;

    @Column(name = "total_copies")
    private int totalCopies;

    @Column(name = "stock")
    private int stock;

    @Column(name = "category")
    private String category;


    @Column(name = "price")
    private Double price;


    @Column(name = "book_cover")
    private String bookCover; // This will store the URL of the image

    @Column(name = "image_name")
    private String imageName; // This will store the name of the image

    @Column(name = "image_path")
    private String imagePath; // This will store the path of the image

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Serialize Feedbacks in Book
    private List<Feedback> feedbacks;

    @Transient
    public double getRate() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return 0.0;
        }
        return feedbacks.stream()
                .mapToDouble(Feedback::getRate)
                .average()
                .orElse(0.0);
    }
}


//
//@Entity
//@Table(name = "book")
//@SuperBuilder
//@EntityListeners(AuditingEntityListener.class) //enable automatic auditing of entity changes (createdDate, lastModifiedDate, CreatedBy , ...)
//@Data
//@AllArgsConstructor  // make no args constructor
//@NoArgsConstructor  // make  args constructor
//public class Book extends BaseEntity {
//
//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "author")
//    private String author;
//
//    @Lob
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "total_copies")
//    private int totalCopies;
//
//    @Column(name = "stock")
//    private int stock;
//
//    @Column(name = "category")
//    private String category;
//
//    @Column(name = "book_cover")
//    private String bookCover;
//
//    @Column(name = "price")
//    private Double price;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference // Serialize feedbacks in Book
//    private List<Feedback> feedbacks;
//
//    @Transient
//    public double getRate() {
//        if (feedbacks == null || feedbacks.isEmpty()) {
//            return 0.0;
//        }
//        var rate = this.feedbacks.stream()
//                .mapToDouble(Feedback::getRate)
//                .average()
//                .orElse(0.0);
//        double roundedRate = Math.round(rate * 10.0) / 10.0;
//
//        return roundedRate;
//    }
//
//}
