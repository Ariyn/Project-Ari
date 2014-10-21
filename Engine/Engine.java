package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Engine extends Thread{
	
	TestGraphics frame;

	ArrayList <Airport> airportList = new ArrayList<Airport>();
	ArrayList <Plane> planeTypeList = new ArrayList<Plane>();
	ArrayList <Plane> FlyingplaneList = new ArrayList<Plane>();	
	
	boolean _thread = true;
	
	Graph allGraph = new Graph();
	
	private static Engine instance = new Engine();
	private Engine(){}
	public static Engine getInstance(){
		if(instance == null)
			instance = new Engine();
		return instance;
	}
	
	private void _addDataAirport(JSONObject data) {		
		@SuppressWarnings("unchecked")
		Set<String> keys = (Set<String>)data.keySet();
		
		for(String i:keys){
			Airport newAirport = new Airport();
			
			@SuppressWarnings("unchecked")
			JSONObject _data = (JSONObject)data.get(i);
			Set<String> _keys = (Set<String>)_data.keySet();

			for(String e:_keys){
				System.out.println(e+" "+_data.get(e));
				switch(e) {
				case "name":
					newAirport.set("Name", _data.get("name").toString());
					break;
					
				case "position":
					JSONObject a = (JSONObject)_data.get("position");
					double lat = (double)a.get("Latitude");
					double lon = (double)a.get("Longitude");
					
					newAirport.set("Latitude", lat);
					newAirport.set("Longitude", lon);
					break;

				case "runways":

					JSONArray adata = (JSONArray)_data.get(e);
					
					for(int bb=0; bb<adata.size(); bb++){
						long num=0, length = 0;
						ArrayList<rNode> rNodeList = new ArrayList<rNode>();
						
						JSONObject __data = (JSONObject) (adata).get(bb);
						Set<String> rkey = __data.keySet();
						for(String re : rkey){
							switch(re){
							case "nodes":
								JSONArray rNodeArray = (JSONArray)__data.get(re);
								
								for(int cc = 0; cc<rNodeArray.size(); cc++){
									rNode rnodes1 = new rNode();
									JSONObject testNode = (JSONObject) rNodeArray.get(cc);
									
									rnodes1.x = (long)testNode.get("x");
									rnodes1.y = (long)testNode.get("y");
									rnodes1.z = (long)testNode.get("z");
									
									rNodeList.add(rnodes1);
								}
								
								break;
								
							case "num":
								num = (long)__data.get(re);
								break;
								
							case "length":
								length = (long)__data.get(re);
								break;
							}
						}
						
						newAirport.setRunways(num, length, rNodeList);
						
					}
					
					break;
				}
			}
			
			newAirport.Data();
			this.airportList.add(newAirport);
		}
	}
	
	private void _addDataAirplane(JSONObject data) {
		@SuppressWarnings("unchecked")
		Set<String> keys = (Set<String>)data.keySet();
		List<String> lonList = Arrays.asList("FuelTank", "BodyWeight", "MTOW", "MTOW_MaxDistance", "MaxSpeed", "CrusingSpeed");
		List<String> strList = Arrays.asList("Name");
		List<String> douList = Arrays.asList("WingLength", "Length", "Height", "WingAngle",
											 "BodyWidth");
		
		for(String i:keys){
			Plane newPlane = new Plane();
			
			@SuppressWarnings("unchecked")
			JSONObject _data = (JSONObject)data.get(i);
			Set<String> _keys = (Set<String>)_data.keySet();

			for(String e:_keys){
				if(lonList.contains(e)) {
					newPlane.set(e, (long)_data.get(e));
				} else if(strList.contains(e)) {
					newPlane.set(e, (String)_data.get(e));
				} else if(douList.contains(e)) {
					newPlane.set(e, (double)_data.get(e));
				}
			}
			
			newPlane.Data();
			this.planeTypeList.add(newPlane);
		}
	}
	
	public void setData(JSONObject data){
		@SuppressWarnings("unchecked")
		Set<String> keys = (Set<String>)data.keySet();
		for(String i : keys){
			if(i.equals("Airports")) {
				this._addDataAirport((JSONObject) data.get(i));
			} else if(i.equals("Planes")) {
				this._addDataAirplane((JSONObject) data.get(i));
			}
		}
		
	}
	
	public synchronized void run() {
		int de=8;
		
		while(this._thread){
			if(FlyingplaneList.isEmpty()){
				this.pause();
			}
			
			for(Plane i : FlyingplaneList) {
				for(Airport ap : airportList){ // �뜝�뙎�궪�삕 �뜝�떛琉꾩삕 �뜝�룞�삕�뜝�룞�삕
					ap.PlaneLandingTakeOff(i, "TakeOff");
					System.out.println(i.getStatus()+"   PlaneLaindingTakeOffEngine : "+ap.getString("Name"));
					System.out.println(i.getStatus()+"   PlaneLaindingTakeOffEngine : "+i.getString("Name"));
				}
				i.Move();
				
				System.out.println("running");
				System.out.println(i.getString("Name")+ "-latitude : "+i.getDouble("Latitude"));
				System.out.println(i.getString("Name")+ "-longitude : "+i.getDouble("Longitude"));
				System.out.println(i.getString("Name")+ "-altitude : "+i.getDouble("Altitude"));
				System.out.println();
				
				if(i.getStatus()==3){
					de = FlyingplaneList.indexOf(i);
//					FlyingplaneList.remove(FlyingplaneList.indexOf(i));
					System.out.println("Bye!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
			}
			if(de!=8){
				FlyingplaneList.remove(de);
				de=8;
			}
			try {
				Thread.sleep(30);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.frame.repaint();
		}
	}
	
	public synchronized void pause(){
		if(this._thread){
			this._thread = false;
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this._thread = true;
			this.notify();
		}
	}
	
	public synchronized void createPlane(JSONObject data){ // �뜝�룞�삕�뜝�뙎�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕
		
		JSONArray flyingPlane = (JSONArray) data.get("Flying_Planes");
		Plane testP = null;
		
		for(Object i : flyingPlane){
			
			JSONObject cpl = (JSONObject)i;
			Set<String> keys = (Set<String>)cpl.keySet();
			
			for(Plane p : planeTypeList){
				if(p.getString("Name").equals(cpl.get("modelNumber")) ){
					testP = p;
					System.out.println("Name==modelNumber Plane : "+testP.getString("Name"));
					System.out.println("Name==modelNumber Plane : "+cpl.get("CodeName"));
				}
			}

			for(String k : keys){
				switch(k){
				case "CodeName":
					testP.set(k, cpl.get(k).toString());
					break;
					
				case "Company":
					testP.set(k, cpl.get(k).toString());
					break;
					
				case "coordinate":
					JSONObject coordinateObject = (JSONObject) cpl.get(k);
					Set<String> ckeys = (Set<String>)coordinateObject.keySet();
					for(String ccdi : ckeys){
						//System.out.println(ccdi+" "+coordinateObject.get(ccdi));
						long abc = (long)coordinateObject.get(ccdi);
						testP.set(ccdi, (double)abc);
					}
					break;
					
				case "graph":
					JSONObject graphObject = (JSONObject)cpl.get(k);
					Set<String> gkeys = (Set<String>)graphObject.keySet();
					for(String ggdi : gkeys){
						testP.set(ggdi, graphObject.get(ggdi).toString());
					}
					break;
				}
			}
			FlyingplaneList.add(testP);
			
			for(Plane ikdj : FlyingplaneList){
				System.out.println("인터넷이 안되겠지 "+ikdj.getString("CodeName"));
			}
		}
		
		for(Plane pn : FlyingplaneList){
			for(Airport ap : airportList){
				if(ap.getString("Name").equals(pn.startSpot) ){
					System.out.println("Set "+pn.getString("CodeName"));
					ap.SetPlane(pn);
					ap.setGraph();
					pn.setRoot(ap.getGraph());
					pn.Spin();
				}
			}
		}
	}
	
	public void setFrame() {
		this.frame = new TestGraphics(this.FlyingplaneList);
	}
	
	public void allGraphCreate(){
		allGraph.AddVertex(2750,400,13);
		
		allGraph.AddVertex(1004,457,13);
		
		allGraph.AddVertex(1100,500,13);
		
		allGraph.AddVertex(1500,450,13);
		allGraph.AddVertex(2000,400,13);
		
		allGraph.AddVertex(2700,360,13);
		
		allGraph.VertexSetEdges(allGraph.vertex.get(0), allGraph.vertex.get(4));
		allGraph.VertexSetEdges(allGraph.vertex.get(4), allGraph.vertex.get(3));
		allGraph.VertexSetEdges(allGraph.vertex.get(3), allGraph.vertex.get(1));
		
		allGraph.VertexSetEdges(allGraph.vertex.get(2), allGraph.vertex.get(3));
		allGraph.VertexSetEdges(allGraph.vertex.get(3), allGraph.vertex.get(4));
		allGraph.VertexSetEdges(allGraph.vertex.get(4), allGraph.vertex.get(5));
	}
}