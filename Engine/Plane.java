package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class Plane {
	double weight, length, height, wingAngle; // 무게, 길이
	double wingLength, bodyWidth;

	long fuelTank, fuel=100; // 연료최대량, 연료량(%)
	int bodyWeight, payloadWeight, MTOW, M_maxDistance;
	long maxspeed, crusingSpeed;
	double speed=2.4; // 최고속도, 고도,속도
	
	//double x,y,z; // 좌표  
	double dx=0,dy=0;
	double ddx=0, ddy=0, ddz=0;
	
	double angleX=0,angleY=0; //Math.toRadians(30); //기울기
	double latitude, longitude, altitude; // 위도(가로선), 경도(세로선) 
	
	String name, codeName, company;//비행기이름, 비행기코드명, 회사명
	String startSpot, endSpot; // 출발공항, 도착공항
	double dz = 0; //speed*Math.tan(angleY);
	
	int status = 0; // 비행기 상태, 0 == 대기, 1 == 이륙 , 2 == 비행중, 3 == 착륙
	Graph root;
//	ArrayList<GNode> GN = new ArrayList<GNode>();
	// 위도와 경도를 3600으로 나눈후 중간을 좌표 0으로 지정 좌우로 +-500씩 할당한다
	// 좌표가 +-500를 초과하면 위도 또는 경도를 변경한다.
	
	//getter
	

	public Plane(){
		

	}
	
	public Plane(Plane type){
		this.maxspeed = type.maxspeed;
		this.speed = type.speed;
		this.fuelTank = type.fuelTank;
		this.company = type.company;
	}

	public void makerPlane(Plane type){
		System.out.println("makerPlane in");
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
		/*case "x" :
			return x;
		case "y" :
			return y;*/
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
	public int getInt(String s){
		switch(s){
	
		default:
			return 0;
		}
	}

	public String getString(String s){
		switch(s){
		case "Name":
			return name;
		case "Company":
			return company;
		case "CodeName":
			return codeName;
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
//			case "x":
//				x=l;
//				break;
//			case "y":
//				y=l;
//				break;
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
				//z=altitude;
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
		}
	}
	
	public void setRoot(Graph g){
		this.root = g;
	}
	
	public void setStatus(String text){
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
		
		if (status==1){ //이륙 
			System.out.println("TakeOff!!! "+speed);
			latitude+=dx;
			longitude+=dy;
			altitude+=dz;
			
			System.out.println("Plane in class dx: "+dx);
			System.out.println("Plane in class dy: "+dy);
			System.out.println("Plane in class dz: "+dz);
			if(altitude>=1300)setStatus("Flying");
		}
		else if(status==2){ // 비행중
			System.out.println("Flying!!! "+speed);

			latitude+=dx;
			longitude+=dy;
			
			System.out.println("Plane in class dx: "+dx);
			System.out.println("Plane in class dy: "+dy);
			System.out.println("Plane in class dz: "+dz);
			
			Dictionary<String, Object> dic = this.root.getNextNode();
			
			if(dic.get("next") == Boolean.FALSE){
				setStatus("Landing");
			}
		}
		else if(status==3){ // 착륙
			latitude+=dx;
			longitude+=dy;
			altitude+=dz;
		}
		
//		if(x>=500){
//			latitude+=1;
//			x-=1000;
//			System.out.println("넘어감 "+x);
//		}else if(x<=-500){
//			latitude-=1;
//			x+=1000;
//			System.out.println("뒤돌아감 "+x);
//		}
//		
//		if(y>=500){
//			longitude+=1;
//			y-=1000;
//		}else if(y<=-500){
//			longitude-=1;
//			y+=1000;
//		}
	}

	public void Fuel(){
		fuel-=(4/fuelTank)*100;
	}
/*	public void Search(){ // 주변 비행기 탐색(주변그래프를 탐색한다)
		
	}*/
	

	public void Data(){ // 비행기 정보 출력
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+bodyWeight/1000+"T");
		System.out.println("Airplane length : "+length+"m");
		System.out.println("Airplane fuelTank : "+fuelTank+"L");

	}
	public void Data2(){ //실시간으로 바뀌는 비행기 데이터
		System.out.println("Airplane fuel: "+fuel+"%");
		System.out.println("Airplane Maxspeed: "+maxspeed*10+"m/s");
		System.out.println("Airplane speed: "+speed*10+"m/s");
		System.out.println("Airplane altitude: "+altitude+"m");
		System.out.println("Airplane latitude: "+latitude);
		System.out.println("Airplane Longitude: "+longitude);
		if(status==0)System.out.println("이륙");
		else if(status==1)System.out.println("착륙");
		else System.out.println("비행중");
	}
	
	public boolean LandingSign(){ // 공항에 착륙 허가 신청
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
				
//				if(dx >= 1000) {
//					dx -= 1000;
//				}
//				if(dy >= 1000) {
//					dy -= 1000;
//				}
				System.out.println("dx = "+dx+"      dy = "+dy + "   dz = "+dz);
		}
			
//		for(int i=0; i<GN.size()-1; i++){
//			
//			if(startSpot.equals("Dalars") && latitude==GN.get(i).latitude() && longitude==GN.get(i).longitude()){
//				if(GN.get(i+1).longitude() == GN.get(i).longitude())
//					angleX=0;
//				else{
//					
//					angleX=Math.atan2( (GN.get(i+1).latitude()-GN.get(i).latitude()),(GN.get(i+1).longitude()-GN.get(i).longitude()) );
//					System.out.println("여기의 angleX 값은 ????? "+angleX);
//					System.out.println("여기의 그래프 노드 값은?  i+1 == latitude : "+GN.get(i+1).latitude() + " longitude : " + GN.get(i+1).longitude());
//					System.out.println("여기의 그래프 노드 값은?  i == latitude : "+GN.get(i).latitude() + " longitude : " + GN.get(i).longitude());
//					System.out.println("gkgkgkgk : "+Math.atan2((GN.get(i+1).latitude()-GN.get(i).latitude()),(GN.get(i+1).longitude()-GN.get(i).longitude())));
//				}
//			}
//			
//			else if(startSpot.equals("Narita")&& latitude==GN.get(i).latitude() && longitude==GN.get(i).longitude()) {
//				if(GN.get(i+1).longitude() == GN.get(i).longitude())
//					angleX=0;
//				else{
//					angleX=Math.acos( (GN.get(i+1).latitude()-GN.get(i).latitude())/(GN.get(i+1).longitude()-GN.get(i).longitude()) );
//					System.out.println("여기의 angleX 값은 !!!!!! "+angleX);
//				}
//			}
//		
//			else {
//				//System.out.println("What the fuck!!!!!");
//				/*if(GN.get(GN.size()-i-1).longitude() == GN.get(GN.size()-i-2).longitude())
//					angleX=0;
//				else{
//					angleX=Math.acos( (GN.get(GN.size()-i-1).latitude()-GN.get(GN.size()-i-2).latitude())/(GN.get(GN.size()-i-1).longitude()-GN.get(GN.size()-i-2).longitude()) );
//				//	System.out.println("여기의 angleX 값은 d.d.d.d.d. "+angleX);
//				}*/
//			}
//				dx = speed/Math.cos(angleX);
//				dy = speed/Math.cos(angleX);
//			//	System.out.println("asddfasdf : "+angleX);
//				//System.out.println("!!!??!?!?!?dddddfff!!!!!!!!    dz : "+dz);
//		}
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
				System.out.println(next.coordinate(GNode.LATI)+ " <- 랄라랄라라 -> " +next.coordinate(GNode.LONG));
				if(this.dx > 0) {
					if(next.coordinate(GNode.LATI) <= this.coordinate(GNode.LATI)) {
						sucX = true;
					}
				} else if(this.dx < 0){
					if(next.coordinate(GNode.LATI) >= this.coordinate(GNode.LATI)) {
						sucX = true;
						System.out.println("여기다가 이걸 하라고??");
					}
				} else if(this.dx==0){
					if(ddx>0 && next.coordinate(GNode.LATI) > this.coordinate(GNode.LATI)){
						sucX = true;
					} else if(ddx<0 && next.coordinate(GNode.LATI) < this.coordinate(GNode.LATI)){
						sucX = true;
					}
					System.out.println("이게 나오겠지 -> next :"+next.coordinate(GNode.LATI)+ " this : " + this.coordinate(GNode.LATI));
				}
				
				if(this.dy > 0 && next.coordinate(GNode.LONG) <= this.coordinate(GNode.LONG)) {
					sucY = true;
				} else if(this.dy < 0 && next.coordinate(GNode.LONG) >= this.coordinate(GNode.LONG)) {
					sucY = true;
				} else if(dy==0){
					if(ddy>0 && next.coordinate(GNode.LONG) > this.coordinate(GNode.LONG)){
						sucY = true;
					} else if(ddy<0 && next.coordinate(GNode.LONG) < this.coordinate(GNode.LONG)){
						sucY = true;
					}else if(next.coordinate(GNode.LONG) == this.coordinate(GNode.LONG)){
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
					if(next.coordinate(GNode.ALT) >= this.coordinate(GNode.ALT)) {
						suc = true;
						this.altitude = next.altitude();
					}
				}
			}
		}
		return suc;
	}
}

// 이륙시에 공항에 신호보내기
// 공항은 신호를 받지 않은 비행기는 무시하고 신호를 보낸 비행기만을 탐색
