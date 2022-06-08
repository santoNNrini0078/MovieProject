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

		//��ȭ �����ϱ�		
		Scanner sc = new Scanner(System.in);
		MainMenu main = new MainMenu();
		main.choose();	
	}
}
interface Menu
{
	//ȭ�� ��°� �̵��� ���� ���α׷��� �����ϰԲ� ����	
	void menuPrint(); //�޴��� ȭ�鿡 ���
	void choose();	//����ڰ� �޴��� ���� ����
}
abstract class AbstractMenu implements Menu
{
	//Menu �������̽��� �����ϴ� �߻� Ŭ����
	//���θ޴�(MainMenu)�� �����ڸ޴�(AdminMenu)�� �θ�Ŭ����	
	public abstract void menuPrint();
	public abstract void choose();
}

class MainMenu extends AbstractMenu implements Menu	//���θ޴� ����Ȯ�� O
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
class AdminMenu extends AbstractMenu implements Menu //�����ڸ޴� ����Ȯ�� O
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
			System.out.println("�޴��� �������ּ���.");
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
					System.out.println("1~4�� �߿� �ٽ� �Է����ּ���.");
					break;
			}
		}
	}
}
class AdminMovie	//��ȭ ����ϱ� Ŭ����
{
	void FileRead()
	{
		
	}
}
class MovieList		//��ȭ ��Ϻ��� Ŭ����
{
	
}
class MovieRemove	//��ȭ �����ϱ� Ŭ����
{
	
}
class Movie
{
	//��ȭ ������ �����ϴ� Ŭ����
	//��ȭ ���� ������� ���
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
	//���� ������ �����ϴ� Ŭ����
	//���� ���� ������� ���
	int no=0;
	long reStamp=0;
	long stamp=0;	
	String name="aaa"; //����ڰ� �Է��ϴ� �� 
	String seat="a-1"; //����ڰ� �Է��ϴ� ��String name;
	
	ArrayList<Movie> al = new ArrayList<Movie>();
	Reservation(int no, long reStamp, long stamp, String name, String seat)
	{
		this.no=no;
		this.reStamp=reStamp;
		this.stamp=stamp;
		this.name=name;
		this.seat=seat;
	}
	
	void print() throws IOException //��ȭ ����� �Ұ� / ���� Ŭ������ ���� �� �ֳ�
	{
		File file = new File("src/movie.txt");	
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);		
		String str=null;	
		
		while((str=br.readLine()) !=null)
		{
			System.out.print(str);	//��ȭ ��Ϻ����ֱ�
		}
		br.close();
		fr.close();
		
		System.out.println("��ȭ�� �����ϼ���");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); // ����ڰ� �Է��� �ѹ�	
		
		name = "";//���񿡼� ��������
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

	
	void write() throws IOException //���� ���� �����ϱ� //0504Ŀ�Ǹ�������
	{
		File file = new File("src/reservation.txt");
		FileWriter fw = new FileWriter(file,true); //������ ���ư��� �ʰ� �߰��ϱ� (name,append)
		BufferedWriter bw = new BufferedWriter(fw);
		
		if (!file.exists())
		{
			System.out.println("������ �����մϴ�.");
			file.createNewFile();
		}		
		
		
		bw.write(no+","+reStamp+","+stamp+","+name+","+seat); //����	�ѹ��� ��� ����?	
		
		bw.close();
		fw.close();
	}	
}
class Seats
{
	//���� �¼��� �����ϴ� Ŭ����, �¼��� ��ȭ���� �ٸ��� ����Ǿ��...
	int[][] seat = new int[5][9];
	int number=-2;	//������ ���� �¼��� 1, �� �¼��� 0, �츮�� OX, -1�Է½� �����,,,
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
			System.out.println(str); // Ȯ�ο�, �����	
			String[] strArray = str.split(",");
			
