package Engine;

import java.util.ArrayList;
import java.util.LinkedList;

public class Airport {
	
	String name; // 공항명
	long latitude, longitude, altitude = 0; // 공항의 위도, 경도
	double x, y;
	
	int maximum_airplane; // 비행기 최대 재적 가능 수
	//long[] runways = new long[1];// 활주로 길이
	
	runwayNode[] runways = new runwayNode[1];
	ArrayList<GNode> vertex;
	//ArrayList<rNode> runwaysNode = new ArrayList<rNode>(); // 활주로 노드
	
	// 비행기 이착륙은 허가 후 이착륙 가능 허가 메소드에서는 큐에 비행기 객체들을 집어넣어 순차적으로 허가시킨다.
	// 비행기 객체는 리스트에 저장
	
	Plane p=new Plane();
	LinkedList2 nowPlane = new LinkedList2();
	ArrayList <Plane> planeList;
	
	PlaneQueue q = new PlaneQueue();
	PlaneQueue q1 = new PlaneQueue();
	//Plane p = new Plane();

	public void setRunways(long num, long length, ArrayList nodes){
		int number = (int)num;
		runways[number].length = length;
		runways[number].runwaysNode = nodes;
	}

	public void set(String s, long l){
		switch(s){
		case "Latitude":
			latitude=l;
			break;
		case "Longitude":
			longitude=l;
			break;
		case"MaxAirplane":
			maximum_airplane=(int)l;
			break;
		}
	}
	
	public void set(String s, double l){
		switch(s){
		case "x":
			x=l;
			break;
		case "y":
			y=l;
			break;
		}
	}
	
	public void set(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		}
	}
	
	public long getLong(String s){
		switch(s){
		case "Latitude":
			return latitude;
		case "Longitude":
			return longitude;
		default:
			return 0;
		}
	}
	
	public String getString(String s){
		switch(s){
		case "Name":
			return name;
		default:
			return " ";
		}
	}
	
	public long getRunwayLength(int num){
		return runways[num].length;
	}
	
	public void PlaneLanding(Plane pl, String type){ // 비행기 이착륙

		if(type.equals("landing")){
			if(q1.<Plane>Push(pl)==true){
				System.out.println("PlaneLanding : "+pl);
				planeList.remove(pl);
			}
		} else {
			Plane _p = (Plane)q.<Plane>Pop().Get();
			if(_p!=null){
				System.out.println("PlaneTakeOff : " +_p);
			}
		}
	}

	public void Data(){ // 공항 정보 출력
		System.out.println("Airport name: "+name);
		System.out.println("Airport latitude: "+latitude+" x:"+x);
		System.out.println("Airport Longitude: "+longitude+" y:"+y);
		
	}
	
	/*public void PlaneSignReceive(){ // 목적 공항에 비행기 탐색 신청
		
	}*/
	
/*	public void OnPlane(){ // 현재 공항에 체류중인 비행기 보여주는 메소드
		System.out.println("PlaneStay : "+nowPlane);
	}
*/
	public void SetPlane(Plane pl){ // 비행기 공항에 생성
		pl.set("Longitude",this.longitude);
		pl.set("Latitude",this.latitude);
//		planeList.add(pl);
	}
	
	public void runwaygraph(Graph g){
		g.AddVertex(0,0,0,0,0);
		g.AddVertex(300,0,0,0,0);
		g.AddVertex(600,0,0,0,50);
		g.AddVertex(1200,0,0,0,300);
		g.AddVertex(2500,0,0,0,1200);
		g.AddVertex(3500,0,0,0,1300);
	}
	
	public void runwayLine(Graph g){
		g.VertexSetEdges(vertex.get(0), vertex.get(1));
		g.VertexSetEdges(vertex.get(1), vertex.get(2));
		g.VertexSetEdges(vertex.get(2), vertex.get(3));
		g.VertexSetEdges(vertex.get(3), vertex.get(4));
	}
	
	public void CreatGraph(Graph g){
		g.AddVertex(0,0,357,1403,1300);
		g.AddVertex(0,0,618,1141,1300);
		g.AddVertex(0,0,880,880,1300);
		g.AddVertex(0,0,1141,618,1300);
		g.AddVertex(0,0,1403,357,1300);
	}
	
	public void CreatLine(Graph g){
		g.VertexSetEdges(vertex.get(0), vertex.get(1));
		g.VertexSetEdges(vertex.get(1), vertex.get(2));
		g.VertexSetEdges(vertex.get(2), vertex.get(3));
		g.VertexSetEdges(vertex.get(3), vertex.get(4));
	}
	
}
