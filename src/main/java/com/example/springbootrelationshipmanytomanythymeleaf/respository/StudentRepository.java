package com.example.springbootrelationshipmanytomanythymeleaf.respository;

import com.example.springbootrelationshipmanytomanythymeleaf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByStudentId(Long studentId);

    @Query("select s from Student s where s.firstname = :firstName")
    List<Student> searchByFirstName(@Param(value = "firstName")String firstName);
}
