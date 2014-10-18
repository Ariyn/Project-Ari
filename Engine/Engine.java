package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Engine extends Thread{

	ArrayList <Airport> airportList = new ArrayList<Airport>();
	ArrayList <Plane> planeTypeList = new ArrayList<Plane>();
	ArrayList <Plane> FlyingplaneList = new ArrayList<Plane>();	

	/*boolean firstInit = false;
	
	public Engine() {
		this.FileRead();
		
		this.firstInit = true;
	}
	
	public void EngineRun() {
		
	}
	
	public void FileRead() {
//		airports.json, planetype.json, flyingplane.json
//		그 외 기타 json파일들(=새롭게 추가될 수 있는 파일)
		ArrayList<String> errorFile = new ArrayList<String>();
		
		if(! this.firstInit){
			String[] arr = new String[3];
			arr[0] = "src\\resource\\airport_text.json";
			arr[1] = "src\\resource\\airport_text.json";
			arr[2] = "src\\resource\\airport_text.json";
			
			for(String i : arr){
				try {
					this._FileRead(new FileReader(i));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					errorFile.add(i);
				}
			}
		} else {
			
		}
	}
	
	private void _FileRead(FileReader fr) {
		Engine engine = new Engine();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(fr);

			JSONObject jsonObject = (JSONObject) obj;
			
			JSONObject abc = engine.JsonParsing(obj); // 파싱

		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
	}
	*/
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

			//newAirport.planeList = this.planeList;
			
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
					
					newAirport.set("Latitude", (long) Math.floor(lat));
					newAirport.set("x", Math.floor((lat-Math.floor(lat))*10000.0)/10.0);
					newAirport.set("Longitude", (long) Math.floor(lon));
					newAirport.set("y", Math.floor((lon-Math.floor(lon))*10000.0)/10.0);
//					System.out.println(Math.floor((lat-Math.floor(lat))*10000.0)/10.0);
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
		List<String> strList = Arrays.asList("Company", "Name");
		List<String> douList = Arrays.asList("WingLength", "Length", "Height", "WingAngle",
											 "BodyWidth");
		
		for(String i:keys){
			Plane newPlane = new Plane();
			
			@SuppressWarnings("unchecked")
			JSONObject _data = (JSONObject)data.get(i);
			Set<String> _keys = (Set<String>)_data.keySet();

			for(String e:_keys){
//				System.out.println(e+" "+_data.get(e));
				if(lonList.contains(e)) {
					newPlane.set(e, (long)_data.get(e));
//					System.out.println(e+" "+(long)_data.get(e));
				} else if(strList.contains(e)) {
					newPlane.set(e, (String)_data.get(e));
//					System.out.println(e+" "+_data.get(e));
				} else if(douList.contains(e)) {
//					System.out.println(e+" "+_data.get(e));
					newPlane.set(e, (double)_data.get(e));
				}
			}
			
			newPlane.Data();
			this.planeTypeList.add(newPlane);
//			System.out.println(newPlane);
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
		
//		int indexi = 0;
//		ArrayList portName = new ArrayList();
//		ArrayList typeName = new ArrayList();
//		
//		System.out.println("keys  "+ data.keySet());
//		
//		portName.add("Dalars");
//		portName.add("Narita");
//		
//		typeName.add("A330");
//		typeName.add("MIG-29");
//		
//		for(Iterator<String> i= typeName.iterator(); i.hasNext();){
//			Plane medium = new Plane();
//			
//			String item = i.next();
//			
//			JSONObject planeObject = (JSONObject) data.get("PlaneType");
//			JSONObject planeType = (JSONObject) planeObject.get(item);
//			
//			medium.setName(planeType.get("code").toString());
//			medium.setfuelTank((long) planeType.get("fuleTank"));
//			medium.setMaxSpeed((long)planeType.get("maxSpeed"));
//			
//			planeTypeList.add(medium);
//		}
//		System.out.println(" "+airportList.get(0).getName() + " " +airportList.get(1).getName());
//		System.out.println(""+planeTypeList.get(0).getName()+ " " +planeTypeList.get(1).getName());
	}
	
	public void run() {
		while(true){
			
			for(Plane i : FlyingplaneList) {
				for(Airport ap : airportList){ // 항상 이륙 실행
					ap.PlaneLandingTakeOff(i, "TakeOff");
				}
				
				i.Spin();
				i.Move();
				System.out.println("running");
				System.out.println(i.getString("Name")+"-x : "+i.getDouble("x"));
				System.out.println(i.getString("Name")+ "-latitude : "+i.getLong("Latitude"));
				System.out.println(i.getString("Name")+ "-longitude : "+i.getLong("Longitude"));
				System.out.println(i.getString("Name")+ "-altitude : "+i.getLong("Altitude"));
				System.out.println();
			}
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void createPlane(JSONObject data){ // 공항에 실제 비행기 생성
		
		JSONArray flyingPlane = (JSONArray) data.get("Flying_Planes");
		Plane testP = null;
		
		for(Object i : flyingPlane){
			
			JSONObject cpl = (JSONObject)i;
			Set<String> keys = (Set<String>)cpl.keySet();
			
			for(Plane p : planeTypeList){
				
				if(p.getString("Name").equals(cpl.get("modelNumber")) ){
					testP = p;
				}
			}

			for(String k : keys){
				switch(k){
				case "modelNumber":
					testP.set(k, cpl.get(k).toString());
					break;
					
				case "company":
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
			
		}
		
		for(Plane pn : FlyingplaneList){
			for(Airport ap : airportList){
				if(ap.getString("Name").equals(pn.startSpot) ){

					ap.SetPlane(pn);
					ap.setGraph();
					pn.setRoot(ap.getGraph());
				}
			}
		}
	}
}