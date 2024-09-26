package com.ReadCorner.Library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference // Prevent recursion
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Serialize OrderItems in Order
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(name = "cart_Id")
    private Integer cartId;

    @Transient
    public double getTotalPrice() {
        if (orderItems == null || orderItems.isEmpty()) {
            return 0.0;
        }
        return orderItems.stream()
                .mapToDouble(OrderItem::getPrice)
                .average()
                .orElse(0.0);
    }
}


//@Entity
//@Data
//@SuperBuilder
//@EntityListeners(AuditingEntityListener.class) // to let the sys auto update (CreatedBy,....)
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "`order`") // Important note , order is reserved in mysql so to call the table order should be escaped by backticks(`)
//public class Order extends BaseEntity {
//
//    @Column(name = "order_date", nullable = false)
//    private LocalDateTime orderDate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private OrderStatus status = OrderStatus.PENDING;
//
//    @Column(name = "total_amount")
//    private Double totalAmount;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private User user;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
//
//    @Column(name = "cart_Id")
//    private Integer cartId;
//
//    @Transient
//    public double getTotalPrice() {
//        if (orderItems == null || orderItems.isEmpty()) {
//            return 0.0;
//        }
//        double price = this.orderItems.stream()
//                .mapToDouble(OrderItem::getPrice)
//                .average()
//                .orElse(0.0);
//
//        return price;
//    }
//}
