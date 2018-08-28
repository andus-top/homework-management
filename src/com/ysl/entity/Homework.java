package com.ysl.entity;

public class Homework {
	private  int id;
	private String subject;
	private String demand;
	private String overtime;
	private String tname;
	public Homework(int id,String subject,String demand,String overtime,String tname) {
		this.subject = subject;
		this.demand = demand;
		this.overtime = overtime;
		this.tname = tname;
		this.id = id;
	}
	public String  getSubject(){
		return subject;
	}
	public void setSubject(String subject){
		this.subject=subject;
	}
	public String  getDemand(){
		return demand;
	}
	public void setDemand(String demand){
		this.demand=demand;
	}
	public String  getOvertime(){
		return overtime;
	}
	public void setOvertime(String overtime){
		this.overtime=overtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
}