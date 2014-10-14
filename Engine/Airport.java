package Engine;

import java.util.ArrayList;
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
	ArrayList <Plane> planeList;
	
	PlaneQueue q = new PlaneQueue();
	PlaneQueue q1 = new PlaneQueue();
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
	
	public void PlaneLanding(Plane pl, String type){ // ����� ������

		if(type.equals("landing")){
			if(q1.<Plane>Push(pl)==true){
				System.out.println("PlaneLanding : "+pl);
				planeList.remove(pl);
			}
		} else {
			Plane _p = (Plane)q.<Plane>Pop().Get();
			if(_p!=null){
				System.out.println("PlaneTakeOff : " +_p);
			}
		}
	}

	public void Data(){ // ���� ���� ���
		System.out.println("Airport name: "+name);
		System.out.println("Airport latitude: "+latitude+" x:"+x);
		System.out.println("Airport Longitude: "+Longitude+" y:"+y);
		
	}
	
	/*public void PlaneSignReceive(){ // ���� ���׿� ����� Ž�� ��û
		
	}*/
	
/*	public void OnPlane(){ // ���� ���׿� ü������ ����� �����ִ� �޼ҵ�
		System.out.println("PlaneStay : "+nowPlane);
	}
*/
	public void SetPlane(Plane pl){ // ����� ���׿� ����
		pl.set("Longitude",this.Longitude);
		pl.set("Latitude",this.latitude);
		planeList.add(pl);
	}

}
