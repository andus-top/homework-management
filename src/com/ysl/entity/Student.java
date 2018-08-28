package com.ysl.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends Person implements Ilogin {
	public Student(String name, String count, String password) {
		super(name, count, password);
	}

	public Student() {
	}

	public int login(String[] user, ArrayList<?> student) {
		for (Object person : student) {
			if (((Student) person).count.equals(user[0]) && ((Student) person).password.equals(user[1]))
				return 1;
		}
		return 0;
	}

	/**
	 * 学生提交作业
	 * 
	 * @param flag
	 *            flag=2为上交作业，flag=4为查看未交作业
	 * @param input
	 *            Scanner对象
	 * @param stuCount
	 *            学号
	 * @param tname
	 *            教师名字
	 * @param homeworkList
	 *            作业集合对象
	 * @param answerList
	 *            答案集合对象
	 */
	public int upHomeWork(int flag, Scanner input, String stuCount, String tname, ArrayList<Homework> homeworkList,
			ArrayList<Answer> answerList) {
		ArrayList<Integer> homeWorkId = new ArrayList<Integer>();
		ArrayList<String> homeWorkDemand = new ArrayList<String>();
		ArrayList<String> subjectList = new ArrayList<String>();
		for (int i = 0; i < homeworkList.size(); i++) {
			if ((homeworkList.get(i)).getTname().equals(tname)) {
				homeWorkId.add((homeworkList.get(i)).getId());
				homeWorkDemand.add(homeworkList.get(i).getDemand());
				subjectList.add(homeworkList.get(i).getSubject());
			}
		}
		int n = 1;// 列表序号
		int[] temp = new int[answerList.size()];
		temp[0] = 0;
		System.out.println("序号\t科目\t要求\t");
		for (int j = 0; j < answerList.size(); j++) {
			for (int m = 0; m < homeWorkId.size(); m++) {
				if (answerList.get(j).getHomeworkId() == homeWorkId.get(m) && answerList.get(j).getGrade() == 0
						&& answerList.get(j).getAnswer() == null) {
					temp[n] = j;
					System.out.println(n + "\t" + subjectList.get(m) + "\t" + homeWorkDemand.get(m) + "\t");
					temp[0] = 1;
					n++;
				}
			}
		}

		if (temp[0] == 0) {
			System.out.println("你没有未完成的作业！！");
			return 1;
		}
		if (flag == 4)
			return 0;// 此时选择的是查看没完成的任务

		System.out.print("请选择你要做的题的序号：");
		int p = input.nextInt();
		if (p > n || p <= 0) {
			System.out.println("输入有误！");
			return 0;
		}
		for (int q = 0; q < answerList.size(); q++) {
			if (q == temp[p]) {
				System.out.print("请输入你的答案：");
				answerList.get(q).setSno(stuCount);
				answerList.get(q).setAnswer(input.next());
				System.out.println("作业完成！");
				return 0;
			}
		}
		return 0;
	}

	/**
	 * 学生查看成绩
	 * 
	 * @param stuCount
	 *            学号
	 * @param homeworkList
	 *            作业集合对象
	 * @param answerList
	 *            答案集合对象
	 */
	public void viewGrade(String stuCount, ArrayList<Homework> homeworkList, ArrayList<Answer> answerList) {
		ArrayList<Integer> homeWorkId = new ArrayList<Integer>();
		ArrayList<Float> gradeList = new ArrayList<Float>();
		for (int i = 0; i < answerList.size(); i++) {
			if ((answerList.get(i)).getSno().equals(stuCount)) {
				homeWorkId.add((answerList.get(i)).getHomeworkId());
				gradeList.add((answerList.get(i)).getGrade());
			}
		}

		int n = 1;// 列表序号
		System.out.println("序号\t要求\t科目\t分数\t");
		for (int j = 0; j < homeWorkId.size(); j++) {
			for (int m = 0; m < homeworkList.size(); m++) {
				if ((homeworkList.get(m)).getId() == homeWorkId.get(j)) {
					System.out.println(n + "\t" + (homeworkList.get(m)).getDemand() + "\t"
							+ (homeworkList.get(m)).getSubject() + "\t" + gradeList.get(j) + "\t");
					n++;
				}
			}
		}
	}
}
