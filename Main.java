import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Engine.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {
	
	public static void main(String argv[]){
		Engine eng = Engine.getInstance();

		JSONParser parser = new JSONParser();
		
		try{
			Object obj;
			File d = new File("src/Engine/resource_temp/airport_text.json");
			if(d.exists()) {
				 obj = parser.parse(new FileReader("src/Engine/resource_temp/airport_text.json"));
			} else {
				 obj = parser.parse(new FileReader("src/Engine/resource/airport_text.json"));
			}
			
			JSONObject jsonObject = (JSONObject) obj;
		
			eng.setData(jsonObject);
			eng.createPlane(jsonObject);
			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		eng.setFrame();
		eng.start();
		
	}
	
}
