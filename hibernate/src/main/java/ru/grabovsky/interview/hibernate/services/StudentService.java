package ru.grabovsky.interview.hibernate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.interview.hibernate.entity.Student;
import ru.grabovsky.interview.hibernate.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
}
