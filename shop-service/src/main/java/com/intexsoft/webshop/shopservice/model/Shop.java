package com.intexsoft.webshop.shopservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shop")
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false, unique = true, length = 100)
    String name;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;
    @Column(name = "phone_number", length = 50)
    String phoneNumber;
    @CreationTimestamp
    @Column(name = "registered_at", nullable = false)
    LocalDateTime registeredAt;
    @Column(name = "about")
    String about;
    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    Set<ShopProductLink> shopProductLinks = new LinkedHashSet<>();
    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    Set<PickupPoint> pickupPoints = new LinkedHashSet<>();

    @OneToMany(
            mappedBy = "shop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    Set<ShopOrderRequest> orderRequests = new LinkedHashSet<>();

    public void addPickupPoint(PickupPoint pickupPoint) {
        pickupPoints.add(pickupPoint);
        pickupPoint.setShop(this);
    }

    public void addOrderRequest(ShopOrderRequest shopOrderRequest) {
        orderRequests.add(shopOrderRequest);
        shopOrderRequest.setShop(this);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Shop shop = (Shop) object;
        return getId() != null && Objects.equals(getId(), shop.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}