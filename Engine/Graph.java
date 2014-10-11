package Engine;

import java.util.ArrayList;

public class Graph {
	private GNode head;
	private ArrayList<GNode> vertex;
	
	public void AddVertex(int x, int y, int lat, int lon, int alt) {
		//Longitude
		GNode g = new GNode(x,y, lat,lon,alt);
		
		if(this.head == null)
			this.head = g;
		else {
			double small = 0;
			int ind = 0;
			
			for(GNode alg : this.vertex) {
				double dis = this.CalcDistance(alg, g);
				if(small == 0 || dis < small) {
					small = dis;
					ind = this.vertex.indexOf(alg);
				}
			}
			
			GNode alg = this.vertex.get(ind);
			
			alg.addElement(g, small);
			g.addElement(alg, small);
		}		
	}
	
	private double CalcDistance(GNode a, GNode b){
		return Math.sqrt(Math.pow(a.altitude() - b.altitude(),2) + Math.pow(a.latitude() - b.latitude(), 2) + Math.pow(a.longitude() - b.longitude(), 2));
	}
}
