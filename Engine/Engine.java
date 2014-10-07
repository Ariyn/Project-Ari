package Engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

public class Engine {

	ArrayList <Airport> airportList = new ArrayList<Airport>();
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
	
	public JSONArray JsonParsingArr(Object o){
		JSONArray _o = (JSONArray) o;
		JSONArray ro = new JSONArray();
		int abc = _o.size();
		for(int i=0; i<abc; i++){
			ro.add(i,JsonParsing(_o.get(i)));
		}
		
		return ro;
	}
	
	public JSONObject JsonParsing(Object o){
		
			long number=0;
		
			JSONObject __o = new JSONObject();
			
			JSONObject _o = (JSONObject) o;
			
			ArrayList airportRunway = new ArrayList();
			
			if(!_o.isEmpty()) {
				
				Set<?> keys = _o.keySet();
				
				System.out.println(keys);
				
				for(Object i : keys){
					
					System.out.print("here      "+i+"       ");
					if(i.equals("Airports") || i.equals("PlaneType")){
						testText = i.toString();
						System.out.println("does it works?????" + testText);
					}
					
					if(i.equals("Dalars")){  // 공항 리스트 추가
						airportList.add(new Airport());
					}else if(i.equals("Narita")){
						airportList.add(new Airport());
					}else if(i.equals("A330")){
						planeList.add(new Plane());
					}else if(i.equals("MIG-29")){
						planeList.add(new Plane());
					}
					
					if(testText == "Airports"){
						if(i.equals("name")){ // 이름 등록
							Airport test1 = airportList.get(airportList.size()-1);
							test1.setName(_o.get(i).toString());
							airportList.set(airportList.size()-1, test1);
						}else if(i.equals("altitude")){
							Airport test1 =  airportList.get(airportList.size()-1);
							test1.setPositionX((long)_o.get(i));
							airportList.set(airportList.size()-1, test1);
						}else if(i.equals("latitude")){
							Airport test1 = airportList.get(airportList.size()-1);
							test1.setPositionY((long)_o.get(i));
							airportList.set(airportList.size()-1, test1);
						}
						
						if(i.equals("num")){
							number = (long)_o.get(i) - 1;
						}
						if(i.equals("length")){
							Airport test1 = airportList.get(airportList.size()-1);
							airportRunway.add((int)number, (long)_o.get(i));
							number = 0;
							test1.setRunwyas((int)number, (long)airportRunway.get(airportRunway.size()-1));
							airportList.set(airportList.size()-1, test1);
						}
						
					}
					
					if(testText == "PlaneType"){
						if(i.equals("fuleTank")){
							Plane test2 = planeList.get(planeList.size()-1);
						}else if(i.equals("code")){
							Plane test2 = planeList.get(planeList.size()-1);
						}else if(i.equals("engine")){
							Plane test2 = planeList.get(planeList.size()-1);
						}else if(i.equals("company")){
							Plane test2 = planeList.get(planeList.size()-1);
						}else if(i.equals("maxSpeed")){
							Plane test2 = planeList.get(planeList.size()-1);
						}
						
					}
					
					//System.out.println(_o.get(i).getClass());
					
					Object _tempO = _o.get(i);
					Class<?> c = _tempO.getClass();
					if(c == String.class || c == Integer.class || c == Boolean.class || c == Long.class){

					} else {
						
						if(_tempO instanceof JSONObject) {
							JSONObject _v = JsonParsing(_tempO);
							__o.put(i, _v);
						} else if(_tempO instanceof JSONArray){
							JSONArray ab = JsonParsingArr(_tempO);
							
							__o.put(i, ab);
						}
						
//						_o.remove(i);
						
					}
				}
			}
			return _o;
		
	}
}
