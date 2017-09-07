package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Course;
import com.demo.hibernate.entity.Instructor;
import com.demo.hibernate.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create the objects

			Instructor tempInstructor = new Instructor("susuan", "desouza", "sdesoza@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.sdesoza@gmail.com",
					"Play TableTennis");
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
			session.close();
			factory.close();
		}
	}

}
