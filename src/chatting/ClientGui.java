package chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import com.sun.tools.javac.Main;

import game.WordChainGame;

public class ClientGui extends JFrame implements ActionListener, Runnable{
// 클라이언트 화면용
Container container = getContentPane();

Image BImg = new ImageIcon("./src/chatting/ToTalk_BackGround.png").getImage();

JTextArea textArea = new JTextArea(){
    { setOpaque( false ) ; }
    public void paintComponent(Graphics g){
        g.drawImage(BImg,0,0,null);       //이미지 그리기
        super.paintComponent(g);
    }
};



JScrollPane scrollPane = new JScrollPane(textArea);
JTextField textField = new JTextField();
// 통신용
Socket socket;
PrintWriter out;
BufferedReader in;
String str;

Font font1 = new Font("메이플스토리", Font.BOLD,30);//대제목
Font font2 = new Font("빙그레 메로나체", Font.BOLD,15);//중제목
Font font3 = new Font("빙그레 메로나체", Font.PLAIN,15);//본문
Font font4 = new Font("S-Core Dream 1 가늘게", Font.PLAIN,10);//캡션

Color color1 = new Color(0XD1CEFF);//보라색
Color color2 = new Color(0X71A6F0 );//진한 파랑
Color color3 = new Color(0X75D6E0);//연한 파랑
Color color4 = new Color(0X74F7C1);//민트
Color color5 = new Color(0X7EDE85);//초록


public ClientGui(String ip, int port) {
   // frame 기본 설정
	//super("Main Frame");
	setIconImage(Toolkit.getDefaultToolkit().getImage(ClientGui.class.getResource("ToTalk.png")));

	JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);
    //menuBar.setBackground(color2);
    
    //JMenu mnNewMenu = new JMenu("Menu");
    //menuBar.add(mnNewMenu);
    //mnNewMenu.setFont(font2);
    //mnNewMenu.setBackground(color1);
    //mnNewMenu.setBackground(color2);
    
    //JMenuItem mntmLogin = new JMenuItem("Login");
    //mnNewMenu.add(mntmLogin); 
    //JMenuItem mntmLogout = new JMenuItem("Logout");
    //mnNewMenu.add(mntmLogout);
    //getContentPane().setLayout(null);
    
    JMenu mnGame = new JMenu("Game");
    menuBar.add(mnGame);
    mnGame.setFont(font2);
    //mnGame.setBackground(color2);
    
    class MenuActionListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
    	   out.println("끝말잇기 게임을 시작합니다.");
//          String name1 = new String("유저 1");
//          String name2 = new String("유저 2");
//          WordChainGame gameStart = new WordChainGame(name1, name2);
          
       }
    }
    
    JMenuItem mnWordChainGame = new JMenuItem("끝말잇기");
    mnGame.add(mnWordChainGame); 
    mnWordChainGame.addActionListener(new MenuActionListener());
	   
//    JMenu mnGame = new JMenu("Game");
//    menuBar.add(mnGame);
//    mnGame.setFont(font2);
//    //mnGame.setBackground(color2);
//    
//    JMenuItem mnWordChainGame = new JMenuItem("끝말잇기");
//    mnGame.add(mnWordChainGame); 
    
//    mnWordChainGame.addActionListener(new MenuActionListener());
//	
//    class MenuActionListener implements ActionListener {
//    	public void actionPerformed(ActionEvent Ev) {
//    		
//    	}
//    }
    
	setTitle("To Talk");
    setSize(410, 750);
    setLocation(200, 50);
    setResizable(false);//창의 크기를 변경하지 못하게
    init();
    start();
    setVisible(true);
    // 통신 초기화
    initNet(ip, port);
    System.out.println("ip = " + ip);
}


// 통신 초기화
private void initNet(String ip, int port) {
   try {
      // 서버에 접속 시도
      socket = new Socket(ip, port);
      // 통신용 input, output 클래스 설정
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // ture : auto flush 설정
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
   } catch (UnknownHostException e) {
      System.out.println("IP 주소가 다릅니다.");
      //e.printStackTrace();
   } catch (IOException e) {
      System.out.println("접속 실패");
      //e.printStackTrace();
   }
   // 쓰레드 구동
   Thread thread = new Thread(this); // run 함수 -> this
   thread.start();
}
private void init() {
   container.setLayout(new BorderLayout());
   container.add("Center", scrollPane);
   container.add("South", textField);
}
private void start() {
   setDefaultCloseOperation(EXIT_ON_CLOSE);
   textField.addActionListener(this);
}
// 응답 대기
// -> 서버로부터 응답으로 전달된 문자열을 읽어서, textArea에 출력하기
@Override
//public void paint(Graphics T) {//그리는 함수
//	T.drawImage(background, 0, 0, null);//background를 그려줌
//}

public void run() {
   while(true) {
      try {
         str = in.readLine();
         textArea.append(str + "\n");
         textArea.setFont(font3);
         //textArea.setForeground(color4);//글자색변경
         textArea.setBackground(color1);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}

@Override
public void actionPerformed(ActionEvent e) {
   // textField의 문자열을 읽어와서 서버로 전송함
   str = textField.getText();
   out.println(str);
   // textField 초기화
   textField.setText("");
}
}