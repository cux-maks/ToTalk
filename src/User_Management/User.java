package User_Management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User extends UserData {

	public String name = new String("noname");
	public String last_play_friend = new String("no friend");
	public String start_date = new String("0000.00.00");
	public int play_times = 0;
	public int win_times = 0;
	public boolean gender = false;
	public double win_rate = 0;
	
	private String id = new String("gest");
	private String pw = new String("1234");
	
	protected String group = new String("independent");
	
	public User() { 
		set_start_date();
	}
	
	public User(String _name) {
		setName(_name);
		set_start_date();
	}
	
	public User(String _name, int _age) {
		setName(_name);
		setAge(_age);
		set_start_date();
	}
	
	public User(String _name, int _age, String _tel) {
		setName(_name);
		setAge(_age);
		setTel(_tel);
		set_start_date();
	}
	
	public User(String _name, int _age, String _tel, boolean _gender) {
		setName(_name);
		setAge(_age);
		setTel(_tel);
		setGender(_gender);
		set_start_date();
	}
	
	public void setName(String _name) { name = _name; }
	public void setTel(String _tel) { tel = _tel; }
	public void setAge(int _age) { age = _age; }
	public void setGender(boolean _gender) { gender = _gender; }
	
	public String getName() { return name; }
	public String getTel() { return start_date; }
	public int getAge() { return age; }
	public boolean getGender() { return gender; }
	
	protected void calc_winRate() { win_rate = (double)win_times/(double)play_times; }
	
	public void register(String _id, String _pw) {
		setId(_id);
		setPw(_pw);
	}
	
	private void setId(String _id) { id = _id; }
	private void setPw(String _pw) { pw = _pw; }
	
	private void set_start_date() {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.mm.dd");
		start_date = now.format(formatter);
	}
}