/*
 * Copyright softtrain. All rights reserved.
 */
package miro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * <b>MiroEducation</b>
 * <pre>
 * @file : service
 *           └ miroTest.java
 *
 * --------------------------------------------------------
 *   수 정 일 자        수 정 자              수 정 내 용
 * --------------  -------------  -------------------------
 * 2018. 3. 28.    ksj  최초작성
 *
 * --------------------------------------------------------
 *</pre>
 * @date : 2018. 3. 28.
 * @author : ksj
 * @version : 1.0 (jdk 1.6)
 */
public class Miro {
	 /*
	 * param level 
	 * 
	 * return List<List<Integer>> 폭탄리스트
	 * 
	 * */

	int level = 0 ;
	static List<Map<String,Object>> bombStoreList = new ArrayList<>();
	
	public Miro(int level){
		this.level = level;
		
	}
	
	
	public static List<List<Integer>> getMiro(int level){
		List<List<Integer>> result = new ArrayList<>();
		//길 1
		//갈수없는 길(폭탄) 0 
		if(level == 1 || level == 2){//	레벨 1 2는 4*4 에 폭탄 3개
			int row = 4;
			int col = 4;
			int bombCount = 3;
			
			bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}else if(level == 3 || level == 4){//	레벨 3 4는 5*5에 폭탄 4개
			int row = 5;
			int col = 5;
			int bombCount = 4;
			
			bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}else if(level == 5){//	레벨 5는 6*6에 폭탄 5개
			int row = 6;
			int col = 6;
			int bombCount = 5;
			
			bombStoreList = getBombStoreList(row,col,bombCount);
			
			result = setMiro(row,col,bombStoreList);
		}
		
		return result;
	}
	
	public static List<Map<String, Object>> getBombStoreList(int row, int col, int bombCount){
		List<Map<String, Object>> result = new ArrayList<>();
		int arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
					 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1}; //폭탄 나오게 할 확률을 위한 배열
		//row = 4;
		//col = 4;
		//bombCount = 2; //배열은 0 인덱스부터 시작이므로. 2로 초기 세팅
		
		while(true){
			for(int i = 0 ; i< row; i++){
				for(int j = 0 ; j< col; j++){
					int dupChk =0;
					if(arr[new Random().nextInt(arr.length)] == 0){
						Map<String,Object> bombStore = new HashMap<>();
						bombStore.put("bombStore", i + "," + j);
						if(result.size() == 0 && !(i== 0 && j ==0)){
							result.add(bombStore);
							bombCount --;
						}else{
							for(int z = 0; z < result.size() ; z++){
								String chkBomStore = result.get(z).get("bombStore").toString();
								if(!chkBomStore.equals(i + "," + j) && !(i== 0 && j ==0) && !(i == row-1 && j ==col-1)){
									bombStore.put("bombStore", i + "," + j);
									dupChk = 1;
									break;
								}
							}
							if(dupChk ==1){
								result.add(bombStore);
								bombCount --;
								if(bombCount == 0){
									break;
								}
							}
						}
					}
				}
			}
			if(bombCount == 0){
				break;
			}
		}
		//폭탄 좌표 확인
//		for(int i = 0; i<result.size(); i++){
//			System.out.println("i값..." + i);
//			System.out.println(result.get(i).get("bombStore").toString());
//		}
		return result;
	}
	
	public static List<List<Integer>> setMiro(int row, int col, List<Map<String,Object>> bombStoreList){
		List<List<Integer>> result = new ArrayList<>();
		
		for(int i=0; i< row; i++){
			List<Integer> rowList = new ArrayList<>();
			for(int j = 0 ; j< col; j++){
				int bombChk = 0;
				for(int z = 0; z< bombStoreList.size(); z++){
					String bombStore = bombStoreList.get(z).get("bombStore").toString();
					if(bombStore.equals(i + "," + j)){
						bombChk = 1;
					}
				}
				if(bombChk == 1){
					rowList.add(0);
				}else{
					rowList.add(1);
				}
			}
			result.add(rowList);
		}

		
		return result;
	}
	
	/**
	 * level
	 * wordCount (총 단어수)
	 * 
	 * 
	 * */
	
	public static List<Map<String, Object>> getWordList(int level, int wordCount){
		Random random = new Random(System.currentTimeMillis());
		
//		int wordCount = 3;//추후 조절
		
		int row = 0;
		int col = 0;
		//갈수없는 길(폭탄) 0 
		if(level == 1 || level == 2){//	레벨 1 2는 4*4 에 폭탄 3개
			row = 4;
			col = 4;
		}else if(level == 3 || level == 4){//	레벨 3 4는 5*5에 폭탄 4개
			row = 5;
			col = 5;
		}else if(level == 5){//	레벨 5는 6*6에 폭탄 5개
			row = 6;
			col = 6;
		}
		int x = random.nextInt(row) + 0;
		int y = random.nextInt(col) + 0;
		
		List<Map<String,Object>> result = new ArrayList<>();//단어가 나올 좌표 지정
		
		while(true){
			Map<String,Object> wordMap = new HashMap<>();
			int x2 = random.nextInt(row) + 0; //0~row수 만큼 x좌표 랜덤
			int y2 = random.nextInt(col) + 0; //0~col수 만큼 y좌표 랜덤
			int wordDupChk = 0;
			int bombChk = 0;
			if(result.size() == 0){
				for(int z = 0; z< bombStoreList.size(); z++){
					String bombStore = bombStoreList.get(z).get("bombStore").toString();
					if(bombStore.equals(x2 + "," + y2)){
						bombChk = 1;
					}
				}
				
				if(wordDupChk ==0 && bombChk == 0 && !(x2==0 && y2 ==0)){
					wordMap.put("wordMap", x2 + "," + y2);
					result.add(wordMap);
					wordCount--;
				}
			}else{
				for(int z = 0; z< result.size(); z++){
					String wordXY = result.get(z).get("wordMap").toString();
					if(wordXY.equals(x2 + "," + y2)){
						wordDupChk = 1;
						
					}
				}
				
				for(int z = 0; z< bombStoreList.size(); z++){
					String bombStore = bombStoreList.get(z).get("bombStore").toString();
					if(bombStore.equals(x2 + "," + y2)){
						bombChk = 1;
					}
				}
				
				if(wordDupChk ==0 && bombChk == 0 && !(x2==0 && y2 ==0)){
					wordMap.put("wordMap", x2 + "," + y2);
					result.add(wordMap);
					wordCount--;
				}
			}
			if(wordCount ==0){
				break;
			}
		}
//		System.out.println(bombStoreList.toString());
//		System.out.println(result.toString());
		return result;
	}
}
