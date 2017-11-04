package StackAndQueuePackage;

/**
   A class of stacks whose entries are stored in a chain of nodes.
   
   @author Frank M. Carrano
   @version 3.0
*/
public class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // references the last node in the chain
	
	public LinkedStack()
	{
		topNode = null;
	} // end default constructor
	
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
	} // end push

	public T peek()
	{
		T top = null;
		
		if (topNode != null)
			top = topNode.getData();
		
		return top;
	} // end peek

	public T pop()
	{
		T top = null;
		
		if (topNode != null)
      {
			top = topNode.getData();
	      topNode = topNode.getPreviousNode(); 
      } // end if
      
	   return top;
	} // end pop

	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty
	
	public void clear()
	{
		topNode = null;	
	} // end clear

	private class Node
	{
      private T    data;     // entry in stack
      private Node previous; // link to previous node

      private Node(T dataPortion)
      {
         data = dataPortion;
         previous = null;	
      } // end constructor

      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         previous = linkPortion;	
      } // end constructor

      private T getData()
      {
         return data;
      } // end getData

      private void setData(T newData)
      {
         data = newData;
      } // end setData

      private Node getPreviousNode()
      {
         return previous;
      } // end getPreviousNode

      private void setPreviousNode(Node previousNode)
      {
         previous = previousNode;
      } // end setPreviousNode
	} // end Node
} // end LinkedStack