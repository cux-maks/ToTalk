package User_Management;

abstract public class UserData {

	public String name;
	public String start_date;
	public int age;
	public boolean gender;
	public double win_rate;
	
	protected String tel;
	protected String group;
	
	abstract public void setName(String _name);
	abstract public void setTel(String _tel);
	abstract public void setAge(int _age);
	abstract public void setGender(boolean _gender);
	
	abstract public String getName();
	abstract public String getTel();
	abstract public int getAge();
	abstract public boolean getGender();
	
	abstract protected void calc_winRate();
	
}