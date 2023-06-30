package com.example.userservice.service;

import com.example.userservice.VO.Department;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUser(Long userId){
        ResponseTemplateVO vo=new ResponseTemplateVO();
        User user=userRepository.findByUserId(userId);
        Department department=restTemplate.getForObject("http://localhost:9002/department/"+user.getDepartmentId(),Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }


    public ResponseTemplateVO saveUserandDept(User user, Department department) {
        User user1=userRepository.save(user);
        Department department1= restTemplate.postForEntity("http://localhost:9002/department/add",department,Department.class).getBody();
        ResponseTemplateVO vo=new ResponseTemplateVO();
        vo.setDepartment(department1);
        vo.setUser(user1);
        return vo;
    }

    public Department updateD(Long departmentId, Department department) {
        restTemplate.put("http://localhost:9002/department/update/{id}",department,departmentId,Department.class);
        department.setDepartmentId(departmentId);



        return department;
    }

    public String deleteD(Long id) {
        restTemplate.delete("http://localhost:9002/department/{id}",id,Department.class);
        return "neyetha";
    }
}
