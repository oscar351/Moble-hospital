import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Doctor{
	String speciality;
	String DoctorName;
	
	int hour = 10;
	String min = "00";
	TreeMap<String, String> HM;
	
	DB db = new DB();
	
	Doctor(String DoctorName, String speciality){
		this.DoctorName = DoctorName;
		this.speciality = speciality;
		
		HM = new TreeMap<>();
		HM.put("09:00", "����");
		HM.put("09:30", "����");
		for(int i=0; i<4; i++) {
			String str = hour +":"+ min;
			HM.put(str, "����");
			if(min == "00") {
				min = "30";
			}else {
				min = "00";
				hour++;
			}
		}
		
		hour = 14;
		min = "00";
		for(int i = 0; i < 8; i++) {
			String str = hour + ":"+ min;
			HM.put(str, "����");
			if(min == "00") {
				min = "30";
			}else {
				min = "00";
				hour++;
			}
		}
        int result = 0;
        String sql = "insert into Doctor values(?,?,?,?)";
        
		PreparedStatement pstmt = null;
        try {
        	pstmt = db.conn.prepareStatement(sql);
        	for(String s : HM.keySet()) {
        	pstmt.setString(1, speciality);
        	pstmt.setString(2, DoctorName);
        	pstmt.setString(3, HM.ceilingKey(s));
        	pstmt.setString(4, HM.get(s));
        	result = pstmt.executeUpdate();
			}
        	
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

class Med extends Doctor{ // ����(Internal Medicine)
	Med(String name){
		super(name, "����");
	}
	@Override
	public String toString() {
		return "����(Internal Medicine)";
	}
}

class Gsr extends Doctor{ // �ܰ�(General Surgery)
	
	Gsr(String name){
		super(name, "�ܰ�");
	}
	
	@Override
	public String toString() {
		return "�ܰ�(General Surgery)";
	}
}

class Eye extends Doctor{ // �Ȱ�(Ophthalmology)
	Eye(String name){
		super(name, "�Ȱ�");
	}
	
	@Override
	public String toString() {
		return "�Ȱ�(Ophthalmology)";
	}
}

class Uro extends Doctor{ // �񴢱��(Urology)

	Uro(String name){
		super(name, "�񴢱��");
	}
	
	@Override
	public String toString() {
		return "�񴢱��(Urology)";
	}
}