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

class GNode implements Cloneable
{
	static int LONG = 0, LATI=1, ALT =2;
	private ArrayList<GNode> _vertex;
	private ArrayList<Double> _edge;
	
	private double x, y;
	private double altitude, latitude, longitude;
	
	public GNode(double x, double y, double lat, double lon, double alt) {
		this.x = x;
		this.y = y;
		
		this.altitude=alt;
		this.latitude=lat;
		this.longitude = lon;
		
		this._vertex = new ArrayList<GNode>();
		this._edge = new ArrayList<Double>();
	}
	
	public void addElement(GNode g, double distance) {
//		double length = Math.sqrt(
//				Math.pow(this.altitude() - g.altitude(),2) +
//				Math.pow(this.latitude() - g.latitude(), 2) +
//				Math.pow(this.longitude() - g.longitude(), 2)
//		);
		
		this._vertex.add(g);
		this._edge.add(new Double(distance));

	}
		
	public double altitude() {
		return this.altitude;
	}
	
	public double latitude() {
		return this.latitude;
	}
	public double longitude() {
		return this.longitude;
	}
	public double x() {
		return this.x;
	}
	public double y() {
		return this.y;
	}
	public double coordinate(int type){
		double retVal = 0;
		if(type == GNode.LONG) {
			retVal = this.longitude+(this.y+500)/1000.0;
		} else if(type == GNode.LATI) {
			retVal = this.latitude+(this.x+500)/1000.0;
		}else if(type == GNode.ALT){
			retVal = altitude;
		}
		return retVal;
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

class LinkedList2 {
    
    private Node header;
    private int size;
    
    public LinkedList2(){
        
        header = new Node(null);
        size = 0;
    }
    
    // �ܼ� ���� ����Ʈ ���
    private class Node{
        
        private Object data;
        private Node nextNode;
        
        Node(Object data){
            
            this.data = data;
            this.nextNode = null;
        }
        
    }
    
    // index ��ġ�� ��� �����͸� ��ȯ�Ѵ�.
    public Object get(int index){
        return getNode(index).data;
    }
    
    // index ��ġ�� ��带 ��ȯ�Ѵ�.
    private Node getNode(int index){
        
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " + size);
        }
        
        Node node = header.nextNode;
        for(int i =0; i < index; i++){
            node = node.nextNode;
        }
        
        return node;
    }
    
    // ù��° ����� �����͸� ��ȯ�Ѵ�.
    public Object getFirst(){
        return get(0);
    }
    
    // �ش� �������� ��� ��ġ index�� ��ȯ�Ѵ�.
    public int getNodeIndex(Object obj){
        
        if(size<=0){
            return -1;
        }
        
        int index=0;
        Node node = header.nextNode;
        Object nodeData = node.data;
        
        // header���� ���� ���������� nodeData�� ���� ���Ѵ�.
        while(!obj.equals(nodeData)){
            node = node.nextNode;
            
            if(node==null){
                return -1;
            }
            
            nodeData = node.data;
            index++;
        }
        
        return index;
    }
    
    // data�� ����Ʈ�� ù��°�� �����Ѵ�.
    public void addFirst(Object data){
        
        Node newNode = new Node(data);
        newNode.nextNode = header.nextNode;
        header.nextNode = newNode;
        size++;
        
    }
    
    // index ��ġ�� data�� �����Ѵ�.
    public void add(int index, Object data){
        
        if(index==0){
            addFirst(data);
            return;
        }
        
        Node previous = getNode(index-1);
        Node next = previous.nextNode;
        
        Node newNode = new Node(data);
        previous.nextNode = newNode;
        newNode.nextNode = next;
        size++;
    }
    
    // ����Ʈ�� �������� data �� �����Ѵ�.
    public void addLast(Object data){
        add(size, data);
    }
    
    // ����Ʈ�� �������� data�� �����Ѵ�.
    public void add(Object data){
        addLast(data);
    }
    
    // ù��° ��带 �����ϰ� �����͸� ��ȯ�Ѵ�.
    public Object removeFirst(){
        
        Node firstNode = getNode(0);
        header.nextNode = firstNode.nextNode;
        size--;
        return firstNode.data;
        
    }
    
    // index ��ġ�� ��带 �����ϰ� �����͸� ��ȯ�Ѵ�.
    public Object remove(int index){
        
        if(index<0 || index>=size){
            
            throw new IndexOutOfBoundsException("Index : " + index + ", Size : " +size);
        
        }else if(index ==0){
            
            return removeFirst();
        
        }
        
        Node previous = getNode(index-1);
        Node removeNode = previous.nextNode;
        Node next = removeNode.nextNode;
        previous.nextNode = next;
        size--;
        
        return removeNode.data;
    }
    
    // ����Ʈ���� data�� ���� ��带 �����ϰ� ���ſ��θ� ��ȯ�Ѵ�.
    public boolean remove(Object data){
        
        int nodeIndex = getNodeIndex(data);
        
        if(nodeIndex == -1){
            return false;
        }else{
            remove(nodeIndex);
            return true;
        }
    }
    
    // ����Ʈ�� ������ ��带 �����ϰ� �����͸� ��ȯ�Ѵ�.
    public Object removeLast(){
        return remove(size-1);
    }
    
    // ����Ʈ�� ũ�⸦ ��ȯ�Ѵ�.
    public int size(){
        return size;
    }
    
    // ����Ʈ�� ������ String���� ��ȯ
    public String toString(){
        
        StringBuffer result = new StringBuffer("[");
        Node node = header.nextNode;
        
        if(node!=null){
            result.append(node.data);
            node = node.nextNode;
            
            while(node != null){
                result.append(", ");
                result.append(node.data);
                node = node.nextNode;
            }
        }
        
        result.append("]");
        return result.toString();
    }

}

class rNode{
	public long x, y ,z;
	
	public rNode(){
		x=0;
		y=0;
		z=0;
	}
}

class runwayNode{
	long length;
	ArrayList<rNode> runwaysNode;
	
	public runwayNode(){
		length = 0;
		runwaysNode = null;
	}
}