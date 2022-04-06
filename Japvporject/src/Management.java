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
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛 曖餌蒂 艙殮й 霞猿婁蒂 摹鷗ж罹 輿撮蹂.	 弛");
		System.out.println("弛   1.頂婁 2.諼婁 3.寰婁 4.綠揣晦婁	 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.print("[opt] : ");
		int opt = sc.nextInt();
		
		switch(opt) {
		case 1:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛        頂婁蒂 摹鷗ж樟蝗棲棻.    	 弛");
			System.out.println("弛  艙殮й 曖餌曖 檜葷擊 殮溘ж罹 輿撮蹂.	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("[檜葷] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Med(name);
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛    頂婁 曖餌 艙殮檜 諫猿腎歷蝗棲棻!   弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		case 2:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛       諼婁蒂 摹鷗ж樟蝗棲棻.    	 弛");
			System.out.println("弛  艙殮й 曖餌曖 檜葷擊 殮溘ж罹 輿撮蹂.	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("[檜葷] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Gsr(name);
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛    諼婁 曖餌 艙殮檜 諫猿腎歷蝗棲棻!   弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		case 3:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛       寰婁蒂 摹鷗ж樟蝗棲棻.    	 弛");
			System.out.println("弛  艙殮й 曖餌曖 檜葷擊 殮溘ж罹 輿撮蹂.	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("[檜葷] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Eye(name);
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛    寰婁 曖餌 艙殮檜 諫猿腎歷蝗棲棻!   弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		case 4:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛       綠揣晦婁蒂 摹鷗ж樟蝗棲棻.    	 弛");
			System.out.println("弛  艙殮й 曖餌曖 檜葷擊 殮溘ж罹 輿撮蹂.	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			System.out.print("[檜葷] : ");
			sc.nextLine();
			name = sc.nextLine();
			
			new Uro(name);
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛  綠揣晦婁 曖餌 艙殮檜 諫猿腎歷蝗棲棻! 	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			break;
		default:
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛         澀跤脹 殮溘殮棲棻.      	弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
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
			if(rs.getString("speciality").equals("頂婁")) Med_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("諼婁")) Gsr_Total += rs.getInt("price");
			else if(rs.getString("speciality").equals("寰婁")) Eye_Total += rs.getInt("price");
			else Uro_Total += rs.getInt("price");
			}
		
//		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
//		System.out.println("弛          [1]婁 滌 衙轎	        弛");
//		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		
		System.out.println("[頂   婁] : " + formatter.format(Med_Total)+ "錳");
		System.out.println("[諼   婁] : " + formatter.format(Gsr_Total) + "錳");
		System.out.println("[寰   婁] : " + formatter.format(Eye_Total) + "錳");
		System.out.println("[綠揣晦婁] : " + formatter.format(Uro_Total) + "錳");
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
		
		System.out.println("[識 衙轎] : " + formatter.format(AllPay) + "錳");
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
			if(rs.getString("speciality").equals("頂婁")) Med_Total++;
			else if(rs.getString("speciality").equals("諼婁")) Gsr_Total++;
			else if(rs.getString("speciality").equals("寰婁")) Eye_Total++;
			else Uro_Total++;
			
			cnt++;
			}
		System.out.println("[頂婁 �素焱 :  " + Med_Total + "貲");
		System.out.println("[諼婁 �素焱 :  " + Gsr_Total + "貲");
		System.out.println("[寰婁 �素焱 :  " + Eye_Total + "貲");
		System.out.println("[綠揣晦婁 �素焱 : " + Uro_Total + "貲");
		System.out.println("[識 �素焱 : " + cnt + "貲");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void PatientInfo() {
		String sql = "SELECT * FROM Wait WHERE ResidentNum = '";
		
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛�素� 輿團蛔煙廓�ㄧ� 殮溘п輿撮蹂.(-んл)	弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		
		System.out.print("[輿團蛔煙廓�β : ");
		String searchnum = sc.nextLine();
		System.out.println();
		
		try {
			pstmt = db.conn.prepareStatement(sql + searchnum + "'");
			rs = pstmt.executeQuery();
		while(rs.next()){	
			System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
			System.out.println("弛[檜葷] 		: " + rs.getString("name") + "   	 弛");
			System.out.println("弛[輿團蛔煙廓�β	: " + rs.getString("ResidentNum") + " 弛");
			System.out.println("弛[釭檜] 		: " + rs.getString("age") + "		 弛");
			System.out.println("弛[撩滌] 		: " + rs.getString("gender") + "	 	 弛");
			System.out.println("弛[輿模] 		: " + rs.getString("address") + "   	 	 弛");
			System.out.println("弛[霞猿婁]		: " + rs.getString("speciality") + "   		 弛");
			System.out.println("弛[曖餌檜葷]	: " + rs.getString("DoctorName") + "   	 弛");
			System.out.println("弛[霞猿衛除]	: " + rs.getString("Time") + "   	 弛");
			System.out.println("弛[霞猿綠] 	: " + formatter.format(Integer.parseInt(rs.getString("price"))) + "錳   	 弛");
			System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DoctorInfo() {
		
		int opt;
	
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
		System.out.println("弛    1.頂婁 2.諼婁 3.寰婁 4.綠揣晦婁	 弛");
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
		System.out.print("[opt] : ");
		opt = sc.nextInt();
		
		switch(opt) {
		case 1: // 頂
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("	頂婁 瞪僥曖 : 檜晦團	    ");
			System.out.println("	�� 賅綰煽錳 頂婁 錳濰	    ");
			System.out.println("	賅綰渠з掖 曖婁渠з 戮餌	    ");
			System.out.println("	賅綰渠з掖煽錳 頂婁 瞪歜曖	");
			System.out.println("	渠и歜鼻蟾擠だз�� 蟾擠だ 歜隸曖 ");
			System.out.println("	渠и頂婁з�� 薑�蛾�		");
			System.out.println("	渠и模�面滼遢簸磈倞� ゎ儅�蛾�	");
			System.out.println("	渠и除з�� ゎ儅�蛾�		");
			System.out.println("	嬴骯 濰餌嫌翱м頂婁 羲歜	");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			break;
		case 2: // 諼婁
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("	諼婁 瞪僥曖 : 堅鷓掏 	    ");
			System.out.println("	賅綰渠з掖 渠з錳 諼婁 夢餌	    ");
			System.out.println("	賅綰渠з掖煽錳(掖熱)	    ");
			System.out.println("	賅綰渠з掖曖猿錳(諼鼻翱掘模濰)	");
			System.out.println("	賅綰渠з掖 諼鼻諼婁 掖熱 ");
			System.out.println("	賅綰渠з掖 曖婁渠з 曖婁з掖褒 掖熱		");
			System.out.println("	賅綰渠з掖煽錳 諼鼻諼婁 婁濰	");
			System.out.println("	賅綰渠з掖曖猿錳 繩欽曖з翱掘錳 諼鼻翱掘模濰		");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			break; 
		case 3: // 寰婁
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("	寰婁 瞪僥曖 : 檜�蕉� 	    ");
			System.out.println("	賅綰曖猿錳 賅綰煽錳 寰婁 熱溼曖 熱猿	    ");
			System.out.println("	賅綰曖猿錳 賅綰煽錳 寰婁 瞪奢曖 熱猿	    ");
			System.out.println("	薯10 瞪癱綠ч欽 о奢曖鼠褒濰 塽 寰婁 婁濰 羲歜	");
			System.out.println("	⑷)賅綰煽錳 寰婁з掖褒 識翕僥�蛻�");
			System.out.println("	⑷)賅綰煽錳 獄薄 渠ル錳濰		");
			System.out.println("	渠и寰婁з��(KOS) 薑�蛾�	");
			System.out.println("	嘐措寰婁з��(AOS) 薑�蛾�		");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			break;
		case 4: // 綠揣晦婁
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			System.out.println("	綠揣晦婁 瞪僥曖 : 鬼辦戮 	    ");
			System.out.println("	匐薑堅衛 14撮 м問	    ");
			System.out.println("	16撮 賅綰渠з掖 曖婁渠з 褻晦褸機(з薄 4.5)	    ");
			System.out.println("	19撮 ж幗萄渠з掖 曖蕨婁 戮餌熱猿	");
			System.out.println("	21撮 撮粽塢蝶煽錳 綠揣晦婁 溯雖湍お");
			System.out.println("	24撮 撮粽塢蝶煽錳 綠揣晦婁 婁濰		");
			System.out.println("	26撮 撮粽塢蝶煽錳 譆翱模 錳濰	");
			System.out.println("	28撮 薯3爾煽餌欽 曖鼠渠渠 霞猿婁 鼻餌 鬼辦戮	");
			System.out.println("式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式");
			break;
		}
		
		
		
		
	}
}
