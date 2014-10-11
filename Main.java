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
		Engine eng = Engine.getInstance();
		JSONParser parser = new JSONParser();
		
		try{

//			Object obj = parser.parse(new FileReader("Engine\\resource\\airport_text.json"));

			Object obj = parser.parse(new FileReader("Engine\\resource\\airport_text.json"));
			Object fpText = parser.parse(new FileReader("Engine\\resource\\FlyingPlane_text.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject jsonObject2 = (JSONObject) fpText;
			
			eng.setData(jsonObject);
			eng.createPlane(jsonObject2);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
	
	}
	
}
