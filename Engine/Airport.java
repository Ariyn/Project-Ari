package Engine;

import java.util.ArrayList;
import java.util.LinkedList;


public class Airport {
	
	String name; // ���׸�
	double latitude, longitude, altitude = 0; // ������ ����, �浵
	double x, y;
	
	int maximum_airplane; // ����� �ִ� ���� ���� ��
	//long[] runways = new long[1];// Ȱ�ַ� ����
	
	
	
	runwayNode[] runways = new runwayNode[1]; //Ȱ�ַ�
	
	
	Graph aa = new Graph();
	//ArrayList<rNode> runwaysNode = new ArrayList<rNode>(); // Ȱ�ַ� ���
	
	// ����� �������� �㰡 �� ������ ���� �㰡 �޼ҵ忡���� ť�� ����� ��ü���� ����־� ���������� �㰡��Ų��.
	// ����� ��ü�� ����Ʈ�� ����
	
	Plane p=new Plane();
	LinkedList2 nowPlane = new LinkedList2();
	ArrayList <Plane> planeList = new ArrayList<Plane>();	
	
	PlaneQueue q = new PlaneQueue();
	PlaneQueue q1 = new PlaneQueue();
	//Plane p = new Plane();

	public void setRunways(long num, long length, ArrayList nodes){
		runways[0] = new runwayNode();
		
		int number = (int)num;
		
		runways[number].length = length;
		runways[number].runwaysNode = nodes;
	}

	public void set(String s, long l){
		switch(s){
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
		case "Latitude":
			latitude=l;
			break;
		case "Longitude":
			longitude=l;
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
	
	public double getDouble(String s){
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
	
	public Graph getGraph(){
		
		return aa;
	}
	
	public void PlaneLandingTakeOff(Plane pl, String text){ // ����� ������
		
		if(text.equals("Landing")){
			if(q1.Push(pl)==true){
				System.out.println("PlaneLanding : "+pl.getString("Name"));
				planeList.remove(pl);
			}
		}else{
			
			if(planeList.contains(pl)){
				planeList.get(planeList.indexOf(pl));
				planeList.remove(pl);
				System.out.println("PlaneTakeOff : " +pl.getString("CodeName"));
				System.out.println("what the PlaneTakeOff : " +pl.getString("Name"));
				System.out.println("what the PlaneTakeOff company : " +pl.getString("Company"));
				pl.setStatus("TakeOff"); // ����� �̷�
			}
			
		}
	}

	public void Data(){ // ���� ���� ���
		System.out.println("Airport name: "+name);
		System.out.println("Airport latitude: "+latitude+" x:"+x);
		System.out.println("Airport Longitude: "+longitude+" y:"+y);
		
	}

	public void SetPlane(Plane pl){ // ����� ���׿� ����
		pl.set("Longitude",this.longitude);
		pl.set("Latitude",this.latitude);
		planeList.add(pl);
	}
	
	public void setGraph(){
		CreatGraph(aa);	
		CreatLine(aa);
	}
	
	public void CreatGraph(Graph g){
		long t = 0;
		long r = 0;
		double lati = latitude;
		for(int i=0; i<runways[0].runwaysNode.size();i++){
			t=runways[0].runwaysNode.get(i).x;
			r=runways[0].runwaysNode.get(i).z;
			System.out.println("r = "+r);
			if(t>=500){
				lati=latitude+1;
				t-=1000;
			}
			g.AddVertex(t,0,lati,longitude,r);
		}
		if(name.equals("Dalars"))
		{
			//g.AddVertex(0,0,357,1403,1311);
			g.AddVertex(0,0,500,1100,1311);
			g.AddVertex(0,0,1000,1200,1311);
			g.AddVertex(0,0,1200,800,1311);
			g.AddVertex(0,0,1403,357,1311);

		}
		else if(name.equals("Narita"))
		{
			//g.AddVertex(0,0,1403,357,1300);
			g.AddVertex(0,0,1141,618,1300);
			g.AddVertex(0,0,880,880,1300);
			g.AddVertex(0,0,618,1141,1300);
			g.AddVertex(0,0,357,1403,1300);
				
		}
		for(GNode ggn : g.vertex){
			System.out.println("graph altitude : "+ggn.altitude() + " longitude : " + ggn.longitude() + " latitude : "+ggn.latitude());
		}
	}
	
	public void CreatLine(Graph g){
		for(int i =0; i<g.vertex.size()-1;i++){
			g.VertexSetEdges(g.vertex.get(i), g.vertex.get(i+1));
		}
	}
}
