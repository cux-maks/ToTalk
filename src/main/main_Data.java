package main;

import User_Management.User;
import java.util.ArrayList;

public class main_Data {
	static public ArrayList<User> Users = new ArrayList<User>();
//  public int user_cnt_main = 0;

	public main_Data() {}
	
	public void addUser(User U) {
		Users.add(Users.size(), U);
//		System.out.println(Users.get(Users.size() - 1).name);
	}
	
	static public boolean login(String _id, String _pw) {
		for(int i = 0; i < Users.size(); i++) {
			User temp = Users.get(i);
//			System.out.println(temp.id + "   " + temp.pw);
			if (temp.check(_id, _pw) == true) {
//				System.out.println("Yeahhhh");
				return true;
			}
		}
//		System.out.println("Nooooooooooo");
		return false;
	}
	
}
