package com.example.departmentservice.controller;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository repo;

    @PostMapping("/add")
    public Department adddepartment(@RequestBody Department department){
        log.info("Inside saveDepartment method of DepartmentController");
        return departmentService.saveDepartment(department);
    }
    @GetMapping
    public List<Department> getAll(){
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Department> findByDepartmentId(@PathVariable("id") Long departmentId){
        log.info("Inside findByDepartmentId method of DepartmentController");
        return departmentService.findByDepartmentId(departmentId);
    }
    @PutMapping("/update/{departmentId}")
        public Department updatedpt(@PathVariable Long departmentId,@RequestBody Department department){
            return departmentService.updatedpt(departmentId,department);

        }
        @DeleteMapping("/{id}")
    public String deleteD(@PathVariable Long id){
        repo.deleteById(id);
        return "charging port illa";
        }

}
