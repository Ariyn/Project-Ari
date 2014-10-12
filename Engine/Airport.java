package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // 공항명
	long latitude, Longitude, altitude = 0; // 공항의 위도, 경도
	double x, y, z=0.0;
	
	int maximum_airplane; // 비행기 최대 재적 가능 수
	long[] runways = new long[1];// 활주로 길이
	
	// 비행기 이착륙은 허가 후 이착륙 가능 허가 메소드에서는 큐에 비행기 객체들을 집어넣어 순차적으로 허가시킨다.
	// 비행기 객체는 리스트에 저장
	Plane p=new Plane();
	LinkedList2 nowPlane = new LinkedList2();
	
	PlaneQueue q = new PlaneQueue();
	//Plane p = new Plane();

	public void setRunwyas(long num, long length){
		int number = (int)num;
		runways[number] = length;
	}

	public void set(String s, long l){
		switch(s){
		case "Latitude":
			latitude=l;
			break;
		case "Longitude":
			Longitude=l;
			break;
		case"MaxAirplane":
			maximum_airplane=(int)l;
			break;
		}
	}
	public void set(String s, double l){
		switch(s){
		case "x":
			x=l;
			break;
		case "y":
			y=l;
			break;
		}
	}
	public void set(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		}
	}
	
	public long getLong(String s){
		switch(s){
		case "Latitude":
			return latitude;
		case "Longitude":
			return Longitude;
		default:
			return 0;
		}
	}
	public String getString(String s){
		switch(s){
		case "Name":
			return name;
		default:
			return " ";
		}
	}
	public long getRunwayLength(int num){
		return runways[num];
	}
	
	public void LandingPermitSign(Plane pl){ // 비행기 착륙 허가
		q.Push(pl);
	}
	
	public void PlaneLanding(Plane pl){ // 비행기 착륙
		nowPlane.addLast(pl);
		System.out.println("PlaneLanding : "+q.Push(pl));
	}
	
	public void TakeOffPermitSign(){ // 비행기 이륙 허가
		
	}
	
	public void PlaneTakeOff(){ // 비행기 이륙
		q.<Plane>Pop();
		nowPlane.removeFirst();
	}
	public void Data(){ // 비행기 정보 출력
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane latitude: "+latitude+" x:"+x);
		System.out.println("Airplane Longitude: "+Longitude+" y:"+y);
		
	}
	
	/*public void PlaneSignReceive(){ // 목적 공항에 비행기 탐색 신청
		
	}*/
	
	public void OnPlane(){ // 현재 공항에 체류중인 비행기 보여주는 메소드
		System.out.println("PlaneStay : "+nowPlane);
	}
	
	public void SetPlane(Plane pl){ // 비행기 공항에 생성
		pl.set("Longitude",this.Longitude);
		pl.set("Latitude",this.latitude);
		nowPlane.addLast(pl);
	}

}
