package CS146project;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.ListIterator;

public class fileWriter {

	
	LinkedList<Object> objects = new LinkedList();
	
	
	public void add(Object a){
		
		objects.add(a);
	
	}
	
	public void write() throws FileNotFoundException, UnsupportedEncodingException{
	
		 ListIterator<Object> listIterator = objects.listIterator();
	        while (listIterator.hasNext()) {
	            listIterator.next();
	        }
	        
	        PrintWriter writer = new PrintWriter("Customers.txt", "UTF-8");

	        //System.out.println("\nLoop Approach: ");
	        // Traditional for loop approach
	        for (int i = 0; i < objects.size(); i++) {
	            //System.out.println(objects.get(i));
	       
	             writer.println(objects.get(i)); 
	      
	        }
	        writer.close();
	}
	
}
