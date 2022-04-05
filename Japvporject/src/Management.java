import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.ParseInfo;

public class Management {
	Scanner sc = new Scanner(System.in);
	DB db = new DB();
	String name;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void AddDoctor() {
//		int num = doctor.size();
		
		System.out.println("��������������������������������������������������������������������");
		System.out.println("�� �ǻ縦 ������ ������� �����Ͽ� �ּ���.	 ��");
		System.out.println("��   1.���� 2.�ܰ� 3.�Ȱ� 4.�񴢱��	 ��");
		System.out.println("��������������������������������������������������������������������");
		System.out.print("[opt] : ");
		int opt = sc.nextInt();
		
		switch(opt) {
		case 1:
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��        ������ �����ϼ̽��ϴ�.    	 ��");
			System.out.println("��  ������ �ǻ��� �̸��� �Է��Ͽ� �ּ���.	 ��");
			System.out.println("��������������������������������������������������������������������");
			System.out.print("[�̸�] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Med(name);
			System.out.println("������������������������������������������������������������������");
			System.out.println("��    ���� �ǻ� ������ �Ϸ�Ǿ����ϴ�!   ��");
			System.out.println("������������������������������������������������������������������");
			break;
		case 2:
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��       �ܰ��� �����ϼ̽��ϴ�.    	 ��");
			System.out.println("��  ������ �ǻ��� �̸��� �Է��Ͽ� �ּ���.	 ��");
			System.out.println("��������������������������������������������������������������������");
			System.out.print("[�̸�] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Gsr(name);
			System.out.println("������������������������������������������������������������������");
			System.out.println("��    �ܰ� �ǻ� ������ �Ϸ�Ǿ����ϴ�!   ��");
			System.out.println("������������������������������������������������������������������");
			break;
		case 3:
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��       �Ȱ��� �����ϼ̽��ϴ�.    	 ��");
			System.out.println("��  ������ �ǻ��� �̸��� �Է��Ͽ� �ּ���.	 ��");
			System.out.println("��������������������������������������������������������������������");
			System.out.print("[�̸�] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Eye(name);
			System.out.println("������������������������������������������������������������������");
			System.out.println("��    �Ȱ� �ǻ� ������ �Ϸ�Ǿ����ϴ�!   ��");
			System.out.println("������������������������������������������������������������������");
			break;
		case 4:
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��       �񴢱���� �����ϼ̽��ϴ�.    	 ��");
			System.out.println("��  ������ �ǻ��� �̸��� �Է��Ͽ� �ּ���.	 ��");
			System.out.println("��������������������������������������������������������������������");
			System.out.print("[�̸�] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Uro(name);
			System.out.println("������������������������������������������������������������������");
			System.out.println("��  �񴢱�� �ǻ� ������ �Ϸ�Ǿ����ϴ�! 	��");
			System.out.println("������������������������������������������������������������������");
			break;
		default:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��         �߸��� �Է��Դϴ�.      	��");
			System.out.println("������������������������������������������������������������������");
			break;
		}
	}

	DecimalFormat formatter = new DecimalFormat("###,###");
	
	public void Pay() {
		int Med_Total = 0, Gsr_Total = 0, Eye_Total = 0, Uro_Total = 0;
		
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
			if(rs.getString("speciality").equals("����")) Med_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("�ܰ�")) Gsr_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("�Ȱ�")) Eye_Total += rs.getInt("price");
			else Uro_Total += rs.getInt("price");
			}
		
//		System.out.println("������������������������������������������������������������������");
//		System.out.println("��          [1]�� �� ����	        ��");
//		System.out.println("������������������������������������������������������������������");
		
		
		System.out.println("[��   ��] : " + formatter.format(Med_Total)+ "��");
		System.out.println("[��   ��] : " + formatter.format(Gsr_Total) + "��");
		System.out.println("[��   ��] : " + formatter.format(Eye_Total) + "��");
		System.out.println("[�񴢱��] : " + formatter.format(Uro_Total) + "��");
		}catch(SQLException e) {
			e.printStackTrace();
		}
}
	public void AllPay() {
		int AllPay = 0;
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
				AllPay += rs.getInt("price");
			}
		
