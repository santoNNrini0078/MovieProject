package exmovie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExMovie {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		MainMenu mm=new MainMenu();
//		mm.choose();
		
		AdminMovie am=new AdminMovie();
		am.FileRead();
	}

}
class AdminMovie	//��ȭ ����ϱ� Ŭ����
{
	Scanner sc=new Scanner(System.in);
	private String moviename;
	private String moviegenre;
	private int no;
	private long stamp;
	private String str;
	void FileRead() throws IOException
	{
		FileReader fr=new FileReader("src/movielist/movielist.txt");
		BufferedReader br=new BufferedReader(fr);
		
		System.out.println("��ȭ ������ �Է����ּ���.");
		moviename=sc.nextLine();
		System.out.println("��ȭ �帣�� �Է����ּ���.");
		moviegenre=sc.nextLine();
		while((moviename=br.readLine())!=null)
		{
			br.readLine();
		}
		br.close();
	}
}


//class MainMenu
//{	
//	//���� �޴��� ��°� �Է¿� ���� ó���� ���	
//	Scanner sc = new Scanner(System.in);
//	private int menu;
//	MainMenu()
//	{
//		
//	}	
//	public void menuPrint()
//	{
//		System.out.println("================================");
//		System.out.println("==========��ȭ ���� ���α׷�=========");
//		System.out.println("================================");	
//		System.out.println("1.��ȭ �Ұ� / 2.��ȭ ���� / 3.���� Ȯ�� / 4.���� ��� / 5.�����ڸ޴� / 6.����");
//		System.out.println("�޴��� �����ϼ���.");
//		menu = sc.nextInt();
//		
//	}	
//	public void choose()
//	{
//		while(true)
//		{
//			menuPrint();
//			switch(menu)
//				{
//				case 1:
//					break;
//				case 2:
//					break;
//				case 3:
//					break;
//				case 4:
//					break;
//				case 5:
//					AdminMenu am=new AdminMenu();
//					am.menuPrint();		//�����ڸ޴� �����ڿ� �޼ҵ� ����
//					break;
//				case 6:
//					break;
//				default:
//					System.out.println("�߸������ϼ̽��ϴ�.");
//					break;
//			}
//		}			
//	}
//}
//class AdminMenu
//{
//	//������ �޴��� ��°� �Է¿� ���� ó���� ���
//	Scanner sc=new Scanner(System.in);
//	public void menuPrint()
//	{
//		System.out.println("������ �޴��Դϴ�. ��й�ȣ�� �Է����ּ���.");
//		int passwd=1234;
//		while(true)
//		{
//			int adpass=sc.nextInt();
//			if(passwd==adpass)
//			{
//				System.out.println("������ �����Ͽ����ϴ�.");
//				choose();
//			}
//			else
//			{
//				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���.");
//			}
//		}
//	}
//	public void choose()
//	{
//		while(true)
//		{
//			System.out.println("1.��ȭ ����ϱ� / 2.��ȭ ��Ϻ��� / 3.��ȭ �����ϱ� / 4.���θ޴��� �̵��ϱ�");
//			int num=sc.nextInt();
//			switch(num)
//			{
//				case 1:
//					
//					break;
//				case 2:
//					
//					break;
//				case 3:
//					
//					break;
//				case 4:
//					MainMenu mm=new MainMenu();
//					mm.menuPrint();
//					break;
//				default:
//					System.out.println("1~4�� �߿� �ٽ� �Է����ּ���.");
//					break;
//			}
//		}
//	}
//}