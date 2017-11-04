package CS146project;
import treepackage.*;

import java.awt.BorderLayout;
//import the required classes
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
//JFrame class that implements action listener interface
public class QueueScheduling extends JFrame implements ActionListener {
	// declare the components and variables
	
	
	
	JPanel jpan, jpan1;
	JLabel label1, label2, label3, label4;
	JButton insert, remove, show, exit, customer, checkIn;
	Queue queCustomer;
	Queue<Customer> queWalkIn;
	String name, startTime, endTime;
	int id;
	int num;
	fileWriter t = new fileWriter();
	SearchTreeInterface<Customer> tree = new BinarySearchTree();

	JTextArea display1, display2;	

	// constructor
	public QueueScheduling() {
		// initialize the Component objects
		queCustomer = new LinkedList();
		queWalkIn = new LinkedList();

		setLayout(new GridLayout(2, 1));
		jpan1 = new JPanel(new FlowLayout());
		jpan = new JPanel(new GridLayout(3, 2));

		// setSize(400, 300);
		display1 = new JTextArea(10, 20);
		display2 = new JTextArea(10, 20);
		checkIn = new JButton("Check-In");
		customer = new JButton("Customer");

		insert = new JButton("Appointment");
		remove = new JButton("Remove");
		show = new JButton("switch");
		// add the components to the panel
		jpan1.add(display1);
		jpan1.add(display2);
		jpan1.add(show);
		// add the component panel to another panel
		// add the main panel

		jpan.add(checkIn, new GridLayout(1, 0));
		jpan.add(insert, new GridLayout(1, 1));
		jpan.add(customer, new GridLayout(2, 0));
		jpan.add(remove, new GridLayout(2, 1));

		add(jpan1, new GridLayout(1, 0));
		add(jpan, new GridLayout(2, 0));
		// add action listeners to the buttons
		insert.addActionListener(this);
		remove.addActionListener(this);
		customer.addActionListener(this);
		checkIn.addActionListener(this);
		show.addActionListener(this);
	}

