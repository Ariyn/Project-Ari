package Engine;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Graph {
	public GNode head;
	public ArrayList<GNode> vertex = new ArrayList<GNode>();

	public void AddVertex(double x, double y, long lat, long lon, long alt) {

		//Longitude
		GNode g = new GNode(x,y, lat,lon,alt);
		
		if(this.head == null)
			this.head = g;	
		this.vertex.add(g);
	}
	
	public Dictionary<String, Object> getNextNode() {
		Dictionary<String, Object> retVal = new Hashtable();
		if(this.vertex.size() <= 1){
			retVal.put("next", Boolean.FALSE);
		} else {
			retVal.put("next", Boolean.TRUE);
			retVal.put("node", this.vertex.get(1));
		}
		
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
