package com.ysl.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.ysl.entity.Manage;
import com.ysl.entity.Person;
import com.ysl.entity.Student;
import com.ysl.entity.Teacher;
import com.ysl.util.Init;

public class Ui 
{
	static
	{
		//数据初始化
		init = new Init();
	}
	static Init init;
	static Scanner input = new Scanner(System.in);
	/**
	 * 系统欢迎界面(系统主界面)
	 */
	public static void welcome() 
	{
		System.out.println("");
		System.out.print("\t\t\t欢迎使用学生作业管理系统\n\n");
		System.out.println("*****************************************************************************\n");
		System.out.println("\t\t\t1.学生\n");
		System.out.println("\t\t\t2.管理员\n");
		System.out.println("\t\t\t3.教师\n");
		System.out.println("\t\t\t4.退出\n");
		System.out.println("*****************************************************************************\n");
		System.out.print("请选择：");
	}
	/**
	 * 登陆页面
	 * @param input 输入
	 * @return 返回账号、密码
	 */
	public static String[] loginFrame() 
	{
		String[] result=new String[2];
		System.out.print("请输入账号：");
		result[0]=input.next();
		System.out.print("请输入密码：");
		result[1]=input.next();
		return result;		
	}
	/**
	 * 修改密码界面
	 * @param obj 用户集合对象
	 */
	public static void changPwdFrame(ArrayList<?> obj)
	{
		System.out.print("请输入你的账号：");
		String count = input.next();
		Person person = new Person();
		if(person.isExist(count, obj))
		{
			System.out.print("请输入新的密码：");
			if(person.changePwd(count,input.next(),obj))
				System.out.println("修改成功！");
			else  System.err.println("修改失败！\n");
		}else System.err.println("用户不存在！");
	}
	/**
	 * 管理员添加学生页面
	 * @param teachers  学生集合对象
	 */
	public static void add_StuFrame(ArrayList<Student> students) {
		Manage manage = new Manage();
		System.out.print("请输入你要添加的学生个数：");
		int count = input.nextInt();
		int i;
		for (i = 1; i <= count; i++) {
			System.out.print("请输入第"+i+"个学生的姓名：");
			String name = input.next();
			System.out.print("请输入第"+i+"个学生的学号：");
			String sno = input.next();
			System.out.println("教务处密码默认与学号相同！！！");
			if(manage.add_student(name, sno, sno, students))
			{
				System.out.println("第"+i+"个学生添加成功！");
			}else
			{
				System.err.println("从第"+i+"个学生起添加失败！！");
				break;
			}
		}
		if(i!=count+1)
		{
			System.out.print("是否继续添加<Y/N>");
			if(input.next().equals("Y")) add_StuFrame(students);
		}
	}
	/**
	 * 管理员添加老师页面
	 * @param teachers  老师集合对象
	 */
	public static void add_TeacherFrame(ArrayList<Teacher> teachers) {
		Manage manage = new Manage();
		System.out.print("请输入你要添加的教师个数：");
		int count = input.nextInt();
		int i;
		for (i = 1; i <= count; i++) {
			System.out.print("请输入第"+i+"个教师的姓名：");
			String name = input.next();
			System.out.print("请输入第"+i+"个教师的账号：");
			String tno = input.next();
			System.out.print("请输入第"+i+"个教师的所教科目：");
			String subject = input.next();
			System.out.println("密码默认与账号相同！！！");
			if(manage.add_teacher(name, tno, tno,subject,teachers))
			{
				System.out.println("第"+i+"个教师添加成功！");
			}else
			{
				System.err.println("从第"+i+"个教师起添加失败！！");
				break;
			}
		}
		if(i!=count+1)
		{
			System.out.print("是否继续添加<Y/N>");
			if(input.next().equals("Y")) add_TeacherFrame(teachers);
		}
	}
	/**
	 * 管理员主页面
	 */
	public static void manage_MainFrame() 
	{
		System.out.println("");
		System.out.println("*****************************************************************************\n");
		System.out.print("——————>管理员：\n\n");
		System.out.println("\t\t\t1.修改密码\n");
		System.out.println("\t\t\t2.添加学生\n");
		System.out.println("\t\t\t3.添加教师\n");
		System.out.println("\t\t\t4.返回主菜单\n");
		System.out.println("*****************************************************************************\n");
		System.out.print("请选择：");
		switch (input.nextInt()) {
		case 1:
			Ui.changPwdFrame(Ui.init.manageList);
			break;
		case 2:
			Ui.add_StuFrame(Ui.init.stuList);
			break;
		case 3:
			add_TeacherFrame(Ui.init.teacherList);
			break;
		case 4:
			Ui.returnMainFrame();
			break;

		default:
			System.err.print("输入有误，重新选择:");
			Ui.manage_MainFrame();
		}
		System.out.print("是否继续<Y/N>:");
		if(input.next().equals("Y")) manage_MainFrame();
	}
	/**
	 * 学生上交作业界面
	 * @param stuCount 学号
	 */
	public static void upHomeWorkFrame(String stuCount) {
		System.out.print("请输入你要提交作业的老师名字：");
		String tname = input.next();
		if (new Teacher().isExietTeacher(tname, Ui.init.teacherList)) {
			new Student().upHomeWork(2,input, stuCount, tname,
					Ui.init.homeworkList, Ui.init.answerList);
		}else {
			System.err.println("无该教师！！");
		}
		
	}
	/**
	 * 学生查看成绩界面
	 * @param stuCount 学号
	 */
	public static void viewGradeFrame(String stuCount) {
		new Student().viewGrade(stuCount, Ui.init.homeworkList, Ui.init.answerList);
	}
	/**
	 * 学生查看未交作业界面
	 * @param stuCount 学号
	 */
	public static void viewHomeWorkFrame(String stuCount) {
		System.out.print("请输入你要提交作业的老师名字：");
		String tname = input.next();
		if (new Teacher().isExietTeacher(tname, Ui.init.teacherList)) {
			Student student = new Student();
			if(student.upHomeWork(4, input, stuCount, tname,
					Ui.init.homeworkList, Ui.init.answerList) == 0)
			{
				System.out.print("是否去完成作业<Y/N>:");
				if (input.next().equals("Y"))  
					student.upHomeWork(2,input, stuCount, tname, 
					Ui.init.homeworkList, Ui.init.answerList);
			}
		}else {
			System.err.println("无该教师！！");
		}
		
	}
	/**
	 * 学生用户界面
	 * @param stuCount 学号
	 */
	public static void student_MainFrame(String stuCount) 
	{
		System.out.println("");
		System.out.println("*****************************************************************************\n");
		System.out.print("——————>学生：\n\n");
		System.out.println("\t\t\t1.修改密码\n");
		System.out.println("\t\t\t2.上传作业\n");
		System.out.println("\t\t\t3.查看成绩\n");
		System.out.println("\t\t\t4.查看作业任务\n");
		System.out.println("\t\t\t5.返回主菜单\n");
		System.out.println("*****************************************************************************\n");
		System.out.print("请选择：");
		switch (input.nextInt()) {
		case 1:
			Ui.changPwdFrame(Ui.init.stuList);
			break;
		case 2:
			Ui.upHomeWorkFrame(stuCount);
			break;
		case 3:
			viewGradeFrame(stuCount);
			break;
		case 4:
			viewHomeWorkFrame(stuCount);
			break;
		case 5:
			Ui.returnMainFrame();
			break;	
		default:
			System.err.println("输入有误，重新选择:");
			Ui.student_MainFrame(stuCount);
		}
		System.out.print("是否继续<Y/N>:");
		if(input.next().equals("Y")) student_MainFrame(stuCount);
	}
	/**
	 * 教师发布作业
	 * @param tcount 教师编号
	 * @param homeworkList 作业集合对象
	 * @param teacherList 教师集合对象
	 */
	public static void addHomeworkFrame(String tcount)
	{
		Teacher teacher = new Teacher();
		System.out.print("请输入作业要求：");
		String demand = input.next();
		System.out.print("请输入作业截止日期：");
		String overtime = input.next();
		if(teacher.addHomeWork(tcount, demand, overtime, Ui.init.homeworkList,Ui.init.teacherList,Ui.init.answerList))
		{
			System.out.println("作业发布成功！！");
		}else
		{
			System.err.println("作业发布失败！！");
		}
		System.out.print("是否继续发布<Y/N>:");
		if(input.next().equals("Y")) addHomeworkFrame(tcount);
	}
	/**
	 * 教师查看已发布作业
	 * @param tcount 教师编号
	 */
	public static void viewPushedHomeworkFrame(String tcount) {
		new Teacher().viewPushedHomework(tcount, Ui.init.homeworkList, Ui.init.teacherList);
	}
	/**
	 * 教师批改作业
	 * @param tcount 教师编号
	 */
	public static void checkHomeworkFrame(String tcount) {
		new Teacher().checkHomework(Ui.input, tcount, Ui.init.homeworkList, Ui.init.teacherList, Ui.init.answerList);
	}
	/**
	 * 教师用户界面
	 * @param 教师编号
	 */
	public static void teacher_MainFrame(String tcount) 
	{
		System.out.println("");
		System.out.println("*****************************************************************************\n");
		System.out.print("——————>教师：\n\n");
		System.out.println("\t\t\t1.修改密码\n");
		System.out.println("\t\t\t2.批改作业\n");
		System.out.println("\t\t\t3.发布作业任务\n");
		System.out.println("\t\t\t4.查看已发布作业任务\n");
		System.out.println("\t\t\t5.返回主菜单\n");
		System.out.println("*****************************************************************************\n");
		System.out.print("请选择：");
		switch (input.nextInt()) {
		case 1:
			Ui.changPwdFrame(Ui.init.teacherList);
			break;
		case 2:
			checkHomeworkFrame(tcount);
			break;
		case 3:
			Ui.addHomeworkFrame(tcount);
			break;
		case 4:
			viewPushedHomeworkFrame(tcount);
			break;
		case 5:
			Ui.returnMainFrame();
			break;

		default:
			System.err.println("输入有误，重新选择:");
			Ui.teacher_MainFrame(tcount);
		}
		System.out.print("是否继续<Y/N>:");
		if(input.next().equals("Y")) teacher_MainFrame(tcount);
	}
	/**
	 * 返回主菜单
	 */
	public static void returnMainFrame() 
	{
		System.out.print("是否返回主菜单<Y/N>:");
		if(input.next().equals("Y")) Ui.mainFrame();
		else {
			System.out.println("谢谢使用！");
			System.exit(0);//必须退出，否则会进入上一层递归
		}
	}
	/**
	 * @author YSL
	 * @主界面
	 */
	public static void mainFrame() 
	{
		Ui.welcome();
		switch (input.nextInt()) 
		{
		case 1:
			Student student = new Student();
			String[] resStu = Ui.loginFrame();
			if(student.login(resStu,Ui.init.stuList) == 1)
			{
			    Ui.student_MainFrame(resStu[0]);
			}
			else System.err.println("用户不存在！\n");
			Ui.returnMainFrame();
			break;
		case 2:
			Manage manage = new Manage();
			if(manage.login(Ui.loginFrame(), Ui.init.manageList) == 1) 
				Ui.manage_MainFrame();
			else System.err.println("用户不存在！\n");
			Ui.returnMainFrame();
			break;
		case 3:
			String[] resTeacher = Ui.loginFrame();
			Teacher teacher = new Teacher();
			if(teacher.login(resTeacher,Ui.init.teacherList) == 1)
			{
			    Ui.teacher_MainFrame(resTeacher[0]);
			}
			else System.err.println("用户不存在！\n");
			Ui.returnMainFrame();
			break;
		case 4:
			System.out.println("谢谢使用!");
			System.exit(0);//必须退出，否则会进入上一层递归

		default:
			System.err.println("输入有误，重新选择:");
			Ui.mainFrame();
		}
	}
}
