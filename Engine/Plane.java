package Engine;

import java.util.Dictionary;

public class Plane implements Cloneable{
	double weight, length, height, wingAngle;
	double wingLength, bodyWidth;

	long fuelTank, fuel=100;
	int bodyWeight, payloadWeight, MTOW, M_maxDistance;
	long maxspeed, crusingSpeed;
	
	double speed=5.6;
	
	double dx=0,dy=0;
	double ddx=0, ddy=0, ddz=0;
	
	double angleX=0,angleY=0; //Math.toRadians(30);
	double latitude, longitude, altitude; 
	
	String name, codeName, company;
	String startSpot, endSpot;
	double dz = 0; //speed*Math.tan(angleY);
	
	int status = 0;
	Graph root;

	public Plane(){
		

	}
	public Plane clone(){  
	    try{  
	        return (Plane)super.clone();  
	    }catch(Exception e){ 
	    	e.printStackTrace();
	        return null; 
	    }
	}
	
	public Plane(Plane type){
		this.maxspeed = type.maxspeed;
		this.speed = type.speed;
		this.fuelTank = type.fuelTank;
		this.company = type.company;
	}

	public void makerPlane(Plane type){
		//System.out.println("makerPlane in");
		this.maxspeed = type.maxspeed;
		this.speed = type.speed;
		this.fuelTank = type.fuelTank;
		this.company = type.company;
		this.speed = this.maxspeed;
	}

	public long getLong(String s){
		switch(s){
		case "FuelTank":
			return fuelTank;
		default:
			return 0;
		}

	}
	public double getDouble(String s){
		switch(s){
		case "MaxSpeed":
			return maxspeed;
		case "speed":
			return speed;
		case "Latitude":
			return latitude;
		case "Longitude":
			return longitude;
		case "Altitude":
			return altitude;
		default:
			return 0;
		}
	}
	public int getStatus(){
		
		return status;
	}

	public String getString(String s){
		switch(s){
		case "Name":
			return name;
		case "Company":
			return company;
		case "CodeName":
			return codeName;
		case "StrartSpot":
			return startSpot;
		case "EndSpot":
			return endSpot;
		default:
			return "string default";
		}

	}

	//setter

