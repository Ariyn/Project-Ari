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
	
	public void setName(String text){
		name = text;
	}
	
	public void setPositionX(long x){
		latitude = x;
	}
	
	public void setPositionY(long y){
		longtitude = y;
	}
	
	public void setRunwyas(long num, long length){
		int number = (int)num;
		runways[number] = length;
	}
	
	public void setMaxAirplane(long x){
		maximum_airplane = (int)x;
	}
	
	public String getName(){
		return name;
	}
	
	public double getPositionX(){ // ���� ��ġ �����ִ� �޼ҵ�
		return latitude;
	}
	
	public double getPositionY(){
		return longtitude;
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
		pl.setPositionX(this.longtitude);
		pl.setPositionY(this.latitude);
	}

}
