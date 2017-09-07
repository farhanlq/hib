package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Course;
import com.demo.hibernate.entity.Instructor;
import com.demo.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();

			// get the instructor from db 
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			
			// create some courses
			Course tempCourse1 = new Course("Data Structures and Algorithms ");
			Course tempCourse2 = new Course("Algorithm Analysis and Design ");
			
			// add courses to instructor
			tempInstructor.ad(tempCourse1);
			tempInstructor.ad(tempCourse2);
			
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			session.close();
			factory.close();
		}
	}

}
