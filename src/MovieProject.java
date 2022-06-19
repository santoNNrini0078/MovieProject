package test4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
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
	void menuPrint() throws IOException; //메뉴를 화면에 출력
	void choose() throws IOException;
}
abstract class AbstractMenu implements Menu
{
	//Menu 인터페이스를 구현하는 추상 클래스
	//메인메뉴(MainMenu)와 관리자메뉴(AdminMenu)의 부모클래스	
	public abstract void menuPrint() throws IOException;
	public abstract void choose() throws IOException;
}

class MainMenu extends AbstractMenu implements Menu
{	
	//메인 메뉴의 출력과 입력에 따른 처리를 담당	
	Scanner sc = new Scanner(System.in);
	long reStamp;
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
	public void choose() throws IOException
	{
		System.out.println("1.영화 소개 / 2.영화 예매 / 3.예매 확인 / 4.예매 취소 / 5.관리자메뉴 / 6.종료");
		System.out.println("메뉴를 선택하세요.");
		boolean select = true;
		String menu;
		
		while(select)
		{
			MovieList ml=new MovieList();
			
			menu = sc.next();							
			switch(menu) {				
			case "1":
				Intro intro = new Intro();
				intro.IntroPrint(ml.getName());
				break;
			case "2":						
				ml.movieListPrint();
				Seats se=new Seats();
				se.viewSeat(ml.getName());
				se.choose(ml.getName(),ml.getStamp());
				re = new Reservation(ml.getStamp(),reStamp,ml.getName(),se.getSeatSelect());
				break;
			case "3":
				re.confirm();
				break;
			case "4":
				re.cancel();
				break;
			case "5":
				AdminMenu am = new AdminMenu();
				am.menuPrint();   //관리자 메뉴 생성자와 메소드 실행
				break;
			case "6":
				System.out.println("종료합니다.");
				System.exit(0);					
			default:
				System.out.println("1~6 사이의 숫자를 입력하세요.");					
				}
			}			
		}		
}
class AdminMenu extends AbstractMenu implements Menu //관리자메뉴 동작확인 O //인풋 미스매치. 
{
	//관리자 메뉴의 출력과 입력에 따른 처리를 담당
	Scanner sc=new Scanner(System.in);
	public void menuPrint() throws IOException
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
	public void choose() throws IOException
	{
		
		while(true)
		{
			MovieList ml=new MovieList();
			System.out.println("1.영화 등록하기 / 2.영화 목록보기 / 3.영화 삭제하기 / 4.영화소개 등록하기 / 5.메인메뉴로 이동하기");
			System.out.println("메뉴를 선택해주세요.");
			int num=sc.nextInt();	//int 입력이라 try catch
			switch(num)
			{
				case 1:
					AdminMovie am=new AdminMovie();
					am.FileWriter();
					break;
				case 2:					
					ml.movieListPrint();
					break;
				case 3:
					MovieRemove mr=new MovieRemove();
					ml.movieListPrint();
					mr.remove(ml.getName());
					break;
				case 4:	
					ml.movieListPrint();
					MovieStory ms = new MovieStory(ml.getName());
					ms.storyWriter();
					break;
				case 5:
					MainMenu mm=new MainMenu();
					mm.menuPrint();
					mm.choose();
					break;
				default:
					System.out.println("1~5번 중에 다시 입력해주세요.");
					break;
			}
		}
	}
}
class AdminMovie	//영화 등록하기 클래스 // 파일이 없으면 만들기.
{
	Scanner sc=new Scanner(System.in);
	AdminMenu am=new AdminMenu();
	private String moviename;
	private String moviegenre;
	private int no;
	private long stamp=System.currentTimeMillis();
	private String str;
	