		System.out.println("[�� ����] : " + formatter.format(AllPay) + "��");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void patientpeople() {
		int cnt = 0, Med_Total = 0, Gsr_Total = 0, Eye_Total = 0, Uro_Total = 0;
		String sql = "SELECT * FROM Wait";
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while(rs.next()){
			if(rs.getString("speciality").equals("����")) Med_Total++;
			else if(rs.getString("speciality").equals("�ܰ�")) Gsr_Total++;
			else if(rs.getString("speciality").equals("�Ȱ�")) Eye_Total++;
			else Uro_Total++;
			
			cnt++;
			}
		System.out.println("[���� ȯ��] :  " + Med_Total + "��");
		System.out.println("[�ܰ� ȯ��] :  " + Gsr_Total + "��");
		System.out.println("[�Ȱ� ȯ��] :  " + Eye_Total + "��");
		System.out.println("[�񴢱�� ȯ��] : " + Uro_Total + "��");
		System.out.println("[�� ȯ��] : " + cnt + "��");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void PatientInfo() {
		String sql = "SELECT * FROM Wait WHERE ResidentNum = '";
		
		System.out.println("������������������������������������������������������������������");
		System.out.println("��ȯ�� �ֹε�Ϲ�ȣ�� �Է����ּ���.(-����)	��");
		System.out.println("������������������������������������������������������������������");
		
		System.out.print("[�ֹε�Ϲ�ȣ] : ");
		String searchnum = sc.nextLine();
		System.out.println();
		
		try {
			pstmt = db.conn.prepareStatement(sql + searchnum + "'");
			rs = pstmt.executeQuery();
		while(rs.next()){	
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��[�̸�] 		: " + rs.getString("name") + "   	 ��");
			System.out.println("��[�ֹε�Ϲ�ȣ]	: " + rs.getString("ResidentNum") + " ��");
			System.out.println("��[����] 		: " + rs.getString("age") + "		 ��");
			System.out.println("��[����] 		: " + rs.getString("gender") + "	 	 ��");
			System.out.println("��[�ּ�] 		: " + rs.getString("address") + "   	 	 ��");
			System.out.println("��[�����]		: " + rs.getString("speciality") + "   		 ��");
			System.out.println("��[�ǻ��̸�]	: " + rs.getString("DoctorName") + "   	 ��");
			System.out.println("��[����ð�]	: " + rs.getString("Time") + "   	 ��");
			System.out.println("��[�����] 	: " + formatter.format(Integer.parseInt(rs.getString("price"))) + "��   	 ��");
			System.out.println("��������������������������������������������������������������������");
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DoctorInfo() {
		
		int opt;
	
		System.out.println("��������������������������������������������������������������������");
		System.out.println("��    1.���� 2.�ܰ� 3.�Ȱ� 4.�񴢱��	 ��");
		System.out.println("��������������������������������������������������������������������");
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		
		switch(opt) {
		case 1: // ��
			System.out.println("����������������������������������������������������������������������������������������");
			System.out.println("	���� ������ : �̱��	    ");
			System.out.println("	�� ����� ���� ����	    ");
			System.out.println("	�����б� �ǰ����� ����	    ");
			System.out.println("	�����б����� ���� ������	");
			System.out.println("	�����ӻ���������ȸ ������ ������ ");
			System.out.println("	���ѳ�����ȸ ��ȸ��		");
			System.out.println("	���Ѽ�ȭ�⳻�ð���ȸ ���ȸ��	");
			System.out.println("	���Ѱ���ȸ ���ȸ��		");
			System.out.println("	�ƻ� �������ճ��� ����	");
			System.out.println("����������������������������������������������������������������������������������������");
			break;
		case 2: // �ܰ�
			System.out.println("����������������������������������������������������������������������������������������");
			System.out.println("	�ܰ� ������ : ���±� 	    ");
			System.out.println("	�����б� ���п� �ܰ� �ڻ�	    ");
			System.out.println("	�����б�����(����)	    ");
			System.out.println("	�����б��Ƿ��(�ܻ󿬱�����)	");
			System.out.println("	�����б� �ܻ�ܰ� ���� ");
			System.out.println("	�����б� �ǰ����� �ǰ��б��� ����		");
			System.out.println("	�����б����� �ܻ�ܰ� ����	");
			System.out.println("	�����б��Ƿ�� ÷�����п����� �ܻ󿬱�����		");
			System.out.println("����������������������������������������������������������������������������������������");
			break; 
		case 3: // �Ȱ�
			System.out.println("����������������������������������������������������������������������������������������");
			System.out.println("	�Ȱ� ������ : ��ȿ�� 	    ");
			System.out.println("	����Ƿ�� ����� �Ȱ� ������ ����	    ");
			System.out.println("	����Ƿ�� ����� �Ȱ� ������ ����	    ");
			System.out.println("	��10 ��������� �װ��ǹ����� �� �Ȱ� ���� ����	");
			System.out.println("	��)����� �Ȱ��б��� �ѵ���ȸ��");
			System.out.println("	��)����� ���� ��ǥ����		");
			System.out.println("	���ѾȰ���ȸ(KOS) ��ȸ��	");
			System.out.println("	�̱��Ȱ���ȸ(AOS) ��ȸ��		");
			System.out.println("����������������������������������������������������������������������������������������");
			break;
		case 4: // �񴢱��
			System.out.println("����������������������������������������������������������������������������������������");
			System.out.println("	�񴢱�� ������ : ���켮 	    ");
			System.out.println("	������� 14�� �հ�	    ");
			System.out.println("	16�� �����б� �ǰ����� ��������(���� 4.5)	    ");
			System.out.println("	19�� �Ϲ�����б� �ǿ��� �������	");
			System.out.println("	21�� ����������� �񴢱�� ������Ʈ");
			System.out.println("	24�� ����������� �񴢱�� ����		");
			System.out.println("	26�� ����������� �ֿ��� ����	");
			System.out.println("	28�� ��3������� �ǹ���� ����� ��� ���켮	");
			System.out.println("����������������������������������������������������������������������������������������");
			break;
		}
		
		
		
		
	}
}
