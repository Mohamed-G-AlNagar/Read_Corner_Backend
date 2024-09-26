package com.ReadCorner.Library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "cart_items")
@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity {

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonBackReference // Prevent recursion
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonBackReference // Prevent recursion
    private Book book;

    public Double getBookPrice() {
        if (book == null) {
            return 0.0;
        }
        return book.getPrice();
    }
}


//
//@Entity
//@Data
//@SuperBuilder
//@EntityListeners(AuditingEntityListener.class) // to let the sys auto update (CreatedBy,....)
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "cart_items")
//public class CartItem extends BaseEntity {
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cart_id")
//    @JsonManagedReference // Serialize cart in CartItem
//    @JsonIgnore
//    private Cart cart;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id")
//    @JsonIgnore
//    private Book book;
//
////    public Double getBookPrice() {
////        return book != null ? (book.getPrice()*this.quantity) : 0.0;
////    }
//public Double getBookPrice() {
//    if (book == null) {
//        return 0.0;
//    }
//    double price = book.getPrice();
//    double total = price * this.quantity;
//    System.out.println("-------------Book Price: " + price);
//    System.out.println("----------Quantity: " + this.quantity);
//    System.out.println("--------Total Price for Item: " + total);
//    return total;
//}
//}
