package CS146project;
import treepackage.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import treepackage.BinarySearchTree;
import treepackage.SearchTreeInterface;

public class MainMenu {
    JTable tblTimes;
    JFrame frmMain;
    Container pane;
    DefaultTableModel mtblTimes; //Table model
    JScrollPane stblTimes, sWalkins; //The scrollpanes       
    JPanel pnlTimes, pnlWalkins;
    JButton btnAppointments, btnRemove, btnCustomer, btnCheckIn;    
    JTextArea walkinList;
    
    AppointmentSlot[] timeSlots;
    Queue<Customer> queWalkIn;
    SearchTreeInterface<Customer> tree = new BinarySearchTree();
    
	String name, startTime, endTime;
	int id;
	int num;
    
    // array to store the appointment times in a day
    String[] times = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am"
			 ,"10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm"
			 ,"1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm"
			 ,"3:30pm", "4:00pm", "5:00pm", "5:30pm", "6:00pm"
			 ,"6:30pm","7:00pm"}; 
	  
	  // constructor
	  MainMenu(){
			// initialize the Component objects
			queWalkIn = new LinkedList();
			
	        //Look and feel
	        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	        catch (ClassNotFoundException e) {}
	        catch (InstantiationException e) {}
	        catch (IllegalAccessException e) {}
	        catch (UnsupportedLookAndFeelException e) {}

	        //Prepare frame
	        frmMain = new JFrame ("Appointment Managment System"); //Create frame
	        frmMain.setSize(660, 600); //Set size
	        pane = frmMain.getContentPane(); //Get content pane
	        pane.setLayout(null); //Apply null layout
	        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

	        //Create controls     
	        mtblTimes = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	        tblTimes = new JTable(mtblTimes);
	        stblTimes = new JScrollPane(tblTimes);
	        walkinList = new JTextArea();
	        sWalkins = new JScrollPane(walkinList);
	        
	        pnlTimes = new JPanel(null);
	        pnlWalkins = new JPanel(null);
	        
	        btnAppointments = new JButton("Appointments");
	        btnRemove = new JButton("Remove");
	        btnCustomer = new JButton("Customer");
	        btnCheckIn = new JButton("Check-In");

	        //Set borders
	        pnlTimes.setBorder(BorderFactory.createTitledBorder("Today's Appointments"));
	        pnlWalkins.setBorder(BorderFactory.createTitledBorder("Walk-In List"));
	         
	        //Register action listeners
	        btnRemove.addActionListener(new btnRemove_Action());
	        btnAppointments.addActionListener(new btnAppointments_Action());
	        btnCustomer.addActionListener(new btnCustomer_Action());
	        btnCheckIn.addActionListener(new btnCheckIn_Action());
	         
	        //Add controls to pane
	        pane.add(pnlTimes);
	        pane.add(pnlWalkins);
	        pane.add(btnAppointments);
	        pane.add(btnRemove);
	        pane.add(btnCustomer);
	        pane.add(btnCheckIn);	        
	        pnlTimes.add(stblTimes);	        
	        pnlWalkins.add(sWalkins);
	         
	        //Set bounds
	        pnlTimes.setBounds(0, 0, 320, 480);
	        pnlWalkins.setBounds(330,0,320,480);
	        stblTimes.setBounds(10, 30, 300, 430);
	        sWalkins.setBounds(10,30,300,430);
	        btnAppointments.setBounds(10,500,140,40);
	        btnCustomer.setBounds(160,500,140,40);
	        btnCheckIn.setBounds(350,500,140,40);
	        btnRemove.setBounds(500,500,140,40);
	         
	        //Make frame visible
	        frmMain.setResizable(false);
	        frmMain.setVisible(true);
	                  
	        //Add headers
	        mtblTimes.addColumn("Time");
	        mtblTimes.addColumn("Customer");
	         
	        tblTimes.getParent().setBackground(tblTimes.getBackground()); //Set background

	        //No resize/reorder
	        tblTimes.getTableHeader().setResizingAllowed(false);
	        tblTimes.getTableHeader().setReorderingAllowed(false);

	        //Single cell selection
	        tblTimes.setColumnSelectionAllowed(true);
	        tblTimes.setRowSelectionAllowed(true);
	        tblTimes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	        //Set row/column count
	        tblTimes.setRowHeight(38);
	        mtblTimes.setColumnCount(2);
	        mtblTimes.setRowCount(22);

	        //Populate table
	        int timeSlotCount = times.length;
	        for (int i=0; i<timeSlotCount; i++){
	            mtblTimes.setValueAt(times[i], i, 0);
	        }
	                 
	        // Set initial position of jframe
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        frmMain.setLocation(dim.width/2-frmMain.getSize().width/2, dim.height/2-frmMain.getSize().height/2);
	  }

	  class btnCheckIn_Action implements ActionListener{
		  public void actionPerformed (ActionEvent e){
	            // create a panel containing labels and buttons
	            JPanel checkInJPan = new JPanel(new GridLayout(2, 2));
	            JLabel checkinCustName = new JLabel("Enter Walk-In Customer Name: ");
	            JTextField jtfCheckInName = new JTextField();

	            // add the components to the panel
	            checkInJPan.add(checkinCustName);
	            checkInJPan.add(jtfCheckInName);
	            checkInJPan.setVisible(true);

	            // add the panel into the JOptionPane
	            JOptionPane.showMessageDialog(null, checkInJPan, "please enter the details", JOptionPane.INFORMATION_MESSAGE);

	            // create the walkin customer object
	            Customer WalkIn = new Customer(jtfCheckInName.getText(), new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date()));

	            // add object to the queue
	            queWalkIn.add(WalkIn);
	            tree.add(WalkIn);

	            populateWalkInList();
		  }            
	  }
	  public void populateWalkInList(){
          // create the header of the table
          walkinList.setText(String.format("%-3s%-20s%-15s%n", "#","NAME", "CHECK-IN TIME"));
          walkinList.append("------------------------------------");
          walkinList.append("\n");

          // get all the items of the list
          int i = 1;
          for(Customer c : queWalkIn){	            	
          	walkinList.append(String.format("%-3d%-20s%-15s%n",i,c.getName(), c.getTime()));
          	i++;
          }
	  }
	  class btnRemove_Action implements ActionListener{
		  public void actionPerformed (ActionEvent e){
			  queWalkIn.remove();
			  populateWalkInList();
		  }            
	  }
	  class btnAppointments_Action implements ActionListener{
		  public void actionPerformed (ActionEvent e){
			  AppointmentCalendar.display();
		  }            
	  }
	  class btnCustomer_Action implements ActionListener{
		  public void actionPerformed (ActionEvent e){
      	
		  }            
	  }
	  public static void main(String args[]) {
		  MainMenu menu = new MainMenu();
	
	  }

}
