package Engine;

public class Plane {
	double weight, length; // 무게, 길이

	int fuelTank, fuel; // 연료최대량, 연료량(%)
	double Maxspeed, altitude, speed; // 최고속도, 고도,속도
	double x,y; // 좌표  
	double dx, dy, dz, angleX=0, angleY=0; //기울기
	long latitude, longtitude; // 위도(가로선), 경도(세로선)
	String name, company;//비행기이름, 회사명
	
	int status; // 비행기 상태, 0 : 이륙, 1 : 착륙 2: 비행중
	
	// 위도와 경도를 3600으로 나눈후 중간을 좌표 0으로 지정 좌우로 +-500씩 할당한다
	// 좌표가 +-500를 초과하면 위도 또는 경도를 변경한다.
	
	//getter
	public long get(String s){
		switch(s){
		case "Latitude":
			return latitude;
		case "Longtitude":
			return longtitude;
		default:
			return 0;
		}
	}
	public double get1(String s){
		switch(s){
		case "Altitude":
			return altitude;
		case "MaxSpeed":
			return Maxspeed;
		case "speed":
			return speed;
			
		default:
			return 0;
		}
	}
	public int get2(String s){
		switch(s){
		case "FuelTank":
			return fuelTank;
		default:
			return 0;
		}
	}
	public String get3(String s){
		switch(s){
		case "Name":
			return name;
		case "Company":
			return company;
		default:
			return " ";
		}
	}
	//setter
	public void set(String s, long l){
		switch(s)
		{
		case "Latitude":
			latitude=l;
			break;
		case "Longtitude":
			longtitude=l;
			break;
		case "Altitude":
			altitude=(double)l;
			break;
		case "FuelTank":
			fuelTank=(int)l;
			break;
		case "Maxspeed":
			Maxspeed=l;
			break;
		case "Speed":
			speed=(double)l;
			break;
		}
	}
	public void set2(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		case "Company":
			company=str;
			break;
		}
	}
	
	public void Move(){
		if (status==0){ //이륙 

			x=x+(speed-speed*Math.acos(angleX)/2);
			y=y+(speed-speed*Math.acos(angleX)/2);;
			altitude+=speed/2;
			if(altitude>=1300)status=2;
		}
		else if(status==2){ // 착륙
			status=1;
			x=x+(speed-speed*Math.acos(angleX)/2);
			y=y+(speed-speed*Math.acos(angleX)/2);
		}
		else { // 비행중
			x=x+(Maxspeed-Maxspeed*Math.acos(angleX));
			y=y+(Maxspeed-Maxspeed*Math.asin(angleX));

		}
	}

	public void Fuel(){
		fuel=100-(4/fuelTank)*100;
	}
/*	public void Search(){ // 주변 비행기 탐색(주변그래프를 탐색한다)
		
	}*/
	

	public void Data(){ // 비행기 정보 출력
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+weight+"T");
		System.out.println("Airplane length : "+length+"m");
		System.out.println("Airplane fuelTank : "+fuelTank+"L");
	}
	public void Data2(){ //실시간으로 바뀌는 비행기 데이터
		System.out.println("Airplane fuel: "+fuel+"%");
		System.out.println("Airplane Maxspeed: "+Maxspeed*10+"m/s");
		System.out.println("Airplane speed: "+speed*10+"m/s");
		System.out.println("Airplane altitude: "+altitude+"m");
		System.out.println("Airplane latitude: "+latitude);
		System.out.println("Airplane longtitude: "+longtitude);
		if(status==0)System.out.println("이륙");
		else if(status==1)System.out.println("착륙");
		else System.out.println("비행중");
	}
	
	public boolean LandingSign(){ // 공항에 착륙 허가 신청
		boolean sign = false;
		
		return sign;
	}
	public void Spin(double x){
		this.angleX=x;
	}

}

// 이륙시에 공항에 신호보내기
// 공항은 신호를 받지 않은 비행기는 무시하고 신호를 보낸 비행기만을 탐색
