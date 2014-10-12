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
	ArrayList <Plane> planeList = new ArrayList<Plane>();	

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
//		while(){
			for(Plane i : this.planeList) {
				i.Move();
				System.out.println("running");
			}
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//	}
	
	public void TestSet() {
		Plane p = new Plane();
	}
	
	public void createPlane(JSONObject data){ // 공항에 실제 비행기 생성
		
		JSONArray flyingPlane = (JSONArray) data.get("Flying_Planes");
		
		for(Object i : flyingPlane){
			
		}
	}
}