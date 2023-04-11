package com.example.mywebapp2;

import com.example.mywebapp2.product.Product;
import com.example.mywebapp2.supplier.Supplier;
import com.example.mywebapp2.supplier.SupplierRepository;
import com.example.mywebapp2.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.swing.text.html.Option;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.OptionalInt;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SupplierRepositoryTest {
    @Autowired private SupplierRepository repo;

    @Test
    public void testAddNew(){
        Supplier sup = new Supplier();
        sup.setEmail("rikolino@rikolino.com");
        sup.setName("Ricolino");
        sup.setPhone("712159847");
        sup.setStatus(true);
        sup.setAddress("Calle 9, atlacomulco, 50450");

        Supplier savedSuplier = repo.save(sup);

        Assertions.assertThat(savedSuplier).isNotNull();
        Assertions.assertThat(savedSuplier.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Supplier> suppliers = repo.findAll();
        Assertions.assertThat(suppliers).hasSizeGreaterThan(0);

        for (Supplier supplier : suppliers){
            System.out.println(supplier);
        }
    }

    @Test
    public void testUpdate(){
        Integer suppId = 2;
        Optional<Supplier> optionalSupplier = repo.findById(suppId);
        Supplier supplier = optionalSupplier.get();
        supplier.setPhone("7122106177");
        repo.save(supplier);

        Supplier updateSupplier = repo.findById(suppId).get();
        Assertions.assertThat(updateSupplier.getPhone()).isEqualTo("7122106177");
    }

    @Test
    public void testGet(){
        Integer suppId = 1;
        Optional<Supplier> optionalSupplier = repo.findById(suppId);
        Supplier supplier = optionalSupplier.get();

        Assertions.assertThat(optionalSupplier).isPresent();
        System.out.println(optionalSupplier.get());
    }

    @Test
    public void testDelete(){
        Integer suppId = 2;
        repo.deleteById(suppId);

        Optional<Supplier> optionalSupplier = repo.findById(suppId);
        Assertions.assertThat(optionalSupplier).isNotPresent();
    }
}
