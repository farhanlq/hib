package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 3;

			session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("Getting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);

			System.out.println("Get Complete: " + myStudent);

			System.out.println("Updating student....");
			myStudent.setFirstName("James");

			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();

			// update email for all elements
			System.out.println("Update email for all students");

			session.createQuery("update Student set email ='xyz@live.in'").executeUpdate();

			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
