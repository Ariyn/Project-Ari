import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Engine.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {
	
	public static void main(String argv[]){
		Engine eng = Engine.getInstance();
		//eng.setFrame();
		JSONParser parser = new JSONParser();
		
		try{
			
			Object obj = parser.parse(new FileReader("src/Engine/resource/airport_text.json"));
			Object fpText = parser.parse(new FileReader("src/Engine/resource/FlyingPlane_text.json"));
			
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject jsonObject2 = (JSONObject) fpText;
		
			eng.setData(jsonObject);
			eng.createPlane(jsonObject);
			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		eng.start();

	}
	
}