			Reservation re = new Reservation(Integer.valueOf(strArray[0]),
					Long.valueOf(strArray[1]),Long.valueOf(strArray[2]),strArray[3],strArray[4]); //��ȭ �Ұ����� ���Ŀ� �°� ����
			al.add(re);
		}		
		br.close();
		fr.close();		
	}

	void viewSeat(Reservation re) //������ �¼���ȣ�� �迭�� ��ȯ�Ͽ� ��Ƽ� ���? //���� ��� ��������?
	{	
		
		System.out.println("=======================");
		System.out.println("      S C R E E N      ");
		System.out.println("=======================");
		
		for(int i=0; i<al.size(); i++)
		{
			if(al.get(i).getName().equals(re.getName())) //������ ��ȭ �̸��� ���� ������ ��ȭ �̸��� ���� ��쿡
			{
				String sNo = al.get(i).getSeat(); //�ش� ��ȭ�� ���ŵ� �¼� ��ȣ�� �ҷ��´�.
				String[] seatNo = sNo.split("-"); //�¼� ��ȣ�� ��ȣ�� �������� ������ �迭�� �����Ѵ�.
				
				for(int j=0; j<seat.length; j++)
				{
					System.out.print((char)(65+j)); //A,B,C,D...
					for(int k=0; k<seat[j].length; k++)
					{ //if������ �¼��� ����Ǿ� ������ X �׷��� ������ O ���
						
						if(seatNo.equals(seat[j][k])) //(E,9)�� ���� ���� �¼� ��ȣ�� seat�� �¼��� ���Ͽ� ������ X
						{
							System.out.print(" X");
						}
						else {
						System.out.print(" O");	//�׷��� ������ O�� ����Ѵ�.
						}
					}
					System.out.println();
				}
				System.out.println("  1 2 3 4 5 6 7 8 9");
			}// ����ڰ� �Է� ������ ����� ����� �Է��϶�� �˷��ֱ�
			
		}

				
	}
	void choose()
	{
		while(number!='q' || isFull ==false) //number�� �ƴѵ�,,,
		{
			isFull = isFull(seat);
			if(isFull)
			{
				System.out.println("������ ��� �Ϸ�Ǿ����ϴ�.");
				break;
			}
			System.out.println("�¼��� �����ϼ���(�� : E-9)");
			System.out.println("ó������ ���ư����� 'q'�� �Է��ϼ���.");
			
			Scanner sc = new Scanner(System.in);
			
			String seatSelect = sc.next();
			String[] seatNo = seatSelect.split("-");
			
			int number = sc.nextInt(); //��Ʈ������ �޾ƾ��ϳ�
			
						
			if((number>=1 && number<=10) || number==-1)
			{
				if(number == -1)
				{
					System.out.println("����Ǿ����ϴ�.");
					break;
				}
				if(seat[number-1][number-1] !=1) //������� ���� �¼��� ���, ��� �ؾ��ұ�,,,
				{
					seat[number-1][number-1] = 1;
				}
				else
				{
					System.out.println("�̹� ����� �ڸ��Դϴ�.");
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
				if((int)seat[i][j]==(int)'O') //�¼��� ���� ����Ǹ� true�� ����, �׷��� �츮�� OX�� �Ұ�,,, �׸��� A,B,C
				{
					break;
				}				
			}			
		}
		if(i==seat.length && j==seat[i].length)
		{
			ret=true;
		}//���� ������?? ���⿡ ���� �ǳ�?
		return ret;
	}
}
class Intro
{
	//��ȭ ������ �Ұ��ϴ� Ŭ���� //���� �ȿ� �Ұ��� ���°� �� ������ ������,,,

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
			System.out.println(str); // Ȯ�ο�, �����
			String[] strArray = str.split(",");
			
			movie = new Movie(Integer.valueOf(strArray[0]),Integer.valueOf(strArray[1]),strArray[2],strArray[3]); //��ȭ �Ұ����� ���Ŀ� �°� ����
			al.add(movie);
		}
		br.close();
		fr.close();
		
		System.out.println("������ Ȯ���� ��ȭ�� �����ϼ���");
		for(int i=0; i<al.size(); i++)
		{
			String name = al.get(i).getName();
			System.out.print((i+1)+". "+name+" / "); //����ڰ� ������ �� �ְ� ����� ȭ�鿡 ���
		}
		System.out.println();
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		no = sc.nextInt(); // ����ڰ� �Է��� �ѹ�	
		
		// ����ڰ� ������ ��ȣ �ҷ�����
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNo() == no) //����ڰ� �Է��� �� ����
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
	//���� ȭ������ ���ư���, �����ϱ� �ΰ��� �޴�
	
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
			System.out.println(str); // Ȯ�ο�, �����	
			String[] strArray = str.split(",");
			
			movie = new Movie(Integer.valueOf(strArray[0]),Integer.valueOf(strArray[1]),strArray[2],strArray[3]); //��ȭ �Ұ����� ���Ŀ� �°� ����
			al.add(movie);
		}
		br.close();
		fr.close();
		
		System.out.println("��ȭ�� �����ϼ���");
		
		Scanner sc = new Scanner(System.in);
		no = sc.nextInt(); // ����ڰ� �Է��� �ѹ�	

		// ����ڰ� ������ ��ȣ �ҷ�����
		for(int i=0; i<al.size(); i++) 
		{
			movie = al.get(i);
			if(movie.getNo() == no) //����ڰ� �Է��� �� ����
			{
				mvname = movie.getName();				
			}
			
		}
	}
}
