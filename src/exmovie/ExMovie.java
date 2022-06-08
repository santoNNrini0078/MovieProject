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
	//메인 메뉴의 출력과 입력에 따른 처리를 담당	
	Scanner sc = new Scanner(System.in);
	private int menu;
	MainMenu()
	{
		
	}	
	public void menuPrint()
	{
		System.out.println("================================");
		System.out.println("==========영화 예매 프로그램=========");
		System.out.println("================================");	
		System.out.println("1.영화 소개 / 2.영화 예매 / 3.예매 확인 / 4.예매 취소 / 5.관리자메뉴 / 6.종료");
		System.out.println("메뉴를 선택하세요.");
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
					am.menuPrint();		//관리자메뉴 생성자와 메소드 실행
					break;
				case 6:
					break;
				default:
					System.out.println("잘못선택하셨습니다.");
					break;
			}
		}			
	}
}
class AdminMenu
{
	//관리자 메뉴의 출력과 입력에 따른 처리를 담당
	Scanner sc=new Scanner(System.in);
	public void menuPrint()
	{
		System.out.println("관리자 메뉴입니다. 비밀번호를 입력해주세요.");
		int passwd=1234;
		while(true)
		{
			int adpass=sc.nextInt();
			if(passwd==adpass)
			{
				System.out.println("인증에 성공하였습니다.");
				choose();
			}
			else
			{
				System.out.println("비밀번호가 틀렸습니다. 다시 입력해주세요.");
			}
		}
	}
	public void choose()
	{
		while(true)
		{
			System.out.println("1.영화 등록하기 / 2.영화 목록보기 / 3.영화 삭제하기 / 4.메인메뉴로 이동하기");
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
					System.out.println("1~4번 중에 다시 입력해주세요.");
			}
		}
	}
}