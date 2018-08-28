package com.ysl.entity;

import java.util.ArrayList;

public class Manage extends Person implements Ilogin{
	public Manage(String name,String count,String password) {
		super(name, count, password);
	}
	
	public Manage() {
	}
	/**
	 * 管理员登陆
	 * @param user 用户名和密码的数组
	 * @param obj 用户的集合对象
	 * @return 1代表登陆成功
	 */
	public int login(String[] user, ArrayList<?> manager) {
		for (Object person : manager) {
			if(((Manage)person).getCount().equals(user[0]) && ((Manage)person).getPassword().equals(user[1]))
				return 1;
		}
		return 0;
	}
	/**
	 * 管理员添加学生
	 * @param name 学生姓名
	 * @param count 学生学号
	 * @param password 学生密码
	 * @param stuList 学生集合对象
	 * @return 添加成功返回true
	 */
	public boolean add_student(String name,String count,String password,ArrayList<Student> stuList){
		for (Student student : stuList) {
			if(student.getCount().equals(count))
			{
				System.out.println("该学号已经存在！！");
				return false;
			}
		}
		if(stuList.add(new Student(name, count, password)))
			return true;
		else return false;
	}
	/**
	 * 管理员添加老师
	 * @param name 老师姓名
	 * @param count 老师编号
	 * @param password 老师密码
	 * @param subject 老师所教科目
	 * @param teacherList 老师集合对象
	 * @return 添加成功返回true
	 */
	public boolean add_teacher(String name,String count,String password,String subject,
			ArrayList<Teacher> teacherList) {
		for (Teacher teacher : teacherList) {
			if (teacher.getCount().equals(count)) {
				System.out.println("该教职工编号已经存在！！");
				return false;
			}
		}
		if(teacherList.add(new Teacher(name, count, password,subject)))
			return true;
		else return false;
	}

}
