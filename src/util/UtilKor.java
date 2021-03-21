package util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UtilKor {
   /**
    * 초성
    */
   private static final char[] firstSounds = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ',
         'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };
   // 한글 중성
   private static final char[] middleSounds = { 'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ',
         'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ' };// 1,3,5,7,9,10,11,14,15,16,19
   // 한글 종성
   private static final char[] lastSounds = { ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ'
                                 , 'ㄵ' , 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ'
                                 , 'ㄻ' , 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ'
                                 , 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ'
                                 , 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ'
                                 , 'ㅌ', 'ㅍ', 'ㅎ' };
                                 //받침 두개있는 종성 : 2,3,5,6,9,10,11,12,13,14,15,18,20
   //level1 단어
   private static final char[] level1Char = { '가', '나', '다', '라', '마', '바', '사', '아', '자', '차' , '카', '타' , '파', '하'};
   /**
    * 문자열을 'euc-kr' 로 인코딩.
    * 
    * @param str
    *            인코딩할 문자열
    * @return 인코딩된 문자열
    */
   public static String convertCharset(String str) {
      try {
         if (str == null)
            return null;
         return new String(str.getBytes("8859_1"), "euc-kr");
      } catch (UnsupportedEncodingException _ex) {
         return "Encoding Error";
      }
   }

   /**
    * 한글 한 글자(char)를 받아 초성, 중성, 종성의 위치를 int[]로 반환 한다.
    * 
    * @param char
    *            : 한글 한 글자
    * @return int[] : 한글 초, 중, 종성의 위치( ex:가 0,0,0 )
    */
   public static int[] split(char c) {
      int sub[] = new int[3];
      sub[0] = (c - 0xAC00) / (21 * 28); // 초성의 위치
      sub[1] = ((c - 0xAC00) % (21 * 28)) / 28; // 중성의 위치
      sub[2] = (c - 0xAC00) % (28);// 종성의 위치
      return sub;
   }

   /**
    * 한글 초,중,종성 분리/조합 테스트 메소드
    */
   public static String doSomething(char str) {
      String result = "0";// 종성 없고, 단모음 타입 1
                     // 종성 없고 , 이중모음 타입 2
                     // 종성 있고, 단모음 타입 3
                     // 종성 있고, 이중모음 타입 4

      int[] x = null;
      char c = str;
      // System.out.println("============한글 분리============");
      int middle = 0;// 단모음이면 0, 이중모음이면 1
      int last = 1; // 종성 없으면 0 , 종성 있으면 1
      if (c >= 0xAC00) {
         x = split(c);
         // 모음 검사(중성)
         int j = x[1];
         if (j == 1 || j == 3 || j == 5 || j == 7 || j == 9 || j == 10 || j == 11 || j == 11 || j == 14 || j == 15
               || j == 16 || j == 19) {
            // 이중모음)
            middle = 1;
         }

         int z = x[2];
         if (z == 0) {
            last = 0;
         }else if(z == 2 || z ==3 || z ==5 || z ==6 || z ==9 || z ==10 || z ==11 || z ==12 || z ==13
               || z ==14 || z ==15 || z ==18 || z ==20){
            last = 3;
         }
         // 종성 없고, 단모음 타입 1 last =0 , middle 0; result ==1
         // 종성 없고 , 이중모음 타입 2 last =0 , middle 1; result ==2
         // 종성 있고, 단모음 타입 3 last =1, middle 0; result ==3
         // 종성 있고, 이중모음 타입 4 last =1, middle 1; result ==4
         // 종성있고, 종성이 받침이 두개 있는 글자 이면 last3 ; result ==5 
         
         if (last == 0 && middle == 0) {
            result = "1";
         } else if (last == 0 && middle == 1) {
            result = "2";
         } else if (last == 1 && middle == 0) {
            result = "3";
         } else if (last == 1 && middle == 1) {
            result = "4";
         } else if(last == 3 ){
            result = "5";
            
         }
         return result;

      }
      return result;
   }

   

   /**
    * 종성 없고, 단모음 타입 type =1 종성 없고 , 
    * 이중모음 타입 2 type =2 
    * 종성 있고, 단모음 타입 3 type =3,
    * 종성 있고, 이중모음 타입 4 type =4,
    * 아무글자 type =9
    */
   public static Map<String, Object> cuttringKorean(String str, int cuttingCnt, String[] types) {
      Map<String, Object> result = new HashMap<>();

      List<String> cuttingList = new ArrayList<>();
      List<Integer> cuttingWordIndex = new ArrayList<Integer>();
      List<String> garbageList = new ArrayList<>();

      if (cuttingCnt > str.length()) {
         System.out.println("cutting 수가 글자수보다 많습니다.");
         return result;
      }

      while (true) {
         int dupChk = 0;
         Random r = new Random();
         int i = r.nextInt(str.length());
         for (int j = 0; j < cuttingWordIndex.size(); j++) {
            if (cuttingWordIndex.get(j) == i) {
               dupChk = 1;
            }
         }

         for(int j = 0; j<types.length; j++){
            String type = types[j];
            
            if (!type.equals("9")) {// type 9가 아니면
               if (doSomething(str.charAt(i)).equals(type) && dupChk == 0) {
                  cuttingWordIndex.add(i);
                  cuttingList.add(new Character(str.charAt(i)).toString());
                  str = str.substring(0, i) + "_" + str.substring(i + 1);
                  cuttingCnt--;
               }
            } else {// type 9:아무글자만 자를때
               if (dupChk == 0) {
                  cuttingWordIndex.add(i);
                  cuttingList.add(new Character(str.charAt(i)).toString());
                  str = str.substring(0, i) + "_" + str.substring(i + 1);
                  cuttingCnt--;
               }
   
            }
         }

         if (cuttingCnt == 0) {
            break;
         }
      }

      int j = 0;
      // 모든 글자 출력
      for (int i = 0xAC00; i <= 0xD7A3; i++) {
         char ch;
         ch = (char) i;
         String str2 = new Character(ch).toString();
         garbageList.add(str2);
         j++;
      }

      for (int i = 0; i < garbageList.size(); i++) {
         for (int z = 0; z < cuttingList.size(); z++) {
            if (garbageList.get(i).equals(cuttingList.get(z))) {
               garbageList.remove(i);
            }
         }
      }
      
//      for (int i = 0; i < garbageList.size(); i++) {
//         System.out.println(garbageList.get(i));
//      }

      // 오름차순 인덱스 정렬
      Collections.sort(cuttingWordIndex);

      result.put("cuttingWord", str);
      result.put("cuttingList", cuttingList);
      result.put("cuttingWordIndex", cuttingWordIndex);
      result.put("garbageList", garbageList);
      
      return result;
   }
   
   
   public static Map<String, Object> level1Info(String str) {
      Map<String, Object> result = new HashMap<>();

      List<String> cuttingList = new ArrayList<>();
      List<Integer> cuttingWordIndex = new ArrayList<Integer>();
      List<String> garbageList = new ArrayList<>();

      
      cuttingWordIndex.add(0);
      cuttingList.add(new Character(str.charAt(0)).toString());
      str = str.substring(0, 0) + "_" + str.substring(0 + 1);

      // 모든 글자 출력
      for (int i = 0xAC00; i <= 0xD7A3; i++) {
         char ch;
         ch = (char) i;
         String str2 = new Character(ch).toString();
         garbageList.add(str2);
      }

      for (int i = 0; i < garbageList.size(); i++) {
         for (int z = 0; z < cuttingList.size(); z++) {
            if (garbageList.get(i).equals(cuttingList.get(z))) {
               garbageList.remove(i);
            }
         }
      }
      
      // 오름차순 인덱스 정렬
      Collections.sort(cuttingWordIndex);

      result.put("cuttingWord", str);
      result.put("cuttingList", cuttingList);
      result.put("cuttingWordIndex", cuttingWordIndex);
      result.put("garbageList", garbageList);
      
      return result;
   }

}