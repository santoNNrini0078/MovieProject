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

class MainMenu extends AbstractMenu implements Menu	//메인메뉴 동작확인 O
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
class AdminMenu extends AbstractMenu implements Menu //관리자메뉴 동작확인 O
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
			System.out.println("메뉴를 선택해주세요.");
			int num=sc.nextInt();
			switch(num)
			{
				case 1:
					AdminMovie am=new AdminMovie();
					break;
				case 2:
					MovieList ml=new MovieList();
					break;
				case 3:
					MovieRemove mr=new MovieRemove();
					break;
				case 4:
					MainMenu mm=new MainMenu();
					mm.menuPrint();
					break;
				default:
					System.out.println("1~4번 중에 다시 입력해주세요.");
					break;
			}
		}
	}
}
class AdminMovie	//영화 등록하기 클래스
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
		while((str=br.readLine())!=null)
		{
			br.readLine();
		}
		br.close();
	}
}
class MovieList		//영화 목록보기 클래스
{
	
}
class MovieRemove	//영화 삭제하기 클래스
{
	
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
	String str;	
	String seatSelect="";
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

	void viewSeat(String mv) //예매한 좌석번호를 배열로 변환하여 담아서 출력? //무비를 어디서 가져오나?
	{	
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<al.size(); i++)
		{
			if(al.get(i).getName().equals(mv)) //선택한 영화 이름과 예매 파일의 영화 이름이 같은 경우에
			{
				String sNo = al.get(i).getSeat(); //해당 영화의 예매된 좌석 번호를 불러온다.
				list.add(sNo); //예매 좌석들을 배열에 저장
//				System.out.println(sNo); //확인용 지울것				
			}// 사용자가 입력 범위를 벗어나면 제대로 입력하라고 알려주기			
		}
//		System.out.println(list); //확인용 지울것
		for(int j=0; j<seat.length; j++)
		{
			System.out.print((char)(65+j)); //A,B,C,D...
			for(int k=0; k<seat[j].length; k++)
			{ //if문으로 좌석이 예약되어 있으면 X 그렇지 않으면 O 출력
//				System.out.println((char)(j+65)+"-"+k);	//확인용,,,
				
					if(list.contains((char)(j+65)+"-"+k)) //(E,9)와 같이 나뉜 좌석 번호와 seat의 좌석을 비교하여 같으면 X
					{							
						System.out.print(" X");
						seat[j][k] ='X';
					}
					else
					{
						System.out.print(" O");	//그렇지 않으면 O를 출력한다. //빈 자리에만 출력할 수 없나?
						seat[j][k]='O';
					}													
			}
			System.out.println();
		}
		System.out.println("  1 2 3 4 5 6 7 8 9");
	}
	void choose()
	{		
		Scanner sc = new Scanner(System.in);	
		
		
		while(!seatSelect.equals('q') || isFull ==false) //number가 아닌데,,,
		{
			isFull = isFull(seat);
			if(isFull)
			{
				System.out.println("모든 좌석이 이미 예약완료되었습니다.");
				MainMenu main = new MainMenu();
				main.menuPrint();
			}			
			
			System.out.println("좌석을 선택하세요(예 : E-9)");
			System.out.println("처음으로 돌아가려면 'q'를 입력하세요.");
			
			seatSelect = sc.next();
			 //입력하는 문자가 3자리가 아닌경우에 잘못 선택했다는 경고
			
			if(seatSelect.equals("q"))
			{
				System.out.println("종료되었습니다.");
				MainMenu main = new MainMenu();
				main.menuPrint();
			}
			
			if(seatSelect.length() !=3) {
				System.out.println("잘못 입력하셨습니다.");
				choose();
			}
			char c= seatSelect.charAt(0);
			int a=(int)c-65;
			
			char d = seatSelect.charAt(2);//숫자가 넘어가면,,,10입력시
			int b=(int)d-49;
			
			if(a>5 && a<=0)
			{
				System.out.println("잘못 선택하셨습니다.");
				System.out.println("A~E 사이에서 선택하세요.");
				choose();
			}
			
			if((a<5 && a>=0) && (b>=0 && b<10))
			{				
				if(seat[a][b] !='X')
				{
					System.out.println(seatSelect+"좌석을 선택하셨습니니다.");					
					while(true)
					{
						System.out.println("예약하시겠습니까?");
						System.out.println("1.예약하기 2.다시 선택하기 3.처음으로 돌아가기");
						try
						{
							int yn = sc.nextInt();
							if(yn<4||yn>0)
							{
								switch(yn)
								{
									case 1:
										//seatSelect 값을 넣어서 예약으로 전달
										break;
									case 2:
										choose();
										break;								
									case 3:
										MainMenu main = new MainMenu();
										main.menuPrint();
										break;
//									default:
//										System.out.println("잘못 선택하셨습니다.");
//										System.out.println("좌석을 선택하세요(예 : E-9)");
//										break;
								}
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("잘못 선택하셨습니다.");
							sc = new Scanner(System.in);
						}						
					}						
				}
				else
				{
					System.out.println("이미 예약된 자리입니다.");
				}
			}
			else
			{
				System.out.println("잘못 선택하셨습니다.");
				System.out.println("A~E 사이에서 선택하세요.");				
			}
		}		
	}
	static boolean isFull(int[][] seat)
	{
		boolean ret=false;
		int i=0;
		int j=0;
		for(i=0; i<seat.length; i++)
		{
			for(j=0; j<seat[i].length; j++)
			{
				if((int)seat[i][j]==(int)'O') //좌석이 전부 예약되면 true를 리턴, 문자의 값을 int 캐스팅해서 숫자로 비교
				{
					break;
				}
			}			
		}		
		if((i)==5 && (j)==8) //for문을 돌고 난 후의 i, j 값이 seat 배열의 길이와 같으면(배열을 전부 돌았다면) true 반환 j=8까지만??
		{
			ret=true;
		}
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
