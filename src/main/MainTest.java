/*
 * Copyright softtrain. All rights reserved.
 */
package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import util.Util;

/**
 * 
 * <b>gui_first</b>
 * <pre>
 * @file : main
 *           └ MainTest.java
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
public class MainTest {
	public static void main(String[] args) {
		//Util util = new Util();
		
//		Map<String,Object> result = util.randomWordCut("hi", 1);
//		
//		
//		System.out.println(result.get("cuttingWord"));
//		System.out.println(result.get("cuttingWordIndex"));
		
//		try {
//			System.out.println(util.stringConvertCalc("(3)* 0*32"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//System.out.println(wordList.toString());
		
//		String test = "abcd";
//		
//		System.out.println(test.charAt(2));
		
		
		
//		List<Integer> testList = new ArrayList<>();
//		
//		testList.add(6);
//		testList.add(3);
//		
//		Collections.sort(testList);
//		
//		System.out.println(testList.toString());
		
		System.out.println(chkUpperAlpabet("a"));
		System.out.println(chkUpperAlpabet("B"));
	}
	
	public static boolean chkUpperAlpabet(String str){
		boolean result = true;
		
		char chr = str.charAt(0);
		
		if(chr >= 'A' && chr <= 'Z'){
			return false;
		}else if( chr>= 'a' && chr <= 'z'){
			return true;
		}
		
		
		return result;
	}
}
