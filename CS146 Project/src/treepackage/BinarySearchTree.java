package treepackage;

import java.util.Arrays;


import java.util.Iterator;
import java.util.LinkedList;
/**
   A class that implements the ADT binary search tree by extending BinaryTree.
   
   @author Frank M. Carrano
   @version 3.0
*/
public class BinarySearchTree<T extends Comparable<? super T>>
             extends BinaryTree<T>
             implements SearchTreeInterface<T>
{
    private T result = null;
 

  public BinarySearchTree(){
     super(); 

  }
  public BinarySearchTree(T rootEntry)
  {
    super();
    setRootNode(new BinaryNode<T>(rootEntry));
  } // end constructor
  
  public void setTree(T rootData) // disable setTree (see Segment 27.6)
  {
    throw new UnsupportedOperationException();
  } // end setTree
  
  public void setTree(T rootData, BinaryTreeInterface<T> leftTree, 
                                  BinaryTreeInterface<T> rightTree)
  {
    throw new UnsupportedOperationException();
    
  } // end setTree
  
	public boolean Search(T entry) 
	{
		//T result = null;
		boolean found = false;
		
		BinaryNodeInterface<T> currentNode = getRootNode();
		while (!found && (currentNode != null) )
		{
			T currentEntry = currentNode.getData();
			//System.out.println(currentNode.getData());
			
			if (entry.compareTo(currentEntry) == 0)
			{
				result = currentEntry;
				found = true;
                                
			}
			else if (entry.compareTo(currentEntry) < 0)
				currentNode = currentNode.getLeftChild(); 
			else
				currentNode = currentNode.getRightChild(); 
		} // end while
		
		return found;
	} // end getEntry
        
        public T getSearchEntry() 
	{
		return result;
	} // end getEntry
        

        
	public boolean contains(T entry)
	{
		return true;//getEntry(entry) != null;
	} // end contains
	
	public T add(T newEntry)
	{
	  T result = null;
	  
	  if (isEmpty())
	    setRootNode(new BinaryNode<T>(newEntry));
	  else
	    result = addEntry(newEntry);
	    
	  return result;
	} // end add

	private T addEntry(T newEntry)
	{
	  BinaryNodeInterface<T> currentNode = getRootNode();
          
	  assert currentNode != null;
	  T result = null;
	  boolean found = false;
	  
	  while (!found)
	  {
	    T currentEntry = currentNode.getData();
            
            //System.out.println(newEntry.compareTo(currentEntry));
	    int comparison = newEntry.compareTo(currentEntry);
	    
	    if (comparison == 0)
	    { // newEntry matches currentEntry;
	      // return and replace currentEntry
	      found = true;
	      result = currentEntry;
	      currentNode.setData(newEntry);
	    }
	    else if (comparison < 0)
	    {
	      if (currentNode.hasLeftChild())
	        currentNode = currentNode.getLeftChild();
	      else
	      {
	        found = true;
	        currentNode.setLeftChild(new BinaryNode<T>(newEntry));
	      } // end if
	    }
	    else
	    {
	      assert comparison > 0;
	      
	      if (currentNode.hasRightChild())
	        currentNode = currentNode.getRightChild();
	      else
	      {
	        found = true;
	        currentNode.setRightChild(new BinaryNode<T>(newEntry));
	      } // end if
	    } // end if
	  } // end while
	  
	  return result; 
	} // end addEntry

	public T remove(T entry)
	{
	  T result = null;
	  
	  // locate node (and its parent) that contains a match for entry
	  NodePair pair = findNode(entry);
	  BinaryNodeInterface<T> currentNode = pair.getFirst();
	  BinaryNodeInterface<T> parentNode = pair.getSecond();
	  
	  if (currentNode != null) // entry is found
	  {
	    result = currentNode.getData(); // get entry to be removed
	    
	    // Case 1: currentNode has two children
	    if (currentNode.hasLeftChild() && currentNode.hasRightChild())
	    {
	      // replace entry in currentNode with the entry in another node
	      // that has at most one child; that node can be deleted
	      
	      // get node to remove (contains inorder predecessor; has at 
	      // most one child) and its parent
	      pair = getNodeToRemove(currentNode);
	      BinaryNodeInterface<T> nodeToRemove = pair.getFirst();
	      parentNode = pair.getSecond();
	      
	      // copy entry from nodeToRemove to currentNode
	      currentNode.setData(nodeToRemove.getData());
	      
	      currentNode = nodeToRemove;
	      // Assertion: currentNode is the node to be removed; it has at 
	      //            most one child
	      // Assertion: Case 1 has been transformed to Case 2
	    } // end if
	    
	    // Case 2: currentNode has at most one child; delete it
	    removeNode(currentNode, parentNode);
	  } // end if
	  
	  return result;
	} // end remove

// Other public methods in SearchTreeInterface are inherited from BinaryTree.

	// locate node that contains a match for entry
	private NodePair findNode(T entry)
	{
	  NodePair result = new NodePair();
	  boolean found = false;
	  
	  BinaryNodeInterface<T> currentNode = getRootNode();
	  BinaryNodeInterface<T> parentNode = null;
	  
	  while (!found && (currentNode != null) )
	  {
	    T currentEntry = currentNode.getData();
	    int comparison = entry.compareTo(currentEntry);
	    
	    if (comparison < 0)
	    {
	      parentNode = currentNode;
	      currentNode = currentNode.getLeftChild(); 
	    }
	    else if (comparison > 0)
	    {
	      parentNode = currentNode;
	      currentNode = currentNode.getRightChild(); 
	    }
	    else
	      found = true;
	  } // end while
	  
	  if (found)
	  	result = new NodePair(currentNode, parentNode);
	  	// found entry is currentNode.getData()
	  	
	  return result;
	} // end findNode

	// Gets the node that contains the inorder predecessor of currentNode.
	// Precondition: currentNode has two children 
	private NodePair getNodeToRemove(BinaryNodeInterface<T> currentNode)
	{
	  // find node with largest entry in left subtree by
	  // moving as far right in the subtree as possible
	  BinaryNodeInterface<T> leftSubtreeRoot = currentNode.getLeftChild();
	  BinaryNodeInterface<T> rightChild = leftSubtreeRoot;
	  BinaryNodeInterface<T> priorNode = currentNode;
	  
	  while (rightChild.hasRightChild())
	  {
	    priorNode = rightChild;
	    rightChild = rightChild.getRightChild();
	  } // end while
	  
	  // rightChild contains the inorder predecessor and is the node to 
	  // remove; priorNode is its parent
	  
	  return new NodePair(rightChild, priorNode);
	} // end getNodeToRemove

	// Removes a node having at most one child. 
	private void removeNode(BinaryNodeInterface<T> nodeToRemove, BinaryNodeInterface<T> parentNode)
	{		
		BinaryNodeInterface<T> childNode;
		
		if (nodeToRemove.hasLeftChild())
		  childNode = nodeToRemove.getLeftChild();
		else 
		  childNode = nodeToRemove.getRightChild();

		// Assertion: if nodeToRemove is a leaf, childNode is null  
		assert (nodeToRemove.isLeaf() && childNode == null) || !nodeToRemove.isLeaf();

		// if nodeToRemove is the root
		if (nodeToRemove == getRootNode())
		  setRootNode(childNode);
		  
		// else nodeToRemove is not the root;
		// link the parent of nodeToRemove to childNode, 
		// thereby deleting nodeToRemove
		else if (parentNode.getLeftChild() == nodeToRemove)
		  parentNode.setLeftChild(childNode);
		else
		  parentNode.setRightChild(childNode);
	} // end removeNode

	private class NodePair
	{
		private BinaryNodeInterface<T> first, second;
		
		public NodePair()
		{
			first = null;
			second = null;
		} // end default constructor

		public NodePair(BinaryNodeInterface<T> firstNode, BinaryNodeInterface<T> secondNode)
		{
			first = firstNode;
			second = secondNode;
		} // end constructor
		
		public BinaryNodeInterface<T> getFirst()
		{
			return first;
		} // end getFirst
		
		public BinaryNodeInterface<T> getSecond()
		{
			return second;
		} // end getSecond
	} // end NodePair

	@Override
	public LinkedList returnCategory(T entry) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LinkedList returnId(T entry) {
		// TODO Auto-generated method stub
		return null;
	}
} // end BinarySearchTree
