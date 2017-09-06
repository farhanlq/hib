package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Student;

public class DeleteStudentDemo {

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

			/*// delete the student
			System.out.println("Deleting student: "+myStudent);
			session.delete(myStudent);
			*/
			// delete student id=2
			System.out.println("Delete student id=2");

			session.createQuery("delete from Student where id=2").executeUpdate();

			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
