package org.example.learning_spring_boot.student.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.learning_spring_boot.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "studentRepo")
//@Component(value = "studentRepo")
//@Scope("singleton")
//@Scope("prototype")
@Primary
public class StudentRepo implements IStudentRepo {

    @Autowired  //Field Injection
    private EntityManager entityManager;

    //Constructor Injection
    public StudentRepo(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Setter Injection
    public StudentRepo setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        return this;
    }

    @Override
    public List<Student> getStudents() {
        final Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM Students", Student.class);
        final List<Student> resultList = (List<Student>) nativeQuery.getResultList();
        return resultList;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void deleteStudent(int id) {
           final EntityTransaction transaction = entityManager.getTransaction();
           try{
               transaction.begin();
               final Student student = entityManager.find(Student.class, id);
               entityManager.remove(student);
               transaction.commit();
           }catch(Exception e){
               transaction.rollback();
           }
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }
}
