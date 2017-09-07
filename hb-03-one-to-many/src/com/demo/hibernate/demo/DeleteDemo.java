package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.entity.Instructor;
import com.demo.hibernate.entity.InstructorDetail;
import com.demo.hibernate.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get instructor by primary key / id
			int theId=1;
			Instructor tempInstructor =
					session.get(Instructor.class,theId);
			
			System.out.println("Found instructor: "+tempInstructor);
			// delete the instructors
			
			if(tempInstructor != null){
				System.out.println("Deleting: "+ tempInstructor);
				
				// It will also delete details of the instructor because of CascadeType.ALL
				session.delete(tempInstructor);
			}
			
			// commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			factory.close();
		}
	}

}
