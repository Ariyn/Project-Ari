package Engine;

///////////////////////////////////////////////////////////////////////////////////////////
//																						 //
//																						 //
//																						 //
//									MADE BY aRiyn.										 //
//								2014 - 09 - 20   01:59 AM								 //
//																						 //
//					  ANY ONE CAN USE THIS SOURCE IF NOTICE MY NAME						 //
//																						 //
//																						 //
//																						 //
///////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import Airport;

public class DS
{
	//list, queue, tree, graph, hash
	
	public DS()
	{
		
	}
	
	public static void main(String argv[])
	{
		CBTree t = new CBTree();
		t.Add("AA0117");
		t.Add("AA0118");
		t.Add("AA0119");
		t.Add("AA0120");
		t.Add("AA0121");
		t.Add("AA0122");
		t.Add("AA0123");
		t.Add("AA0124");
		
		CBNode<?> n = t.NodeOfValue("AA0119");
		//System.out.println(n+" "+n.Get()+" "+n.GetChildren()+" "+ n.GetParent());
		t.Pop(t.NodeOfValue("AA0119"));
		t._Print();
		
		//System.out.println("test");
	}
	
	
}

class Queue
{
	public int UNLIMIT_QUE = -1;
	LinkedList<Node<?>> nodes;
	
	int maximum;
	
	public Queue()
	{
		this.nodes = new LinkedList<Node<?>>();
		this.maximum = this.UNLIMIT_QUE;
	}
	
	public boolean Contain(Class<?> e)
	{
		System.out.println(e);
		return false;
	}
	public void SetMaximum(int max)
	{
		this.maximum = max;
	}
	