	public void set(String s, long l){
		switch(s)
		{
		case "FuelTank":
			fuelTank=(int)l;
			break;
		case "BodyWeight":
			bodyWeight = (int)l;
			break;
		case "MTOW":
			MTOW = (int)l;
			break;
		case "MTOW_MaxDistance":
			M_maxDistance = (int)l;
			break;
		case "Maxspeed":
			maxspeed=l;
			break;
		case "CrusingSpeed":
			crusingSpeed = l;
			break;
		}

	}
	public void set(String s, double l){
		switch(s)
		{
			case "Length":
				length = l;
				break;
			case "Weight":
				weight = l;
				break;
			case "WingLength":
				wingLength = l;
				break;
			case "BodyWidth":
				bodyWidth = l;
				break;
			case "WingAngle":
				wingAngle = l;
				break;
			case "Height":
				height=l;
				break;
			case "Latitude":
				latitude=l;
				break;
			case "Longitude":
				longitude=l;
				break;
			case "Altitude":
				altitude=l;
				break;
			case "Speed":
				speed=l;
				break;
		}
	}
	public void set(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		case "Company":
			company=str;
			break;
		case "startSpot":
			startSpot = str;
			break;
		case "endSpot":
			endSpot = str;
			break;
		case "CodeName":
			codeName = str;
			break;
		}
	}
	
	public void setRoot(Graph g){
		this.root = g;
	}
	
	public void setStatus(String text){
		System.out.println(name+" SetStatus !!!!");
		if(text.equals("TakeOff")){
			status=1;
		}else if(text.equals("Flying")){
			status=2;
		}else if(text.equals("Landing")){
			status=3;
		}
	}
	
	public void Move(){
		if(this.passed(0)) {
			this.root.nextNode();
			Spin();
		}
		if(this.passed(1)) {
			this.ddx = this.dx;
			this.dx = 0;
		} 
		if(this.passed(2)) {
			this.ddy = this.dy;
			this.dy = 0;
		}
		if(this.passed(3)) {
			this.ddz = this.dz;
			this.dz = 0;
		}
		
		if (status==1){ //占싱뤄옙 
//			System.out.println("TakeOff!!! "+speed);

			latitude+=dx;
			longitude+=dy;
			altitude+=dz;
			
//			System.out.println("Plane in class dx: "+dx);
//			System.out.println("Plane in class dy: "+dy);
//			System.out.println("Plane in class dz: "+dz);
			if(altitude>=13)setStatus("Flying");
		}

		else if(status==2){ // 占쏙옙占쏙옙占쏙옙
//			System.out.println("Flying!!! "+speed);


			latitude+=dx;
			longitude+=dy;
			
//			System.out.println("Plane in class dx: "+dx);
//			System.out.println("Plane in class dy: "+dy);
//			System.out.println("Plane in class dz: "+dz);
			
			Dictionary<String, Object> dic = this.root.getNextNode();
		}
		
		else if(status==3){
			
			System.out.println("Plane in class dx: "+dx);
			System.out.println("Plane in class dy: "+dy);
			System.out.println("Plane in class dz: "+dz);
			
			latitude+=dx;
			longitude+=dy;
			altitude+=dz;
		}

	}

	public void Fuel(){
		fuel-=(4/fuelTank)*100;
	}

	public void Data(){ // 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占�
		//System.out.println("Airplane name: "+name);
		//System.out.println("Airplane company: "+company);
		//System.out.println("Airplane weight : "+bodyWeight/1000+"T");
		//System.out.println("Airplane length : "+length+"m");
		//System.out.println("Airplane fuelTank : "+fuelTank+"L");

	}
	public void Data2(){ //占실시곤옙占쏙옙占쏙옙 占쌕뀐옙占� 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙
		//System.out.println("Airplane fuel: "+fuel+"%");
		//System.out.println("Airplane Maxspeed: "+maxspeed*10+"m/s");
		//System.out.println("Airplane speed: "+speed*10+"m/s");
		//System.out.println("Airplane altitude: "+altitude+"m");
		//System.out.println("Airplane latitude: "+latitude);
		//System.out.println("Airplane Longitude: "+longitude);
//		if(status==0)//System.out.println("占싱뤄옙");
//		else if(status==1)//System.out.println("占쏙옙占�");
//		else //System.out.println("占쏙옙占쏙옙占쏙옙");

	}
	
	public boolean LandingSign(){
		boolean sign = false;
		
		return sign;
	}
	
	public void Spin(){
		Dictionary<String, Object> dic = this.root.getNextNode();
		double speedP=0;
		double rz=0;
		if(dic.get("next") == Boolean.FALSE) {
			
		} else {
			GNode next = (GNode) dic.get("node");
			
				System.out.println("this.Coor : "+this.coordinate(GNode.LATI)+", "+this.coordinate(GNode.LONG)+" "+this.coordinate(GNode.ALT) +
						"\t\tnext.Coor : "+next.coordinate(GNode.LATI)+", "+next.coordinate(GNode.LONG) + " " +next.coordinate(GNode.ALT) );
				if(next.coordinate(GNode.ALT)-this.coordinate(GNode.ALT) == 0){
					rz=0;
				}else{
					rz=Math.pow(next.coordinate(GNode.ALT)-this.coordinate(GNode.ALT), 2);
				}
				
				speedP = Math.sqrt(Math.pow((next.coordinate(GNode.LATI)-this.coordinate(GNode.LATI)), 2) + Math.pow((next.coordinate(GNode.LONG)-this.coordinate(GNode.LONG)), 2));
				
				System.out.println(next.coordinate(GNode.ALT)+","+this.coordinate(GNode.ALT));
				System.out.println("speedP : "+speedP+",\t\t\taltitude :"+Math.abs(next.coordinate(GNode.ALT)-this.coordinate(GNode.ALT)));
				
				angleY=Math.atan2(Math.abs(next.coordinate(GNode.ALT)-this.coordinate(GNode.ALT)), speedP);
				angleX=Math.atan2(Math.abs(next.coordinate(GNode.LONG)-this.coordinate(GNode.LONG)), Math.abs(next.coordinate(GNode.LATI)-this.coordinate(GNode.LATI)));
				
				
				System.out.println("angle X = "+angleX+"      "+Math.toDegrees(angleX));
				System.out.println("angle Y = "+angleY+"      "+Math.toDegrees(angleY));
				
				dx = speed*Math.cos(angleY)*Math.cos(angleX);
				dy = speed*Math.cos(angleY)*Math.sin(angleX);
				dz = speed*Math.sin(angleY);
				
				if(this.coordinate(GNode.LATI)>next.coordinate(GNode.LATI)){
					dx = -dx;
				}
				if(this.coordinate(GNode.LONG)>next.coordinate(GNode.LONG)){
					dy = -dy;
				}
				if(this.coordinate(GNode.ALT)>next.coordinate(GNode.ALT)){
					dz = -dz;
				}
		}

	}
	public double coordinate(int type){
		double retVal = 0;
		if(type == GNode.LONG) {
			retVal = this.longitude;
		} else if(type == GNode.LATI) {
			retVal = this.latitude;
		} else if(type == GNode.ALT) {
			return this.altitude;
		}
		return retVal;
	}
	
	private boolean passed(int type){
		Dictionary<String, Object> dic = this.root.getNextNode();
		boolean sucX = false, sucY = false, sucZ = false, suc = false;
		if(type == 0){
			if(dic.get("next") == Boolean.TRUE) {
				GNode next = (GNode) dic.get("node");


//				System.out.println(next.coordinate(GNode.LATI)+ " <- 랄라랄라라 -> " +next.coordinate(GNode.LONG) +" 랄라랄라라33 : " + next.coordinate(GNode.ALT));



				if(this.dx > 0) {
					if(next.coordinate(GNode.LATI) <= this.coordinate(GNode.LATI)) {
						sucX = true;
					}
				} else if(this.dx < 0){
					if(next.coordinate(GNode.LATI) >= this.coordinate(GNode.LATI)) {
						sucX = true;
					}
				} else if(this.dx==0){
					if(ddx>0 && next.coordinate(GNode.LATI) > this.coordinate(GNode.LATI)){
						sucX = true;
					} else if(ddx<0 && next.coordinate(GNode.LATI) < this.coordinate(GNode.LATI)){
						sucX = true;
					} else if(next.coordinate(GNode.LATI) == this.coordinate(GNode.LATI)){
						sucX = true;
					}
				}
				
				if(this.dy > 0 && next.coordinate(GNode.LONG) <= this.coordinate(GNode.LONG)) {
					sucY = true;
				} else if(this.dy < 0 && next.coordinate(GNode.LONG) >= this.coordinate(GNode.LONG)) {
					sucY = true;
				} else if(this.dy==0){
					if(ddy>0 && next.coordinate(GNode.LONG) > this.coordinate(GNode.LONG)){
						sucY = true;
					} else if(ddy<0 && next.coordinate(GNode.LONG) < this.coordinate(GNode.LONG)){
						sucY = true;
					} else if(next.coordinate(GNode.LONG) == this.coordinate(GNode.LONG)){
						sucY=true;
					}
				}
				
				if(this.dz > 0) {
					if(next.coordinate(GNode.ALT) <= this.coordinate(GNode.ALT)) {
						sucZ = true;
					}
				} else if(this.dz < 0){
					if(next.coordinate(GNode.ALT) >= this.coordinate(GNode.ALT)) {
						sucZ = true;
					}
				} else if(this.dz ==0){
					if(ddz>0 && next.coordinate(GNode.ALT) > this.coordinate(GNode.ALT)){
						sucZ = true;
					} else if(ddz<0 && next.coordinate(GNode.ALT) < this.coordinate(GNode.ALT)){
						sucZ = true;
					} else if(next.coordinate(GNode.ALT) == this.coordinate(GNode.ALT)){
						sucZ=true;
					}
				}
			}
			System.out.println("sucX : " +sucX + " sucY : "+sucY + " sucZ : "+ sucZ);
			if(dic.get("next") == Boolean.FALSE){
				setStatus("Landing");
			}
			return sucX & sucY & sucZ;
		} else if(type == 1) {
			if(dic.get("next") == Boolean.TRUE) {
				GNode next = (GNode) dic.get("node");
				if(this.dx >= 0) {
					if(next.coordinate(GNode.LATI) <= this.coordinate(GNode.LATI)) {
						suc = true;
					}
				} else {
					if(next.coordinate(GNode.LATI) >= this.coordinate(GNode.LATI)) {
						suc = true;
						//System.out.println("3333333333");
					}
				}
			}
		} else if(type == 2) {
			if(dic.get("next") == Boolean.TRUE) {
				GNode next = (GNode) dic.get("node");
				if(this.dy >= 0) {
					if(next.coordinate(GNode.LONG) <= this.coordinate(GNode.LONG)) {
						suc = true;
					}
				} else {
					if(next.coordinate(GNode.LONG) >= this.coordinate(GNode.LONG)) {
						suc = true;
					}
				}
			}
		}  else if(type == 3) {
			if(dic.get("next") == Boolean.TRUE) {
				GNode next = (GNode) dic.get("node");
				if(this.dz >= 0) {
					if(next.coordinate(GNode.ALT) <= this.coordinate(GNode.ALT)) {
						suc = true;
						this.altitude = next.altitude();
					}
				} else {
					if(next.coordinate(GNode.ALT) <= this.coordinate(GNode.ALT)) {
						suc = true;
						this.altitude = next.altitude();
					}
				}
			}
		}
		return suc;
		}
}
