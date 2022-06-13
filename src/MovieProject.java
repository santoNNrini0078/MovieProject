package test5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) throws IOException {

		//영화 예매하기		
		Scanner sc = new Scanner(System.in);
		MainMenu main = new MainMenu();
		main.menuPrint();
		main.choose();	
	}
}
interface Menu
{
	//화면 출력과 이동을 통해 프로그램이 동작하게끔 유도	
	void menuPrint(); //메뉴를 화면에 출력
//	void choose();	//사용자가 메뉴를 고르면 동작
}
abstract class AbstractMenu implements Menu
{
	//Menu 인터페이스를 구현하는 추상 클래스
	//메인메뉴(MainMenu)와 관리자메뉴(AdminMenu)의 부모클래스	
	public abstract void menuPrint();
//	public abstract void choose();
}

class MainMenu extends AbstractMenu implements Menu
{	
	//메인 메뉴의 출력과 입력에 따른 처리를 담당	
	Scanner sc = new Scanner(System.in);
	long reStamp;
//	private int menu;
	Reservation re=new Reservation();
	MainMenu()
	{
		
	}	
	public void menuPrint()
	{
		System.out.println("================================");
		System.out.println("==========영화 예매 프로그램=========");
		System.out.println("================================");			
	}	
	void choose() throws IOException
	{
		System.out.println("1.영화 소개 / 2.영화 예매 / 3.예매 확인 / 4.예매 취소 / 5.관리자메뉴 / 6.종료");
		System.out.println("메뉴를 선택하세요.");
		boolean select = true;
		int menu;
		
		while(select)
		{
			MovieList ml=new MovieList();
						
			try {
				menu = sc.nextInt();
				if(menu>0 && menu<7) {				
					switch(menu) {				
					case 1:
						Intro intro = new Intro();
						intro.IntroPrint(ml.getName());
						break;
					case 2:						
						ml.movieListPrint();
						Seats se=new Seats();
						se.viewSeat(ml.getName());
						se.choose(ml.getName(),ml.getStamp());
						re = new Reservation(ml.getStamp(),reStamp,ml.getName(),se.getSeatSelect());
						break;
					case 3:
						re.confirm();
						break;
					case 4:
						re.cancel();
						break;
					case 5:
						AdminMenu am = new AdminMenu();
						am.menuPrint();   //관리자 메뉴 생성자와 메소드 실행
						break;
					case 6:
						System.out.println("종료합니다.");
						select = false;
						break;				
						}
					}
				else{
					System.out.println("1~6 사이의 숫자를 입력하세요.");
					
					break;
				}
			}
			catch(InputMismatchException e){
				System.out.println("1~6사이의 숫자를 입력하세요.");
				sc = new Scanner(System.in);
				
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
	AdminMenu am=new AdminMenu();
	private String moviename;
	private String moviegenre;
	private int no;
	private long stamp=System.currentTimeMillis();
	private String str;
	
	void FileRead() throws IOException
	{
		FileWriter fw=new FileWriter("src/movietest/movielist.txt",true);
		BufferedWriter bw=new BufferedWriter(fw);
		
		System.out.println("영화 제목을 입력해주세요.");
		moviename=sc.nextLine();
		System.out.println("영화 장르를 입력해주세요.");
		moviegenre=sc.nextLine();
		str=stamp+","+moviename+","+moviegenre;
		
		bw.write(str);
		bw.write("\n");
		
		bw.close();
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println("영화 등록에 성공하였습니다. 관리자 메뉴로 돌아갑니다.");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n");
		
		am.choose();
	}
}
class MovieRemove
{
	
}
class Movie
{
	//영화 정보를 관리하는 클래스
	//영화 파일 입출력을 담당
	long stamp;
	String name;
	String genre;
	Movie()
	{
		
	}
	Movie(long stamp, String name, String genre)
	{		
		this.stamp = stamp;
		this.name = name;
		this.genre = genre;
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
class Reservation// 예매 저장까지 완료
{
	//예매 정보를 관리하는 클래스
	//예매 파일 입출력을 담당
	long reStamp;
	long stamp;
	String name; 	
	String seat;
	MovieList ml=new MovieList();
	File file = new File("src/reservation.txt");
	Scanner sc=new Scanner(System.in);
	
	ArrayList<Movie> al = new ArrayList<Movie>();
	ArrayList<Reservation> list = new ArrayList<Reservation>();
	Reservation()
	{

	}
	Reservation(long reStamp, long stamp, String name, String seat)//생성자
	{
		this.reStamp=reStamp;
		this.stamp=stamp;
		this.name=name;
		this.seat=seat;
	}
	void write(long stamp, String name, String seat) throws IOException //예매 정보 저장하기
	{
		this.seat=seat;
		this.stamp=stamp;
		this.name=name;

		FileWriter fw = new FileWriter(file,true); //파일이 날아가지 않게 추가하기 (name,append)
		BufferedWriter bw = new BufferedWriter(fw);
		
		if (!file.exists())
		{
			System.out.println("파일을 생성합니다.");
			file.createNewFile();
		}		
		reStamp=System.currentTimeMillis();		
		bw.write(reStamp+","+stamp+","+name+","+seat+"\n");
		System.out.println("예약번호 : "+reStamp+" , 영화이름 : "+name+" , 좌석번호 : "+seat+" 으로 예약되었습니다.");
		
		bw.close();
		fw.close();
	}
	int search(ArrayList<Reservation> list) throws IOException
	{
		int idx=-1;
		Reservation re=null;
		long num = sc.nextLong(); //확인하려는 영화의 예매번호 입력하기
		String str;

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);					
		
		while((str=br.readLine()) !=null)
		{
			String[] strArray = str.split(",");
			
			re = new Reservation(Long.valueOf(strArray[0]),Long.valueOf(strArray[1]),strArray[2],strArray[3]); //영화 소개파일 형식에 맞게 변경
			list.add(re);
		}		
		br.close();
		fr.close();
		
		for(int i=0; i<list.size(); i++)
		{
			re=list.get(i);
			if(num==re.reStamp)
			{
				System.out.println("예매하신 영화는 "+re.getName()+", 좌석번호 : "+re.getSeat()+" 입니다.");
				idx=i;
				break;
			}
		}
		if(idx==-1)
		{
			System.out.println("예매번호가 바르지 않습니다.");

			MainMenu main = new MainMenu();
			main.choose();	
		}		
		return idx;
	}
	void confirm() throws IOException //예매 확인하기
	{		
		System.out.println("확인하려는 영화의 예매번호를 입력하세요");
		int idx =search(list);

		MainMenu main = new MainMenu();
		main.menuPrint();
		main.choose();	
	}
	void cancel() throws IOException //예매 취소하기
	{
		System.out.println("취소하려는 영화의 예매번호를 입력하세요");
		int idx =search(list);
//		System.out.println(idx);
		System.out.println("영화를 취소 하시겠습니까?");
		System.out.println("1. 예매 취소 / 2. 처음으로 돌아가기");
		int no = sc.nextInt();
		MainMenu main = new MainMenu();
		switch(no)
		{			
			case 1:
				list.remove(idx); //리스트로 하면 이렇게 왜 취소가 안될까요,,, 아,,, 파일에 다시 써야하는구나.. 지우기만 하는 방법은 없나?
				System.out.println("영화를 취소하였습니다.");
				
				main.menuPrint();
				main.choose();
				break;
			case 2:				
				main.menuPrint();
				main.choose();
				break;
			default :
				System.out.println("잘못 입력하셨습니다.");				
				main.choose();	
				break;
		}		
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
}
class Seats
{	
	//예매 좌석을 관리하는 클래스, 좌석은 영화별로 다르게 저장되어야...
	int[][] seat = new int[5][9];
	int number=-2;	//예약이 끝난 좌석은 1, 빈 좌석은 0, 우리는 OX
	boolean isFull = false;
	boolean flag=true;
	String str;	
	String seatSelect="";
	Reservation re;	
	MovieList ml=new MovieList();
	String name;
	long stamp;
		
	ArrayList<Reservation> al = new ArrayList<Reservation>();
	Seats(){ //String name??
		try {
			File file = new File("src/reservation.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);					
			
			while((str=br.readLine()) !=null)
			{
				String[] strArray = str.split(",");
				
				re = new Reservation(Long.valueOf(strArray[0]),Long.valueOf(strArray[1]),strArray[2],strArray[3]); //영화 소개파일 형식에 맞게 변경
				al.add(re);
			}		
			br.close();
			fr.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}				
	}
	void viewSeat(String name) //예매한 좌석번호를 배열로 변환하여 담아서 출력
	{		
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		List<String> list = new ArrayList<String>();
		
		for(int i=0; i<al.size(); i++)
		{
			if(al.get(i).getName().equals(name)) //선택한 영화 이름과 예매 파일의 영화 이름이 같은 경우에
			{
				String sNo = al.get(i).getSeat(); //해당 영화의 예매된 좌석 번호를 불러온다.				
				list.add(sNo); //예매 좌석들을 배열에 저장
			}// 사용자가 입력 범위를 벗어나면 제대로 입력하라고 알려주기			
		}
		for(int j=0; j<seat.length; j++)
		{
			System.out.print((char)(65+j)); //A,B,C,D...
			for(int k=0; k<seat[j].length; k++)
			{ //if문으로 좌석이 예약되어 있으면 X 그렇지 않으면 O 출력
					if(list.contains((char)(j+65)+"-"+(k+1))) //(E,9)와 같이 나뉜 좌석 번호와 seat의 좌석을 비교하여 같으면 X
					{							
						System.out.print(" X");
						seat[j][k] ='X';
					}
					else
					{
						System.out.print(" O");	//그렇지 않으면 O를 출력한다.
						seat[j][k]='O';
					}													
			}
			System.out.println();
		}
		System.out.println("  1 2 3 4 5 6 7 8 9");
	}
	
	void choose(String name, long stamp) throws IOException
	{		
		this.name=name;
		this.stamp=stamp;
		Scanner sc = new Scanner(System.in);	
		
		while(!seatSelect.equals('q') || isFull ==false)
		{
			isFull = isFull(seat);
			if(isFull)
			{
				System.out.println("모든 좌석이 이미 예약완료되었습니다.");
				MainMenu main = new MainMenu();
				main.menuPrint();
			}			
			System.out.println("좌석을 선택하세요(예 : E-9)"); //가운데 - 하이픈은 아무거나 입력해도 상관없다?
			System.out.println("처음으로 돌아가려면 'q'를 입력하세요.");
			
			seatSelect = sc.next();
					
			if(seatSelect.equals("q"))
			{
				System.out.println("종료되었습니다.");
				MainMenu main = new MainMenu();
				main.menuPrint();
				main.choose();
			}
			 //입력하는 문자가 3자리가 아닌경우에 잘못 선택했다는 경고	
			if(seatSelect.length() !=3) {
				System.out.println("잘못 입력하셨습니다.");
				choose(name,stamp);
			}
			char c= seatSelect.charAt(0);
			int a=(int)c-65;
			
			char d = seatSelect.charAt(2);//숫자가 넘어가면,,,10입력시
			int b=(int)d-49;
			
			if(a>5 && a<=0)
			{
				System.out.println("잘못 선택하셨습니다.");
				System.out.println("A~E 사이에서 선택하세요.");
				choose(name,stamp);
			}
			
			if((a<5 && a>=0) && (b>=0 && b<10))
			{				
				if(seat[a][b] !='X')
				{
					System.out.println(seatSelect+"좌석을 선택하셨습니니다.");					
					while(flag)
					{
						System.out.println("예약하시겠습니까?");
						System.out.println("1.예약하기 2.다시 선택하기 3.처음으로 돌아가기");
						
						int yn = sc.nextInt();
						switch(yn)
							{
								case 1:										
									//seatSelect 값을 넣어서 예약으로 전달
									re.write(stamp,name,seatSelect);
									flag=false;
									break;
								case 2:
									choose(name,stamp);
									break;								
								case 3:
									MainMenu main = new MainMenu();
									main.menuPrint();
									main.choose();
									break;
								default:
									System.out.println("잘못 입력하셨습니다.");
									sc = new Scanner(System.in);
									break;
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
	public String getSeatSelect() {
		return seatSelect;		
	}
	public void setSeatSelect(String seatSelect) {
		this.seatSelect = seatSelect;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
class Intro //완료
{
	File file = new File("src/movie.txt");	
	Movie movie=new Movie();
	Intro()
	{
		
	}
	MovieList ml=new MovieList();
	
	void IntroPrint(String mvname) throws IOException
	{			
		ml.movieListPrint();		
		String name= ml.getName();
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str=null;
		ArrayList<Movie> al = new ArrayList<Movie>();
				
		while((str=br.readLine()) !=null)
		{
			String[] strArray = str.split(",");			
			movie = new Movie(Long.valueOf(strArray[0]),strArray[1],strArray[2]); //영화 소개파일 형식에 맞게 변경
			al.add(movie);
		}
		br.close();
		fr.close();
		
		String path2 ="src/"+name+".txt";
		FileReader fr2 = new FileReader(path2);
		BufferedReader br2 = new BufferedReader(fr2);
		while((str=br2.readLine()) !=null)
		{
			System.out.println(str);
		}
		MainMenu main = new MainMenu();
		main.choose();
	}
}
class MovieList // 완료
{
	File file = new File("src/movie.txt");	
	
	Movie movie=new Movie();
	String name;
	long stamp;
	int no;
	boolean flag=true;
	String str;
	
	ArrayList<Movie> al = new ArrayList<Movie>();
	
	MovieList()
	{
		
	}
	void movieListPrint() throws IOException
	{				
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);		
				
		while((str=br.readLine()) !=null)
		{					
			String[] strArray = str.split(",");
			
			movie = new Movie(Long.valueOf(strArray[0]),strArray[1],strArray[2]); //영화 소개파일 형식에 맞게 변경
			al.add(movie);
		}
		br.close();
		fr.close();				
			
		Scanner sc = new Scanner(System.in);
		
		while(flag==true)
		{				
			System.out.println("영화를 선택하세요");
			for(int i=0; i<al.size(); i++)
			{							
				System.out.print((i+1)+". "+al.get(i).getName()+" / "); //사용자가 선택할 수 있게 목록을 화면에 출력
			}	
			System.out.println();
			
			no = sc.nextInt(); // 사용자가 입력한 넘버
			try 
			{				
				if(no>0 && no<(al.size()+1))
				{
					movie = al.get(no-1); // 사용자가 선택한 번호 불러오기													
					name = movie.getName();
					stamp =movie.getStamp();
					System.out.println(no+". "+name+"를 선택하셨습니다.");
					flag=false;
					break;								
				}				
				else
				{
					System.out.println("잘못 선택하셨습니다.");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("숫자만 입력하세요");
				sc = new Scanner(System.in);
			}	
		}
	}
	public long getStamp() {
		return stamp;
	}
	public void setStamp(long stamp) {
		this.stamp = stamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
