//package com.ReadCorner.Library.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.SuperBuilder;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Entity
//@Table(name = "categories")
//@Data
//@SuperBuilder
//@EntityListeners(AuditingEntityListener.class) // to let the sys auto update (CreatedBy,....)
//@AllArgsConstructor
//@NoArgsConstructor
//public class Category extends BaseEntity {
//
//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
//
//    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
//    private List<Book> books;
//}
//
