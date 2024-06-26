package com.workintech.s17d2.rest;

import jakarta.annotation.PostConstruct;
import model.Developer;
import model.JuniorDeveloper;
import model.MidDeveloper;
import model.SeniorDeveloper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tax.Taxable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    public Map<Integer, Developer> developers = new HashMap<>();
    private final Taxable tax;

    @Autowired
    public DeveloperController(Taxable tax) {
        this.tax = tax;
    }

    @PostConstruct
    public void init() {
        developers.put(1, new JuniorDeveloper(1, "Taylan", 40000));
        developers.put(2, new MidDeveloper(2, "Can", 60000));
        developers.put(3, new SeniorDeveloper(3, "Kai", 80000));
    }

    @GetMapping("/all")
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id) {
        return developers.get(id);
    }

    @PostMapping("/add")
    public Developer addDeveloper(@RequestBody Developer developer) {
        if (developer instanceof JuniorDeveloper) {
            developer.setSalary(developer.getSalary() - tax.getSimpleTaxRate());
        } else if (developer instanceof MidDeveloper) {
            developer.setSalary(developer.getSalary() - tax.getMiddleTaxRate());
        } else if (developer instanceof SeniorDeveloper) {
            developer.setSalary(developer.getSalary() - tax.getUpperTaxRate());
        }
        developers.put(developer.getId(), developer);
        return developer;
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        developers.put(id, developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable int id) {
        developers.remove(id);
    }
}
