package com.ysl.main;

import java.util.InputMismatchException;

import com.ysl.ui.Ui;
/**
 * @author YSL
 */
public class Main {
	public static void main(String[] args) {
		try {
			Ui.mainFrame();
		} catch (InputMismatchException e) {
			System.err.println("您输入了非法字符！sorry！程序已经退出！！");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
