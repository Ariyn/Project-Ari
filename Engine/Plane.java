package Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plane {
	double weight, length, height, wingAngle; // ����, ����
	double wingLength, bodyWidth;

	long fuelTank, fuel=100; // �����ִ뷮, ���ᷮ(%)
	int bodyWeight, payloadWeight, MTOW, M_maxDistance;
	long maxspeed, crusingSpeed, speed; // �ְ�ӵ�, ��,�ӵ�
	
	double x,y; // ��ǥ  
	double angleX=0; //����
	long latitude, longitude, altitude; // ����(���μ�), �浵(���μ�)
	
	String name, codeName, company;//������̸�, ������ڵ��, ȸ���
	String startSpot, endSpot; // ��߰���, ��������
	
	int status; // ����� ����, 0 : �̷�, 1 : ���� 2: ������
	
	Graph root;
	Airport air=new Airport();
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	
	//getter
	
	public Plane(){
		
	}
	
	public Plane(Plane type){
		this.maxspeed = type.maxspeed;
		this.speed = type.speed;
		this.fuelTank = type.fuelTank;
		this.company = type.company;
	}


	public long getLong(String s){
		switch(s){
		case "Latitude":
			return latitude;
		case "Longitude":
			return longitude;
		case "Altitude":
			return altitude;
		case "FuelTank":
			return fuelTank;
		default:
			return 0;
		}

	}
	public double getDouble(String s){
		switch(s){
		case "MaxSpeed":
			return maxspeed;
		case "speed":
			return speed;
			
		default:
			return 0;
		}
	}
	public int getInt(String s){
		switch(s){
	
		default:
			return 0;
		}
	}

	public String getString(String s){
		switch(s){
		case "Name":
			return name;
		case "Company":
			return company;
		case "CodeName":
			return codeName;
		default:
			return " ";
		}

	}

	//setter
	public void set(String s, long l){
		switch(s)
		{
		case "Latitude":
			latitude=l;
			break;
		case "Longitude":
			longitude=l;
			break;
		case "Altitude":
			altitude=l;
			break;
		case "FuelTank":
			fuelTank=(int)l;
			break;
		case "BodyWeight":
			bodyWeight = (int)l;
			break;
		case "MTOW":
			MTOW = (int)l;
			break;
		case "MTOW_MaxDistance":
			M_maxDistance = (int)l;
			break;
		case "Maxspeed":
			maxspeed=l;
			break;
		case "Speed":
			speed=l;
			break;
		case "CrusingSpeed":
			crusingSpeed = l;
			break;
		}
	}
	public void set(String s, double l){
		switch(s)
		{
			case "x":
				x=l;
				break;
			case "y":
				y=l;
				break;
			case "Length":
				length = l;
				break;
			case "Weight":
				weight = l;
				break;
			case "WingLength":
				wingLength = l;
				break;
			case "BodyWidth":
				bodyWidth = l;
				break;
			case "WingAngle":
				wingAngle = l;
				break;
			case "Height":
				height=l;
				break;
		}
	}
	public void set(String s, String str){
		switch(s){
		case "Name":
			name=str;
			break;
		case "Company":
			company=str;
			break;
		case "startSpot":
			startSpot = str;
			break;
		case "endSpot":
			endSpot = str;
			break;
		case "CodeName":
			codeName = str;
		}
	}
	
	public void setRoot(Graph g){
		this.root = g;
	}
	
	public void Move(){
		if (status==0){ //�̷� 

			x=x+(speed-speed*Math.acos(angleX)/2);
			y=y+(speed-speed*Math.acos(angleX)/2);;
			altitude+=speed/2;
			if(altitude>=1300)status=2;
		}
		else if(status==2){ // ����
			status=1;
			x=x+(speed-speed*Math.acos(angleX)/2);
			y=y+(speed-speed*Math.acos(angleX)/2);
		}
		else { // ������
			x=x+(maxspeed-maxspeed*Math.acos(angleX));
			y=y+(maxspeed-maxspeed*Math.asin(angleX));

		}
	}

	public void Fuel(){
		fuel-=(4/fuelTank)*100;
	}
/*	public void Search(){ // �ֺ� ����� Ž��(�ֺ��׷����� Ž���Ѵ�)
		
	}*/
	

	public void Data(){ // ����� ���� ���
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+bodyWeight/1000+"T");
		System.out.println("Airplane length : "+length+"m");
		System.out.println("Airplane fuelTank : "+fuelTank+"L");

	}
	public void Data2(){ //�ǽð����� �ٲ�� ����� ������
		System.out.println("Airplane fuel: "+fuel+"%");
		System.out.println("Airplane Maxspeed: "+maxspeed*10+"m/s");
		System.out.println("Airplane speed: "+speed*10+"m/s");
		System.out.println("Airplane altitude: "+altitude+"m");
		System.out.println("Airplane latitude: "+latitude);
		System.out.println("Airplane Longitude: "+longitude);
		if(status==0)System.out.println("�̷�");
		else if(status==1)System.out.println("����");
		else System.out.println("������");
	}
	
	public boolean LandingSign(){ // ���׿� ���� �㰡 ��û
		boolean sign = false;
		
		return sign;
	}
	public void Spin(double x){
		this.angleX=x;
		//for(int i=0; i<vertex.length-1; i++)
		if(latitude==air.vertex.get(0/*i*/).latitude()&&longitude==air.vertex.get(0/*i*/).longitude()){
		angleX=Math.acos((air.vertex.get(1/*i+1*/).latitude()-air.vertex.get(0/*i*/).latitude())/(air.vertex.get(1/*i+1*/).longitude()-air.vertex.get(0/*i*/).longitude()));
		}
	}
}

// �̷��ÿ� ���׿� ��ȣ������
// ������ ��ȣ�� ���� ���� ������ �����ϰ� ��ȣ�� ���� ����⸸�� Ž��
