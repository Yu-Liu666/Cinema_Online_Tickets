package com.group99.javabean;
/**
 * The entity class of student.
 * @author group 99
 *
 */
public class Student {

	private String name;
	private String studentNum;
	
	public Student() {
	}
	/**
	 * This is the constructor of Student.
	 * @param name The student name.
	 * @param studentNum The student number.
	 */
	public Student(String name, String studentNum) {
		this.name = name;
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", studentNum=" + studentNum + "]";
	}
	
	
}
