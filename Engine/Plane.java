package Engine;

public class Plane {
	public int code; double weight, length; // ����� ����, ����, ����
	int fuelTank, fuel; // ����, �����ִ뷮, ���ᷮ(%)
	double Maxspeed, height, speed; // �ְ�ӵ�, ��,�ӵ�
	double x,y; // ��ǥ  
	double dx, dy, dz, angleX=0, angleY=0; //����
	long latitude, longtitude; // ����(���μ�), �浵(���μ�)
	String name;//������̸�
	
	int status; // ����� ����, 0 : �̷�, 1 : ���� 2: ������
	
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	

	//getter
	Airport port =new Airport();
	public long getPositionX(){ // ����� ����
		return latitude;
	}
	
	public long getPostionY(){ // ����� �浵
		return longtitude;
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
	
	public double getMaxSpeed(){
		return Maxspeed;
	}
	public double getSpeed(){
		return speed;
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
	public void setHeight(double x){
		height=x;
	}
	
	public void setfuel(int t,int usefuel){//t�� �ð� usefuel�� ��뿬��
		fuel=100-t*usefuel;
	}
	
	public void setfuelTank(int f){
		fuelTank=f;
	}
	public void setMaxSpeed(int s){
		Maxspeed=s;
	}
	public void setSpeed(double x){
		speed=x;
	}
	public void TMove(){ // ����� ��ǥ �̵�
		
		x=x+(Maxspeed-Maxspeed*Math.acos(angleX));
		y=y+(Maxspeed-Maxspeed*Math.asin(angleX));
	//	������ �̷� ����
	}
	public void SMove(){
		if (status==0){ //�̷� 
			x=x+speed/2;
			y=y+speed/2;
		}
		else if(status==1){ //����
			x=x+speed/2;
			y=y+speed/2;
		}
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
