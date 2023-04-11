package com.example.mywebapp2.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repoSupp;

    public List<Supplier> listAll(){
        return (List<Supplier>) repoSupp.findAll();
    }


    public void save(Supplier supplier){
        repoSupp.save(supplier);
    }

    public Supplier get(Integer id) throws SupplierNotFoundException {
        Optional<Supplier> result = repoSupp.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new SupplierNotFoundException("Could not find any suppliers with ID -> " +id);
    }

    public void delete(Integer id) throws SupplierNotFoundException {
        Long count = repoSupp.countById(id);
        if (count == null || count == 0){
            throw new SupplierNotFoundException("Could not find any suppliers with ID -> " +id);
        }
        repoSupp.deleteById(id);
    }

}
