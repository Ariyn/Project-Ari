package Engine;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Graph {
	public GNode head;
	public ArrayList<GNode> vertex;

	public void AddVertex(double x, double y, long lat, long lon, long alt) {

		//Longitude
		GNode g = new GNode(x,y, lat,lon,alt);
		
		if(this.head == null)
			this.head = g;	
		this.vertex.add(g);
	}
	
	public Dictionary<String, Object> getNextNode() {
		Dictionary<String, Object> retVal = new Hashtable();
		retVal.put("Latitude", this.vertex.get(1).latitude());
		retVal.put("x", this.vertex.get(1).x());
		
		retVal.put("Longitude", this.vertex.get(1).longitude());
		retVal.put("y", this.vertex.get(1).y());
		
		return retVal;
	}
	
	public void nextNode(){
		this.head = this.vertex.get(1);
		this.vertex.remove(0);
	}
	
	public void VertexSetEdges(GNode fromN, GNode to) {
		double dis = this.CalcDistance(fromN, to);
		fromN.addElement(to, dis);
	}
	
	private double CalcDistance(GNode a, GNode b){
		return Math.sqrt(Math.pow(a.altitude() - b.altitude(),2) + Math.pow(a.latitude() - b.latitude(), 2) + Math.pow(a.longitude() - b.longitude(), 2));
	}
}
