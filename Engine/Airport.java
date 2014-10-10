package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // 공항명
	long latitude, altitude; // 공항의 위도, 경도
	int maximum_airplane; // 비행기 최대 재적 가능 수
	
	long[] runways = new long[1];// 활주로 길이
	
	// 비행기 이착륙은 허가 후 이착륙 가능 허가 메소드에서는 큐에 비행기 객체들을 집어넣어 순차적으로 허가시킨다.
	// 비행기 객체는 리스트에 저장
	class LinkedList {
	    
	    private Node header;
	    private int size;
	    
	    public LinkedList(){
	        
	        header = new Node(null);
	        size = 0;
	    }
	    
	    // 단순 연결 리스트 노드
	    private class Node{
	        
	        private Object data;
	        private Node nextNode;
	        
	        Node(Object data){
	            
	            this.data = data;
	            this.nextNode = null;
	        }
	        
	    }
	    
	    // index 위치의 노드 데이터를 반환한다.
	    public Object get(int index){
	        return getNode(index).data;
	    }
	    
	    // index 위치의 노드를 반환한다.
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
	    
	    // 첫번째 노드의 데이터를 반환한다.
	    public Object getFirst(){
	        return get(0);
	    }
	    
	    // 해당 데이터의 노드 위치 index를 반환한다.
	    public int getNodeIndex(Object obj){
	        
	        if(size<=0){
	            return -1;
	        }
	        
	        int index=0;
	        Node node = header.nextNode;
	        Object nodeData = node.data;
	        
	        // header에서 부터 순차적으로 nodeData와 값을 비교한다.
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
	    
	    // data를 리스트의 첫번째에 삽입한다.
	    public void addFirst(Object data){
	        
	        Node newNode = new Node(data);
	        newNode.nextNode = header.nextNode;
	        header.nextNode = newNode;
	        size++;
	        
	    }
	    
	    // index 위치에 data를 삽입한다.
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
	    
	    // 리스트의 마지막에 data 를 삽입한다.
	    public void addLast(Object data){
	        add(size, data);
	    }
	    
	    // 리스트의 마지막에 data를 삽입한다.
	    public void add(Object data){
	        addLast(data);
	    }
	    
	    // 첫번째 노드를 제거하고 데이터를 반환한다.
	    public Object removeFirst(){
	        
	        Node firstNode = getNode(0);
	        header.nextNode = firstNode.nextNode;
	        size--;
	        return firstNode.data;
	        
	    }
	    
	    // index 위치의 노드를 제거하고 데이터를 반환한다.
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
	    
	    // 리스트에서 data를 가진 노드를 제거하고 제거여부를 반환한다.
	    public boolean remove(Object data){
	        
	        int nodeIndex = getNodeIndex(data);
	        
	        if(nodeIndex == -1){
	            return false;
	        }else{
	            remove(nodeIndex);
	            return true;
	        }
	    }
	    
	    // 리스트의 마지막 노드를 제거하고 데이터를 반환한다.
	    public Object removeLast(){
	        return remove(size-1);
	    }
	    
	    // 리스트의 크기를 반환한다.
	    public int size(){
	        return size;
	    }
	    
	    // 리스트의 데이터 String으로 반환
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
	
	LinkedList nowPlane = new LinkedList();
	
	Queue q = new Queue();
	//Plane p = new Plane();

	public void setName(String text){
		name = text;
	}
	
	public void setPositionX(long x){
		latitude = x;
	}
	
	public void setPositionY(long y){
		altitude = y;
	}
	
	public void setRunwyas(int num, long length){
		runways[num] = length;
	}

	
	public String getName(){
		return name;
	}
	
	public double getPositionX(){ // 공항 위치 보여주는 메소드
		return latitude;
	}
	
	public double getPositionY(){
		return altitude;
	}
	
	public long getRunwayLength(int num){
		return runways[num];
	}
	
	public void LandingPermitSign(Plane pl){ // 비행기 착륙 허가
		System.out.println("PlaneLading : "+q.Push(pl));
	}
	
	public void PlaneLanding(Plane pl){ // 비행기 착륙
		nowPlane.addLast(pl);
	}
	
	public void TakeOffPermitSign(){ // 비행기 이륙 허가
		
	}
	
	public void PlaneTakeOff(){ // 비행기 이륙
		nowPlane.removeFirst();
	}
	
	public void PlaneSignReceive(){ // 목적 공항에 비행기 탐색 신청
		
	}
	
	public void OnPlane(){ // 현재 공항에 체류중인 비행기 보여주는 메소드
		System.out.println("PlaneStay : "+nowPlane);
	}
	
	public void SetPlane(Plane pl){ // 비행기 공항에 생성
		pl.setPositionX(this.altitude);
		pl.setPositionY(this.latitude);
		
	}
}
