/*
 * Copyright softtrain. All rights reserved.
 */
package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * <b>gui_first</b>
 * <pre>
 * @file : util
 *           └ randomWordCut.java
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
public class Util {
	
	public Map<String ,Object> randomWordCutEng(String word, int cuttingCnt){
		List<String> garbageArrayList = new ArrayList<String>();
		
		garbageArrayList.add("a");
		garbageArrayList.add("b");
		garbageArrayList.add("c");
		garbageArrayList.add("d");
		garbageArrayList.add("e");
		garbageArrayList.add("f");
		garbageArrayList.add("g");
		garbageArrayList.add("h");
		garbageArrayList.add("i");
		garbageArrayList.add("j");
		garbageArrayList.add("k");
		garbageArrayList.add("l");
		garbageArrayList.add("m");
		garbageArrayList.add("n");
		garbageArrayList.add("o");
		garbageArrayList.add("p");
		garbageArrayList.add("q");
		garbageArrayList.add("r");
		garbageArrayList.add("s");
		garbageArrayList.add("t");
		garbageArrayList.add("u");
		garbageArrayList.add("v");
		garbageArrayList.add("w");
		garbageArrayList.add("x");
		garbageArrayList.add("y");
		garbageArrayList.add("z");
		
		Map<String,Object> result = new HashMap<>();
		
		String cuttingWord = word;
		List<Integer> cuttingWordIndex = new ArrayList<Integer>();
		//잘린 단어 담고 있는 List
		List<String> cuttingWordList = new ArrayList<String>();
		
		while(true){
			Random random = new Random();
			int wordLength = word.length();
			int randomInt = random.nextInt(wordLength-1) + 0;
			int dupChk = 0;
			
			for(int j = 0; j<cuttingWordIndex.size(); j++){
				if(cuttingWordIndex.get(j) == randomInt){
					dupChk = 1;
				}
			}
			
			if(dupChk ==0){
				int wordDup = 0 ;
				for(int i = 0 ; i< cuttingWordList.size();i++){
					if(cuttingWordList.get(i).equals(word.charAt(randomInt))){
						wordDup = 1;
					}
					
				}
				if(wordDup == 0){
					cuttingWord = cuttingWord.substring(0, randomInt) + "_" + cuttingWord.substring(randomInt+1);
					cuttingWordIndex.add(randomInt);
					char cr = word.charAt(randomInt);
					String str = new Character(cr).toString();
					cuttingWordList.add(str);
					cuttingCnt --;
				}
			}
			if(cuttingCnt == 0){
				break;
			}
		}
		
		for(int i = 0 ; i < cuttingWordList.size(); i++ ){
			
			for(int j =0 ;j <garbageArrayList.size(); j++){
				if(garbageArrayList.get(j).equals(cuttingWordList.get(i))){
					garbageArrayList.remove(j);
				}
			}
		}
		
		//오름차순 인덱스 정렬
		Collections.sort(cuttingWordIndex);

		result.put("cuttingWord", cuttingWord);
		result.put("cuttingWordIndex", cuttingWordIndex);
		result.put("garbageWordList", garbageArrayList);
	
		return result;
	}
	
	
	public Map<String ,Object> getEnglishLevel1Setting(String word){
		List<String> garbageArrayList = new ArrayList<String>();
		String type = "";//대문자면 소문자 garbageArrayList, 소문자면 대문자 garbageArrayList
		
		if(chkUpperAlpabet(word)){
			type = "L";
		}else{
			type = "U";
		}
		
		if(type.equals("U")){//입력받은 것이 소문자인지 대문자인지 구별
			garbageArrayList.add("a");
			garbageArrayList.add("b");
			garbageArrayList.add("c");
			garbageArrayList.add("d");
			garbageArrayList.add("e");
			garbageArrayList.add("f");
			garbageArrayList.add("g");
			garbageArrayList.add("h");
			garbageArrayList.add("i");
			garbageArrayList.add("j");
			garbageArrayList.add("k");
			garbageArrayList.add("l");
			garbageArrayList.add("m");
			garbageArrayList.add("n");
			garbageArrayList.add("o");
			garbageArrayList.add("p");
			garbageArrayList.add("q");
			garbageArrayList.add("r");
			garbageArrayList.add("s");
			garbageArrayList.add("t");
			garbageArrayList.add("u");
			garbageArrayList.add("v");
			garbageArrayList.add("w");
			garbageArrayList.add("x");
			garbageArrayList.add("y");
			garbageArrayList.add("z");
		}else{
			garbageArrayList.add("A");
			garbageArrayList.add("B");
			garbageArrayList.add("C");
			garbageArrayList.add("D");
			garbageArrayList.add("E");
			garbageArrayList.add("F");
			garbageArrayList.add("G");
			garbageArrayList.add("H");
			garbageArrayList.add("I");
			garbageArrayList.add("J");
			garbageArrayList.add("K");
			garbageArrayList.add("L");
			garbageArrayList.add("M");
			garbageArrayList.add("N");
			garbageArrayList.add("O");
			garbageArrayList.add("P");
			garbageArrayList.add("Q");
			garbageArrayList.add("R");
			garbageArrayList.add("S");
			garbageArrayList.add("T");
			garbageArrayList.add("U");
			garbageArrayList.add("V");
			garbageArrayList.add("W");
			garbageArrayList.add("X");
			garbageArrayList.add("Y");
			garbageArrayList.add("Z");
		}
		
		Map<String,Object> result = new HashMap<>();
		
		for(int i = 0; i<garbageArrayList.size(); i++){
			if(type.equals("L")){
				if(garbageArrayList.get(i).equals(word.toUpperCase())){
					garbageArrayList.remove(i);
				}
			}else{
				if(garbageArrayList.get(i).equals(word.toLowerCase())){
					garbageArrayList.remove(i);
				}
			}
		}
		result.put("wordType", type);
		result.put("garbageWordList", garbageArrayList);
		return result;
	}
	
	/**
	 * 대소문자 구별
	 * return -> 대문자면 false, 소문자면 true
	 * 
	 * */
	public boolean chkUpperAlpabet(String str){
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

