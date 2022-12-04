package game;

import java.util.Scanner;
import java.util.ArrayList;

class Player{
 
	Scanner scan = new Scanner(System.in);
	public String name;
	public String word;
	
	public String writeWord() {
		word = scan.next();
		return word;
	}
	
	public boolean checkSuccess (char lastChar) {
		if (lastChar == word.charAt(0)) return true;
		else return false;
	}
	
}

public class WordChainGame {
	
	public WordChainGame(String name_user1, String name_user2) {
		String startword = "아버지";
        ArrayList word_list = new ArrayList();
        int word_cnt = 0;
        
        System.out.println("끝말잇기 게임을 시작합니다...");
        Scanner scan = new Scanner(System.in);
        int playernum = 2;
        
        Player[] play = new Player[playernum];
        
        for(int i = 0; i<playernum; i++) play[i] = new Player();
      
        play[0].name = name_user1;
        play[1].name = name_user2;
        
        System.out.printf("시작하는 단어는 %s입니다.", startword);
 
        int i = 0, j = 0;
        while(true) {
            
            i = j%playernum; 
            
            int lastIndex = startword.length() - 1;
            char lastChar = startword.charAt(lastIndex);
            
            System.out.println(play[i].name + ">> ");
            play[i].writeWord();
            
            boolean counting = play[i].checkSuccess(lastChar);
            
            if(counting==false) {
            	System.out.println("첫 글자가 다릅니다.");
                System.out.println(play[i].name + "님이 졌습니다.");
                break;
            }else {
            	if (word_list.contains(play[i].word)) {
            		System.out.println("이미 사용된 단어입니다.");
            		System.out.println(play[i].name + "님이 졌습니다.");
            		break;
            	}else {
            		word_list.add(word_cnt, play[i].word);
            		word_cnt += 1;
            	}
            }
           startword = play[i].word;
            j++;
        }
        scan.close();
        	
	}
	
	public static void main(String[] args) {
		 
	}
}