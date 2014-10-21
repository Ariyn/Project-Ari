package Engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Airport {
	
	String name;
	double latitude, longitude, altitude = 0;
	
	int maximum_airplane;
	
	runwayNode[] runways = new runwayNode[1];
	
	Graph aa = new Graph();

	Plane p=new Plane();
	LinkedList2 nowPlane = new LinkedList2();
	ArrayList <Plane> planeList = new ArrayList<Plane>();
	
	Queue<Plane> q = new LinkedList<Plane>();
	Queue<Plane> q1 = new LinkedList<Plane>();
	
	Plane pp = null;

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
		case "Latitude":
			latitude=l;
			System.out.printf("%s's latitude changed %f", this.name, this.latitude);
			break;
		case "Longitude":
			longitude=l;
			System.out.printf("%s's longitude changed %f", this.name, this.longitude);
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
		
		return aa.clone();
	}
	
	public Plane PlaneLandingTakeOff(String text){ // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝占�
		Plane i = null;
		
		System.out.println("");
		System.out.println(this.name);
			
		if(pp == null) {
			this.pp = this.q.poll();
			if(pp != null)
				pp.setStatus("TakeOff");
			i=pp;
		}
		else {
			System.out.println("testForPlaneTakeoff"+this.pp.codeName+"\t\t"+this.pp + "\t" +this.q +"\t"+this.planeList);
			if(pp.getStatus() == 2) {
				this.planeList.remove( this.planeList.indexOf( this.pp ) );
				this.pp = this.q.poll();
				
				if(pp != null) {
					pp.setStatus("TakeOff");
				}
				i=pp;
			}
		}
		if(i != null && i.codeName.equals("AA013"))
			System.out.println(i.root.head);
		
		return i;
	}

//	@SuppressWarnings("null")
//	public int PlaneLandingTakeOff(Plane pl, String text){ // �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝占�
//		int retVal =0;
//		if(text.equals("Landing")){
//			if(q1.offer(pl)==true){
//				System.out.println("PlaneLanding : "+pl.getString("Name"));
//				planeList.remove(pl);
//			}
//		}else{
//			if(pp == null || pp.getStatus()==2){
//				pp = q.poll();
//				if(pp != null)
//					retVal = 1;				
//			}
//			if(planeList.contains(pl)&&pp==pl ){
//				planeList.get(planeList.indexOf(pl));
//				planeList.remove(pl);
//				System.out.println("PlaneTakeOff : " +pl.getString("CodeName"));
//				System.out.println("what the PlaneTakeOff : " +pl.getString("Name"));
//				System.out.println("what the PlaneTakeOff company : " +pl.getString("Company"));
//				pl.setStatus("TakeOff");
//			}
//		}
//		return retVal;
//	}

	public void Data(){
		System.out.println("Airport name: "+name);
		System.out.println("Airport latitude: "+latitude);
		System.out.println("Airport Longitude: "+longitude);
		
	}

	public void SetPlane(Plane pl){
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
		for(int i=0; i<runways[0].runwaysNode.size();i++){
			t=runways[0].runwaysNode.get(i).x;
			r=runways[0].runwaysNode.get(i).z;
			g.AddVertex(latitude + t/1000, longitude,r);
		}
		if(name.equals("Dalars")){
			g.AddVertex(2750,400,13);
			g.AddVertex(1600,300,13);
			g.AddVertex(1500,600,13);
			g.AddVertex(1404,357,13);

		} else if(name.equals("Narita")){
			g.AddVertex(1410,400,13);
			g.AddVertex(1500,450,13);
			g.AddVertex(2000,400,13);
			g.AddVertex(2700,360,13);
				
		} else if(name.equals("Incheon")){
			g.AddVertex(1270, 380, 13);
			g.AddVertex(1350, 390, 13);
			g.AddVertex(1390, 400, 13);
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
