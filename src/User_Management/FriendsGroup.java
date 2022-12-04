package User_Management;

import java.util.ArrayList;

public class FriendsGroup {
	
	 String group_name = new String();
	 ArrayList Friend_list = new ArrayList();
	 
	 int friend_num = 0;
	 
	 public FriendsGroup(String _name) {
		 group_name = _name;
	 }
	 
	 public void append(Friend _friend) {
		 Friend_list.add(friend_num, _friend);
		 friend_num += 1;
	 }
	 
	 public void remove(Friend _friend) {
		 boolean result = Friend_list.remove(_friend);
		 if (result){
			 System.out.println("삭제되었습니다.");
		 }else {
			 System.out.println("해당 친구가 존재하지 않습니다.");
		 }
	 }
}