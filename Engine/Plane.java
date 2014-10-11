package Engine;

public class Plane {
	double weight, length; // ����, ����

	int fuelTank, fuel; // �����ִ뷮, ���ᷮ(%)
	double Maxspeed, altitude, speed; // �ְ�ӵ�, ��,�ӵ�
	double x,y; // ��ǥ  
	double dx, dy, dz, angleX=0, angleY=0; //����
	long latitude, longtitude; // ����(���μ�), �浵(���μ�)
	String name, company;//������̸�, ȸ���
	String startSpot, endSpot; // ��߰���, ��������
	int status; // ����� ����, 0 : �̷�, 1 : ���� 2: ������
	
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	
	//getter
	
	public Plane(){
		
	}
	
	public Plane(Plane type){
		
	}


	public long getLong(String s){
		switch(s){
		case "Latitude":
			return latitude;
		case "Longtitude":
			return longtitude;
		default:
			return 0;
		}

	}
	public double getDouble(String s){
		switch(s){
		case "Altitude":
			return altitude;
		case "MaxSpeed":
			return Maxspeed;
		case "speed":
			return speed;
			
		default:
			return 0;
		}
	}
	public int getInt(String s){
		switch(s){
		case "FuelTank":
			return fuelTank;
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
		case "Longtitude":
			longtitude=l;
			break;
		case "Altitude":
			altitude=(double)l;
			break;
		case "FuelTank":
			fuelTank=(int)l;
			break;
		case "Maxspeed":
			Maxspeed=l;
			break;
		case "Speed":
			speed=(double)l;
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
		}
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
			x=x+(Maxspeed-Maxspeed*Math.acos(angleX));
			y=y+(Maxspeed-Maxspeed*Math.asin(angleX));

		}
	}

	public void Fuel(){
		fuel=100-(4/fuelTank)*100;
	}
/*	public void Search(){ // �ֺ� ����� Ž��(�ֺ��׷����� Ž���Ѵ�)
		
	}*/
	

	public void Data(){ // ����� ���� ���
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+weight+"T");
		System.out.println("Airplane length : "+length+"m");
		System.out.println("Airplane fuelTank : "+fuelTank+"L");
	}
	public void Data2(){ //�ǽð����� �ٲ�� ����� ������
		System.out.println("Airplane fuel: "+fuel+"%");
		System.out.println("Airplane Maxspeed: "+Maxspeed*10+"m/s");
		System.out.println("Airplane speed: "+speed*10+"m/s");
		System.out.println("Airplane altitude: "+altitude+"m");
		System.out.println("Airplane latitude: "+latitude);
		System.out.println("Airplane longtitude: "+longtitude);
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
	}

}

// �̷��ÿ� ���׿� ��ȣ������
// ������ ��ȣ�� ���� ���� ������ �����ϰ� ��ȣ�� ���� ����⸸�� Ž��
