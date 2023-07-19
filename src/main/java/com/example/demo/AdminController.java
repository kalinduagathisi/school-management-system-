package com.example.demo;

import com.example.service.StudentService;
import com.example.user.Student;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final StudentService studentService;

    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    // return list fo employees when exposed to "/employees"
    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }


    // add new employee : POST request
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){   // employee data is coming through the request body in json format-) that why @RequestBody

        // if id!= 0, then this will be treated as an update.
        // to avoid this,initially set the employee id to zero.
        // then it'll force to save a new item instead of update
        student.setStudentId(0);
        return studentService.save(student);
    }












//    @GetMapping
//    @PreAuthorize("hasAuthority('admin:read')")
//    public String get() {
//        return "GET:: admin controller";
//    }
//    @PostMapping
//    @PreAuthorize("hasAuthority('admin:create')")
//    @Hidden
//    public String post() {
//        return "POST:: admin controller";
//    }
//    @PutMapping
//    @PreAuthorize("hasAuthority('admin:update')")
//    @Hidden
//    public String put() {
//        return "PUT:: admin controller";
//    }
//    @DeleteMapping
//    @PreAuthorize("hasAuthority('admin:delete')")
//    @Hidden
//    public String delete() {
//        return "DELETE:: admin controller";
//    }
}
