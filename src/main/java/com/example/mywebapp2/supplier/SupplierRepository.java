package com.example.mywebapp2.supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
    public Long countById(Integer id);
}
