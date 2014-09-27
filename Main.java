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
	
	ArrayList airportList = new ArrayList();
	
	public static void main(String argv[]){
		Main mai = new Main(); // 메인 클래스
		JSONParser parser = new JSONParser();
		
		try{
			Object obj = parser.parse(new FileReader("C:\\Users\\rockpell\\workspace\\Project-Ari\\src\\Engine\\resource\\airport_text.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			
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
		Airport a = (Airport) airportList.get(0);
		System.out.println("test Name :!>!!  : "+a.getName());
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
		
		JSONObject __o = new JSONObject();
			
			JSONObject _o = (JSONObject) o;
			
			
			if(!_o.isEmpty()) {
				
				Set<?> keys = _o.keySet();
				
				System.out.println(keys);
				
				for(Object i : keys){
					
					System.out.print("here      "+i+"       ");
					if(i.equals("Dalars")){  // 공항 리스트 추가
						airportList.add(new Airport());
					}
					else if(i.equals("Narita")){
						airportList.add(new Airport());
					}
					
					if(i.equals("name")){ // 이름 등록
						Airport test1 = (Airport) airportList.get(airportList.size()-1);
						test1.setName(_o.get(i).toString());
						airportList.set(airportList.size()-1, test1);
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
							//System.out.println("!@S");
							
							__o.put(i, ab);
						}
						
//						_o.remove(i);
						
					}
				}
			}
			return _o;
		
	}
}
