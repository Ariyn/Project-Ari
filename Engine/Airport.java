package Engine;

public class Airport {
	
	String name; // ���׸�
	int latitude, altitude; // ������ ����, �浵
	int maximum_airplane; // ����� �ִ� ���� ���� ��
	
	int[] runways = new int[1];// Ȱ�ַ� ����
	
	// ����� �������� �㰡 �� ������ ���� �㰡 �޼ҵ忡���� ť�� ����� ��ü���� ����־� ���������� �㰡��Ų��.
	// ����� ��ü�� Ʈ�������� ����
	
	public void setName(String text){
		name = text;
	}
	
	public void setPosition(int x, int y){
		latitude = x;
		altitude = y;
	}
	
	public void setRunwyas(int num, int length){
		runways[num] = length;
	}
	
	public String getName(){
		return name;
	}
	
	public int PositionX(){ // ���� ��ġ �����ִ� �޼ҵ�
		return latitude;
	}
	
	public int PositionY(){
		return altitude;
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
