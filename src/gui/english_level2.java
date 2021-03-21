package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.DBConnection;
import gui.english_level1.MyKeyListener;
import miro.Miro;
import util.Util;
import util.UtilKor;

public class english_level2 extends JFrame {
	// 말 위치
	private int chrX = 0;
	private int chrY = 0;

	// 미로 상자크기
	int rectWitdh = 75;
	int rectHeight = 75;

	//단어
	String word = "";
			
	//이미지
	String filePath = "";
	
	//미로 리스트
	private List<List<Integer>> getMiroList = new ArrayList<>();
	//단어 좌표
	private List<Map<String,Object>> getWordList = new ArrayList<>();
	//blank 단어 저장
	private List<Map<String,Object>> blankWordList = new ArrayList<>();
	//gerbage 단어 저장 Index
	private List<Map<String,Object>> garbageWordIndexList = new ArrayList<>();
	//gerbage 단어
	private List<String> garbageWordList = new ArrayList<>();
	//잘린단어 위치
	private List<Integer> cuttingWordIndex = new ArrayList<>();
	//잘린 단어
	private List<String> cuttingWordList = new ArrayList<>();
	//garbege print 리스트
	private List<String> garbagePrintList = new ArrayList<>();
	
	//이동경로 담고있는 list
	List<Map<String,Object>> goList = new ArrayList<>();


	//blank count
	int blankCnt = 1;
	
	//정답 담고있는 정적배열 선언
	String[] answerList = new String[blankCnt];
	
	int correctCnt = 0;
	
	
	int goListSize = 0;
	
	int enterChk = 0;
	
	int moveCnt = 0 ;
	
	Thread thread;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public english_level2() {
		setTitle("English");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.addKeyListener(new MyKeyListener());
		// setSize(300, 300);
		setVisible(true);
		contentPane.requestFocus();

		setBounds(100, 100, 788, 558);

		ImageIcon back_icon = new ImageIcon(main_page.class.getResource("../images/back.png"));
		Image originImg6 = back_icon.getImage();
		Image changedImg6 = originImg6.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon6 = new ImageIcon(changedImg6);
		JButton back = new JButton(newIcon6);
		back.setBounds(0, 0, 52, 51);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new english_level().setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(back);

		ImageIcon Icon7 = new ImageIcon(main_page.class.getResource("../images/exit.png"));
		Image originImg7 = Icon7.getImage();
		Image changedImg7 = originImg7.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon newIcon7 = new ImageIcon(changedImg7);
		JButton exit = new JButton(newIcon7);
		exit.setBounds(51, 0, 52, 51);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		contentPane.add(exit);
		// 화면 리사이징 false
		super.setResizable(false);

		// 미로판 생성
		Miro miro = new Miro(2);

		getMiroList = miro.getMiro(2);
		getWordList = miro.getWordList(2,4); //오답수 3 정답수 1

		for(int i = 0 ; i<blankCnt; i++){
			blankWordList.add(getWordList.get(i));
			getWordList.remove(i);
			
		}
		
		//wordList에 남는곳에 garbageList넣기
		for(int i = 0; i< getWordList.size(); i++){
			garbageWordIndexList.add(getWordList.get(i));
		}
		
		// 단어 받아오기(SQL)
		DBConnection dbConnection = new DBConnection();
		String sql = "select seq from TB_EDU_DATA WHERE LEVEL = 2 AND TYPE = 2";

		// seq
		String getString = "seq";
		List<Map<String, Object>> seqList = dbConnection.DBConnection(sql, getString);
		
		Random random = new Random();
		String seq = seqList.get(random.nextInt(seqList.size() ) + 0).get("seq").toString();
		
		//word, filePath
		String sql2 = "select * from TB_EDU_DATA WHERE SEQ = " + seq;
		String getString2 = "word";
		List<Map<String, Object>> wordList = dbConnection.DBConnection(sql2, getString2);

		word = wordList.get(0).get("word").toString();
		
		Util util = new Util();
		Map<String,Object> map = util.randomWordCutEng(word, 1);//blank 단어갯수 
		cuttingWordIndex = (List<Integer>) map.get("cuttingWordIndex");
		garbageWordList = (List<String>) map.get("garbageWordList");
		
		
		for(int i = 0; i<cuttingWordIndex.size(); i++){
			String change = "";
			char word = this.word.charAt(((int) cuttingWordIndex.get(i)));
			change = new Character(word).toString();
			//word 
			cuttingWordList.add(i , change);
		}
		
		List<Integer> dupChk = new ArrayList<>();
		
		int garbageCnt = garbageWordIndexList.size();
		while(true){
			Random ran = new Random();
			int ranInx = ran.nextInt(garbageWordList.size());
			int garbageIndexDupChk = 0;
			for(int i = 0 ; i < dupChk.size(); i++){
				if(dupChk.get(i)== ranInx){
					garbageIndexDupChk = 1;
				}
			}
			if(garbageIndexDupChk ==0){
				garbagePrintList.add(garbageWordList.get(ranInx));
				dupChk.add(ranInx);
				garbageCnt--;
			}
			if(garbageCnt ==0){
				break;
			}
		}
		//System.out.println(cuttingWordList.toString());
		
		word = map.get("cuttingWord").toString();
		
		String getString3 = "filePath";
		List<Map<String, Object>> filePathList = dbConnection.DBConnection(sql2, getString3);
		
		filePath = filePathList.get(0).get("filePath").toString();
		System.out.println(filePath);
	}

