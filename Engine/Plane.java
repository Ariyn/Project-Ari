package Engine;

public class Plane {
	public int code, weight, length; // ����� ����, ����, ����
	int fuel, fuelTank, fuelPercent; // ����, �����ִ뷮, ���ᷮ(%)
	int speed, height; // �ӵ�, ��
	int x,y; // ��ǥ 
	int latitude, altitude; // ����(���μ�), �浵(���μ�)
	
	int status; // ����� ����, 0 : ����, 1 : ������, 2 : Ư����Ȳ
	
	// ������ �浵�� 3600���� ������ �߰��� ��ǥ 0���� ���� �¿�� +-500�� �Ҵ��Ѵ�
	// ��ǥ�� +-500�� �ʰ��ϸ� ���� �Ǵ� �浵�� �����Ѵ�.
	
	boolean hiJack; // ������ ����

	public int PositionX(){ // ����� ����
		return latitude;
	}
	
	public int PostionY(){ // ����� �浵
		return altitude;
	}
	
	public void Move(){ // ����� ��ǥ �̵�(���� �Ҹ� ǥ��)
		
	}

	public void Search(){ // �ֺ� ����� Ž��
		
	}
	
	public void SearchPort(Object port){ // �ֺ� ���� Ž��
		
	}
	
	public void Data(){ // ����� ���� ���
		
	}
	
	public boolean LandingSign(){ // ���׿� ���� �㰡 ��û
		boolean sign = true;
		
		return sign;
	}
	
}

// �̷��ÿ� ���׿� ��ȣ������
// ������ ��ȣ�� ���� ���� ������ �����ϰ� ��ȣ�� ���� ����⸸�� Ž��
