package com.spring.orm;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.Dao.StudentDao;
import com.spring.orm.entity.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("student", StudentDao.class);
		Student student = new Student();
		boolean inApp = true;

		while (inApp) {
			System.out.println("========== Enter Your Choice From Following ==========");
			System.out.println("Enter 1 to Insert the Student ");
			System.out.println("Enter 2 to Update the Student ");
			System.out.println("Enter 3 to Get the single Student Record");
			System.out.println("Enter 4 to Get the All Student Records ");
			System.out.println("Enter 5 to Delete  the Student ");
			System.out.println("Enter 6 to Exit ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter Student Id :");
				int id = scanner.nextInt();
				System.out.println("Enter Student Name :");
				String name = scanner.next();
				System.out.println("Enter Student City :");
				String City = scanner.next();
				student.setId(id);
				student.setName(name);
				student.setCity(City);
				int result = studentDao.insert(student);
				System.out.println("========== " + result + " id inserted Successfully ==========");
				break;
			case 2:
				System.out.println("Enter Student Id Which you Want to Update:");
				id = scanner.nextInt();
				System.out.println("Enter Student Name :");
				name = scanner.next();
				System.out.println("Enter Student City :");
				City = scanner.next();
				student.setId(id);
				student.setName(name);
				student.setCity(City);
				studentDao.update(student);
				break;
			case 3:
				System.out.println("Enter the Student Id to get the Student");
				id = scanner.nextInt();
				Student student2 = studentDao.getStudent(id);
				System.out.println(
						"Id : " + student2.getId() + " Name " + student2.getName() + " City : " + student2.getCity());
				break;
			case 4:
				System.out.println("========== Data Of All Students ==========");
				List<Student> students = studentDao.getStudents();
				for (Student stud : students) {
					System.out
							.println("Id : " + stud.getId() + " Name " + stud.getName() + " City : " + stud.getCity());
				}
				break;
			case 5:
				System.out.println("Enter student id to Delete the Student :");
				id = scanner.nextInt();
				studentDao.delete(id);
				break;
			case 6:
				System.out.println("========== Exiting the App ==========");
				inApp = false;
				break;
			default:
				System.out.println("========== Wrong Input ==========");
			}

		}

	}

}
