package Engine;

public class Airport {
	
	String name; // ���׸�
	int latitude, altitude; // ������ ����, �浵
	int runwayLength;// Ȱ�ַ� ����
	
	// ����� �������� �㰡 �� ������ ���� �㰡 �޼ҵ忡���� ť�� ����� ��ü���� ����־� ���������� �㰡��Ų��.
	// ����� ��ü�� Ʈ�������� ����
	
	public Airport(){
		
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
