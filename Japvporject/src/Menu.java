import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.protocol.FullReadInputStream;

public class Menu {
	Scanner sc = new Scanner(System.in);
	Management mng = new Management();
	SimpleDateFormat format = new SimpleDateFormat ( "HH:mm:ss");
	DB db = new DB();
	int opt;
	int patientnum;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String name;
	String ResidentNum;
	String address;
	String gender;
	int age, no;

	void Reservation() {
		System.out.println("������������������������������������������������������������������");
		System.out.println("��    [1]���� [2]������� [0]����   	��");
		System.out.println("������������������������������������������������������������������");
		
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1: // [1]����
			menu1();
			break;
		case 2: // [2]�������
			Cancel();
			break;
		case 0: // [0]����
			break;
		default:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��         �߸��� �Է��Դϴ�.      	��");
			System.out.println("������������������������������������������������������������������");
			break;
		}
	}
	
	void Cancel() {
		String doctorname = null;
		Time Time = null;
		while(true) {
		System.out.println("����������������������������������������������������������������������������������");
		System.out.println("��           ���� ��Ҹ� �����մϴ�.         	��");
		System.out.println("��  ȯ�� �̸��� �ֹε�Ϲ�ȣ�� �Է����ּ���(-����)	��");
		System.out.println("����������������������������������������������������������������������������������");
		
		System.out.print("[�̸�] : ");
		name = sc.next();
		System.out.print("[�ֹε�Ϲ�ȣ] : ");
		ResidentNum = sc.next();
		
		String sql = "SELECT * FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				Time = rs.getTime("Time");
			}
			if(doctorname == null) {
				System.out.println("����������������������������������������������������������������������������������");
				System.out.println("��  ������ ������ �����ϴ�. �ٽ� �Է��Ͽ��ּ���.  	��");
				System.out.println("����������������������������������������������������������������������������������");		     
		         continue;
		         }
		         else {
		         sql = "UPDATE doctor SET Reserv = '����' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + Time + "'";
		         pstmt = db.conn.prepareStatement(sql);
		         pstmt.executeUpdate();
		         sql = "DELETE FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		         pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		         pstmt.executeUpdate();
				 
				 	System.out.println("������������������������������������������������������������������");
					System.out.println("��      ���� ��Ұ� �Ϸ�Ǿ����ϴ�.   	��");
					System.out.println("������������������������������������������������������������������");
				 
		         break;
		         }
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		}
	}
	void Check() {

		System.out.println("����������������������������������������������������������������������������������");
		System.out.println("��  ȯ�� �̸��� �ֹε�Ϲ�ȣ�� �Է����ּ���(-����)	��");
		System.out.println("����������������������������������������������������������������������������������");
		
		System.out.print("[�̸�] : ");
		name = sc.next();
		System.out.print("[�ֹε�Ϲ�ȣ] : ");
		ResidentNum = sc.next();
		
		String sql = "SELECT * FROM Patient WHERE ResidentNum = '";
		
				 try {
					pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
					rs = pstmt.executeQuery();

					if(rs.next() == true) {
						name = rs.getString("name");
						ResidentNum = rs.getNString("ResidentNum");
						address = rs.getString("address");
						gender = rs.getString("gender");
						age = rs.getInt("age");
						no = rs.getInt("no");
						
						System.out.println("��������������������������������������������������������������������");
						System.out.println("��[�̸�] 		: " + name + "   	 ��");
						System.out.println("��[�ֹε�Ϲ�ȣ]	: " + ResidentNum + " ��");
						System.out.println("��[�ּ�] 		: " + address + "		 ��");
						System.out.println("��[����] 		: " + gender + "	 	 ��");
						System.out.println("��[����] 		: " + age + "   	 	 ��");
						System.out.println("��[ID] 		: " + no + "   		 ��");
						System.out.println("��������������������������������������������������������������������");
						
						System.out.println("��������������������������������������������������������������������");
						System.out.println("��            �����Դϴ�.         	 ��");
						System.out.println("��������������������������������������������������������������������");
						
					}
					else{
							
						System.out.println("��������������������������������������������������������������������");
						System.out.println("��            �����Դϴ�.         	 ��");
						System.out.println("��������������������������������������������������������������������");
						
						System.out.println("��������������������������������������������������������������������");
						System.out.println("��       ȯ���� �ּҸ� �Է����ּ���.	 ��");
						System.out.println("��������������������������������������������������������������������");
						System.out.print("[�ּ�] : ");
						address = sc.next();
							
							new Patient(name, address, ResidentNum);
						}


				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	void menu1() { // ����
		String speciality;
		String doctorname = null;
		String time;
		
		Check();
		pstmt = null;
		rs = null;
		
		System.out.println("��������������������������������������������������������������������");
		System.out.println("��         ������� �Է��ϼ���.      	 ��");
		System.out.println("��                           	 ��");
		System.out.println("��    [����] [�ܰ�] [�Ȱ�] [�񴢱��]	 ��");
		System.out.println("��������������������������������������������������������������������");
		
		System.out.print("[�����] : ");
		speciality = sc.next();
		
		String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + speciality + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��       ������ ����� ����մϴ�.     	 ��");
			System.out.println("��    ����ް� ���� �����Ǹ� �����ϼ���.	 ��");
			System.out.println("��������������������������������������������������������������������");
			
			System.out.print("[");
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				System.out.print(" " + doctorname + " ");
			}
			System.out.println("]");
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		
		System.out.print("\n[������] : ");
		doctorname = sc.next();
		
		sql = "SELECT * FROM doctor WHERE DoctorName = '";
		outer:
		while(true) {
		 try {
			pstmt = db.conn.prepareStatement(sql + doctorname + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("��������������������������������������������������������������������");
			System.out.println("��      ���� ���� �ð��� ����մϴ�.  	 ��");
			System.out.println("��������������������������������������������������������������������");
			
			System.out.print("[");
			while(rs.next()) {
				String test = " " + rs.getString("Time") + " = " + rs.getString("Reserv");
				if(rs.getString("Reserv").equals("�Ұ���")) ColorConsole.red(test);
				else System.out.print(test);
			}
			System.out.println(" ]");
		} catch (SQLException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
		 	
		 	System.out.println("��������������������������������������������������������������������");
			System.out.println("��      ���ϴ� �ð��� �������ּ���.  	 ��");
			System.out.println("��������������������������������������������������������������������");
		 
			System.out.print("[00:00] = ");
			time = sc.next();

			String sql2 = "SELECT * FROM doctor WHERE DoctorName = '"+doctorname + "' AND Time = '";
			
			 try {
				pstmt = db.conn.prepareStatement(sql2 + time + "'");
				rs = pstmt.executeQuery();
				while(rs.next()) {
				if(rs.getString("Reserv").equals("�Ұ���")) {
					
					System.out.println("������������������������������������������������������������������");
					System.out.println("��       �̹� ����� �ð��Դϴ�.    	��");
					System.out.println("�� ���� ������ �ð��� �ٽ� �Է��Ͽ��ּ���.	��");
					System.out.println("������������������������������������������������������������������");
					
					//System.out.println("�̹� ����� �ð��Դϴ�. ���� ������ �ð��� �ٽ� �Է��Ͽ��ּ���.");
					break;
				}
				else if(rs.getString("Reserv").equals("����")) {
					sql = "UPDATE doctor SET Reserv = '�Ұ���' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + time + "'";
					pstmt = db.conn.prepareStatement(sql);
					
					System.out.println("������������������������������������������������������������������");
					System.out.println("��        ������ �Ϸ�Ǿ����ϴ�!    	��");
					System.out.println("������������������������������������������������������������������");
					
		        	int result = pstmt.executeUpdate();
		        	
		        	if(result == 1) {
		        		System.out.println("�־���");
		        	}
		        	try {
		        		sql = "SELECT * FROM Patient WHERE ResidentNum = '";
		        		pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		        		rs = pstmt.executeQuery();

						while(rs.next()) {
							name = rs.getString("name");
							ResidentNum = rs.getNString("ResidentNum");
							address = rs.getString("address");
							gender = rs.getString("gender");
							age = rs.getInt("age");
							no = rs.getInt("no");
						}
						sql = "insert into Wait values(?,?,?,?,?,?,?,?,?,?)";
		        		pstmt = db.conn.prepareStatement(sql);
		        		pstmt.setString(1, name);
		        		pstmt.setString(2, address);
		        		pstmt.setString(3, ResidentNum);
		        		pstmt.setString(4, gender);
		        		pstmt.setInt(5, age);
		        		pstmt.setInt(6, no);
		        		pstmt.setString(7, speciality);
		        		pstmt.setString(8, doctorname);
		        		pstmt.setString(9, time);
		        		pstmt.setInt(10, (int)(Math.random()*100000));
		        		pstmt.executeUpdate();
		        	}catch(Exception e) {
		            	System.out.println("����");
		        	}
					break outer;
				}
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void menu2() { // ����
		Date time = new Date();
		String time1 = format.format(time);
		
		String speciality;
		String doctorname;
		int cnt = 0;
		do {
			System.out.println("��������������������������������������������������������������������������������������������������");
			System.out.println("��     [1] �湮 ���� [2] ���� ���� ȯ�� ��� [0] ����	��");
			System.out.println("��������������������������������������������������������������������������������������������������");
			System.out.print("[opt] : ");
			opt = sc.nextInt();
			
			switch(opt) {
			case 1:
				Check();
				
				System.out.println("��������������������������������������������������������������������");
				System.out.println("��         ������� �Է��ϼ���.      	 ��");
				System.out.println("��                           	 ��");
				System.out.println("��    [����] [�ܰ�] [�Ȱ�] [�񴢱��]	 ��");
				System.out.println("��������������������������������������������������������������������");
				
				System.out.print("[�����] : ");
				speciality = sc.next();
				
				String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
				
				System.out.println("��������������������������������������������������������������������");
				System.out.println("��       ������ ����� ����մϴ�.     	 ��");
				System.out.println("��    ����ް� ���� �����Ǹ� �����ϼ���.	 ��");
				System.out.println("��������������������������������������������������������������������");
				
				 try {
					pstmt = db.conn.prepareStatement(sql + speciality + "'");
					rs = pstmt.executeQuery();
					
					System.out.print("[");
					while(rs.next()) {
						doctorname = rs.getString("DoctorName");
						System.out.print(" " + doctorname + " ");
					}
					System.out.println("]");
				} catch (SQLException e) {
					// TODO �ڵ� ������ catch ���
					e.printStackTrace();
				}
				
				System.out.print("\n[������] : ");
				doctorname = sc.next();
				
				try {
	        		sql = "SELECT * FROM Patient WHERE ResidentNum = '";
	        		pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
	        		rs = pstmt.executeQuery();

					while(rs.next()) {
						name = rs.getString("name");
						ResidentNum = rs.getNString("ResidentNum");
						address = rs.getString("address");
						gender = rs.getString("gender");
						age = rs.getInt("age");
						no = rs.getInt("no");
					}
					sql = "insert into Wait values(?,?,?,?,?,?,?,?,?,?)";
	        		pstmt = db.conn.prepareStatement(sql);
	        		pstmt.setString(1, name);
	        		pstmt.setString(2, address);
	        		pstmt.setString(3, ResidentNum);
	        		pstmt.setString(4, gender);
	        		pstmt.setInt(5, age);
	        		pstmt.setInt(6, no);
	        		pstmt.setString(7, speciality);
	        		pstmt.setString(8, doctorname);
	        		pstmt.setString(9, time1);
	        		pstmt.setInt(10, (int)(Math.random()*100000));
	        		pstmt.executeUpdate();
	        		System.out.println("������������������������������������������������������������������");
					System.out.println("��      ���� ����� �Ϸ�Ǿ����ϴ�.    	��");
					System.out.println("������������������������������������������������������������������");
	        		
	        	}catch(Exception e) {
	            	System.out.println("����");
	        	}
				
				break;
			case 2:
        		try {
        			sql = "SELECT * FROM Wait ORDER BY DoctorName ASC, Time ASC";
					pstmt = db.conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
				} catch (SQLException e) {
					// TODO �ڵ� ������ catch ���
					e.printStackTrace();
				}
        		
        		System.out.println("��������������������������������������������������������������������");
        		System.out.println("��       [2] ���� ���� ȯ�� ���	 ��");
				System.out.println("��    1.���� 2.�ܰ� 3.�Ȱ� 4.�񴢱��	 ��");
				System.out.println("��������������������������������������������������������������������");
        		System.out.print("[opt] : ");
        		opt = sc.nextInt();
        		
        		switch(opt) {
        		case 1:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '����' AND DoctorName = '�̱��'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[������] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '����' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
        					System.out.println("������������������������������������������������������������������");
    						System.out.println("��         ������� �����ϴ�.    	��");
    						System.out.println("������������������������������������������������������������������");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 2:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '�ܰ�' AND DoctorName = '���±�'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[������] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '�ܰ�' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
    						System.out.println("������������������������������������������������������������������");
    						System.out.println("��         ������� �����ϴ�.    	��");
    						System.out.println("������������������������������������������������������������������");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 3:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '�Ȱ�' AND DoctorName = '��ȿ��'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[������] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '�Ȱ�' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
    					System.out.println("������������������������������������������������������������������");
						System.out.println("��         ������� �����ϴ�.    	��");
						System.out.println("������������������������������������������������������������������");
						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 4:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '�񴢱��' AND DoctorName = '���켮'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[������] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '�񴢱��' AND DoctorName = '���켮' ORDER BY DoctorName ASC, Time ASC";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					boolean result = rs.isBeforeFirst();
    					if(result){
    						while(rs.next()) {
    						System.out.print("[");
    						System.out.print(rs.getString("name") + " ");
    						System.out.print(rs.getString("Time"));
    						System.out.print("]" + " ");
    						}
    						System.out.println();
    						}
    					else {
        					System.out.println("������������������������������������������������������������������");
    						System.out.println("��         ������� �����ϴ�.    	��");
    						System.out.println("������������������������������������������������������������������");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 0:
        			break;
        		default:
        			System.out.println("�߸��� �Է��Դϴ�.");
        			break;
        		}
				break;
			case 0:
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				break;
			}
			
		}while(opt != 0);
	}
	
	void menu3() { // ���
		do {
		System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
		System.out.println("��   [1]�� �� ���� [2]���� �� ���� [3] ���� ȯ�� �� [4]ȯ�� ��� ��ȸ [5]�ǻ� ���� [6] �ǻ� ���� [7]�׽�Ʈ [0]���� 	��");
		System.out.println("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");	
			
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [1]�� �� ����	        ��");
			System.out.println("������������������������������������������������������������������");
			mng.Pay();
			break;
		case 2:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [2]���� �� ����	        ��");
			System.out.println("������������������������������������������������������������������");
			mng.AllPay();
			break;
		case 3:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [3]���� ȯ�� ��     	��");
			System.out.println("������������������������������������������������������������������");
			mng.patientpeople();
			break;
		case 4:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��         [4]ȯ�� ��� ��ȸ     	��");
			System.out.println("������������������������������������������������������������������");
			mng.PatientInfo();
			break;
		case 5:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [5]�ǻ� ����         	��");
			System.out.println("������������������������������������������������������������������");
			mng.DoctorInfo();
			break;
		case 6:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [6]�ǻ� ����         	��");
			System.out.println("������������������������������������������������������������������");
			mng.AddDoctor();
			break;
		case 7:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��          [7]�� �� Ʈ         	��");
			System.out.println("������������������������������������������������������������������");
			new Med("�̱��");
			new Gsr("���±�");
			new Eye("��ȿ��");
			new Uro("���켮");
			break;
		case 0:
			break;
		default:
			System.out.println("������������������������������������������������������������������");
			System.out.println("��         �߸��� �Է��Դϴ�.      	��");
			System.out.println("������������������������������������������������������������������");
			break;
		}
		}while(opt != 0);
	}}
