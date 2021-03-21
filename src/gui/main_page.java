package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Toolkit;

public class main_page extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_page frame = new main_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_page() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 558);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ImageIcon Icon1 = new ImageIcon(main_page.class.getResource("../images/korean.png"));
	    Image originImg1 = Icon1.getImage(); 
	    Image changedImg1= originImg1.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon1 = new ImageIcon(changedImg1);
	    contentPane.setLayout(null);
	    JButton button1 = new JButton(newIcon1);
	    button1.setBounds(156, 196, 96, 101);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new korean_level().setVisible(true);
			}
		
		});
		contentPane.add(button1);
		
		
		ImageIcon Icon2 = new ImageIcon(main_page.class.getResource("../images/english.png"));
	    Image originImg2 = Icon2.getImage(); 
	    Image changedImg2 = originImg2.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon2 = new ImageIcon(changedImg2);
	    JButton button2 = new JButton(newIcon2);
	    button2.setBounds(357, 196, 96, 101);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				dispose();
				new english_level().setVisible(true);
			}
		});
		contentPane.add(button2);
		
		ImageIcon Icon3 = new ImageIcon(main_page.class.getResource("../images/math.png"));
	    Image originImg3 = Icon3.getImage(); 
	    Image changedImg3 = originImg3.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	    ImageIcon newIcon3 = new ImageIcon(changedImg3);
		JButton button3 = new JButton(newIcon3);
		button3.setBounds(551, 196, 96, 101);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new math_level().setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(button3);
		
		ImageIcon Icon7 = new ImageIcon(main_page.class.getResource("../images/exit.png"));
	    Image originImg7 = Icon7.getImage(); 
	    Image changedImg7 = originImg7.getScaledInstance(50, 50, Image.SCALE_SMOOTH );
	    ImageIcon newIcon7 = new ImageIcon(changedImg7);
		JButton exit = new JButton(newIcon7);
		exit.setBounds(51, 0, 52, 51);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(exit);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(main_page.class.getResource("../images/forest.png")));
		lblNewLabel.setBounds(0, 0, 772, 516);
		contentPane.add(lblNewLabel);
		
	}
}
