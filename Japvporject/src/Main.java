import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
   	public static void PrintLine() {
		System.out.println("---------------------------------------");
	}
	
	public static void main(String[] args) {
		DB db = new DB();
	      Scanner sc = new Scanner(System.in);
	      Menu mn = new Menu();
	      int opt;
	      
	      
	      do {
				System.out.println("������������������������������������������������������������������");
				System.out.println("��                           	��");
				System.out.println("��       ����� �������� �ý���   	��");
				System.out.println("��                           	��");
				System.out.println("������������������������������������������������������������������");
				
				System.out.println("������������������������������������������������������������������");
				System.out.println("��  (1)���� (2)���� (3)���� (0)����	��");
				System.out.println("������������������������������������������������������������������");
				
				System.out.print("(opt) : ");
				opt = sc.nextInt();
				
				switch(opt) {
				case 1: // ȯ�� ����ý���
					System.out.println("������������������������������������������������������������������");
					System.out.println("��  (1) ȯ�� ���� �ý����� �����մϴ�.	��");
					System.out.println("������������������������������������������������������������������");
					// ����� ����
					mn.Reservation();
					break;
				case 2:
					System.out.println("������������������������������������������������������������������");
					System.out.println("��  (2) ȯ�� ���� �ý����� �����մϴ�.	��");
					System.out.println("������������������������������������������������������������������");
					
					mn.menu2();
					break;
				case 3:
					System.out.println("������������������������������������������������������������������");
					System.out.println("��  (3) ���� �ý����� �����մϴ�.   	��");
					System.out.println("������������������������������������������������������������������");
					mn.menu3();
					break;
				default:
					System.out.println("������������������������������������������������������������������");
					System.out.println("��         �߸��� �Է��Դϴ�.      	��");
					System.out.println("������������������������������������������������������������������");
					break;
				case 0:
					System.out.println("������������������������������������������������������������������");
					System.out.println("��       ���α׷��� �����մϴ�.      	��");
					System.out.println("������������������������������������������������������������������");
					break;
				}
			} while(opt != 0);

	   }

}
