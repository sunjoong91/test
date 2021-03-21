package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class UtilMath {

	/**
	 * 수학문제 : word
	 * 오탑 수 : inCorrectCnt
	 * 
	 * */
	public Map<String, Object> getMathProblemInfo(String word, int inCorrectCnt) throws Exception {
		Map<String, Object> result = new HashMap();

		int answer = stringConvertCalc(word);

		List<Integer> garbageList = new ArrayList<>();

		while(true){
			int dupChk = 0 ;
			
			
			int random = randomRange(answer -3 , answer + 3);
			
			
			if(random == answer){
				dupChk = 1;
			}
			
			for(int i =0 ; i < garbageList.size(); i++){
				if(garbageList.get(i) == random){
					dupChk = 1;
				}
			}
			
			if(dupChk ==0){
				garbageList.add(random);
				inCorrectCnt--;
			}
			
			if(inCorrectCnt == 0){
				break;
			}
		}
		
		result.put("answer", answer);
		result.put("garbageList", garbageList);
		
		
		return result;
	}

	/**
	 * 스트링형태 숫자열로 계산
	 * 
	 */
	public int stringConvertCalc(String calc) throws Exception {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		int result = (int) engine.eval(calc);

		return result;
	}

	/**
	 * 랜덤 함수 범위
	 */
	public int randomRange(int n1, int n2) {
		return (int) (Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	
}
