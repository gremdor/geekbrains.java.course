package com.geekbrains.hibernate.h2;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "product_id")
//    private Long productId;
//
//    @Column(name = "client_id")
//    private Long clientId;

    @Column(name = "product_count")
    private int cnt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created=new Date();

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cnt=" + cnt +
                ", created=" + created +
                ", price=" + price +
                ", product_id=" + product.getId() +
                ", client_id=" + client.getId() +
                '}' + '\n';
    }
}
