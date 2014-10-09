package Engine;

import java.util.LinkedList;

public class PlaneStack {
	public int UNLIMIT_STACK = -1;
	LinkedList<Node<?>> nodes;
	int index;
	int maximum;
	
	public PlaneStack()
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
	public boolean Add(Plane c)
	{
		Node<Plane> n = new Node<Plane>(c);
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
