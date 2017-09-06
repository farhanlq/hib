package com.demo.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create a student object
			System.out.println("Creating new Student object...");
			Student student = new Student("ab", "devillers", "abdevillers@gmail.com");

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").list();

			// diplay the students
			displayStudents(theStudents);

			// query students: lastName='doe'
			theStudents = session.createQuery("from Student s where s.lastname='Doe'").getResultList();

			// display the students
			System.out.println("Students who have last name of Doe");
			displayStudents(theStudents);

			// query students: lastName='Doe' OR firstName='Virat'
			theStudents = session.createQuery("from Student s where " + " s.lastname='Doe' OR s.firstName='Virat'")
					.getResultList();

			System.out.println("Students who have last name of Doe OR first name of Virat");
			displayStudents(theStudents);

			// query student where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where s.email" + " LIKE '%gmail.com'").list();
			System.out.println("Students who's email ends with gmail.com");
			displayStudents(theStudents);

			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");

		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
