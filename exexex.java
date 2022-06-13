package movietest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Movietest1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		AdminList al=new AdminList();
//		al.list();
		
//		MovieRemove mr=new MovieRemove();
//		mr.remove();
		
		AdminMenu am=new AdminMenu();
		am.menuPrint();
	}

}

class MovieRemove	//영화 삭제하기 클래스
{
	private String str;
	private String res;
	private int num;
	private String moviename;
	private String moviegenre;
	private int no;
	private long stamp=System.currentTimeMillis();
	Scanner sc=new Scanner(System.in);
	
	void remove() throws IOException
	{
		Movie movie=new Movie();
		
		FileReader fr=new FileReader("src/movietest/movielist.txt");
		BufferedReader br=new BufferedReader(fr);
		
		FileWriter fw=new FileWriter("src/movietest/movielist.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		
		ArrayList<Movie> al=new ArrayList<Movie>();
		
		while( (str=br.readLine() )!=null)
		{
			String[] strArray = str.split(",");
			
			movie = new Movie(Long.valueOf(strArray[0]),strArray[1],strArray[2]);
			al.add(movie);
			
		}
		br.close();
		fr.close();
		
		AdminMenu am=new AdminMenu();
		
		for(int i=0;i<al.size();i++)
		{
			System.out.print((i+1)+". "+al.get(i).getName()+" / ");
		}
		System.out.println();
		
		System.out.println("===================================");
		System.out.println("삭제할 영화 번호를 눌러주세요. ");
		
		num=sc.nextInt();

		
		System.out.println(al.get(num).getName()+"를 선택하셨습니다");
		al.remove(num);
		System.out.println("------------------------------");
		System.out.println("삭제를 성공하였습니다.");
		
//		for(int j=0;j<al.size();j++)
//		{
//			Movie m=al.get(j);
//			System.out.println(m.toString());
////			bw.write(m.toString());
//		}
		bw.close();
		fw.close();
		
		System.out.println("관리자메뉴로 돌아갑니다.");
		
		
		
		am.choose();
		
		
//		String name=movie.getName();
//		long stamp=movie.getStamp();
//		al.get(num).getName();
//		for(int i=0;i<al.size();i++)
//		{
//			if(al.get(i).getName().contains())
//			{
//				idx=al.indexOf(num);
//				al.remove(idx);
//				System.out.println("------------------------------");
//				System.out.println("삭제를 성공하였습니다.");
//				System.out.println("관리자메뉴로 돌아갑니다.");
//				am.menuPrint();
//			}
//			else
//			{
//				System.out.println("없는 번호입니다. 다시 입력해주세요.");
//				remove();
//			}
//		}
	}
}


class AdminList		//영화 목록보기 클래스
{
	private String str;
	
	void list() throws IOException
	{
		Movie movie=new Movie();
		FileReader fr=new FileReader("src/movietest/movielist.txt");
		BufferedReader br=new BufferedReader(fr);
		
		String name;
		long stamp;
		int no;
		boolean flag=true;
		
		Scanner sc=new Scanner(System.in);
		
		ArrayList<Movie> al=new ArrayList<Movie>();
		
		while( (str=br.readLine() )!=null)
		{
			String[] strArray = str.split(",");
			
			movie = new Movie(Long.valueOf(strArray[0]),strArray[1],strArray[2]);
			al.add(movie);
			
			System.out.println(str);
		}
		br.close();
		fr.close();
		System.out.println("######등록된 영화 목록입니다.#######");
		for(int i=0;i<al.size();i++) 
		{
			System.out.println((i+1)+". "+al.get(i).getName());
		}
		
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
		return "Movie [stamp=" + stamp + ", name=" + name + ", genre=" + genre + "]";
	}
	
}
class AdminMenu //관리자메뉴 동작확인 O
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
			System.out.println("1.영화 등록하기 / 2.영화 목록보기 / 3.영화 삭제하기 / 4.메인메뉴로 이동하기");
			System.out.println("메뉴를 선택해주세요.");
			int num=sc.nextInt();
			switch(num)
			{
				case 1:
					AdminMovie amovie=new AdminMovie();
					amovie.FileRead();
					break;
				case 2:
					AdminList al=new AdminList();
					al.list();
					break;
				case 3:
					MovieRemove mr=new MovieRemove();
					mr.remove();
					break;
				case 4:
					
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
