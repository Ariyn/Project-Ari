package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // 공항명
	int latitude, altitude; // 공항의 위도, 경도
	int runwayLength;// 활주로 길이
	
	// 비행기 이착륙은 허가 후 이착륙 가능 허가 메소드에서는 큐에 비행기 객체들을 집어넣어 순차적으로 허가시킨다.
	// 비행기 객체는 트리구조로 저장
	
	Queue q = new Queue();
	Plane p = new Plane();
	
	public Airport(int airportX,int airportY){
		this.latitude=airportX;
		this.altitude=airportY;
	}
	
	public int PositionX(){ // 공항 위치 보여주는 메소드
		return latitude;
	}
	
	public int PositionY(){
		return altitude;
	}
	
	public void LandingPermitSign(){ // 비행기 착륙 허가
	
		
	}
	
	public void PlaneLanding(){ // 비행기 착륙
		
	}
	
	public void TakeOffPermitSign(){ // 비행기 이륙 허가
		
	}
	
	public void PlaneTakeOff(){ // 비행기 이륙
		
	}
	
	public void PlaneSignReceive(){ // 목적 공항에 비행기 탐색 신청
		
	}
	
	public void OnPlane(){ // 현재 공항에 체류중인 비행기 보여주는 메소드
		
	}
	
}
