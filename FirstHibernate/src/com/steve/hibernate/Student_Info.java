/**
 * 
 */
package com.steve.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author damoklesh
 *
 */
@Entity
@Table (name="STUDENT_INFORMATION")
public class Student_Info {

	
	private int rollNo;
	
	private String name;
	
	@Id
	@GeneratedValue
	public synchronized int getRollNo() {
		return rollNo;
	}
	public synchronized void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
}