	/*
	 * 키 이벤트
	 * 
	 */
	class MyKeyListener extends KeyAdapter implements Runnable{
		public void keyPressed(KeyEvent e) {
			Map<String,Object> map = new HashMap<>();
			int keyCode = e.getKeyCode();
			
			String blankXY = answerList[correctCnt];
            String blankArr[] = blankXY.split(",");

			switch (keyCode) {
			case 10:
				
				thread = new Thread(this); // Runnable구현한 객체는 = 나
                thread.start();
				
				for(int i =0; i<goList.size(); i++){
					//폭탄 밟았는지 확인
					for (int a = 0; a < getMiroList.size(); a++) {
						for (int b = 0; b < getMiroList.size(); b++) {
							if (getMiroList.get(b).get(a) == 0) {
								if (Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) == a && 
									Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) == b) {
									chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
									chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
									repaint();
									JOptionPane.showMessageDialog(null, "폭탄을 밟았습니다.(game over)");
									//재시작
									dispose();
									new english_level2().setVisible(true);
									return;
								}
							}
						}
					}
					
					//오답 유무 확인
					for (int a = 0; a < garbageWordIndexList.size(); a++) {
						String wordMap = garbageWordIndexList.get(a).get("wordMap").toString();
						String wordMapArr[] = wordMap.split(",");
						
						if((wordMapArr[1] + "," + wordMapArr[0]).equals(goList.get(goList.size()-1).get("go"))){
							JOptionPane.showMessageDialog(null, "오답입니다.");
							
							goList = new ArrayList<>();
							
							chrX = 0;
							chrY = 0;
							
							moveCnt = 0;
							repaint();
							
							return;
						}
					}
					
					if((blankArr[1] + "," + blankArr[0]).equals(goList.get(goList.size()-1).get("go"))){
						goListSize = goList.size();
						if(enterChk == goListSize){
							return;
						}
						correctCnt ++;
						chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						if (correctCnt == blankCnt){
            				JOptionPane.showMessageDialog(null, "정답을 모두 맞추셨습니다.");
            				dispose();
            				new english_level2().setVisible(true);
        					return;
						} 
            			JOptionPane.showMessageDialog(null, correctCnt + " 번째 정답을 맞추셨습니다.");
            			enterChk = goList.size();
            			repaint();
    					return;
            		}else if(Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) <= -1){
            			chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "X좌표 경로 이탈.");
						//재시작
						dispose();
						new english_level2().setVisible(true);
            			return;
            		}else if(Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) <= -1){
            			chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "Y좌표 경로 이탈.");
						//재시작
						dispose();
						new english_level2().setVisible(true);
            			return;
            		}else if(Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]) > 3){
            			chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "Y좌표 경로 이탈.");
						//재시작
						dispose();
						new english_level2().setVisible(true);
            			return;
            		}else if(Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]) > 3){
            			chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
						repaint();
						JOptionPane.showMessageDialog(null, "X좌표 경로 이탈.");
						//재시작
						dispose();
						new english_level2().setVisible(true);
            			return;
            		}else{//엔터키 눌렀을때 말 위치 변경            			
//            			chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
//						chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
//						repaint();
            		}
				}
            		
            	break;
			case KeyEvent.VK_UP:
            	
            	chrY = chrY - 1;
            	
            	map.put("go", chrX + "," + chrY);
            	goList.add(map);
            	
                break;
            case KeyEvent.VK_DOWN:
            	
            	chrY = chrY + 1;
            	
            	map.put("go", chrX + "," + chrY);
            	goList.add(map);

            	break;
            case KeyEvent.VK_LEFT:

            	chrX = chrX -1;

            	map.put("go", chrX + "," + chrY);
            	goList.add(map);
            	
                break;
            case KeyEvent.VK_RIGHT:
            	chrX = chrX + 1;
            	
            	map.put("go", chrX + "," + chrY);
            	goList.add(map);
            	
                break;
            }
		}
		@Override
		public void run() {
			for(int i =moveCnt; i<goList.size(); i++){
	             try {
	                  thread.sleep(100); // 0.1초마다 실행되게 하자! (sleep메서도는 1/1000초까지 표현가능
	                                 // 1초를 표현할려면 1000을 넣어주시면되요)
	               } catch (InterruptedException e) {
	                  e.printStackTrace();
	               }
	            
	            chrX = Integer.parseInt(goList.get(i).get("go").toString().split(",")[0]);
	            chrY = Integer.parseInt(goList.get(i).get("go").toString().split(",")[1]);
	            repaint();
	            
	            moveCnt = goList.size();
	         }
		}
	}

	public void paint(Graphics g) {
		// backGround
		Image backImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/forest.png"));
		g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);

		//drawBackClose
		Image drawBackClose = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/drawBackClose.png"));
		g.drawImage(drawBackClose, 2, 27, 102, 50, this);
		
		// 단어 이미지
		if (filePath != null) {
			Image wordImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource(filePath));
			g.drawImage(wordImage, 75, 150, 300, 300, this);
		} else {
			JOptionPane.showMessageDialog(null, "이미지가 없거나 이미지 경로가 잘못됬습니다.");
		}

		// 미로 단어 font
		Font font = new Font("font", Font.BOLD, 50);
		// 단어 font
		Font font2 = new Font("font", Font.BOLD, 35);
		int startX = 6;
		int startY = 2;

		// 미로판
		for (int x = 0; x < 4; x++) {// level 1,2->4, level 3,4->5, level 5->6
			for (int y = 0; y < 4; y++) {// level 1,2->4, level 3,4->5, level 5->6
				g.drawRect((x + startX) * rectWitdh, (y + startY) * rectHeight, rectWitdh, rectHeight);
			}
		}

		//폭탄 & 단어
		//blank단어 리스트 인덱스
		int inx = 0;
		//garbage 단어 리스트 인덱스
		int inx2 = garbagePrintList.size()-1;
		for (int a = 0; a < getMiroList.size(); a++) {
			for (int b = 0; b < getMiroList.size(); b++) {
				if (getMiroList.get(b).get(a) == 0) {
					g.fillRect((a + startX) * rectWitdh, (b + startY) * rectHeight, rectWitdh, rectHeight);
				}else{
					for(int c =0 ; c<blankWordList.size(); c++){
						String str = blankWordList.get(c).get("wordMap").toString();
						if(str.equals(b + "," + a)){
							if(inx < cuttingWordList.size() ){
								g.setFont(font);
								g.drawString(cuttingWordList.get(inx), (a + startX) * (rectWitdh+2), (b + startY + 1) * (rectHeight-3));
								answerList[inx] = b + "," + a;
								inx ++;
							}
						}
					}
					for(int d =0 ; d<garbageWordIndexList.size(); d++){
						String str = garbageWordIndexList.get(d).get("wordMap").toString();
						if(str.equals(b + "," + a)){
							if(inx2 > -1){
								g.setFont(font);
								g.drawString(garbagePrintList.get(inx2), (a + startX) * (rectWitdh+2), (b + startY + 1) * (rectHeight-3));
								inx2 --;
							}
						}
					}
				}
			}
		}
		
		//단어
		int wordX = (word.length()-2) * 20;
		int wordY = 130;
		g.setFont(font2);
		g.drawString(word, 190 - wordX, wordY);

		// 말
		Image chrImage = Toolkit.getDefaultToolkit().getImage(main_page.class.getResource("../images/chr.jpg"));
		g.drawImage(chrImage, rectWitdh * (startX + chrX), rectHeight * (startY + chrY), rectWitdh, rectHeight, this);
	}
}