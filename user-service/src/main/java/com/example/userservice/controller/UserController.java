package com.example.userservice.controller;

import com.example.userservice.VO.Department;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repo;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/adduseranddept")
    public ResponseEntity<ResponseTemplateVO> saveUserandDept(@RequestBody ResponseTemplateVO requestTemplateDTO) {
        User user = requestTemplateDTO.getUser();
        Department department = requestTemplateDTO.getDepartment();
        ResponseTemplateVO savedUser = userService.saveUserandDept(user, department);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateD(@PathVariable Long departmentId,@RequestBody Department department){
        return ResponseEntity.ok(userService.updateD(departmentId,department));
    }

    @GetMapping()
    public List<User> getall(){
        return repo.findAll();
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseTemplateVO> getUserWithDepartment(@PathVariable Long userId) {
        ResponseTemplateVO response = userService.getUser(userId);
        if (response.getUser() != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/{id}")
                public String deleteD(@PathVariable Long id){
        return userService.deleteD(id);
    }
    @PostMapping("/addsome")
    public ResponseEntity<Department>rew(@RequestBody Department department)
    {
        String a="http://localhost:9002/department/add";
        ResponseEntity<Department> dett=restTemplate.postForEntity(a,department,Department.class);
        return ResponseEntity.ok(dett.getBody());
    }
}
