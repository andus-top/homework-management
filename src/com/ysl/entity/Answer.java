package com.ysl.entity;

public class Answer {
	private int homeworkId;
	private String sno = "-1";
	private String answer;
	private float grade=0;
	public Answer() {}
	public Answer(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getAnswer(){
		return answer;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}
	public float getGrade(){
		return grade;
	}
	public void setGrade(float grade){
		this.grade=grade;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
}
