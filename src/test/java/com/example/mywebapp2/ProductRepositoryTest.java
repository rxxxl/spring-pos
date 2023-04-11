package com.example.mywebapp2;

import com.example.mywebapp2.product.Product;
import com.example.mywebapp2.product.ProductRepository;
import com.example.mywebapp2.product.ProductService;
import com.example.mywebapp2.supplier.Supplier;
import com.example.mywebapp2.supplier.SupplierRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
    @Autowired private ProductRepository repo;
    @Autowired private SupplierRepository repoSupp;

    @Test
    public void testAddNew(){

        Product product = new Product();
        product.setName("Moritas");
        product.setBuyPrice(12.5);
        product.setSellPrice(15.0);
        product.setStock(5);
        Supplier supplier = repoSupp.findById(1).orElse(null);
        product.setSupplier(supplier);

        Product savedProduct = repo.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllProducts(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for (Product product:
             products) {
            System.out.println(product);
        }
    }

    @Test
    public void testUpdateProduct(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();

        Supplier supplier = repoSupp.findById(1).orElse(null);
        product.setSupplier(supplier);

        repo.save(product);

        Product updateProduct = repo.findById(productId).get();
        Assertions.assertThat(updateProduct.getSupplier()).isEqualTo(supplier);
    }
}
