package CS146project;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppointmentDay {
    static JTable tblTimes;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblTimes; //Table model
    static JScrollPane stblTimes; //The scrollpane
        
    static JPanel pnlTimes;
    static JButton btnRemove,btnAdd;
    
    static AppointmentSlot[] timeSlots;
    
    static String[] times = {"8:00am", "8:30am", "9:00am", "9:30am", "10:00am"
			 ,"10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm"
			 ,"1:00pm", "1:30pm", "2:00pm", "2:30pm", "3:00pm"
			 ,"3:30pm", "4:00pm", "5:00pm", "5:30pm", "6:00pm"
			 ,"6:30pm","7:00pm"}; 
    
    AppointmentDay(){
    	timeSlots = new AppointmentSlot[times.length];
    }

	public static void display() {
        //Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

        //Prepare frame
        frmMain = new JFrame ("Appointments"); //Create frame
        frmMain.setSize(330, 800); //Set size to 400x400 pixels
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        //frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

        //Create controls     
        mtblTimes = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblTimes = new JTable(mtblTimes);
        stblTimes = new JScrollPane(tblTimes);
        
        pnlTimes = new JPanel(null);        
        btnRemove = new JButton("Cancel");
        btnAdd = new JButton("Book");

        //Set border
        pnlTimes.setBorder(BorderFactory.createTitledBorder("Appointment Times"));
         
        //Register action listeners
        btnRemove.addActionListener(new btnRemove_Action());
        btnAdd.addActionListener(new btnAdd_Action());
         
        //Add controls to pane
        pane.add(pnlTimes);
        pnlTimes.add(btnRemove);
        pnlTimes.add(btnAdd);
        pnlTimes.add(stblTimes);
         
        //Set bounds
        pnlTimes.setBounds(0, 0, 320, 800);
        btnRemove.setBounds(10, 675, 100, 25);
        btnAdd.setBounds(150,675,100,25);
        stblTimes.setBounds(10, 50, 300, 600);
         
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
        
        tblTimes.addMouseListener(new MouseAdapter() {
        	  public void mouseClicked(MouseEvent e) {
        		  Integer selDateInt = 0;
        		    if (e.getClickCount() == 2) {
        		      JTable target = (JTable)e.getSource();
        		      int row = target.getSelectedRow();
        		      int column = target.getSelectedColumn();
        		      
        		      // get the date in format yyyymmdd after clicking on calendar date        		      
        		      //String selDate = cmbYear.getSelectedItem() + getMonth(lblMonth.getText()) + String.format("%02d",target.getValueAt(target.getSelectedRow(), target.getSelectedColumn()) );
       		     
        		    }
        		  }
        		});
       

	}
	
  static class btnAdd_Action implements ActionListener{
      public void actionPerformed (ActionEvent e){

      }
  }
  
  static class btnRemove_Action implements ActionListener{
      public void actionPerformed (ActionEvent e){

      }
  }

}
