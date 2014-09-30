import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Engine.Airport;
import Engine.Plane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

public class Main {
	
	ArrayList <Airport> airportList = new ArrayList();
	ArrayList <Plane> planeList = new ArrayList();
	
	String testText;
	
	public static void main(String argv[]){
		Main mai = new Main(); // 메인 클래스
		JSONParser parser = new JSONParser();
		
		try{
			Object obj = parser.parse(new FileReader("/Users/hongjuhyeong/Documents/workspace/project-ahri/src/Engine/resource/airport_text.json"));

			//JSONObject jsonObject = (JSONObject) obj;
			
			JSONObject abc = mai.JsonParsing(obj); // 파싱
			
			mai.test528();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}
	
	public void test528(){
		Airport a =  airportList.get(0);
		Airport b =  airportList.get(1);
		
		System.out.println("test Name :!>!!  : "+a.getName());
		System.out.println("test Name :!>!!  : altitude : "+a.getPositionX() + " latitude : " +a.getPositionY());
		System.out.println("test Name :!>!!  : runwayLength : "+a.getRunwayLength(0));
		System.out.println("test Name :!>!!  : "+b.getName());
		System.out.println("test Name :!>!!  : altitude : "+b.getPositionX() + " latitude : " +b.getPositionY());
		System.out.println("test Name :!>!!  : runwayLength : "+b.getRunwayLength(0));
		
	}
	
	public JSONArray JsonParsingArr(Object o){
		JSONArray _o = (JSONArray) o;
		JSONArray ro = new JSONArray();
		int abc = _o.size();
		for(int i=0; i<abc; i++){
			JSONObject order = (JSONObject) _o.get(i);
			System.out.print("!!!     ");
			System.out.println(order);
			//_o.get(i) = JsonParsing(order);
			ro.add(i,JsonParsing(order));
			
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
					if(i.equals("Airports")){
						testText = i.toString();
					}else if(i.equals("PlaneType")){
						testText = i.toString();
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
					
					System.out.print(_o.get(i)+"       \n");
					
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
