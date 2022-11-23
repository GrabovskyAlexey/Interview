package ru.grabovsky.interview.hibernate.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.grabovsky.interview.hibernate.entity.Student;
import ru.grabovsky.interview.hibernate.services.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public Student getById(@PathVariable final Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final Long id){
        studentService.deleteStudentById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestBody final Student student){
        studentService.deleteStudent(student);
    }

    @PostMapping
    public void addStudent(@RequestBody final Student student){
        studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable final Long id, @RequestBody final Student student){
        studentService.saveStudent(student);
    }
}
