import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		//사용자 - 영화 소개확인, 영화 예매, 내역확인, 취소
		//영화 소개확인
		//영화 예매하기
		Scanner sc = new Scanner(System.in);		
		System.out.println("================================");
		System.out.println("==========영화 예매 프로그램=========");
		System.out.println("================================");		
		System.out.println("1.영화 소개 / 2.영화 예매 / 3.예매 확인 / 4.예매 취소 / 5.관리자메뉴 / 6.종료");
		System.out.println("메뉴를 선택하세요.");
		
		MainMenu main = new MainMenu();
		Scanner sc1 = new Scanner(System.in);
	}
}
abstract class AbstractMenu
{
	//화면 출력과 이동을 통해 프로그램이 동작하게끔 유도

}
interface Menu
{
	//Menu 인터페이스를 구현하는 추상 클래스
	//메인메뉴(MainMenu)와 관리자메뉴(AdminMenu)의 부모클래스
	
}
class MainMenu extends AbstractMenu implements Menu
{
	//메인 메뉴의 출력과 입력에 따른 처리를 담당	
	private int sel;
	MainMenu()
	{
		
	}
	sel = sc1.nextInt();	
		
	while(true)
	{
		switch(sel)
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
				break;
			case 6:
				break;
			default:
				System.out.println("잘못선택하셨습니다.");	
			}		
	}	
}
class AdminMenu extends AbstractMenu implements Menu
{
	//관리자 메뉴의 출력과 입력에 따른 처리를 담당
}
class Movie
{
	//영화 정보를 관리하는 클래스
	//영화 파일 입출력을 담당
}
class Reservation
{
	//예매 정보를 관리하는 클래스
	//예매 파일 입출력을 담당
}
class Seats
{
	//예매 좌석을 관리하는 클래스
	int[][] seat = new int[5][9];
	int number=-2;	//예약이 끝난 좌석은 1, 빈 좌석은 0, 우리는 OX, -1입력시 종료는,,,
	boolean isFull = false;
	while(number != -1 || isFull ==false)
	{
		isFull = isFull(seat);
		if(isFull)
		{
			System.out.println("예약이 모두 완료되었습니다.");
			break;
		}
		System.out.println("좌석을 선택하세요(예 : E-9)");
		System.out.println("처음으로 돌아가려면 'q'를 입력하세요.");
		number = sc1.next(); //스트링으로 받아야하네
		if((number>=1 && number<=10) || number==-1)
		{
			if(number == -1)
			{
				System.out.println("종료되었습니다.");
				break;
			}
			if(seat[number-1][number-1] !=1) //예약되지 않은 좌석의 경우, 어떻게 해야할까,,,
			{
				seat[number-1][number-1] = 1;
			}
			else
			{
				System.out.println("이미 예약된 자리입니다.");
			}
		}
	}
	void viewSeat(int[][] seat)
	{
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		for(int i=0; i<seat.length; i++)
		{
			System.out.print((char)(65+i)); //A,B,C,D...
			for(int j=0; j<seat[i].length; j++)
			{
				System.out.print(" O");
			}
			System.out.println();
		}
		System.out.println("  1 2 3 4 5 6 7 8 9");
	}
	boolean isFull(int[][] seat)
	{
		boolean ret=false;
		int i=0;
		int j=0;
		for(i=0; i<seat.length; i++)
		{
			for(j=0; j<seat[i].length; j++)
			{
				if(seat[i][j]==0) //좌석이 전부 예약되면 true를 리턴, 그런데 우리는 OX로 할거임? 그리고 A,B,C
				{
					break;
				}
			}			
		}
		if(i==seat.length && j==seat[i].length)
		{
			ret=true;
		}
		return ret;
	}
}
class Intro
{
	//영화 내용을 소개하는 클래스
	Intro()
	{
		
	}
	public void intro()
	{
		
	}
}
