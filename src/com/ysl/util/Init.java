package com.ysl.util;

import java.util.ArrayList;

import com.ysl.entity.Answer;
import com.ysl.entity.Homework;
import com.ysl.entity.Manage;
import com.ysl.entity.Student;
import com.ysl.entity.Teacher;

public class Init {
	public ArrayList<Manage> manageList = new ArrayList<Manage>(); 
	public ArrayList<Student> stuList = new ArrayList<Student>();
	public ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
	public ArrayList<Homework> homeworkList = new ArrayList<Homework>();
	public ArrayList<Answer> answerList = new ArrayList<Answer>();
	public Init() {
		manageInit();
		StuInit();
		TeacherInit();
		HomeworkInit();
	}
	/**
	 * 管理员初始化
	 */
	public void manageInit() {
		manageList.add(new Manage("HY","123","000"));
	}
	/**
	 * 学生初始化
	 */
	public void StuInit() {		
		Student stu1=new Student("stu","001","001");
		Student stu2=new Student("YSL", "002", "002");
		stuList.add(stu1);
		stuList.add(stu2);
	}
	/**
	 * 教师初始化
	 */
	public void TeacherInit() {
		Teacher t1=new Teacher("LB", "100", "100", "数据结构");
		Teacher t2=new Teacher("WYL", "200", "200", "C语言");
		teacherList.add(t1);
		teacherList.add(t2);
	}
	/**
	 * 作业+答案初始化
	 */
	public void HomeworkInit() {
		Homework homework1 = new Homework(1,"c语言", "按时完成", "10月7日", "WYL");
		Homework homework2 = new Homework(2,"数据结构", "按时完成", "12月26日", "LB");
		Answer answer1 = new Answer(1);
		Answer answer2 = new Answer(2);
		homeworkList.add(homework1);
		homeworkList.add(homework2);
		answerList.add(answer1);
		answerList.add(answer2);
	}
}
