/**
 * 
 */
package com.steve.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 * @author damoklesh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SessionFactory sessionFactory;
		Student_Info student = new Student_Info();
		Student_Info student2 = new Student_Info();
		Student_Info student3 = new Student_Info();
		
		student.setName("Steve");
		student2.setName("lucia");
		student3.setName("manu");
		//student.setRollNo(4);
		//student.setRollNo(4);
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		
		} catch (Throwable ex) {
			
			System.err.println("Initial sessionfactory failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		//open connection
		Session session = sessionFactory.openSession();
		//open transaction
		session.beginTransaction();
		
		//save student
		session.save(student);
		session.save(student2);
		session.save(student3);
		
		//commit, close
		session.getTransaction().commit();
		
		
		//read all students
		List students = session.createQuery("FROM Student_Info").list();
		Iterator iter = students.iterator();
		while (iter.hasNext()){
			Student_Info std = (Student_Info)iter.next();
			System.out.println("Student no."+std.getRollNo() + "name: "+std.getName());
		}
		
		session.close();
		sessionFactory.close();
		
	}

}
