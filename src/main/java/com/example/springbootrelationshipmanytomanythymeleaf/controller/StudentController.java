package com.example.springbootrelationshipmanytomanythymeleaf.controller;


import com.example.springbootrelationshipmanytomanythymeleaf.entity.Course;
import com.example.springbootrelationshipmanytomanythymeleaf.entity.Student;
import com.example.springbootrelationshipmanytomanythymeleaf.respository.CourseRepository;
import com.example.springbootrelationshipmanytomanythymeleaf.respository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(value = "/student")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getStudent(Model model){
        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("title","List Student");
        model.addAttribute("students",studentList);
        log.info("get students");
        return "index";
    }

    @RequestMapping(value = "/addStudent")
    public String  addStudent(Model model){
        model.addAttribute("title","Add Student");
        model.addAttribute("student",new Student());
        log.info("getAddStudent()");
        return "add_student";
    }

    @RequestMapping(value = "/save")
    public String saveStudent(Student student){
        studentRepository.save(student);
        log.info("save Student");
        return "redirect:/student";
    }

    @RequestMapping(value = "/get/{studentId}")
    public String getStudentId(@PathVariable("studentId") Long studentId, Model model){
        Student student = studentRepository.findByStudentId(studentId);
        log.debug("student"+student.toString());
        model.addAttribute("title","Student");
        model.addAttribute("student", student);
        model.addAttribute("courses", courseRepository.findAll());
        return "add_student_course";
    }

    @RequestMapping(value = "/addCourseStudent/{studentId}/course")
    public String addCourseStudent(@PathVariable("studentId") Long studentId, @RequestParam("courseId") Long courseId){
        Course course = courseRepository.findByCourseId(courseId);
        Student student = studentRepository.findByStudentId(studentId);
        if(student != null){
            student.getCourses().add(course);
            log.debug("TEST STUDENT-COURSE == "+student.getCourses().add(course));
            studentRepository.save(student);
        }
        return "redirect:/student";
    }
}
