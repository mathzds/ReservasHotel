package com.springboot.restJPA.controller;

import com.springboot.restJPA.models.User;
import com.springboot.restJPA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping()
    public Iterable<User> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found!"));
    }

    @PostMapping()
    public String add(@RequestBody User user) {
        repository.save(user);
        return "success";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") String id, @RequestBody User user) {
        if (!repository.existsById(id)) {
            return "not found!";
        }
        user.setId(id);
        repository.save(user);
        return "updated successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return "not found!";
        }
        repository.deleteById(id);
        return "deleted successfully";
    }
}
