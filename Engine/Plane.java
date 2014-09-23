package Engine;

public class Plane {
	public int code, weight, length; // 비행기 기종, 무게, 길이
	int fuel, fuelTank, fuelPercent; // 연료, 연료최대량, 연료량(%)
	int speed, height; // 속도, 고도
	int x,y; // 좌표 
	int latitude, altitude; // 위도(가로선), 경도(세로선)
	
	// 위도와 경도를 3600으로 나눈후 중간을 좌표 0으로 지정 좌우로 +-500씩 할당한다
	// 좌표가 +-500를 초과하면 위도 또는 경도를 변경한다.
	
	boolean hiJack; // 하이잭 유무
	
	public Plane(){
		code = 10;
	}
	
	public void Position(){ // 비행기 위치 보여주는 메소드
	
	
	}
	
	public void Move(){ // 비행기 좌표 이동(연료 소모 표시)
		
	}

	/*public void Search(){ // 주변 비행기 탐색
		
	}*/
	
	public void Data(){ // 비행기 정보 출력
		
	}
	
	public boolean LandingSign(){ // 공항에 착륙 허가 신청
		boolean sign = true;
		
		return sign;
	}
}
