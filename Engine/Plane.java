package Engine;

public class Plane {
	public int code; double weight, length; // 비행기 기종, 무게, 길이
	int fuelTank, fuel; // 연료, 연료최대량, 연료량(%)
	double speed, height; // 속도, 고도
	double x,y; // 좌표  
	double dx, dy, dz, angleX, angleY; //기울기
	double latitude, altitude; // 위도(가로선), 경도(세로선)
	String name;//비행기이름
	
	int status; // 비행기 상태, 0 : 땅위, 1 : 비행중, 2 : 특수상황
	
	// 위도와 경도를 3600으로 나눈후 중간을 좌표 0으로 지정 좌우로 +-500씩 할당한다
	// 좌표가 +-500를 초과하면 위도 또는 경도를 변경한다.
	
	boolean hiJack; // 하이잭 유무
	

	//getter
	public double getPositionX(){ // 비행기 위도
		return latitude;
	}
	
	public double getPostionY(){ // 비행기 경도
		return altitude;
	}
	
	public String getName(){//비행기 이름 
		return name;
	}
	
	public double getHeihgt(){//비행기 고도 
		return height;
	}
	public int getfuel(){//비행기 현재 연료(%)
		return fuel;
	}
	public int getfuelTank(){//비행기 연료 최대
		return fuelTank;
	}
	
	public double getSpeed(){
		return speed;
	}
	//setter
	public void setPositionX(double x){
		latitude=x;
	}
	
	public void setPositionY(double y){
		altitude=y;
	}
	
	public void setName(String text){
		name=text;
	}
	public void setHeight(double x){
		height=x;
	}
	
	public void setfuel(int t,int usefuel){//t는 시간 usefuel은 사용연료
		fuel=100-t*usefuel;
	}
	
	public void setfuelTank(int f){
		fuelTank=f;
	}
	public void setSpeed(int s){
		speed=s;
	}
	public void Move(){ // 비행기 좌표 이동
		x=x+(speed-speed*Math.acos(angleX));
		y=y+(speed-speed*Math.asin(angleX));
	}

/*	public void Search(){ // 주변 비행기 탐색
		
	}*/
	
	
	public void Data(){ // 비행기 정보 출력
		
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
