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
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛    [1]蕨擒 [2]蕨擒鏃模 [0]檜瞪   	弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1: // [1]蕨擒
			menu1();
			break;
		case 2: // [2]蕨擒鏃模
			Cancel();
			break;
		case 0: // [0]檜瞪
			break;
		default:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛         澀跤脹 殮溘殮棲棻.      	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		}
	}
	
	void Cancel() {
		String doctorname = null;
		Time Time = null;
		while(true) {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛           蕨擒 鏃模蒂 褒чм棲棻.         	弛");
		System.out.println("弛  �素� 檜葷婁 輿團蛔煙廓�ㄧ� 殮溘п輿撮蹂(-んл)	弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		System.out.print("[檜葷] : ");
		name = sc.next();
		System.out.print("[輿團蛔煙廓�β : ");
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
				System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
				System.out.println("弛  翕橾и 蕨擒檜 橈蝗棲棻. 棻衛 殮溘ж罹輿撮蹂.  	弛");
				System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");		     
		         continue;
		         }
		         else {
		         sql = "UPDATE doctor SET Reserv = '陛棟' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + Time + "'";
		         pstmt = db.conn.prepareStatement(sql);
		         pstmt.executeUpdate();
		         sql = "DELETE FROM Wait WHERE name = '" + name + "' AND ResidentNum = '";
		         pstmt = db.conn.prepareStatement(sql + ResidentNum + "'");
		         pstmt.executeUpdate();
				 
				 	System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
					System.out.println("弛      蕨擒 鏃模陛 諫猿腎歷蝗棲棻.   	弛");
					System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
				 
		         break;
		         }
		} catch (SQLException e) {
			// TODO 濠翕 儅撩脹 catch 綰煙
			e.printStackTrace();
		}
		}
	}
	void Check() {

		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛  �素� 檜葷婁 輿團蛔煙廓�ㄧ� 殮溘п輿撮蹂(-んл)	弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		System.out.print("[檜葷] : ");
		name = sc.next();
		System.out.print("[輿團蛔煙廓�β : ");
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
						
						System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
						System.out.println("弛[檜葷] 		: " + name + "   	 弛");
						System.out.println("弛[輿團蛔煙廓�β	: " + ResidentNum + " 弛");
						System.out.println("弛[輿模] 		: " + address + "		 弛");
						System.out.println("弛[撩滌] 		: " + gender + "	 	 弛");
						System.out.println("弛[釭檜] 		: " + age + "   	 	 弛");
						System.out.println("弛[ID] 		: " + no + "   		 弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						
						System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
						System.out.println("弛            營霞殮棲棻.         	 弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						
					}
					else{
							
						System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
						System.out.println("弛            蟾霞殮棲棻.         	 弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						
						System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
						System.out.println("弛       �素睎� 輿模蒂 殮溘п輿撮蹂.	 弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						System.out.print("[輿模] : ");
						address = sc.next();
							
							new Patient(name, address, ResidentNum);
						}


				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	void menu1() { // 蕨擒
		String speciality;
		String doctorname = null;
		String time;
		
		Check();
		pstmt = null;
		rs = null;
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛         霞猿婁蒂 殮溘ж撮蹂.      	 弛");
		System.out.println("弛                           	 弛");
		System.out.println("弛    [頂婁] [諼婁] [寰婁] [綠揣晦婁]	 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		System.out.print("[霞猿婁] : ");
		speciality = sc.next();
		
		String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
		
		 try {
			pstmt = db.conn.prepareStatement(sql + speciality + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛       瞪僥曖 跡煙擊 轎溘м棲棻.     	 弛");
			System.out.println("弛    霞猿嫡堅 談擎 瞪僥曖蒂 摹鷗ж撮蹂.	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			
			System.out.print("[");
			while(rs.next()) {
				doctorname = rs.getString("DoctorName");
				System.out.print(" " + doctorname + " ");
			}
			System.out.println("]");
		} catch (SQLException e) {
			// TODO 濠翕 儅撩脹 catch 綰煙
			e.printStackTrace();
		}
		
		System.out.print("\n[瞪僥曖] : ");
		doctorname = sc.next();
		
		sql = "SELECT * FROM doctor WHERE DoctorName = '";
		outer:
		while(true) {
		 try {
			pstmt = db.conn.prepareStatement(sql + doctorname + "'");
			rs = pstmt.executeQuery();
			
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛      霞猿 蕨擒 衛除擊 轎溘м棲棻.  	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			
			System.out.print("[");
			while(rs.next()) {
				String test = " " + rs.getString("Time") + " = " + rs.getString("Reserv");
				if(rs.getString("Reserv").equals("碳陛棟")) ColorConsole.red(test);
				else System.out.print(test);
			}
			System.out.println(" ]");
		} catch (SQLException e) {
			// TODO 濠翕 儅撩脹 catch 綰煙
			e.printStackTrace();
		}
		 	
		 	System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛      錳ж朝 衛除擊 摹鷗п輿撮蹂.  	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		 
			System.out.print("[00:00] = ");
			time = sc.next();

			String sql2 = "SELECT * FROM doctor WHERE DoctorName = '"+doctorname + "' AND Time = '";
			
			 try {
				pstmt = db.conn.prepareStatement(sql2 + time + "'");
				rs = pstmt.executeQuery();
				while(rs.next()) {
				if(rs.getString("Reserv").equals("碳陛棟")) {
					
					System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
					System.out.println("弛       檜嘐 蕨擒脹 衛除殮棲棻.    	弛");
					System.out.println("弛 蕨擒 陛棟и 衛除擊 棻衛 殮溘ж罹輿撮蹂.	弛");
					System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
					
					//System.out.println("檜嘐 蕨擒脹 衛除殮棲棻. 蕨擒 陛棟и 衛除擊 棻衛 殮溘ж罹輿撮蹂.");
					break;
				}
				else if(rs.getString("Reserv").equals("陛棟")) {
					sql = "UPDATE doctor SET Reserv = '碳陛棟' WHERE DoctorName = '" + doctorname + "'" + "AND Time = '" + time + "'";
					pstmt = db.conn.prepareStatement(sql);
					
					System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
					System.out.println("弛        蕨擒檜 諫猿腎歷蝗棲棻!    	弛");
					System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
					
		        	int result = pstmt.executeUpdate();
		        	
		        	if(result == 1) {
		        		System.out.println("厥歷評");
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
		            	System.out.println("褒ぬ");
		        	}
					break outer;
				}
				}
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void menu2() { // 霞猿
		Date time = new Date();
		String time1 = format.format(time);
		
		String speciality;
		String doctorname;
		int cnt = 0;
		do {
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛     [1] 寞僥 霞猿 [2] 旎橾 霞猿 �素� 貲欽 [0] 檜瞪	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("[opt] : ");
			opt = sc.nextInt();
			
			switch(opt) {
			case 1:
				Check();
				
				System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
				System.out.println("弛         霞猿婁蒂 殮溘ж撮蹂.      	 弛");
				System.out.println("弛                           	 弛");
				System.out.println("弛    [頂婁] [諼婁] [寰婁] [綠揣晦婁]	 弛");
				System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
				
				System.out.print("[霞猿婁] : ");
				speciality = sc.next();
				
				String sql = "SELECT * FROM doctor WHERE Time = '09:00' AND speciality = '";
				
				System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
				System.out.println("弛       瞪僥曖 跡煙擊 轎溘м棲棻.     	 弛");
				System.out.println("弛    霞猿嫡堅 談擎 瞪僥曖蒂 摹鷗ж撮蹂.	 弛");
				System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
				
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
					// TODO 濠翕 儅撩脹 catch 綰煙
					e.printStackTrace();
				}
				
				System.out.print("\n[瞪僥曖] : ");
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
	        		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
					System.out.println("弛      霞猿 蛔煙檜 諫猿腎歷蝗棲棻.    	弛");
					System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
	        		
	        	}catch(Exception e) {
	            	System.out.println("褒ぬ");
	        	}
				
				break;
			case 2:
        		try {
        			sql = "SELECT * FROM Wait ORDER BY DoctorName ASC, Time ASC";
					pstmt = db.conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
				} catch (SQLException e) {
					// TODO 濠翕 儅撩脹 catch 綰煙
					e.printStackTrace();
				}
        		
        		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        		System.out.println("弛       [2] 旎橾 霞猿 �素� 貲欽	 弛");
				System.out.println("弛    1.頂婁 2.諼婁 3.寰婁 4.綠揣晦婁	 弛");
				System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        		System.out.print("[opt] : ");
        		opt = sc.nextInt();
        		
        		switch(opt) {
        		case 1:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '頂婁' AND DoctorName = '檜晦團'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[瞪僥曖] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '頂婁' ORDER BY DoctorName ASC, Time ASC";
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
        					System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
    						System.out.println("弛         渠晦貲欽檜 橈蝗棲棻.    	弛");
    						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 2:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '諼婁' AND DoctorName = '堅鷓掏'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[瞪僥曖] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '諼婁' ORDER BY DoctorName ASC, Time ASC";
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
    						System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
    						System.out.println("弛         渠晦貲欽檜 橈蝗棲棻.    	弛");
    						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 3:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '寰婁' AND DoctorName = '檜�蕉�'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[瞪僥曖] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '寰婁' ORDER BY DoctorName ASC, Time ASC";
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
    					System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
						System.out.println("弛         渠晦貲欽檜 橈蝗棲棻.    	弛");
						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 4:
        			try {
        				sql = "SELECT * FROM Doctor WHERE speciality = '綠揣晦婁' AND DoctorName = '鬼辦戮'";
    					pstmt = db.conn.prepareStatement(sql);
    					rs = pstmt.executeQuery();
    					while(rs.next()) {
    						if(rs.isFirst()) {
    						System.out.println("[瞪僥曖] : " + rs.getString("doctorname"));
    						break;
    						}
    					}
    					sql = "SELECT * FROM Wait WHERE speciality = '綠揣晦婁' AND DoctorName = '鬼辦戮' ORDER BY DoctorName ASC, Time ASC";
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
        					System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
    						System.out.println("弛         渠晦貲欽檜 橈蝗棲棻.    	弛");
    						System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
    						}
            			}catch(SQLException e) {
            				e.printStackTrace();
            			}
        			break;
        		case 0:
        			break;
        		default:
        			System.out.println("澀跤脹 殮溘殮棲棻.");
        			break;
        		}
				break;
			case 0:
				break;
			default:
				System.out.println("澀跤脹 殮溘殮棲棻.");
				break;
			}
			
		}while(opt != 0);
	}
	
	void menu3() { // 晦煙
		do {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛   [1]婁 滌 衙轎 [2]煽錳 識 衙轎 [3] 橾橾 �素� 熱 [4]�素� 晦煙 褻�� [5]曖餌 薑爾 [6] 曖餌 艙殮 [7]纔蝶お [0]檜瞪 	弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");	
			
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		switch(opt) {
		case 1:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [1]婁 滌 衙轎	        弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.Pay();
			break;
		case 2:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [2]煽錳 識 衙轎	        弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.AllPay();
			break;
		case 3:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [3]橾橾 �素� 熱     	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.patientpeople();
			break;
		case 4:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛         [4]�素� 晦煙 褻��     	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.PatientInfo();
			break;
		case 5:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [5]曖餌 薑爾         	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.DoctorInfo();
			break;
		case 6:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [6]曖餌 艙殮         	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			mng.AddDoctor();
			break;
		case 7:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛          [7]纔 蝶 お         	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			new Med("檜晦團");
			new Gsr("堅鷓掏");
			new Eye("檜�蕉�");
			new Uro("鬼辦戮");
			break;
		case 0:
			break;
		default:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛         澀跤脹 殮溘殮棲棻.      	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		}
		}while(opt != 0);
	}}
