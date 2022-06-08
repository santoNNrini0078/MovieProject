import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		//영화 소개확인
		//영화 예매하기		
		Scanner sc = new Scanner(System.in);
		MainMenu main = new MainMenu();
		main.choose();	
	}
}
interface Menu
{
	//화면 출력과 이동을 통해 프로그램이 동작하게끔 유도	
	void menuPrint(); //메뉴를 화면에 출력
	void choose();	//사용자가 메뉴를 고르면 동작

}
abstract class AbstractMenu implements Menu
{
	//Menu 인터페이스를 구현하는 추상 클래스
	//메인메뉴(MainMenu)와 관리자메뉴(AdminMenu)의 부모클래스	
	Scanner sc = new Scanner(System.in);
	public abstract void menuPrint();
	int num = sc.nextInt();
	void choose(int num)
	{
		
	}
}

class MainMenu extends AbstractMenu implements Menu
{	
	//메인 메뉴의 출력과 입력에 따른 처리를 담당	
	
	MainMenu()
	{
		
	}	
	
	public void choose()
	{
		System.out.println("================================");
		System.out.println("==========영화 예매 프로그램=========");
		System.out.println("================================");		
		System.out.println("1.영화 소개 / 2.영화 예매 / 3.예매 확인 / 4.예매 취소 / 5.관리자메뉴 / 6.종료");
		System.out.println("메뉴를 선택하세요.");
		int num = sc.nextInt();
		
		while(true)
		{
			switch(num)
				{
				case 1:
					Intro();
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
	public void menuPrint()
	{
		
	}
}
class AdminMenu extends AbstractMenu implements Menu
{
	//관리자 메뉴의 출력과 입력에 따른 처리를 담당
	public void menuPrint()
	{
		
	}
	public void choose()
	{
		dfdf
	}
}
class Movie
{
	//영화 정보를 관리하는 클래스
	//영화 파일 입출력을 담당
	int num;
	String name;
	String genre;
	Movie()
	{
		
	}
	Movie(int num, String name, String genre)
	{
		this.num = num;
		this.name = name;
		this.genre = genre;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
class Reservation
{
	//예매 정보를 관리하는 클래스
	//예매 파일 입출력을 담당
}
class Seats
{
	//예매 좌석을 관리하는 클래스, 좌석은 영화별로 다르게 저장되어야...
	int[][] seat = new int[5][9];
	int number=-2;	//예약이 끝난 좌석은 1, 빈 좌석은 0, 우리는 OX, -1입력시 종료는,,,
	boolean isFull = false;
		
	void viewSeat(Movie mv) //예매한 좌석번호를 배열로 변환하여 담아서 출력?
	{
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		for(int i=0; i<seat.length; i++)
		{
			System.out.print((char)(65+i)); //A,B,C,D...
			for(int j=0; j<seat[i].length; j++)
			{ //if문으로 좌석이 예약되어 있으면 X 그렇지 않으면 O 출력
				System.out.print(" O");
			}
			System.out.println();
		}
		System.out.println("  1 2 3 4 5 6 7 8 9");
	}// 사용자가 입력 범위를 벗어나면 제대로 입력하라고 알려주기
	void choose()
	{
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
			
			Scanner sc = new Scanner(System.in);
			int number = sc.nextInt(); //스트링으로 받아야하네
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
				if(seat[i][j]==0) //좌석이 전부 예약되면 true를 리턴, 그런데 우리는 OX로 할거,,, 그리고 A,B,C
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
	//영화 내용을 소개하는 클래스 , movies.txt를 불러서 읽어야 하나,,,

	File file = new File("src/movie.txt");	
	
	String mvname;
	Movie movie=new Movie();
		
	Intro() throws IOException
	{			
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str=null;
		ArrayList<Movie> al = new ArrayList<Movie>();
		
		while((str=br.readLine()) !=null)
		{
			System.out.println(str); // 확인용, 숨기기
			String[] strArray = str.split(",");
			
			movie = new Movie(Integer.valueOf(strArray[0]),strArray[1],strArray[2]);
			al.add(movie);
		}
		br.close();
		fr.close();
		
		System.out.println("정보를 확인할 영화를 선택하세요");
		for(int i=0; i<al.size(); i++)
		{
			String name = al.get(i).getName();
			System.out.print((i+1)+". "+name+" / ");
		}
		System.out.println();
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int no = sc.nextInt(); // 사용자가 입력한 넘버	
		
		// 사용자가 선택한 번호 불러오기
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNum() == no) //사용자가 입력한 값 대입
			{
				mvname = movie.getName();
				String path2 ="src/test/"+mvname+".txt";
				FileReader fr2 = new FileReader(path2);
				BufferedReader br2 = new BufferedReader(fr2);
				while((str=br2.readLine()) !=null)
				{
					System.out.println(str);
				}
			}
		}
	}	//이전 화면으로 돌아가기, 예매하기 두개의 메뉴
}
