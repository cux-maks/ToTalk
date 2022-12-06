package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import main.main_Data;
import java.awt.Toolkit;

import javax.swing.*;

import User_Management.User;

@SuppressWarnings("serial")
public class JoinScreen extends JFrame{
	
	Font font1 = new Font("메이플스토리", Font.BOLD,30);//대제목
	Font font2 = new Font("빙그레 메로나체", Font.BOLD,15);//중제목
	Font font3 = new Font("빙그레 메로나체", Font.PLAIN,15);//본문
	Font font4 = new Font("S-Core Dream 1 가늘게", Font.PLAIN,10);//캡션

	Color color1 = new Color(0XD1CEFF);//보라색
	Color color2 = new Color(0X71A6F0 );//진한 파랑
	Color color3 = new Color(0X75D6E0);//연한 파랑
	Color color4 = new Color(0X74F7C1);//민트
	Color color5 = new Color(0X7EDE85);//초록

	String choice = null;
	
	public JoinScreen() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JoinScreen.class.getResource("ToTalk.png")));
		
		setTitle("회원관리 화면");
		JPanel title = new JPanel();

		// title 컨테이너에 들어갈 컴포넌트
		JLabel newUse = new JLabel("회원가입", JLabel.CENTER);
		
		newUse.setForeground(Color.BLACK);
		title.setBackground(color1);
		//newUse.setBackground(color1);
		newUse.setFont(font1);
		
		//title.setBackground(color1);
		title.add(newUse);
		
		
		
		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");
		join.setBackground(Color.LIGHT_GRAY);
		join.setBorderPainted(false);//테두리 제거
		join.setFont(font2);
		cancel.setBackground(Color.LIGHT_GRAY);
		cancel.setBorderPainted(false);//테두리 제거
		cancel.setFont(font2);
		
		JTextField id = new JTextField(10);
		JPasswordField pwd = new JPasswordField(10);
		JTextField name = new JTextField(10);
		JTextField phone = new JTextField(10);
		
		JRadioButton client = new JRadioButton("교수");
		JRadioButton manager = new JRadioButton("학생");
		JRadioButton etc = new JRadioButton("기타");
		client.setFont(font2);
		manager.setFont(font2);
		etc.setFont(font2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(client); bg.add(manager); bg.add(etc);
		
		// radio panel
		JPanel radioPanel = new JPanel();
		radioPanel.add(client);
		radioPanel.add(manager);
		radioPanel.add(etc);
		
		// form panel
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel idP = new JLabel("아이디 : ",JLabel.CENTER);
		idP.setFont(font2);
		idPanel.add(idP);
		idPanel.add(id);
		idPanel.setFont(font2);
		
		
		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel pwP = new JLabel("비밀번호 : ",JLabel.CENTER);
		pwP.setFont(font2);
		pwdPanel.add(pwP);
		pwdPanel.add(pwd);
		
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel nameP = new JLabel("이    름 : ",JLabel.CENTER);
		nameP.setFont(font2);
		namePanel.add(nameP);
		namePanel.add(name);
		
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel phoneP = new JLabel("연 락 처 : ",JLabel.CENTER);
		phoneP.setFont(font2);
		phonePanel.add(phoneP);
		phonePanel.add(phone);
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(namePanel);
		formPanel.add(phonePanel);
		
		// radio + form panel
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(radioPanel);
		contentPanel.add(formPanel);
		
		// button panel
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		//panel.setBackground(color1);//젤 아래 버튼 색상
		panel.add(join);
		panel.add(cancel);

		setLayout(new BorderLayout());
		
		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		
		
		setBounds(200, 200, 300, 315);

		setResizable(false);  // 화면 크기 고정하는 작업
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		
		// 이벤트 처리
		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String myId = id.getText();
				String myPwd = new String(pwd.getPassword());
				String myName = name.getText();
				String myPhone = phone.getText();
				
				if(client.isSelected()) {
					choice = client.getText();
				}else if(manager.isSelected()) {
					choice = manager.getText();
				}else if(etc.isSelected()) {
					choice = etc.getText();
				}
				
				JOptionPane.showMessageDialog
					(null, "아이디 : "+myId+", 비밀번호 : "+myPwd+
					", 이 름 : "+myName+", 연락처 : "+myPhone+
					", 가입유형 : "+choice);
				
				
				User new_user = new User(myName);
				new_user.register(myId, myPwd);
				main_Data a = new main_Data();
				a.addUser(new_user);
				
			}
		});
		
		
		// 취소 버튼을 클릭했을 때 이벤트 처리
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new LoginScreen();
				dispose();
				
			}
		});
	}
}