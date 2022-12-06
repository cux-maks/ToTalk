package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.awt.Toolkit;

import javax.swing.*;

import chatting.ChatServer;
import chatting.ClientGui;

import main.main_Data;




@SuppressWarnings("serial")
public class LoginScreen extends JFrame {
	
	Font font1 = new Font("메이플스토리", Font.BOLD,30);//대제목
	Font font2 = new Font("빙그레 메로나체", Font.BOLD,15);//중제목
	Font font3 = new Font("빙그레 메로나체", Font.PLAIN,15);//본문
	Font font4 = new Font("S-Core Dream 1 가늘게", Font.PLAIN,10);//캡션

	Color color1 = new Color(0XD1CEFF);//보라색
	Color color2 = new Color(0X71A6F0 );//진한 파랑
	Color color3 = new Color(0X75D6E0);//연한 파랑
	Color color4 = new Color(0X74F7C1);//민트
	Color color5 = new Color(0X7EDE85);//초록
	Color color6 = new Color(0XAAC9F7);//new!


	static String myId = new String("");
	static String myPwd = new String("");
	static String IP = new String("");
	
	public LoginScreen() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("ToTalk.png")));
		
		setTitle("ToTalk 로그인");
		JPanel title = new JPanel();
		
		// title 컨테이너에 들어갈 컴포넌트를 만들어 보자.
		JLabel login = new JLabel("로그인", JLabel.CENTER);

		// Color color = new Color(5, 0, 153)

		login.setForeground(Color.BLACK);
		title.setBackground(color1);
	
		login.setFont(font1);
		
		// 컴포넌트를 title 컨테이너에 올려 주자.
		title.add(login);
		
		
		
		
		JPanel jp1 = new JPanel();
		GridLayout Grid=new GridLayout(4,3);
		jp1.setLayout(Grid);
		jp1.setOpaque(false);
		
		JPanel idPanel = 
			new JPanel(new FlowLayout(FlowLayout.CENTER));
		//idPanel.setSize(50,10);
		JLabel jlb1 = new JLabel("아이디  ", JLabel.CENTER);
		jlb1.setFont(font2);
		idPanel.add(jlb1);
		idPanel.setOpaque(false);
		
		JPanel idPanel2 = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField jtf1 = new JTextField(15);
		idPanel2.add(jtf1);
		idPanel2.setOpaque(false);
		
		
		jp1.add(idPanel); jp1.add(idPanel2);
		
		
		JPanel pwdPanel = 
			new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel jlb2 = new JLabel("비밀번호  ", JLabel.CENTER);
		jlb2.setFont(font2);
		
		JPanel pwdPanel2 = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPasswordField jtf2 = new JPasswordField(15);
		
		
		JPanel IPPanel1 = 
				new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel IP1 = new JLabel("접속 IP  ", JLabel.CENTER);
		IP1.setFont(font2);
		
		IPPanel1.add(IP1);
		JPanel IPPanel2 = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextField IP2 = new JTextField(15);
		
		
		IPPanel2.add(IP2);
		
		pwdPanel.add(jlb2); pwdPanel2.add(jtf2);
		
		jp1.add(pwdPanel); jp1.add(pwdPanel2);
		jp1.add(IPPanel1); jp1.add(IPPanel2);
		
		
		JPanel loginPanel = 
				new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jLogin = new JButton("로그인");
		jLogin.setBackground(Color.LIGHT_GRAY);//버튼 색
		jLogin.setFont(font2);
		//jLogin.setForeground(Color.white);//글자색
		jLogin.setBorderPainted(false);//테두리 제거
		
		JPanel joinPanel = 
				new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton join = new JButton("회원가입");
		join.setBackground(Color.LIGHT_GRAY);//버튼 색
		join.setFont(font2);
		//join.setForeground(Color.white);//글자색
		join.setBorderPainted(false);//테두리 제거
		
		
		loginPanel.add(jLogin); joinPanel.add(join);
		
		jp1.add(loginPanel); jp1.add(joinPanel);
		
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		jp2.add(jp1);
		
		setLayout(new BorderLayout());
		
		add(title, BorderLayout.NORTH);
		add(jp2, BorderLayout.CENTER);
		
		setBounds(200, 200, 400, 250);
		
		setResizable(false);  // 화면 크기 고정하는 작업
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		
		// 이벤트 처리
		jLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				myId = jtf1.getText();
				myPwd = new String(jtf2.getPassword());
				IP = IP2.getText();
				
				JOptionPane.showMessageDialog
					(null, "아이디 : "+myId+", 비밀번호 : "+myPwd+", 접속 IP : "+IP);
				
				main_Data a = new main_Data();
				if (a.login(myId, myPwd)) {
				
					if(IP == "") {
					
						try {
							InetAddress ia = InetAddress.getLocalHost();
							String ip_str = ia.toString();
							String ip = ip_str.substring(ip_str.indexOf("/") + 1);
							//System.out.println("test" + ip);
							new ClientGui(ip, 5420);
						} catch (UnknownHostException E) {
							E.printStackTrace();
						}
					
					}else {
						new ClientGui(IP, 5420);
					}
				}
				
			}
		});
		
		
		join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new JoinScreen();
				dispose();  // 현재의 frame을 종료시키는 메서드.
				
			}
		});

		
	}
	
	static public String getId() {
		return myId;
	}
	
}