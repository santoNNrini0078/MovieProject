package exmovie;

import java.util.Scanner;

public class ExMovie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu mm=new MainMenu();
		mm.choose();
	}

}
class MainMenu
{	
	//���� �޴��� ��°� �Է¿� ���� ó���� ���	
	Scanner sc = new Scanner(System.in);
	private int menu;
	MainMenu()
	{
		
	}	
	public void menuPrint()
	{
		System.out.println("================================");
		System.out.println("==========��ȭ ���� ���α׷�=========");
		System.out.println("================================");	
		System.out.println("1.��ȭ �Ұ� / 2.��ȭ ���� / 3.���� Ȯ�� / 4.���� ��� / 5.�����ڸ޴� / 6.����");
		System.out.println("�޴��� �����ϼ���.");
		menu = sc.nextInt();
		
	}	
	public void choose()
	{
		while(true)
		{
			menuPrint();
			switch(menu)
				{
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					AdminMenu am=new AdminMenu();
					am.menuPrint();		//�����ڸ޴� �����ڿ� �޼ҵ� ����
					break;
				case 6:
					break;
				default:
					System.out.println("�߸������ϼ̽��ϴ�.");
					break;
			}
		}			
	}
}
class AdminMenu
{
	//������ �޴��� ��°� �Է¿� ���� ó���� ���
	Scanner sc=new Scanner(System.in);
	public void menuPrint()
	{
		System.out.println("������ �޴��Դϴ�. ��й�ȣ�� �Է����ּ���.");
		int passwd=1234;
		while(true)
		{
			int adpass=sc.nextInt();
			if(passwd==adpass)
			{
				System.out.println("������ �����Ͽ����ϴ�.");
				choose();
			}
			else
			{
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
	public void choose()
	{
		while(true)
		{
			System.out.println("1.��ȭ ����ϱ� / 2.��ȭ ��Ϻ��� / 3.��ȭ �����ϱ� / 4.���θ޴��� �̵��ϱ�");
			int num=sc.nextInt();
			switch(num)
			{
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					MainMenu mm=new MainMenu();
					mm.menuPrint();
					break;
				default:
					System.out.println("1~4�� �߿� �ٽ� �Է����ּ���.");
			}
		}
	}
}