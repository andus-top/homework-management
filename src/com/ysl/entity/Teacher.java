package com.ysl.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class Teacher extends Person implements Ilogin {
	private String subject;

	public Teacher(String name, String count, String password, String subject) {
		super(name, count, password);
		this.subject = subject;
	}

	public Teacher() {
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 教师登陆
	 * 
	 * @param user
	 *            用户名和密码的数组
	 * @param obj
	 *            用户的集合对象
	 * @return 1代表登陆成功
	 */
	public int login(String[] user, ArrayList<?> obj) {
		// TODO Auto-generated method stub
		for (Object person : obj) {
			if (((Teacher) person).count.equals(user[0]) && ((Teacher) person).password.equals(user[1]))
				return 1;
		}
		return 0;
	}

	/**
	 * 通过教师姓名判断该教师是否存在
	 * 
	 * @param tname
	 *            教师姓名
	 * @param obj
	 *            教师集合对象
	 * @return 存在返回true
	 */
	public boolean isExietTeacher(String tname, ArrayList<Teacher> obj) {
		for (Teacher teacher : obj) {
			if (teacher.getName().equals(tname)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 通过教师编号返回教师名字
	 * 
	 * @param tcount
	 *            教师编号
	 * @param obj
	 *            教师集合对象
	 * @return 教师名字 教师所教科目
	 */
	public String[] getNameAndSubjectByCount(String tcount, ArrayList<Teacher> obj) {
		String[] data = new String[2];
		for (Object person : obj) {
			if (((Teacher) person).count.equals(tcount)) {
				data[0] = ((Teacher) person).getName();
				data[1] = ((Teacher) person).getSubject();
				return data;
			}
		}
		return null;
	}

	/**
	 * 教师添加作业
	 * 
	 * @param tcount
	 *            教师编号
	 * @param demand
	 *            题目/要求
	 * @param overtime
	 *            截止日期
	 * @param homeworkList
	 *            作业集合对象
	 * @param teacherList
	 *            教师集合对象
	 * @return 作业发布成功返回true
	 */
	public boolean addHomeWork(String tcount, String demand, String overtime, ArrayList<Homework> homeworkList,
			ArrayList<Teacher> teacherList, ArrayList<Answer> answerList) {
		String data[] = getNameAndSubjectByCount(tcount, teacherList);
		if (homeworkList.add(new Homework(homeworkList.size() + 1, data[1], demand, overtime, data[0]))) {
			// 注意这里长度不在加1，因为前面作业已经添加成功，长度已经加1
			answerList.add(new Answer(homeworkList.size()));
			return true;
		}
		return false;
	}

	/**
	 * 教师查看自己已经发布的作业
	 * 
	 * @param tcount
	 *            教师编号
	 * @param homeworkList
	 *            作业集合对象
	 * @param teacherList
	 *            教师集合对象
	 */
	public void viewPushedHomework(String tcount, ArrayList<Homework> homeworkList, ArrayList<Teacher> teacherList) {
		String data[] = getNameAndSubjectByCount(tcount, teacherList);
		System.out.println("序号\t要求\t截至日期\t");
		for (int i = 0; i < homeworkList.size(); i++) {
			if ((homeworkList.get(i)).getTname().equals(data[0])) {
				System.out.println(i + "\t" + (homeworkList.get(i)).getDemand() + "\t"
						+ (homeworkList.get(i)).getOvertime() + "\t");
			}
		}
	}

	/**
	 * 教师批改作业
	 * 
	 * @param input
	 *            Scanner对象
	 * @param tcount
	 *            教师编号
	 * @param homeworkList
	 *            作业集合对象
	 * @param teacherList
	 *            教师集合对象
	 * @param answerList
	 *            答案集合对象
	 */
	public void checkHomework(Scanner input, String tcount, ArrayList<Homework> homeworkList,
			ArrayList<Teacher> teacherList, ArrayList<Answer> answerList) {
		if (answerList.size() != 0) {
			ArrayList<Integer> homeWorkId = new ArrayList<Integer>();
			ArrayList<String> homeWorkDemand = new ArrayList<String>();
			String data[] = getNameAndSubjectByCount(tcount, teacherList);
			for (int i = 0; i < homeworkList.size(); i++) {
				if ((homeworkList.get(i)).getTname().equals(data[0])) {
					homeWorkId.add((homeworkList.get(i)).getId()); // 通过老师的名字得到他布置的题的题号
					homeWorkDemand.add(homeworkList.get(i).getDemand());// 通过老师的名字得到该题的题目要求
				}
			}

			int n = 1;// 列表序号
			int[] temp = new int[answerList.size()];
			System.out.println("序号\t要求\t答案\t分数\t");
			for (int j = 0; j < answerList.size(); j++) {
				for (int m = 0; m < homeWorkId.size(); m++) {
					if (answerList.get(j).getHomeworkId() == homeWorkId.get(m) // 该题号分数为0，则输出让老师打分
							&& answerList.get(j).getGrade() == 0) {
						temp[n] = j;
						String answer = answerList.get(j).getAnswer();
						if (answerList.get(j).getAnswer() == null)
							answer = "";
						System.out.println(n + "\t" + homeWorkDemand.get(m) + "\t" + answer + "\t"
								+ answerList.get(j).getGrade() + "\t");
						n++;
					}
				}
			}

			System.out.print("请选择你要打分的序号：");
			int p = input.nextInt();
			if (p > n || p <= 0) {
				System.out.println("输入有误！");
				return;
			}
			for (int q = 0; q < answerList.size(); q++) {
				if (q == temp[p]) {
					System.out.print("请输入你要打的分数：");
					answerList.get(q).setGrade(input.nextInt());
					System.out.println("打分成功！");
					break;
				}
			}
		} else {
			System.out.println("学生未提交任何作业！");
		}
	}
}