	public boolean Push(Node<?> e) throws CloneNotSupportedException
	{
		Node<?> n = (Node<?>)e.clone();
		Node<?> n1 = this.nodes.getFirst();
		
		boolean suc = false;
		if(n != null)
		{
			this.nodes.push(n);
			
			if(n1 != null)
				n1.SetChild(n);
			suc = true;
		}
		return suc;
	}
	public boolean Push(Class<?> c)
	{
		Node<Class<?>> n = new Node<Class<?>>(c);
		try {
			boolean a = false;
			if(this.maximum == this.UNLIMIT_QUE || this.nodes.size() < this.maximum){
				a = this.Push(n);
			}
			return a;
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	public boolean Push(String e)
	{
		Node<?> n1 = this.nodes.getFirst();
		Node<String> n = new Node<String>(e);
		
		try {
			boolean a = false;
			if(this.maximum == this.UNLIMIT_QUE || this.nodes.size() < this.maximum){
				a = this.Push(n);
			}
			return a;
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public Node<?> Pop()
	{
		Node<?> n = this.nodes.removeFirst();
		return n;
	}
}

class Stack
{
	public int UNLIMIT_STACK = -1;
	LinkedList<Node<?>> nodes;
	int index;
	int maximum;
	
	public Stack()
	{
		this.nodes = new LinkedList<Node<?>>();
		this.index = 0;
	}
	public void SetMaxium(int max)
	{
		this.maximum = max;
	}
	
	public boolean Add(Node<?> e) throws CloneNotSupportedException
	{
		Node<?> n = (Node<?>)e.clone(); 
		
		boolean suc = false;
		if(n != null)
		{
			this.nodes.add(n);
			Node<?> n2 = this.nodes.getLast();
			
			if(n2 != null)
				n2.SetChild(n);
			suc = true;
		}
		return suc;
	}
	public boolean Add(Class<?> c)
	{
		Node<Class<?>> n = new Node<Class<?>>(c);
		try {
			boolean a = false;
			if(this.maximum == this.UNLIMIT_STACK || this.nodes.size() < this.maximum){
				a = this.Add(n);
			}
			return a;
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	public boolean Add(String e)
	{
		Node<String> n = new Node<String>(e);
		try {
			return this.Add(n);
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public Node<?> Pop()
	{
		Node<?> n = this.nodes.pop();
		this.nodes.getLast().SetChild(null);
		return n;
	}
}

class CBTree
{
	CBNode<?> root;
	ArrayList<CBNode<?>> children;
	
	public CBTree()
	{
		this.root = null;
		this.children = new ArrayList<CBNode<?>>();
	}
	
	@SuppressWarnings("unused")
	private void _CalcArray()
	{
		int num = this.children.size();
		//does this really necessary?
		if(((num+1) & num) == 0)
			for(int i=1;i<num; i++)
				this.children.add(null);
	}
	
	public void _Print()
	{
		System.out.println(this.children);
		
		for(CBNode<?> i:this.children)
		{
			System.out.print("self   "+i.Get()+", ");
			if(i.GetLeftChild() != null)
				System.out.print("left   "+i.GetLeftChild().Get()+", ");
			if(i.GetRightChild() != null)
				System.out.print("right   "+i.GetRightChild().Get());
			System.out.println();
		}
	}
	
	public int Add(CBNode<?> e)
	{
		int ret = -1;
		CBNode<?> tn = (CBNode<?>)e.clone();
		if(tn != null)
		{
			boolean suc = this.children.add(tn);
			if(suc)
			{
				//System.out.println("suc");
				ret = this.children.indexOf(tn);
				CBNode<?> parent = this.children.get((ret-1)/2);
				if(parent != tn){
					//System.out.println(parent);
					tn.SetParent(parent);
					//System.out.println(tn.GetParent());
					
					if(parent.AddChildren(tn))
						ret = -1;
				}
			}
		}
		return ret;
	}
	public void Add(int e)
	{
		CBNode<Integer> tn = new CBNode<Integer>(e);
		int ind = this.Add(tn);
		if(ind == -1)
			ind=ind;
	}
	public void Add(String e)
	{
		CBNode<String> tn = new CBNode<String>(e);
		int ind = this.Add(tn);
		if(ind == -1)
			ind=ind;
	}
	
	private CBNode<?> _Pop(int e)
	{
		CBNode<?> tn = this.children.get(e);
		tn.GetParent().RemoveChild(tn);
		this.children.remove(e);
		return tn;
	}
	public CBNode<?> Pop()
	{
		int num = this.children.size();
		return this._Pop(num-1);
	}
	public CBNode<?> Pop(CBNode<?> me)
	{
		int num = this.children.indexOf(me);
		CBNode<?> n = this._Pop(num);
		return n;
	}
	
	public int Depth()
	{
		return this.children.size()/2 +1;
	}
	public int Depth(Class<?> e)
	{
		return 0;
	}
	
	public int IndexOfObject(CBNode<?> e)
	{
		return this.children.indexOf(e);
	}
	
	private CBNode<?> _SearchValue(String e)
	{
		//System.out.println(this.children.toArray(new CBNode<?>[18]));
		CBNode<?> ret = null;
		for(CBNode<?> n : this.children.toArray(new CBNode<?>[0]))
		{
			if(n.Get().equals(e))
			{
				ret = n;
				break;
			}
		}
		return ret;
	}
	public int IndexOfValue(String e)
	{
		return this.children.indexOf(this._SearchValue(e));
	}
	public CBNode<?> NodeOfValue(String e)
	{
		return this._SearchValue(e);
	}
	
	private CBNode<?> _SearchValue(int e)
	{
		CBNode<?> ret = null;
		for(CBNode<?> n : this.children.toArray(new CBNode<?>[0]))
		{
			if(n.Get().equals(e))
			{
				ret = n;
				break;
			}
		}
		return ret;
	}
	public int IndexOfValue(int e)
	{
		return this.children.indexOf(this._SearchValue(e));
	}
	
	private CBNode<?> _SearchValue(Class<?> e)
	{
		CBNode<?> ret = null;
		for(CBNode<?> n : this.children.toArray(new CBNode<?>[0]))
		{
			if(n.Get().equals(e))
			{
				ret = n;
				break;
			}
		}
		return ret;
	}
	public int IndexOfValue(Class<?> e)
	{
		return this.children.indexOf(this._SearchValue(e));
	}
}

class CBNode<T> extends Node<T> implements Cloneable
{
	private CBNode<?>[] _children;
	
	private CBNode<?> _parent;
	
	private void _CBNode()
	{
		this._children = new CBNode<?>[2];
		this._children[0] = null;
		this._children[1] = null;
		this._parent = null;
	}
	
	public CBNode(T v)
	{
		super(v);
		this._CBNode();
	}
	
	public CBNode<?> clone(){  
	    try{  
	        return (CBNode<?>)super.clone();  
	    }catch(Exception e){ 
	    	e.printStackTrace();
	        return null; 
	    }
	}
	
	public boolean AddChildren(CBNode<?> e)
	{
		boolean suc = false;
		int len = -1;
		
		if(this._children[0] == null) {
			//System.out.println(e.Get());
			len = 0;
			suc = true;
		} else if(this._children[1] == null){
			len = 1;
			suc = true;
		}
		if(len != -1)
			this._children[len] = e;
		
		return suc;
	}
	public boolean AddChildren(String e)
	{
		CBNode<String> _node = new CBNode<String>(e);
		return this.AddChildren(_node);
	}
	public boolean AddChildren(int e)
	{
		CBNode<Integer> _node = new CBNode<Integer>(e);
		return this.AddChildren(_node);
	}
	
	public CBNode<?> GetParent()
	{
		return this._parent;
	}
	public boolean SetParent(CBNode<?> e)
	{
		this._parent = e;
		return true;
	}
	public CBNode<?>[] GetChildren()
	{
		return this._children;
	}
	public CBNode<?> GetLeftChild()
	{
		return this._children[0];
	}
	public CBNode<?> GetRightChild()
	{
		return this._children[1];
	}
	
	public boolean RemoveChild(CBNode<?> child)
	{
		boolean suc = false;
		if(this._children[0] == child) {
			this._children[0] = null;
			suc = true;
		} else if(this._children[1] == child) {
			this._children[1] = null;
			suc = true;
		}
		
		return suc;
	}
	public boolean RemoveChildren()
	{
		this.RemoveChild(this._children[0]);
		this.RemoveChild(this._children[1]);
		
		return true;
	}
}

class Node<T> implements Cloneable 
{
	private T value;
	
	Node<?> _previous;
	Node<?> _post;
	
	public void _Node()
	{	
		this._previous = null;
		this._post = null;
	}
	
	public Node(T v)
	{
		_Node();
		this.value = v;
	}
	public T Get()
	{
		return this.value;
	}
	public Class<? extends Object> GetType()
	{
		return this.value.getClass();
	}
	public Node<?> clone(){  
	    try{  
	        return (Node<?>)super.clone();  
	    }catch(Exception e){ 
	    	e.printStackTrace();
	        return null; 
	    }
	}		
	public boolean SetChild(Node<?> e)
	{
		this._post = e;
		return true;
	}
	public Node<?> GetChild()
	{
		return this._post;
	}
	
	public boolean SetParent(Node<?> e)
	{
		this._previous = e;
		return true;
	}
	public Node<?> GetParent()
	{
		return this._previous;
	}
}