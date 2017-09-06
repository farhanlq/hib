package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create a student object
			System.out.println("Creating new Student object...");
			Student student = new Student("ben", "stokes", "bstokes@yahoo.com");

			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(student);
			session.save(student);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Saved student. Generated id: " + student.getId());
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Getting student with id: " + student.getId());
			Student myStudent = session.get(Student.class, student.getId());
			System.out.println("Get Complete: " + myStudent);
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
