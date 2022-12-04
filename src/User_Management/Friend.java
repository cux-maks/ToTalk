package User_Management;

public class Friend extends UserData{
	 public String name = new String("friend");
	 public int vs_play_times = 0;
	 public int win_times = 0;
	 
	 protected void increase_VsPlayTimes() {
		vs_play_times ++;
	 }

	 public Friend() {
		 
	 }
	 
	 public Friend(String _name) {
			setName(_name);
		}
		
		public Friend(String _name, int _age) {
			setName(_name);
			setAge(_age);
		}
		
		public Friend(String _name, int _age, String _tel) {
			setName(_name);
			setAge(_age);
			setTel(_tel);
		}
		
		public Friend(String _name, int _age, String _tel, boolean _gender) {
			setName(_name);
			setAge(_age);
			setTel(_tel);
			setGender(_gender);
		}
	 @Override
	 public void setName(String _name) { name = _name; }
	 public void setTel(String _tel) { tel = _tel; }
	 public void setAge(int _age) { age = _age; }
	 public void setGender(boolean _gender) { gender = _gender; }
	   
	 public String getName() { return name; }
	 public String getTel() { return start_date; }
	 public int getAge() { return age; }
	 public boolean getGender() { return gender; }

	 @Override
	 protected void calc_winRate() { win_rate = (double)win_times/(double)vs_play_times; }
}