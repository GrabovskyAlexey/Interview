package ru.grabovsky.interview.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.grabovsky.interview.hibernate.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
