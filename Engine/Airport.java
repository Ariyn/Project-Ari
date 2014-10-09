package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // 공항명
	long latitude, altitude; // 공항의 위도, 경도
	int maximum_airplane; // 비행기 최대 재적 가능 수
	
	long[] runways = new long[1];// 활주로 길이
	
	// 비행기 이착륙은 허가 후 이착륙 가능 허가 메소드에서는 큐에 비행기 객체들을 집어넣어 순차적으로 허가시킨다.
	// 비행기 객체는 트리구조로 저장
	

	Queue q = new Queue();
	//Plane p = new Plane();

	public void setName(String text){
		name = text;
	}
	
	public void setPositionX(long x){
		latitude = x;
	}
	
	public void setPositionY(long y){
		altitude = y;
	}
	
	public void setRunwyas(int num, long length){
		runways[num] = length;
	}

	
	public String getName(){
		return name;
	}
	
	public double getPositionX(){ // 공항 위치 보여주는 메소드
		return latitude;
	}
	
	public double getPositionY(){
		return altitude;
	}
	
	public long getRunwayLength(int num){
		return runways[num];
	}
	
	public void LandingPermitSign(){ // 비행기 착륙 허가
	//큐가 비어있는지 확인후 비어있으면 허가 
	}
	
	public void PlaneLanding(Plane pl){ // 비행기 착륙
		System.out.println("PlaneLading : "+q.Push(pl));
	}
	
	public void TakeOffPermitSign(){ // 비행기 이륙 허가
		
	}
	
	public void PlaneTakeOff(){ // 비행기 이륙
		
	}
	
	public void PlaneSignReceive(){ // 목적 공항에 비행기 탐색 신청
		
	}
	
	public void OnPlane(){ // 현재 공항에 체류중인 비행기 보여주는 메소드
		
	}
	
	public void SetPlane(Plane pl){ // 비행기 공항에 생성
		pl.setPositionX(this.altitude);
		pl.setPositionY(this.latitude);
	}
}