	void FileWriter() throws IOException
	{
		FileWriter fw=new FileWriter("src/movie.txt",true);
		BufferedWriter bw=new BufferedWriter(fw);
		
		System.out.println("영화 제목을 입력해주세요.");
		moviename=sc.nextLine();
		System.out.println("영화 장르를 입력해주세요.");
		moviegenre=sc.nextLine();
		str=stamp+","+moviename+","+moviegenre;
		
		try {
			bw.write(str);
			bw.write("\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println("영화 등록에 성공하였습니다. 관리자 메뉴로 돌아갑니다.");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
		System.out.println("\n");
		
		am.choose();
	}
}
class MovieRemove	//영화 삭제하기 클래스
{
	private String str;
	private String res;
	private int num;
	String name;
	Scanner sc=new Scanner(System.in);
	MovieRemove()
	{
		
	}	
	void remove(String name) throws IOException
	{		
		this.name=name;
		Movie movie=new Movie();				
		ArrayList<Movie> al=new ArrayList<Movie>();				
		AdminMenu am=new AdminMenu();
		int idx=-1;
		String file = "src/movie.txt";
		
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
				
		while( (str=br.readLine() )!=null)
		{
			String[] strArray = str.split(",");			
			movie = new Movie(Long.valueOf(strArray[0]),strArray[1],strArray[2]);
			al.add(movie);			
		}
		br.close();
		fr.close();
		System.out.println("선택하신 영화를 삭제합니다.");
		
		
		for(int i=0; i<al.size(); i++)
		{
			movie=al.get(i);
			if(name.equals(movie.getName()))
			{				
				idx=i;
				break;
			}
		}		
		try {							
			al.remove(idx);
				
			System.out.println("삭제를 완료하였습니다!!!!!!!!!!!!!!!!");
						
			FileWriter fw=new FileWriter(file);
			BufferedWriter bw=new BufferedWriter(fw);
			
			for(int j=0;j<al.size();j++)
			{
				Movie m=al.get(j);			
				bw.write(m.toString()+"\n");				
			}
			bw.close();
			fw.close();
			
			System.out.println("관리자메뉴로 돌아갑니다.");			
			am.choose();
		}catch(InputMismatchException e) {
			System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다.");
			
		}
		
	}
}
class MovieStory
{
	Scanner sc = new Scanner(System.in);
	String name;
	String q = "q";
	MovieStory(String name)//name 받아오기
	{
		this.name = name;
	}
	void storyWriter() throws IOException
	{
		File file = new File("src/"+name+".txt");
		FileWriter fw = new FileWriter(file,true); //파일이 날아가지 않게 추가하기 (name,append)
		BufferedWriter bw = new BufferedWriter(fw);
		AdminMenu am = new AdminMenu();
		String q = "q";
		//		ArrayList<String> al = new ArrayList<String>();
		
		if (!file.exists())
		{
			System.out.println("파일을 생성합니다.");
			file.createNewFile();
		}
		System.out.println("소개할 내용을 입력하세요.");
		System.out.println("작성을 종료하려면 'q'를 입력하세요");
		
		String story="";
		while(!story.equals(q))
		{				
			story = sc.next();
			bw.write(story+"\n");		//q도 저장되네.. ㅜㅜ
		}
		bw.close();
		fw.close();
	}
}
class Movie
{
	//영화 정보를 관리하는 클래스
	//영화 파일 입출력을 담당
	private long stamp;
	private String name;
	private String genre;
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
	@Override
	public String toString() {
		return stamp + "," + name + "," + genre;
	}		
}
class Reservation implements Serializable// 예매 저장까지 완료
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
	@Override
	public String toString() {		
		return reStamp+","+ stamp+","+name+","+seat;		
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
		
		try {
			long num = sc.nextLong(); //확인하려는 영화의 예매번호 입력하기 , long 대신 string으로 받는게 더 편한가?
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
			
		}catch(InputMismatchException e){ //NumberFormatException
			System.out.println("잘못 입력하셨습니다.");
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
		System.out.println("영화를 취소 하시겠습니까?");
		System.out.println("1. 예매 취소 / 2. 처음으로 돌아가기");
		
		MainMenu main = new MainMenu();
		String str="";
		while(true)
		{
			try {
				int no = sc.nextInt();
				switch(no) //String으로 받아서 try catch 없이 만드는 것과 int로 받아서 try catch로 만드는 것의 차이? 어떤게 더 나은가요?
				{			
					case 1:
						list.remove(idx); //리스트에서 지우고 파일에 다시 쓰기
						
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);									
					
						for(int i=0; i<list.size(); i++)
						{
							Reservation re = list.get(i);					
							bw.write(re.toString()+"\n");
						}	
																		
						bw.close();
						fw.close();			
					
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
						System.out.println("다시 입력해 주세요.");
						break;
				}	
			}
			catch(InputMismatchException e) {
				System.out.println("1~2 중에서 선택하세요.");
				sc = new Scanner(System.in);
			}
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
	int number=-1;	//예약이 끝난 좌석은 X, 빈 좌석은 0
	boolean isFull = false;
	boolean flag=true;
	String str;	
	String seatSelect="";
	Reservation re;	
	MovieList ml=new MovieList();
	String name;
	long stamp;
		
	ArrayList<Reservation> al = new ArrayList<Reservation>();
	Seats(){ //파일을 읽어서 어레이 리스트에 저장
		try {
			File file = new File("src/reservation.txt");
			
			if (file.exists())
			
			{
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
			
			}//else {파일이 존재하지 않으면 	읽지 않고 다음으로 넘어간다.}
		}
		catch(Exception e) {
			System.out.println(e);
		}				
	}
	void viewSeat(String name) //선택한 영화의 좌석번호를 배열로 변환하여 담아서 출력
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
				list.add(sNo); //예매된 좌석들을 배열에 저장
			}		
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
			System.out.println("좌석을 선택하세요(예 : E-9)");
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
			
			char f = seatSelect.charAt(1);			
			
			if(a>5 && a<=0)
			{
				System.out.println("잘못 선택하셨습니다.");
				System.out.println("A~E 사이에서 A-1 형식으로 선택하세요.");
				choose(name,stamp);
			}
			
			if((a<5 && a>=0) && (b>=0 && b<10) && (f=='-'))
			{				
				if(seat[a][b] !='X')
				{
					System.out.println(seatSelect+"좌석을 선택하셨습니니다.");					
					while(flag)
					{
						System.out.println("예약하시겠습니까?");
						System.out.println("1.예약하기 2.다시 선택하기 3.처음으로 돌아가기");
												
						try
						{
							int yn = sc.nextInt();
							switch(yn)
							{
								case 1:										
									//seatSelect 값을 넣어서 예약으로 전달
									Reservation re2 = new Reservation();
									re2.write(stamp,name,seatSelect);									
									MainMenu main = new MainMenu();
									main.menuPrint();
									main.choose();
								case 2:
									choose(name,stamp);
									break;								
								case 3:
									MainMenu main2 = new MainMenu();
									main2.menuPrint();
									main2.choose();
									break;
								default:
									System.out.println("잘못 입력하셨습니다.");
									sc = new Scanner(System.in);
									break;
							}	
						}
						catch(InputMismatchException e){
								System.out.println("숫자를 입력하세요");
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
		if((i)==5 && (j)==8) //for문을 돌고 난 후의 i, j 값이 seat 배열의 길이와 같으면(배열을 전부 돌았다면) true 반환 j=8일때 마지막 좌석이 break 되므로 1이 증가하지 않는다.
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
	private String name;
	private long stamp;
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
			System.out.println("현재 상영중인 영화입니다.");
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
