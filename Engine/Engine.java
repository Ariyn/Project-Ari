package Engine;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Engine {

	ArrayList <Airport> airportTypeList = new ArrayList<Airport>();
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
		
		ArrayList abdx = new ArrayList();
		
		abdx.add("Dalars");
		abdx.add("Narita");
		
		for (Iterator<String> i = abdx.iterator(); i.hasNext();){
			Airport medium = new Airport();
			
			String item = i.next();
			
			JSONObject airportObject = (JSONObject) data.get("Airports");
			JSONObject airportName = (JSONObject) airportObject.get(item);
			JSONObject airportPosition = (JSONObject) airportName.get("position");
			JSONArray airportRunways = (JSONArray) airportName.get("runways");
			
			medium.setName(airportName.get("name").toString());
			medium.setPositionX( (long) airportPosition.get("latitude") );
			medium.setPositionY( (long) airportPosition.get("longtitude"));
			medium.setRunwyas( (long)( (JSONObject)airportRunways.get(0)).get("num"), (long)( (JSONObject)airportRunways.get(0)).get("length") );
			medium.setMaxAirplane( (long)airportName.get("maximum_airplane") );
					
			airportTypeList.add(medium);
			
			System.out.println("i : "+item);
		}
		
		System.out.println(" "+airportTypeList.get(0).getName() + " " +airportTypeList.get(1).getName());	
	}
	
	public void Run() {
		for(Plane i : this.planeList) {
			i.
		}
	}
	public void Start() {
		
	}
	public void Stop() {
		
	}
	
}