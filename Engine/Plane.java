package Engine;

public class Plane {
	double weight, length; // ����, ����
	int fuelTank, fuel; // �����ִ뷮, ���ᷮ(%)
	double Maxspeed, altitude, speed; // �ְ�ӵ�, ��,�ӵ�
	double x,y; // ��ǥ  
	double dx, dy, dz, angleX=0, angleY=0; //����
	long latitude, longtitude; // ����(���μ�), �浵(���μ�)
	String name, company;//������̸�, ȸ���
	
	int status; // ����� ����, 0 : �̷�, 1 : ���� 2: ������
	
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	

	//getter
	public long getPositionX(){ // ����� ����
		return latitude;
	}
	
	public long getPostionY(){ // ����� �浵
		return longtitude;
	}
	
	public String getName(){//����� �̸� 
		return name;
	}
	
	public double getAltitude(){//����� �� 
		return altitude;
	}
	public int getfuel(){//����� ���� ����(%)
		return fuel;
	}
	public int getfuelTank(){//����� ���� �ִ�
		return fuelTank;
	}
	
	public double getMaxSpeed(){
		return Maxspeed;
	}
	public double getSpeed(){
		return speed;
	}
	public String getCompany(){
		return company;
	}
	//setter
	public void setPositionX(long x){
		latitude=x;
	}
	
	public void setPositionY(long y){
		longtitude=y;
	}
	
	public void setName(String text){
		name=text;
	}
	public void setAltitude(double x){
		altitude=x;
	}
	
	public void setfuel(int t){//1�ʿ� 4����
		fuel=100;
	}
	
	public void setfuelTank(long f){
		int number = (int)f;
		fuelTank=number;
	}
	public void setMaxSpeed(long s){
		int number = (int)s;
		Maxspeed=number;
	}
	public void setSpeed(double x){
		speed=x;
	}
	public void setSCompany(String s){
		company=s;
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

/*	public void Search(){ // �ֺ� ����� Ž��(�ֺ��׷����� Ž���Ѵ�)
		
	}*/
	

	public void Data(){ // ����� ���� ���
		System.out.println("Airplane name: "+name);
		System.out.println("Airplane company: "+company);
		System.out.println("Airplane weight : "+weight);
		System.out.println("Airplane length : "+length);
		System.out.println("Airplane fuelTank : "+fuelTank);
		System.out.println("Airplane fuel: "+fuel);
		System.out.println("Airplane Maxspeed: "+Maxspeed);
		System.out.println("Airplane speed: "+speed);
		System.out.println("Airplane altitude: "+altitude);
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
