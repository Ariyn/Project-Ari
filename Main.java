import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Engine.Plane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

public class Main {
	public static void main(String argv[]){
		
		Plane a = new Plane();
		//System.out.println(a.code);
		
		JSONParser parser = new JSONParser();
		
		String x1 = null, x2 = null, x3= null, x4 = null;
		
		try{
			Object obj = parser.parse(new FileReader("C:\\Users\\rockpell\\workspace\\Project-Ari\\src\\Engine\\resource\\airport_text.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			//JSONObject airports = (JSONObject)jsonObject.get("Airports");
			
			//JSONArray msg = (JSONArray) jsonObject.get("Airports");
			//Iterator<String> iterator = msg.iterator();
			
			/*
			 Set<?> keys = airports.keySet();
			
			for(Object i : keys){
				System.out.println(i);
				System.out.println(airports.get(i));
			}*/
			
			JSONObject abc = JsonParsing(obj);
			
			
			System.out.println(abc.get("postion"));
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		/*System.out.println("AirPortName : " + x1);
		System.out.println("latitude : " + x2);
		System.out.println("altitude : " + x3);
		System.out.println("runwayLength : "+ x4);
		*/
	}
	public static JSONArray JsonParsingArr(Object o){
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
	
	public static JSONObject JsonParsing(Object o){
		
		JSONObject __o = new JSONObject();
			
			JSONObject _o = (JSONObject) o;
			//System.out.println("!!!!!");
			
			
			if(!_o.isEmpty()) {
				
				Set<?> keys = _o.keySet();
				
				System.out.println(keys);
				
				//System.out.println(keys);
				
				for(Object i : keys){
						
//					System.out.println(i);
//					System.out.println(_o.get(i));
		
					
					
					System.out.print("here      "+i+"       ");
					
					System.out.print(_o.get(i)+"       ");
					
					System.out.println(_o.get(i).getClass());
					
					Object _tempO = _o.get(i);
					Class<?> c = _tempO.getClass();
					if(c == String.class || c == Integer.class || c == Boolean.class || c == Long.class){

					} else {
						/*if(o instanceof JSONArray){
							JSONArray _v = JsonParsingArr(_tempO);
							__o.put(i, _v);
							return null;
						} else */
						
						if(_tempO instanceof JSONObject) {
							JSONObject _v = JsonParsing(_tempO);
							__o.put(i, _v);
						} else if(_tempO instanceof JSONArray){
							JSONArray ab = JsonParsingArr(_tempO);
							System.out.println("!@S");
	
							__o.put(i, ab);
						}	
						
//						_o.remove(i);
						
					}
				}
			}
			return _o;
		
		
		
//		JSONObject airport1 = (JSONObject) o.get("Dalars");
		
		/*Set<?> keys2 = airport1.keySet();
		
		for(Object i : keys2){
			System.out.println(i);
			System.out.println(o.get(i));
			
		}*/
		
		
		//String name = (String)airport1.get("name");
		//System.out.println(name);
		
		//JSONObject position = (JSONObject) airport1.get("position");
		
		//long latitude = (Long)position.get("latitude");
		//long altitude = (Long)position.get("altitude");
		//System.out.println(latitude);
		//System.out.println(altitude);
		
		//JSONObject runways = (JSONObject)airport1.get("runways");
		
		//JSONArray msg = (JSONArray)airport1.get("runways");
		
		/*long num = (Long)runways.get("num");
		long length = (Long)runways.get("length");*/
		
		//airport1.put("runways", (JSONArray)airport1.get("runways"));
		
		//return airport1;
	}
}
