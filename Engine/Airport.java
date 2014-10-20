package Engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Airport {
	
	String name; // �뜝�룞�삕�뜝�뙎紐뚯삕
	double latitude, longitude, altitude = 0; // �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕, �뜝�뜽�룄
	//double x, y;
	
	int maximum_airplane; // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�뙇�뙋�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕
	//long[] runways = new long[1];// �솢�뜝�뙇琉꾩삕 �뜝�룞�삕�뜝�룞�삕
	
	
	
	runwayNode[] runways = new runwayNode[1]; //�솢�뜝�뙇琉꾩삕
	
	
	Graph aa = new Graph();
	//ArrayList<rNode> runwaysNode = new ArrayList<rNode>(); // �솢�뜝�뙇琉꾩삕 �뜝�룞�삕�뜝占�
	
	// �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�뜫媛� �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�뜫媛� �뜝�뙣�냼�뱶�뿉�뜝�룞�삕�뜝�룞�삕 �걧�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕泥닷뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�뙇�뼲�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뜫媛��뜝�룞�삕�궓�뜝�룞�삕.
	// �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	
	Plane p=new Plane();
	LinkedList2 nowPlane = new LinkedList2();
	ArrayList <Plane> planeList = new ArrayList<Plane>();	
	
	Queue<Plane> q = new LinkedList<Plane>();
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
		/*case "x":
			x=l;
			break;
		case "y":
			y=l;
			break;*/
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
	
	public void PlaneLandingTakeOff(Plane pl, String text){ // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝占�
		Plane pp = q.poll();
		if(text.equals("Landing")){
			if(q1.Push(pl)==true){
				System.out.println("PlaneLanding : "+pl.getString("Name"));
				planeList.remove(pl);
			}
		}else{
			
			if(planeList.contains(pl)&&pp!=null){
				planeList.get(planeList.indexOf(pl));
				planeList.remove(pl);
				System.out.println("PlaneTakeOff : " +pl.getString("CodeName"));
				System.out.println("what the PlaneTakeOff : " +pl.getString("Name"));
				System.out.println("what the PlaneTakeOff company : " +pl.getString("Company"));
				pl.setStatus("TakeOff"); // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�떛琉꾩삕
			}
			
		}
	}

	public void Data(){ // �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
		System.out.println("Airport name: "+name);
		System.out.println("Airport latitude: "+latitude);
		System.out.println("Airport Longitude: "+longitude);
		
	}

	public void SetPlane(Plane pl){ // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�뙎�슱�삕 �뜝�룞�삕
		pl.set("Longitude",this.longitude);
		pl.set("Latitude",this.latitude);
		planeList.add(pl);
		q.offer(pl);
	}
	
	public void setGraph(){
		CreatGraph(aa);	
		CreatLine(aa);
	}
	
	public void CreatGraph(Graph g){
		double t = 0, r = 0;
		//double lati = latitude;
		for(int i=0; i<runways[0].runwaysNode.size();i++){
			t=runways[0].runwaysNode.get(i).x;
			r=runways[0].runwaysNode.get(i).z;
			System.out.println("r = "+r);
			
			g.AddVertex(latitude + t/1000, longitude,r);
		}
		if(name.equals("Dalars"))
		{
			//g.AddVertex(0,0,357,1403,1311);

			g.AddVertex(1300,400,13);
			g.AddVertex(1500,300,13);
			g.AddVertex(800,600,13);
			g.AddVertex(358,1330,13);


		}
		else if(name.equals("Narita"))
		{
			//g.AddVertex(0,0,1403,357,1300);

			g.AddVertex(2700,360,13);
			g.AddVertex(2400,450,13);
			g.AddVertex(1700,500,13);
			g.AddVertex(1500,450,13);
				
		}
		for(GNode ggn : g.vertex){
			System.out.println(" latitude : "+ggn.latitude() +" longitude : " + ggn.longitude() + " altitude : "+ggn.altitude());
		}
	}
	
	public void CreatLine(Graph g){
		for(int i =0; i<g.vertex.size()-1;i++){
			g.VertexSetEdges(g.vertex.get(i), g.vertex.get(i+1));
		}
	}
}
