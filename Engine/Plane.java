package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plane {
	double weight, length, height, wingAngle; // 무게, 길이
	double wingLength, bodyWidth;

	long fuelTank, fuel=100; // 연료최대량, 연료량(%)
	int bodyWeight, payloadWeight, MTOW, M_maxDistance;
	long maxspeed, crusingSpeed, speed=100; // 최고속도, 고도,속도
	
	double x,y; // 좌표  
	double dx,dy;
	
	double angleX=0,angleY=Math.toRadians(30); //기울기
	long latitude, longitude, altitude; // 위도(가로선), 경도(세로선)
	
	String name, codeName, company;//비행기이름, 비행기코드명, 회사명
	String startSpot, endSpot; // 출발공항, 도착공항
	double dz = speed*Math.tan(angleY);
	
	int status = 0; // 비행기 상태, 0 == 대기, 1 == 이륙 , 2 == 비행중, 3 == 착륙
	Graph root;
	ArrayList<GNode> GN = new ArrayList<GNode>();
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
		case "Latitude":
			return latitude;
		case "Longitude":
			return longitude;
		case "Altitude":
			return altitude;
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
		case "x" :
			return x;
		case "y" :
			return y;
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
		case "Latitude":
			latitude=l;
			break;
		case "Longitude":
			longitude=l;
			break;
		case "Altitude":
			altitude=l;
			break;
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
		case "Speed":
			speed=l;
			break;
		case "CrusingSpeed":
			crusingSpeed = l;
			break;
		}

	}
	public void set(String s, double l){
		switch(s)
		{
			case "x":
				x=l;
				break;
			case "y":
				y=l;
				break;
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
		for(GNode gn : g.vertex){
			GN.add(gn);
		}
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
		
		Spin();
		
		if (status==1){ //이륙 
			System.out.println("TakeOff!!! "+speed);
			x=x+dx;
			System.out.println("Plane in class dx: "+dx);
			System.out.println("Plane in class x: "+x);
			altitude=altitude+(long)dz;
			if(altitude>=1300)setStatus("Flying");
		}
		else if(status==2){ // 비행중
			System.out.println("Flying!!! "+speed);
			System.out.println("HAHAHAHAHAHAH "+GN.size());
			x=x+dx;
			y=y+dy;
			
			if(GN.get(GN.size()-1).latitude()==latitude && GN.get(GN.size()-1).longitude()==longitude){
				setStatus("Landing");
			}
		}
		else if(status==3){ // 착륙
			x=x+dx;
			altitude=altitude-(long)dz;
		}
		
		if(x>=500){
			latitude+=1;
			x-=1000;
			System.out.println("지금은 이륙중인가 비행중인가?!!?!??");
		}else if(x<=-500){
			latitude-=1;
			x+=1000;
		}
		
		if(y>=500){
			longitude+=1;
			y-=1000;
		}else if(y<=-500){
			longitude-=1;
			y+=1000;
		}
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
		for(int i=0; i<GN.size()-1; i++){
			if(startSpot.equals("Dalars") && latitude==GN.get(i).latitude() && longitude==GN.get(i).longitude()){
				if(GN.get(i+1).longitude() == GN.get(i).longitude())
					angleX=0;
				else{
					angleX=Math.acos( (GN.get(i+1).latitude()-GN.get(i).latitude())/(GN.get(i+1).longitude()-GN.get(i).longitude()));
					//System.out.println("여기의 angleX 값은 ????? "+angleX);
				}
			}
			
			else if(startSpot.equals("Narita")&& latitude==GN.get(i).latitude() && longitude==GN.get(i).longitude()) {
				if(GN.get(i+1).longitude() == GN.get(i).longitude())
					angleX=0;
				else{
					angleX=Math.acos( (GN.get(i+1).latitude()-GN.get(i).latitude())/(GN.get(i+1).longitude()-GN.get(i).longitude()) );
				//	System.out.println("여기의 angleX 값은 !!!!!! "+angleX);
				}
			}
		
			else {
				if(GN.get(GN.size()-i-1).longitude() == GN.get(GN.size()-i-2).longitude())
					angleX=0;
				else{
					angleX=Math.acos( (GN.get(GN.size()-i-1).latitude()-GN.get(GN.size()-i-2).latitude())/(GN.get(GN.size()-i-1).longitude()-GN.get(GN.size()-i-2).longitude()) );
				//	System.out.println("여기의 angleX 값은 d.d.d.d.d. "+angleX);
				}
			}
				dx = speed/Math.cos(angleX);
				dy = speed/Math.cos(angleX);
			//	System.out.println("asddfasdf : "+angleX);
				//System.out.println("!!!??!?!?!?dddddfff!!!!!!!!    dz : "+dz);
		}
	}
	
}

// 이륙시에 공항에 신호보내기
// 공항은 신호를 받지 않은 비행기는 무시하고 신호를 보낸 비행기만을 탐색
