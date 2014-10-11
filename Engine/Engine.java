package Engine;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Engine {

	ArrayList <Airport> airportList = new ArrayList<Airport>();
	ArrayList <Plane> planeTypeList = new ArrayList<Plane>();
	ArrayList <Plane> planeList = new ArrayList<Plane>();	

	String testText = "a";

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
	
	public void setData(JSONObject data){
		int indexi = 0;
		ArrayList portName = new ArrayList();
		ArrayList typeName = new ArrayList();
		
		portName.add("Dalars");
		portName.add("Narita");
		
		typeName.add("A330");
		typeName.add("MIG-29");
		
		for(Iterator<String> i= typeName.iterator(); i.hasNext();){
			Plane medium = new Plane();
			
			String item = i.next();
			
			JSONObject planeObject = (JSONObject) data.get("PlaneType");
			JSONObject planeType = (JSONObject) planeObject.get(item);
			
			medium.set("Name", planeType.get("code").toString());
			medium.set("FuelTank", (long) planeType.get("fuleTank"));
			medium.set("MaxSpeed", (long)planeType.get("maxSpeed"));
			
			planeTypeList.add(medium);
		}
		System.out.println(" "+airportList.get(0).getString("Name") + " " +airportList.get(1).getString("Name"));
		System.out.println(""+planeTypeList.get(0).getString("Name")+ " " +planeTypeList.get(1).getString("Name"));

	}
	
	public void Run() {
		for(Plane i : this.planeList) {
//			i.
		}
	}
	public void Start() {
		
	}
	public void Stop() {
		
		
	}
	
	public void createPlane(JSONObject data){ // 공항에 실제 비행기 생성
		
		JSONArray flyingPlane = (JSONArray) data.get("Flying_Planes");
		
		for(Object i : flyingPlane){
			
		}
	}
}