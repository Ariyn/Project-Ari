import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

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
		eng.setFrame(false);
		
		Main.MatrixTime(3000);
//		eng.start();
		
	}
	public static void MatrixTime(int delayTime){

	       long saveTime = System.currentTimeMillis();
	       long currTime = 0;

	       while( currTime - saveTime < delayTime){
	           currTime = System.currentTimeMillis();
	       }
	   }
}

/*
 * A330-latitude : 2700.1559840840687
	A330-longitude : 360.0
	A330-altitude : 0.5793694551124018
	
	A330-latitude : 2700.6
	A330-longitude : 360.0
	A330-altitude : 0.0
	*/
