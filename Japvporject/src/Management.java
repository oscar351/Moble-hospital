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
		
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│ 의사를 영입할 진료과를 선택하여 주세요.	 │");
		System.out.println("│   1.내과 2.외과 3.안과 4.비뇨기과	 │");
		System.out.println("└────────────────────────────────┘");
		System.out.print("[opt] : ");
		int opt = sc.nextInt();
		
		switch(opt) {
		case 1:
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│        내과를 선택하셨습니다.    	 │");
			System.out.println("│  영입할 의사의 이름을 입력하여 주세요.	 │");
			System.out.println("└────────────────────────────────┘");
			System.out.print("[이름] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Med(name);
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    내과 의사 영입이 완료되었습니다!   │");
			System.out.println("└───────────────────────────────┘");
			break;
		case 2:
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│       외과를 선택하셨습니다.    	 │");
			System.out.println("│  영입할 의사의 이름을 입력하여 주세요.	 │");
			System.out.println("└────────────────────────────────┘");
			System.out.print("[이름] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Gsr(name);
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    외과 의사 영입이 완료되었습니다!   │");
			System.out.println("└───────────────────────────────┘");
			break;
		case 3:
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│       안과를 선택하셨습니다.    	 │");
			System.out.println("│  영입할 의사의 이름을 입력하여 주세요.	 │");
			System.out.println("└────────────────────────────────┘");
			System.out.print("[이름] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Eye(name);
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│    안과 의사 영입이 완료되었습니다!   │");
			System.out.println("└───────────────────────────────┘");
			break;
		case 4:
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│       비뇨기과를 선택하셨습니다.    	 │");
			System.out.println("│  영입할 의사의 이름을 입력하여 주세요.	 │");
			System.out.println("└────────────────────────────────┘");
			System.out.print("[이름] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Uro(name);
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│  비뇨기과 의사 영입이 완료되었습니다! 	│");
			System.out.println("└───────────────────────────────┘");
			break;
		default:
			System.out.println("┌───────────────────────────────┐");
			System.out.println("│         잘못된 입력입니다.      	│");
			System.out.println("└───────────────────────────────┘");
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
			if(rs.getString("speciality").equals("내과")) Med_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("외과")) Gsr_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("안과")) Eye_Total += rs.getInt("price");
			else Uro_Total += rs.getInt("price");
			}
		
//		System.out.println("┌───────────────────────────────┐");
//		System.out.println("│          [1]과 별 매출	        │");
//		System.out.println("└───────────────────────────────┘");
		
		
		System.out.println("[내   과] : " + formatter.format(Med_Total)+ "원");
		System.out.println("[외   과] : " + formatter.format(Gsr_Total) + "원");
		System.out.println("[안   과] : " + formatter.format(Eye_Total) + "원");
		System.out.println("[비뇨기과] : " + formatter.format(Uro_Total) + "원");
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
		
		System.out.println("[총 매출] : " + formatter.format(AllPay) + "원");
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
			if(rs.getString("speciality").equals("내과")) Med_Total++;
			else if(rs.getString("speciality").equals("외과")) Gsr_Total++;
			else if(rs.getString("speciality").equals("안과")) Eye_Total++;
			else Uro_Total++;
			
			cnt++;
			}
		System.out.println("[내과 환자] :  " + Med_Total + "명");
		System.out.println("[외과 환자] :  " + Gsr_Total + "명");
		System.out.println("[안과 환자] :  " + Eye_Total + "명");
		System.out.println("[비뇨기과 환자] : " + Uro_Total + "명");
		System.out.println("[총 환자] : " + cnt + "명");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void PatientInfo() {
		String sql = "SELECT * FROM Wait WHERE ResidentNum = '";
		
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│환자 주민등록번호를 입력해주세요.(-포함)	│");
		System.out.println("└───────────────────────────────┘");
		
		System.out.print("[주민등록번호] : ");
		String searchnum = sc.nextLine();
		System.out.println();
		
		try {
			pstmt = db.conn.prepareStatement(sql + searchnum + "'");
			rs = pstmt.executeQuery();
		while(rs.next()){	
			System.out.println("┌────────────────────────────────┐");
			System.out.println("│[이름] 		: " + rs.getString("name") + "   	 │");
			System.out.println("│[주민등록번호]	: " + rs.getString("ResidentNum") + " │");
			System.out.println("│[나이] 		: " + rs.getString("age") + "		 │");
			System.out.println("│[성별] 		: " + rs.getString("gender") + "	 	 │");
			System.out.println("│[주소] 		: " + rs.getString("address") + "   	 	 │");
			System.out.println("│[진료과]		: " + rs.getString("speciality") + "   		 │");
			System.out.println("│[의사이름]	: " + rs.getString("DoctorName") + "   	 │");
			System.out.println("│[진료시간]	: " + rs.getString("Time") + "   	 │");
			System.out.println("│[진료비] 	: " + formatter.format(Integer.parseInt(rs.getString("price"))) + "원   	 │");
			System.out.println("└────────────────────────────────┘");
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DoctorInfo() {
		
		int opt;
	
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│    1.내과 2.외과 3.안과 4.비뇨기과	 │");
		System.out.println("└────────────────────────────────┘");
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		
		switch(opt) {
		case 1: // 내
			System.out.println("────────────────────────────────────────────");
			System.out.println("	내과 전문의 : 이기민	    ");
			System.out.println("	現 모블병원 내과 원장	    ");
			System.out.println("	모블대학교 의과대학 석사	    ");
			System.out.println("	모블대학교병원 내과 전임의	");
			System.out.println("	대한임상초음파학회 초음파 임증의 ");
			System.out.println("	대한내과학회 정회원		");
			System.out.println("	대한소화기내시경학회 평생회원	");
			System.out.println("	대한간학회 평생회원		");
			System.out.println("	아산 장사랑연합내과 역임	");
			System.out.println("────────────────────────────────────────────");
			break;
		case 2: // 외과
			System.out.println("────────────────────────────────────────────");
			System.out.println("	외과 전문의 : 고태권 	    ");
			System.out.println("	모블대학교 대학원 외과 박사	    ");
			System.out.println("	모블대학교병원(교수)	    ");
			System.out.println("	모블대학교의료원(외상연구소장)	");
			System.out.println("	모블대학교 외상외과 교수 ");
			System.out.println("	모블대학교 의과대학 의과학교실 교수		");
			System.out.println("	모블대학교병원 외상외과 과장	");
			System.out.println("	모블대학교의료원 첨단의학연구원 외상연구소장		");
			System.out.println("────────────────────────────────────────────");
			break; 
		case 3: // 안과
			System.out.println("────────────────────────────────────────────");
			System.out.println("	안과 전문의 : 이효석 	    ");
			System.out.println("	모블의료원 모블병원 안과 수련의 수료	    ");
			System.out.println("	모블의료원 모블병원 안과 전공의 수료	    ");
			System.out.println("	제10 전투비행단 항공의무실장 및 안과 과장 역임	");
			System.out.println("	현)모블병원 안과학교실 총동문회장");
			System.out.println("	현)모블병원 본점 대표원장		");
			System.out.println("	대한안과학회(KOS) 정회원	");
			System.out.println("	미국안과학회(AOS) 정회원		");
			System.out.println("────────────────────────────────────────────");
			break;
		case 4: // 비뇨기과
			System.out.println("────────────────────────────────────────────");
			System.out.println("	비뇨기과 전문의 : 강우석 	    ");
			System.out.println("	검정고시 14세 합격	    ");
			System.out.println("	16세 모블대학교 의과대학 조기졸업(학점 4.5)	    ");
			System.out.println("	19세 하버드대학교 의예과 석사수료	");
			System.out.println("	21세 세브란스병원 비뇨기과 레지던트");
			System.out.println("	24세 세브란스병원 비뇨기과 과장		");
			System.out.println("	26세 세브란스병원 최연소 원장	");
			System.out.println("	28세 제3보병사단 의무대대 진료과 상사 강우석	");
			System.out.println("────────────────────────────────────────────");
			break;
		}
		
		
		
		
	}
}
