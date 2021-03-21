/*
 * Copyright softtrain. All rights reserved.
 */
package main;

import java.awt.EventQueue;

import gui.english_level1;

/**
 * 
 * <b>gui_first</b>
 * <pre>
 * @file : main
 *           └ main.java
 *
 * --------------------------------------------------------
 *   수 정 일 자        수 정 자              수 정 내 용
 * --------------  -------------  -------------------------
 * 2018. 4. 5.    ksj  최초작성
 *
 * --------------------------------------------------------
 *</pre>
 * @date : 2018. 4. 5.
 * @author : ksj
 * @version : 1.0 (jdk 1.6)
 */
public class main {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					//main_page frame = new main_page();
					english_level1 frame = new english_level1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
