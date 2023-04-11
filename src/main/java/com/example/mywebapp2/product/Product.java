package com.example.mywebapp2.product;

import com.example.mywebapp2.supplier.Supplier;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 4, name = "buy_price")
    private double buyPrice;

    @Column(nullable = false, length = 4, name = "sell_price")
    private double sellPrice;

    @Column( nullable = false,length = 4, name  = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {

        this.supplier = supplier;
    }
  //////

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", stock=" + stock +
                ", supplier=" + supplier +
                '}';
    }
}
