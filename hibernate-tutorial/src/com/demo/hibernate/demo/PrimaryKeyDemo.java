package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create a student object
			System.out.println("Creating a Student object...");
			Student student = new Student("ab", "devillers", "abdevillers@gmail.com");

			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving a student...");

			session.save(student);

			// commit the transaction

			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			factory.close();
		}

	}

}
