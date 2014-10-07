import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import Engine.Airport;
import Engine.Engine;
import Engine.Plane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.*;

public class Main {
	
	public static void main(String argv[]){
		Main mai = new Main(); // 메인 클래스
		Engine eng = new Engine();
		JSONParser parser = new JSONParser();
		
		try{
			Object obj = parser.parse(new FileReader("src\\Engine\\resource\\airport_text.json"));

			JSONObject jsonObject = (JSONObject) obj;
			
			JSONObject abc = eng.JsonParsing(obj); // 파싱
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		
	}
	
	/*public void test528(){
		Airport a =  airportList.get(0);
		Airport b =  airportList.get(1);
		
		System.out.println("test Name :!>!!  : "+a.getName());
		System.out.println("test Name :!>!!  : altitude : "+a.getPositionX() + " latitude : " +a.getPositionY());
		System.out.println("test Name :!>!!  : runwayLength : "+a.getRunwayLength(0));
		System.out.println("test Name :!>!!  : "+b.getName());
		System.out.println("test Name :!>!!  : altitude : "+b.getPositionX() + " latitude : " +b.getPositionY());
		System.out.println("test Name :!>!!  : runwayLength : "+b.getRunwayLength(0));
		
	}*/
	
	
}
