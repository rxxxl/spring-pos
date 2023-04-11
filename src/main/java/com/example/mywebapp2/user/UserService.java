package com.example.mywebapp2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRespository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> resul = repo.findById(id);
        if(resul.isPresent()){
            return resul.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " +id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with ID " +id);
        }
        repo.deleteById(id);
    }
}
