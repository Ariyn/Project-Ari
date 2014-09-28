package Engine;

public class Plane {
	public int code, weight, length; // 비행기 기종, 무게, 길이
	int fuel, fuelTank, fuelPercent; // 연료, 연료최대량, 연료량(%)
	int speed, height; // 속도, 고도
	int x,y; // 좌표 
	int latitude, altitude; // 위도(가로선), 경도(세로선)
	
	int status; // 비행기 상태, 0 : 땅위, 1 : 비행중, 2 : 특수상황
	
	// 위도와 경도를 3600으로 나눈후 중간을 좌표 0으로 지정 좌우로 +-500씩 할당한다
	// 좌표가 +-500를 초과하면 위도 또는 경도를 변경한다.
	
	boolean hiJack; // 하이잭 유무

	public int PositionX(){ // 비행기 위도
		return latitude;
	}
	
	public int PostionY(){ // 비행기 경도
		return altitude;
	}
	
	public void Move(){ // 비행기 좌표 이동(연료 소모 표시)
		
	}

	public void Search(){ // 주변 비행기 탐색
		
	}
	
	public void SearchPort(Object port){ // 주변 공항 탐색
		
	}
	
	public void Data(){ // 비행기 정보 출력
		
	}
	
	public boolean LandingSign(){ // 공항에 착륙 허가 신청
		boolean sign = true;
		
		return sign;
	}
	
}

// 이륙시에 공항에 신호보내기
// 공항은 신호를 받지 않은 비행기는 무시하고 신호를 보낸 비행기만을 탐색
