import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

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
	public abstract void menuPrint();
	public abstract void choose();
}

class MainMenu extends AbstractMenu implements Menu
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
					break;
				case 6:
					break;
				default:
					System.out.println("잘못선택하셨습니다.");	
			}
		}			
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
		
	}
}
class Movie
{
	//영화 정보를 관리하는 클래스
	//영화 파일 입출력을 담당
	int no;
	long stamp;
	String name;
	String genre;
	Movie()
	{
		
	}
	Movie(int no, long stamp, String name, String genre)
	{
		this.no = no;
		this.stamp = stamp;
		this.name = name;
		this.genre = genre;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public long getStamp() {
		return stamp;
	}
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	
}
class Reservation extends Movie
{
	//예매 정보를 관리하는 클래스
	//예매 파일 입출력을 담당
	int no=0;
	long reStamp=0;
	long stamp=0;	
	String name="aaa"; //사용자가 입력하는 값 
	String seat="a-1"; //사용자가 입력하는 값String name;
	
	ArrayList<Movie> al = new ArrayList<Movie>();
	Reservation(int no, long reStamp, long stamp, String name, String seat)
	{
		this.no=no;
		this.reStamp=reStamp;
		this.stamp=stamp;
		this.name=name;
		this.seat=seat;
	}
	
	void print() throws IOException //영화 목록을 소개 / 따로 클래스를 만들 수 있나
	{
		File file = new File("src/movie.txt");	
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);		
		String str=null;	
		
		while((str=br.readLine()) !=null)
		{
			System.out.print(str);	//영화 목록보여주기
		}
		br.close();
		fr.close();
		
		System.out.println("영화를 선택하세요");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // 사용자가 입력한 넘버	
		
		name = "";//무비에서 가져오기
		stamp = System.currentTimeMillis();
	}	
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	
	void write() throws IOException //예매 정보 저장하기 //0504커피메인참고
	{
		File file = new File("src/reservation.txt");
		FileWriter fw = new FileWriter(file,true); //파일이 날아가지 않게 추가하기 (name,append)
		BufferedWriter bw = new BufferedWriter(fw);
		
		if (!file.exists())
		{
			System.out.println("파일을 생성합니다.");
			file.createNewFile();
		}		
		
		
		bw.write(no+","+reStamp+","+stamp+","+name+","+seat); //저장	넘버는 어떻게 저장?	
		
		bw.close();
		fw.close();
	}	
}
class Seats
{
	//예매 좌석을 관리하는 클래스, 좌석은 영화별로 다르게 저장되어야...
	int[][] seat = new int[5][9];
	int number=-2;	//예약이 끝난 좌석은 1, 빈 좌석은 0, 우리는 OX, -1입력시 종료는,,,
	boolean isFull = false;
	String str=null;
	
	Reservation re;
	
	ArrayList<Reservation> al = new ArrayList<Reservation>();
	
	Seats() throws IOException {
		
		File file = new File("src/reservation.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);					
		
		while((str=br.readLine()) !=null)
		{
			System.out.println(str); // 확인용, 숨기기	
			String[] strArray = str.split(",");
			
			Reservation re = new Reservation(Integer.valueOf(strArray[0]),
					Long.valueOf(strArray[1]),Long.valueOf(strArray[2]),strArray[3],strArray[4]); //영화 소개파일 형식에 맞게 변경
			al.add(re);
		}		
		br.close();
		fr.close();		
	}

	void viewSeat(Reservation re) //예매한 좌석번호를 배열로 변환하여 담아서 출력? //무비를 어디서 가져오나?
	{	
		
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		
		for(int i=0; i<al.size(); i++)
		{
			if(al.get(i).getName().equals(re.getName())) //선택한 영화 이름과 예매 파일의 영화 이름이 같은 경우에
			{
				String sNo = al.get(i).getSeat(); //해당 영화의 예매된 좌석 번호를 불러온다.
				String[] seatNo = sNo.split("-"); //좌석 번호는 부호를 기준으로 나누어 배열에 저장한다.
				
				for(int j=0; j<seat.length; j++)
				{
					System.out.print((char)(65+j)); //A,B,C,D...
					for(int k=0; k<seat[j].length; k++)
					{ //if문으로 좌석이 예약되어 있으면 X 그렇지 않으면 O 출력
						
						if(seatNo.equals(seat[j][k])) //(E,9)와 같이 나뉜 좌석 번호와 seat의 좌석을 비교하여 같으면 X
						{
							System.out.print(" X");
						}
						else {
						System.out.print(" O");	//그렇지 않으면 O를 출력한다.
						}
					}
					System.out.println();
				}
				System.out.println("  1 2 3 4 5 6 7 8 9");
			}// 사용자가 입력 범위를 벗어나면 제대로 입력하라고 알려주기
			
		}

				
	}
	void choose()
	{
		while(number!='q' || isFull ==false) //number가 아닌데,,,
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
			
			String seatSelect = sc.next();
			String[] seatNo = seatSelect.split("-");
			
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
				if((int)seat[i][j]==(int)'O') //좌석이 전부 예약되면 true를 리턴, 그런데 우리는 OX로 할거,,, 그리고 A,B,C
				{
					break;
				}				
			}			
		}
		if(i==seat.length && j==seat[i].length)
		{
			ret=true;
		}//같지 않으면?? 여기에 쓰면 되나?
		return ret;
	}
}
class Intro
{
	//영화 내용을 소개하는 클래스 //예매 안에 소개가 들어가는게 더 좋을것 같은데,,,

	File file = new File("src/movie.txt");	
	
	String mvname;
	Movie movie=new Movie();
	int no;
		
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
			
			movie = new Movie(Integer.valueOf(strArray[0]),Integer.valueOf(strArray[1]),strArray[2],strArray[3]); //영화 소개파일 형식에 맞게 변경
			al.add(movie);
		}
		br.close();
		fr.close();
		
		System.out.println("정보를 확인할 영화를 선택하세요");
		for(int i=0; i<al.size(); i++)
		{
			String name = al.get(i).getName();
			System.out.print((i+1)+". "+name+" / "); //사용자가 선택할 수 있게 목록을 화면에 출력
		}
		System.out.println();
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		no = sc.nextInt(); // 사용자가 입력한 넘버	
		
		// 사용자가 선택한 번호 불러오기
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNo() == no) //사용자가 입력한 값 대입
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
	}
	//이전 화면으로 돌아가기, 예매하기 두개의 메뉴
	
}
class movieList
{
	File file = new File("src/movie.txt");	
	
	String mvname;
	int no;
	Movie movie=new Movie();
	ArrayList<Movie> al = new ArrayList<Movie>();
	
	movieList() throws IOException
	{				
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str=null;
				
		while((str=br.readLine()) !=null)
		{
			System.out.println(str); // 확인용, 숨기기	
			String[] strArray = str.split(",");
			
			movie = new Movie(Integer.valueOf(strArray[0]),Integer.valueOf(strArray[1]),strArray[2],strArray[3]); //영화 소개파일 형식에 맞게 변경
			al.add(movie);
		}
		br.close();
		fr.close();
		
		System.out.println("영화를 선택하세요");
		
		Scanner sc = new Scanner(System.in);
		no = sc.nextInt(); // 사용자가 입력한 넘버	

		// 사용자가 선택한 번호 불러오기
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNo() == no) //사용자가 입력한 값 대입
			{
				mvname = movie.getName();				
			}
			
		}
	}
}
