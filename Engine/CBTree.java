package Engine;

import java.util.ArrayList;

public class CBTree<T>
{
	CBNode<T> root;
	ArrayList<CBNode<T>> children;
	
	public CBTree()
	{
		this.root = null;
		this.children = new ArrayList<CBNode<T>>();
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
	
	public int Add(CBNode<T> e)
	{
		int ret = -1;
		@SuppressWarnings("unchecked")
		CBNode<T> tn = (CBNode<T>) e.clone();
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
//	public void Add(int e)
//	{
//		CBNode<Integer> tn = new CBNode<Integer>(e);
//		int ind = this.Add(tn);
//		if(ind == -1)
//			ind=ind;
//	}
//	public void Add(String e)
//	{
//		CBNode<String> tn = new CBNode<String>(e);
//		int ind = this.Add(tn);
//		if(ind == -1)
//			ind=ind;
//	}
//	public void Add(Plane e)
//	{
//		CBNode<Plane> tn = new CBNode<Plane>(e);
//		int ind = this.Add(tn);
//		if(ind == -1)
//			ind=ind;
//	}
	
	private CBNode<T> _Pop(int e)
	{
		CBNode<T> tn = this.children.get(e);
		tn.GetParent().RemoveChild(tn);
		this.children.remove(e);
		return tn;
	}
	public CBNode<T> Pop()
	{
		int num = this.children.size();
		return this._Pop(num-1);
	}
	@SuppressWarnings("unchecked")
	public CBNode<T> Pop(CBNode<T> me)
	{
		CBNode<?> n = null;
		int num = this.children.indexOf(me);
		if(num != -1)
			n = this._Pop(num);
		
		return (CBNode<T>) n;
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