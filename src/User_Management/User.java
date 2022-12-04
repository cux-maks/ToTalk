package User_Management;

//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.text.SimpleDateFormat;

public class User extends UserData {

	public String name = new String("noname");
	public String last_play_friend = new String("no friend");
	public String start_date = new String("0000.00.00");
	public int play_times = 0;
	public int win_times = 0;
	public boolean gender = false;
	public double win_rate = 0;
	
	public String id = new String("gest");
	public String pw = new String("1234");
	
//	private String id = new String("gest");
//	private String pw = new String("1234");
	
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
	
	public boolean check(String _id, String _pw) {
		if (id.equals(_id) && pw.equals(_pw)) {
			return true;
		}else {
			return false;
		}
	}
	
	protected void calc_winRate() { win_rate = (double)win_times/(double)play_times; }
	
	public void register(String _id, String _pw) {
		setId(_id);
		setPw(_pw);
	}
	
	private void setId(String _id) { id = _id; }
	private void setPw(String _pw) { pw = _pw; }
	
	private void set_start_date() {
//		LocalDate now = LocalDate.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//		start_date = now.format(formatter);
//		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		// 년월일시분초 14자리 포멧
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy.MM.dd"); 
		System.out.println(fourteen_format.format(date_now));
		
		start_date = fourteen_format.format(date_now);
	}
}