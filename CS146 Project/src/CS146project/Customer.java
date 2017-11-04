package CS146project;
import java.io.FileNotFoundException;


import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Customer implements Comparable<Customer>{
	// declare the variables
	private String name;
	private String phone;
	private int[] appointments;

	// constructors
	public Customer(String name){
		this.name = name;
	}
	public Customer(String name, String time){
		this.name = name;
		this.time = time;
	}
	private String time;
	// access and mutator methods for
	// class variables
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getPhone() {
		return name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}
	@Override
	public int compareTo(Customer other){
		if(this.name.equals( other.getName())){
			return 0;
		}
		else if(this.name ==  other.getName()){
			return 0;
		}		
		return -1;
	}

	// toString() method
	public String toString() {
		return getName() + "\t" + getTime();
	}


	
}