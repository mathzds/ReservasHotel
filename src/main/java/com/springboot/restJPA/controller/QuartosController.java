package com.springboot.restJPA.controller;

import com.springboot.restJPA.models.Quarto;
import com.springboot.restJPA.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartosController {

    @Autowired
    private QuartoRepository repository;

    @PostMapping()
    public String create(@RequestBody Quarto quarto) {
        if (repository.findByNumero(quarto.getNumero()) != null) {
            return "there already a room with number";
        }

        repository.save(quarto);
        return "success ";
    }

    @GetMapping("/{id}")
    public Quarto get(@PathVariable("id") String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @GetMapping()
    public List<Quarto> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "Deleted";
    }
}