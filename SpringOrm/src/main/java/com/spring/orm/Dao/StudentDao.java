package com.spring.orm.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entity.Student;

public class StudentDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public int insert(Student student) {
		Student stud = hibernateTemplate.get(Student.class, student.getId());
		if (stud != null) {
			System.out.println("==========Duplicate ID Unable to insert Record ==========");
			return 0;
		} else {
			Integer i = (Integer) this.hibernateTemplate.save(student);
			return i;
		}
	}

	public Student getStudent(int id) {
		Student student = this.hibernateTemplate.get(Student.class, id);
		return student;
	}

	public List<Student> getStudents() {
		List<Student> students = this.hibernateTemplate.loadAll(Student.class);
		return students;
	}

	@Transactional
	public void delete(int id) {
		Student student = this.hibernateTemplate.get(Student.class, id);
		if (student != null) {
			this.hibernateTemplate.delete(student);
			System.out.println("========== student deleted of id " + student.getId() + "==========");
		} else {
			System.out.println("========== No Record with " + student.getId() + " Available ==========");
		}
	}

	@Transactional
	public void update(Student student) {
	try {
		this.hibernateTemplate.update(student);
		System.out.println("========== Data Updated Successfully ==========");		
	} catch (Exception e) {
		System.out.println("========== No Data Available with that id ==========s");
	}

	}

}
