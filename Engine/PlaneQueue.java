package Engine;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlaneQueue {
		public int UNLIMIT_QUE = -1;
		ArrayList<Node<Plane>> nodes;
		
		int maximum;
		
		public PlaneQueue()
		{
			this.nodes = new ArrayList<Node<Plane>>();
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
		
		public boolean Push(Node<Plane> e) throws CloneNotSupportedException
		{
			@SuppressWarnings("unchecked")
			Node<Plane> n = (Node<Plane>)e.clone();
			@SuppressWarnings("unchecked")
			Node<Plane> n1 = (Node<Plane>)this.nodes.get(this.nodes.size()-1);
			
			boolean suc = false;
			if(n != null)
			{
				this.nodes.add(n);
				
				if(n1 != null)
					n1.SetChild(n);
				suc = true;
			}
			return suc;
		}
		public boolean Push(Plane c)
		{
			Node<Plane> n = new Node<Plane>(c);
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
//		public boolean Push(String e)
//		{
//			Node<?> n1 = this.nodes.getFirst();
//			Node<String> n = new Node<String>(e);
//			
//			try {
//				boolean a = false;
//				if(this.maximum == this.UNLIMIT_QUE || this.nodes.size() < this.maximum){
//					a = this.Push(n);
//				}
//				return a;
//			} catch (CloneNotSupportedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				return false;
//			}
//		}
		
		public Node<?> Pop()
		{
			Node<?> n = this.nodes.remove(0);
			return n;
		}
}
