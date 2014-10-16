package Engine;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Graph {
	public GNode head;
	public ArrayList<GNode> vertex;
	
<<<<<<< HEAD
	public GNode AddVertex(double x, double y, long lat, long lon, long alt) {
=======
	public void AddVertex(double x, double y, int lat, int lon, int alt) {
>>>>>>> 08a0a49909c9d5cb941092f1d737a23268316328
		//Longitude
		GNode g = new GNode(x,y, lat,lon,alt);
		
		if(this.head == null)
			this.head = g;
//		else {
//			double small = 0;
//			int ind = 0;
//			
//			for(GNode alg : this.vertex) {
//				double dis = this.CalcDistance(alg, g);
//				if(small == 0 || dis < small) {
//					small = dis;
//					ind = this.vertex.indexOf(alg);
//				}
//			}
//			
//			GNode alg = this.vertex.get(ind);
//			
//			alg.addElement(g, small);
//			g.addElement(alg, small);
//		}	
		this.vertex.add(g);
	}
/*
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
	*/
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
