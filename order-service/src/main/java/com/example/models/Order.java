package com.example.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity

@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "resturantId")
    private String resturantId;
    @Column(name = "customerId")
    private String customerId;

    @Column(name="totalPrice")
    private Long totalPrice;
    @Column(name = "trackId")
    private String trackId;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Order(Long id, String resturantId, String customerId, String trackId) {
        this.id = id;
        this.resturantId = resturantId;
        this.customerId = customerId;
        this.trackId = trackId;
    }
}
