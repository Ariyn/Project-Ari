package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // ���׸�
	long latitude, altitude; // ������ ����, �浵
	int maximum_airplane; // ����� �ִ� ���� ���� ��
	
	long[] runways = new long[1];// Ȱ�ַ� ����
	
	// ����� �������� �㰡 �� ������ ���� �㰡 �޼ҵ忡���� ť�� ����� ��ü���� ����־� ���������� �㰡��Ų��.
	// ����� ��ü�� Ʈ�������� ����
	

	Queue q = new Queue();
	Plane p = new Plane();

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
	
	public long PositionX(){ // ���� ��ġ �����ִ� �޼ҵ�
		return latitude;
	}
	
	public long PositionY(){
		return altitude;
	}
	
	public long getRunwayLength(int num){
		return runways[num];
	}
	
	public void LandingPermitSign(){ // ����� ���� �㰡
	
		
	}
	
	public void PlaneLanding(){ // ����� ����
		
	}
	
	public void TakeOffPermitSign(){ // ����� �̷� �㰡
		
	}
	
	public void PlaneTakeOff(){ // ����� �̷�
		
	}
	
	public void PlaneSignReceive(){ // ���� ���׿� ����� Ž�� ��û
		
	}
	
	public void OnPlane(){ // ���� ���׿� ü������ ����� �����ִ� �޼ҵ�
		
	}
	
}
