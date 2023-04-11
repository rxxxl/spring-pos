package com.example.mywebapp2.product;

import com.example.mywebapp2.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductRepository repo;

    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }

    public void save(Product product){
        repo.save(product);
    }

    public Product get(Integer id) throws ProductNotFoundException{
        Optional<Product> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any products with id ->" +id);
    }

    public void delete(Integer id) throws ProductNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new ProductNotFoundException ("Could not find any products with id ->" +id);
        }
        repo.deleteById(id);
    }
}
