package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Instructor;
import com.demo.hibernate.entity.InstructorDetail;
import com.demo.hibernate.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the objects

			/*Instructor tempInstructor = new Instructor("xyz", "abc", "xaby@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.xyz@gmail.com",
					"Programming Life");
			// associate the objects

			tempInstructor.setInstructorDetail(tempInstructorDetail);
*/
			Instructor tempInstructor = new Instructor("kaif", "khan", "kkhan@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.kkhan@gmail.com",
					"Play Table Tennis");
			// associate the objects

			tempInstructor.setInstructorDetail(tempInstructorDetail);

			
			// start a transaction
			session.beginTransaction();
			
			
			// save the instructor
			
			// it will also save the details object because of CascadeType.ALL

			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
