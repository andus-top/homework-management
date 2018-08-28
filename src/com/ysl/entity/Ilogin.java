package com.ysl.entity;

import java.util.ArrayList;

public interface Ilogin {
	/**
	 * 
	 * @param 保存用户账户和密码
	 * @param 学生或老师或管理员的集合对象
	 * @return
	 */
	public int login(String[] user,ArrayList<?> obj);
	
}