	// @Override actionPerformed method
	public void actionPerformed(ActionEvent ae) {
		// if "Add" button is pressed prompt
		// the information and add the CustomerNames
		// object to the queue
		if (insert == ae.getSource()) {
			AppointmentCalendar.display();
			
			/*
			// create a panel containing labels and buttons
			JPanel insertJPan = new JPanel(new GridLayout(3, 3));
			JLabel jlAppTime = new JLabel("Appointment Time");
			JTextField jtxappTime = new JTextField();
			JLabel custName = new JLabel("Enter Customer Name: ");
			JTextField jtxName = new JTextField();
			JLabel custNum = new JLabel("Enter Customer Number: ");
			JTextField jtxNum = new JTextField();
			
			// add the components to the panel
			insertJPan.add(jlAppTime);
			insertJPan.add(jtxappTime);
			insertJPan.add(custName);
			insertJPan.add(jtxName);
			insertJPan.add(custNum);
			insertJPan.add(jtxNum);
			insertJPan.setVisible(true);
			// add the panel into the JOptionPane
			JOptionPane.showMessageDialog(null, insertJPan,
					"Please enter the details ",
					JOptionPane.INFORMATION_MESSAGE);
			

			// get the values
			id = Integer.parseInt(jtxappTime.getText());
			num = Integer.parseInt(jtxNum.getText());
			name = jtxName.getText();
			// get the time of creation
			Date d = new Date();
			startTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
					.format(d);
			// create the CustomerNames object
			Customer customerInfo;
			
				customerInfo = new Customer(id, name, startTime, num);
				
				queCustomer.add(customerInfo);
				tree.add(customerInfo);
				
				
				t.add(customerInfo);
				
				try {
					t.write();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			// add object to the queue
			
			
			String str = "Appointment\tNAME\tTIME START\n\n";
			Iterator it = queCustomer.iterator();
			// get all the items of the list
			while (it.hasNext()) {
				str += it.next() + "\n";
			}
			// display the items
			display1.setText(str);
			
		}
		

		
		
		// if "Remove" button is pressed
		else if (remove == ae.getSource()) {
			// call the remove method of queue and
			// store the returned object into
			// CustomerNames object
			JFrame remove = new JFrame("REMOVE MENU");
			JPanel reb = new JPanel();
			reb.setLayout(new BorderLayout());
			JLabel l1 = new JLabel("Select which to remove: ");
			JButton b1 = new JButton("Check-In");
			JButton b2 = new JButton("Appointment");
						
			reb.add(b2, BorderLayout.LINE_START);
			reb.add(b1 , BorderLayout.LINE_END);
			reb.add(l1, BorderLayout.NORTH);
			
			remove.add(reb);
			remove.setDefaultCloseOperation(HIDE_ON_CLOSE);
			remove.setVisible(true);
			remove.setSize(300, 200);
			remove.pack();
			
			b1.addActionListener(new ActionListener() {
				
				  public void actionPerformed(ActionEvent e) { 
					  
					  Customer cName = (Customer) queWalkIn.remove();
					  // create a Date object
			Date end = new Date();
			// get the time format
			endTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
					.format(end);
			// calculate the elapsed time
			String elapse = getElapsedTime(cName.getTime(), endTime);
			// display the time
			JOptionPane.showMessageDialog(null,
					"Appointment: " + cName.getAppointment() + "\nStart time: "
							+ cName.getTime() + "End time: " + endTime
							+ "\nelapsed time: " + elapse);
				  }
			});
			
			b2.addActionListener(new ActionListener() {
						
					  public void actionPerformed(ActionEvent e) { 
						  Customer cName = (Customer) queCustomer.remove();
						  // create a Date object
				Date end = new Date();
				// get the time format
				endTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
						.format(end);
				// calculate the elapsed time
				String elapse = getElapsedTime(cName.getTime(), endTime);
				// display the time
				JOptionPane.showMessageDialog(null,
						"Appointment: " + cName.getAppointment() + "\nStart time: "
								+ cName.getTime() + "End time: " + endTime
								+ "\nelapsed time: " + elapse);
						  
					  }
			});
						  
			

			// create the header of the table
			String str = "Appointment\tNAME\tTIME START\n\n";
			// call the iterator of queCustomer
			Iterator it = queCustomer.iterator();
			// get all the items of the list
			while (it.hasNext()) {
				str += it.next() + "\n";
			}
			// display the items
			display1.setText(str);
		}
		
		
		// if "Display" button is pressed
		else if (customer == ae.getSource()) {
			
			JFrame customer = new JFrame();
			
			
			
			JPanel insertJPan = new JPanel(new GridLayout(5, 5));
			JLabel jlSearch = new JLabel("Search Name: ");
			final JTextField jtxSearch = new JTextField();
			JButton searchButton = new JButton("Search"); 
			
			// add the components to the panel
			insertJPan.add(jlSearch);
			insertJPan.add(jtxSearch);
			insertJPan.add(searchButton);
			insertJPan.setVisible(true);
			
			customer.add(insertJPan);
			customer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			customer.setSize(300, 300);
			customer.setVisible(true);
			customer.pack();
			customer.setLocationRelativeTo(null);
			
			
			searchButton.addActionListener(new ActionListener() {
				
				  public void actionPerformed(ActionEvent e) { 
					  
					String search = jtxSearch.getText();
				    Customer s = new Customer(search);
			
				    if (tree.Search(s)){
				
				    	JOptionPane.showMessageDialog(null, "result: " + tree.getSearchEntry());
				 }
					  
			  } 
		} );

			
			// create the header of the table
			String str = "Appointment\tNAME\tTIME START\n\n";
			// call the iterator of queCustomer
			Iterator it = queCustomer.iterator();
			// get all the items of the list
			while (it.hasNext()) {
				str += it.next() + "\n";
			}

			// display the items
			display1.setText(str);
		}
		
		else if(show == ae.getSource()){
			System.out.println("here");
		}
				
	     else if(checkIn == ae.getSource()){
	    	 
	    	 //System.out.println("Im here");

	            // create a panel containing labels and buttons

	            JPanel checkInJPan = new JPanel(new GridLayout(2, 2));

	            JLabel checkinCustName = new JLabel("Enter Walk-In Customer Name: ");

	            JTextField jtfCheckInName = new JTextField();

	            // add the components to the panel

	            checkInJPan.add(checkinCustName);

	            checkInJPan.add(jtfCheckInName);

	            checkInJPan.setVisible(true);

	            // add the panel into the JOptionPane

	            JOptionPane.showMessageDialog(null, checkInJPan,

	                    "please enter the details",

	                    JOptionPane.INFORMATION_MESSAGE);
	     

	            // get the value

	            name = jtfCheckInName.getText();

	            // get the time of creation

	            Date a = new Date();

	            startTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)

	                    .format(a);

	            // create the CustomerNames object

	            Customer WalkIn = new Customer(id, name, startTime);

	            // add object to the queue

	            queWalkIn.add(WalkIn);
	            tree.add(WalkIn);

	            // create the header of the table

	            String walkinStr = "Check-in\tNAME\tTIME START\n\n";

	            // call the iterator of queCustomer

	            Iterator walkinIt = queWalkIn.iterator();

	            // get all the items of the list

	            while (walkinIt.hasNext()) {

	                walkinStr += walkinIt.next() + "\n";

	            }
	            // display the items

	            display2.setText(walkinStr);
	        }
		
	}
	
	
	// calculation of elapse time
	public static String getElapsedTime(String starting, String ending) {
		String startToken[] = starting.split(":");
		String endToken[] = ending.split(":");
		int hours = Math.abs(Integer.parseInt(startToken[0])
				- Integer.parseInt(endToken[0]));
		int minutes = Math.abs(Integer.parseInt(startToken[1])
				- Integer.parseInt(endToken[1]));
		int seconds = Math.abs(Integer.parseInt(startToken[2])
				- Integer.parseInt(endToken[2]));
		return hours + ":" + minutes + ":" + seconds;
	}

	// main method
	/*
	public static void main(String args[]) {
		QueueScheduling queSchd = new QueueScheduling();
		queSchd.setVisible(true);
		queSchd.setSize(400, 400);
		queSchd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queSchd.setTitle("Queue Waiting List GUI"); // Queue Interface
													// Implementation
		queSchd.pack();
		queSchd.setLocationRelativeTo(null);
	}
	*/
	
	

//;


