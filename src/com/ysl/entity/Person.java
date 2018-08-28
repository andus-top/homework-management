package com.ysl.entity;

import java.util.ArrayList;

public class Person{
	protected String name;
	protected String count;
	protected String password;
	
	public Person(String name,String count,String password) {
		this.count=count;
		this.name=name;
		this.password=password;
	}
	
	public Person() {}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getCount(){
		return count;
	}
	public void setCount(String name){
		this.name=name;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
	/**
	 * 修改密码
	 * @param pwd 新密码
	 * @return 成功返回true
	 */
	public  boolean changePwd(String count,String pwd,ArrayList<?> obj){
		for (Object object : obj) {
			if(((Person)object).getCount().equals(count))
			{
				((Person)object).setPassword(pwd);
				System.out.println("新密码："+((Person)object).getPassword());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 通过账号判断该用户是否存在
	 * @param count 用户的账号
	 * @param obj 学生或老师或管理员的集合对象
	 * @return 存在返回true
	 */
	public boolean isExist(String count, ArrayList<?> obj) {
		for (Object object : obj) {
			if(((Person)object).getCount().equals(count))return true;
		}
		return false;
	}

}
