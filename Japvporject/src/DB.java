import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	 private static final String driver="com.mysql.cj.jdbc.Driver";
	 private static final String url="jdbc:MySQL://localhost:3306/Project?autoReconnect=true&useUnicode=true&characterEncoding=euckr";
	 private static final String user="root";
	 private static final String pwd="1234";
	 Connection conn = null;
	 
	 
	 public DB() {
     try {
        Class.forName(driver);
        System.out.println("MySQL ����̹� �ε� ����");
        conn = DriverManager.getConnection(url,user,pwd);
        System.out.println("Connection ���� ����");
        
        System.out.println("Statement  ��ü ���� ����");
     }catch(Exception e) {
        System.out.println("�ε� ����");
        try {
     	   conn.close();
        }catch(SQLException e1) {}
     }
}
}
