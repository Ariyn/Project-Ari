package Engine;

import java.util.LinkedList;


public class Airport {
	
	String name; // ���׸�
	long latitude, Longitude, altitude = 0; // ������ ����, �浵
	double x, y, z=0.0;
	
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
	public void Data(){ // ����� ���� ���
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane latitude: "+latitude+" x:"+x);
		System.out.println("Airplane Longitude: "+Longitude+" y:"+y);
		
	}
	
	/*public void PlaneSignReceive(){ // ���� ���׿� ����� Ž�� ��û
		
	}*/
	
	public void OnPlane(){ // ���� ���׿� ü������ ����� �����ִ� �޼ҵ�
		System.out.println("PlaneStay : "+nowPlane);
	}
	
	public void SetPlane(Plane pl){ // ����� ���׿� ����
		pl.set("Longitude",this.Longitude);
		pl.set("Latitude",this.latitude);
		nowPlane.addLast(pl);
	}

}
