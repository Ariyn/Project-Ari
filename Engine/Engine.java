package Engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Engine extends Thread{
	
	TestGraphics frame;

	ArrayList <Airport> airportList = new ArrayList<Airport>();
	ArrayList <Plane> planeTypeList = new ArrayList<Plane>();
	ArrayList <Plane> FlyingplaneList = new ArrayList<Plane>();
	ArrayList <Plane> FlyingplaneList2 = new ArrayList<Plane>();
	
	boolean _thread = true;
	
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
			newAirport.setGraph();
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
	
	public void run() {
		int de=8;
		
		while(this._thread){
			if(FlyingplaneList.isEmpty() || this._thread == false){
				this.pause();
			}
			
			for(Airport ap : airportList){ // �뜝�뙎�궪�삕 �뜝�떛琉꾩삕 �뜝�룞�삕�뜝�룞�삕
				Plane i = ap.PlaneLandingTakeOff("TakeOff");
				if(i != null) {
					i.altitude = ap.altitude;
					i.longitude = ap.longitude;
					i.latitude = ap.latitude;
					
					if(i.codeName.equals("AA013"))
						System.out.println("AA013 root"+i.root.head.coordinate(0)+"\t"+i.root.head.coordinate(1)+"\t"+i.root.head.coordinate(2));
					
					i.Spin();
					
					if(i.codeName.equals("AA013"))
						System.out.println("AA013 root"+i.root.head.coordinate(0)+"\t"+i.root.head.coordinate(1)+"\t"+i.root.head.coordinate(2));

					
					this.FlyingplaneList2.add(i);
//					this.FlyingplaneList.remove(i);
					System.out.println(i.getStatus()+"   PlaneLaindingTakeOffEngine : "+ap.getString("Name"));
					System.out.println(i.getStatus()+"   PlaneLaindingTakeOffEngine : "+i.getString("CodeName"));
				}
				
			}
			
			for(Plane i : FlyingplaneList2) {
				
				i.Move();
				if(i.codeName.equals("AA013") || i.codeName.equals("AA012")){
					System.out.println("running \t\t"+i.root);

					Dictionary<String, Object> d = i.root.getNextNode();
					if(d.get("next") != null && d.get("node") != null)
						System.out.printf("%s's coordinate = %f, %f, %f\n",
								i.codeName,
								((GNode)d.get("node")).coordinate(GNode.LATI),
								((GNode)d.get("node")).coordinate(GNode.LONG),
								((GNode)d.get("node")).coordinate(GNode.ALT));

					System.out.println(i.getString("Name")+ "-latitude : "+i.getDouble("Latitude"));
					System.out.println(i.getString("Name")+ "-longitude : "+i.getDouble("Longitude"));
					System.out.println(i.getString("Name")+ "-altitude : "+i.getDouble("Altitude"));
					
					System.out.printf("%s's coordinate = %f, %f, %f", i.codeName,i.dx,i.dy,i.dz);
					
					System.out.println();
					System.out.println();
					
					if(i.codeName.equals("AA013") && i.dx==0){
						//this.pause();
					}
				}

				
//				if(i.getStatus()==3){
//					de = FlyingplaneList.indexOf(i);
//					System.out.println("Bye!!!!!!!!!!!!!!!!!!!!!!!!!!");
//				}
				if(i.getStatus()==3){

					de = FlyingplaneList2.indexOf(i);
//					FlyingplaneList.remove(FlyingplaneList.indexOf(i));
					System.out.println("Bye!!!!!!!!!!!!!!!!!!!!!!!!!!");
				}
			}
			if(de!=8){
				FlyingplaneList2.remove(de);
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
	public void setRun(boolean r) {
		this._thread = r;
	}
	public synchronized void pause(){
		System.out.println("engine pause");
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
	
	public void createPlane(JSONObject data){ // �뜝�룞�삕�뜝�뙎�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕
		
		JSONArray flyingPlane = (JSONArray) data.get("Flying_Planes");
		Plane testP = null;
		
		for(Object i : flyingPlane){
			
			JSONObject cpl = (JSONObject)i;
			Set<String> keys = (Set<String>)cpl.keySet();
			
			for(Plane p : planeTypeList){
				if(p.getString("Name").equals(cpl.get("modelNumber")) ){

					testP = p.clone();
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

			System.out.println("인터넷이 안되겠지 "+testP.getString("CodeName"));
		}

		
		for(Plane pn : FlyingplaneList){
			for(Airport ap : airportList){
				if(ap.getString("Name").equals(pn.startSpot) ){
					System.out.println("Set "+pn.getString("CodeName"));

					ap.SetPlane(pn);					

					pn.setRoot(ap.getGraph());
					pn.Spin();
				}
			}
		}
		
		for(Plane pn : FlyingplaneList){
			if(pn.codeName.equals("AA012")){
				System.out.println("AA012 root"+pn.root.head.coordinate(0)+"\t"+pn.root.head.coordinate(1)+"\t"+pn.root.head.coordinate(2));
				pn.root.nextNode();
				System.out.println("AA012 root"+pn.root.head.coordinate(0)+"\t"+pn.root.head.coordinate(1)+"\t"+pn.root.head.coordinate(2));
			}
			if(pn.codeName.equals("AA013")) {
				System.out.println("AA013 root"+pn.root.head.coordinate(0)+"\t"+pn.root.head.coordinate(1)+"\t"+pn.root.head.coordinate(2));
			}
		}
	}
	
	public void save() {
		File d = new File("src/Engine/resource_temp");
		if(!d.exists()) {
			d.mkdir();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("src/Engine/resource_temp/airport_text.json"));
			out.write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setFrame() {
		this.frame = new TestGraphics(this);
		this.setList();
	}
	public void setList() {
		this.frame.setList(this.FlyingplaneList2, this.airportList);
	}
	
}