package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // ���׸�
	long latitude, longtitude; // ������ ����, �浵
	int maximum_airplane; // ����� �ִ� ���� ���� ��
	long[] runways = new long[1];// Ȱ�ַ� ����
	
	// ����� �������� �㰡 �� ������ ���� �㰡 �޼ҵ忡���� ť�� ����� ��ü���� ����־� ���������� �㰡��Ų��.
	// ����� ��ü�� ����Ʈ�� ����
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
		case "Longtitude":
			longtitude=l;
			break;
		case"MaxAirplane":
			maximum_airplane=(int)l;
			break;
		}
	}
	public void set2(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		}
	}
	
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
	public String get2(String s){
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
	
	public void LandingPermitSign(Plane pl){ // ����� ���� �㰡
		q.Push(pl);
	}
	
	public void PlaneLanding(Plane pl){ // ����� ����
		nowPlane.addLast(pl);
		System.out.println("PlaneLanding : "+q.Push(pl));
	}
	
	public void TakeOffPermitSign(){ // ����� �̷� �㰡
		
	}
	
	public void PlaneTakeOff(){ // ����� �̷�
		q.<Plane>Pop();
		nowPlane.removeFirst();
	}
	
	/*public void PlaneSignReceive(){ // ���� ���׿� ����� Ž�� ��û
		
	}*/
	
	public void OnPlane(){ // ���� ���׿� ü������ ����� �����ִ� �޼ҵ�
		System.out.println("PlaneStay : "+nowPlane);
	}
	
	public void SetPlane(Plane pl){ // ����� ���׿� ����
		pl.set("Longtitude",this.longtitude);
		pl.set("Latitude",this.latitude);
		nowPlane.addLast(pl);
	}

}
