package Engine;

public class Plane {
	public int code; double weight, length; // ����� ����, ����, ����
	int fuelTank, fuel; // ����, �����ִ뷮, ���ᷮ(%)
	double speed, height; // �ӵ�, ��
	double x,y; // ��ǥ  
	double dx, dy, dz, angleX, angleY; //����
	double latitude, altitude; // ����(���μ�), �浵(���μ�)
	String name;//������̸�
	
	int status; // ����� ����, 0 : ����, 1 : ������, 2 : Ư����Ȳ
	
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	
	boolean hiJack; // ������ ����
	

	//getter
	public double getPositionX(){ // ����� ����
		return latitude;
	}
	
	public double getPostionY(){ // ����� �浵
		return altitude;
	}
	
	public String getName(){//����� �̸� 
		return name;
	}
	
	public double getHeihgt(){//����� �� 
		return height;
	}
	public int getfuel(){//����� ���� ����(%)
		return fuel;
	}
	public int getfuelTank(){//����� ���� �ִ�
		return fuelTank;
	}
	
	public double getSpeed(){
		return speed;
	}
	//setter
	public void setPositionX(double x){
		latitude=x;
	}
	
	public void setPositionY(double y){
		altitude=y;
	}
	
	public void setName(String text){
		name=text;
	}
	public void setHeight(double x){
		height=x;
	}
	
	public void setfuel(int t,int usefuel){//t�� �ð� usefuel�� ��뿬��
		fuel=100-t*usefuel;
	}
	
	public void setfuelTank(int f){
		fuelTank=f;
	}
	public void setSpeed(int s){
		speed=s;
	}
	public void Move(){ // ����� ��ǥ �̵�
		x=x+(speed-speed*Math.acos(angleX));
		y=y+(speed-speed*Math.asin(angleX));
	}

/*	public void Search(){ // �ֺ� ����� Ž��
		
	}*/
	
	
	public void Data(){ // ����� ���� ���
		
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
