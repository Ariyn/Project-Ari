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
	public long getPositionX(){ // 비행기 위도
		return latitude;
	}
	
	public long getPostionY(){ // 비행기 경도
		return longtitude;
	}
	
	public String getName(){//비행기 이름 
		return name;
	}
	
	public double getAltitude(){//비행기 고도 
		return altitude;
	}
	public int getfuel(){//비행기 현재 연료(%)
		return fuel;
	}
	public int getfuelTank(){//비행기 연료 최대
		return fuelTank;
	}
	
	public double getMaxSpeed(){
		return Maxspeed;
	}
	public double getSpeed(){
		return speed;
	}
	public String getCompany(){
		return company;
	}
	//setter
	public void setPositionX(long x){
		latitude=x;
	}
	
	public void setPositionY(long y){
		longtitude=y;
	}
	
	public void setName(String text){
		name=text;
	}
	public void setAltitude(double x){
		altitude=x;
	}
	
	public void setfuel(int t){//1초에 4리터
		fuel=100;
	}
	
	public void setfuelTank(long f){
		int number = (int)f;
		fuelTank=number;
	}
	public void setMaxSpeed(long s){
		int number = (int)s;
		Maxspeed=number;
	}
	public void setSpeed(double x){
		speed=x;
	}
	public void setSCompany(String s){
		company=s;
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

/*	public void Search(){ // 주변 비행기 탐색(주변그래프를 탐색한다)
		
	}*/
	

	public void Data(){ // 비행기 정보 출력
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+weight);
		System.out.println("Airplane length : "+length);
		System.out.println("Airplane fuelTank : "+fuelTank);
		System.out.println("Airplane fuel: "+fuel);
		System.out.println("Airplane Maxspeed: "+Maxspeed);
		System.out.println("Airplane speed: "+speed);
		System.out.println("Airplane altitude: "+altitude);
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
