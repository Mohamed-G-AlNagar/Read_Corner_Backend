package com.ReadCorner.Library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cart")
@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference // Prevent recursion
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Serialize CartItems in Cart
    private List<CartItem> cartItems = new ArrayList<>();

    @Transient
    public double getTotalPrice() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0.0;
        }
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getBookPrice() * cartItem.getQuantity())
                .sum();
    }

    public CartItem getCartItem(Integer bookId) {
        return cartItems.stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
}


//
//@Entity
//@Table(name = "cart")
//@Data
//@SuperBuilder
//@EntityListeners(AuditingEntityListener.class) // to let the sys auto update (CreatedBy,....)
//@AllArgsConstructor
//public class Cart extends BaseEntity{
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonManagedReference // The "parent" side of the relationship
//    private User user;
//
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference // The "parent" side of the relationship
//    private List<CartItem> cartItems = new ArrayList<>();
//
//    public Cart() {
//        this.cartItems = new ArrayList<>();
//    }
//
//    @Transient
//    public double getTotalPrice() {
//        if (cartItems == null || cartItems.isEmpty()) {
//            return 0.0;
//        }
//                double sum = cartItems.stream()
//                .mapToDouble(CartItem::getBookPrice)
//                .sum();
//        System.out.println("-------************ total price = " + sum);
//        return sum;
//    }
//}
