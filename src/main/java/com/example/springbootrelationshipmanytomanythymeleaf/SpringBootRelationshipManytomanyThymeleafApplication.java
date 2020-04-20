package com.example.springbootrelationshipmanytomanythymeleaf;

import com.example.springbootrelationshipmanytomanythymeleaf.entity.Address;
import com.example.springbootrelationshipmanytomanythymeleaf.entity.Student;
import com.example.springbootrelationshipmanytomanythymeleaf.entity.Teacher;
import com.example.springbootrelationshipmanytomanythymeleaf.respository.AddressRepository;
import com.example.springbootrelationshipmanytomanythymeleaf.respository.StudentRepository;
import com.example.springbootrelationshipmanytomanythymeleaf.respository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootRelationshipManytomanyThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRelationshipManytomanyThymeleafApplication.class, args);
    }

    @Component
    class CommandLineRunnerData implements CommandLineRunner {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private TeacherRepository teacherReposistory;

        @Autowired
        private AddressRepository addressRepository;

        @Override
        public void run(String... args) throws Exception {

            try{

                List<Student> students = new ArrayList<>();
                students = studentRepository.searchByFirstName("dicka");

                if (students.size() > 0){
                    System.out.println("found : "+students.size());
                }else{
                    System.out.println("notfound.");
                }

            }catch (NullPointerException e){
                e.printStackTrace();
            }

            try{

                Address address = new Address();
                Teacher teacher = new Teacher();

                address.setNo("address no 1");
                address.setProvince("address province 1");
                address.setZipcode("12430");
                address.setStreet("address street 1");
                address.setVillage("address vilage 1");


                teacher.setName("teacher name 1");
                teacher.setDob(new Date());
                teacher.setAddress(address);

                addressRepository.save(address);
                teacherReposistory.save(teacher);


            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

}
