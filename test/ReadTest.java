package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		File file = new File("src/test/movie.txt");
		String mvname;		
		Movie movie=new Movie();
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
		int no = 1;//sc.nextInt(); // 사용자가 입력한 넘버	
		
		// 사용자가 선택한 번호 불러오기
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNum() == no+1) //사용자가 입력한 값 대입
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

}
class Movie
{
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