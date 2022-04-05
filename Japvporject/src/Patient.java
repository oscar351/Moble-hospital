import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Patient {
	String name;		// ȯ���̸�
	String address;		// �ּ�
	String ResidentNum;	// �ֹε�Ϲ�ȣ
	int age;			// ����
	DB db = new DB();
	public Patient() { } // �⺻������
	
	Patient(String name, String address, String ResidentNum){
		String gender = null;
		this.name = name;
		this.address = address;
		this.ResidentNum = ResidentNum;
		int age = Integer.parseInt(ResidentNum.substring(0, 2)); // �ֹε�Ϲ�ȣ �� 2�ڸ� 
		
		char ch = ResidentNum.charAt(7); // �ֹε�Ϲ�ȣ ���ڸ� ù�� ° ��ȣ
		if(ch == '1' || ch == '3') gender = "����";
		else if(ch == '2' || ch == '4') gender ="����";
		
		if(ch == '1' || ch == '2') { // 1900��� ����,������ ���
			age = 2022 - (1900+age) + 1;
			this.age = age;
		}
		else if(ch == '3' || ch == '4') { // 2000��� ����,������ ���
			age = 2022 - (2000+age) + 1;
			this.age = age;
		}

        String sql = "insert into Patient values(?,?,?,?,?,?)";
        
        PreparedStatement pstmt = null;
        
        try {
			pstmt = db.conn.prepareStatement(sql);
        	pstmt.setString(1, name);
        	pstmt.setString(2, address);
        	pstmt.setString(3, ResidentNum);
        	pstmt.setString(4, gender);
        	pstmt.setInt(5, age);
        	pstmt.setString(6, null);
        	
        	int result = pstmt.executeUpdate();
        	if(result == 1) {
        		//System.out.println("�־���");
        	}
        }catch(Exception e) {
        	System.out.println("����");
        } finally{
        	try {
        		if(pstmt != null & !pstmt.isClosed()) {
        			pstmt.close();
        		}
        	}catch(Exception e2) {}
        }
		
	}
}

