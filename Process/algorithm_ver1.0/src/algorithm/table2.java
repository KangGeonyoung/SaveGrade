package algorithm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.Scanner;

public class table2 extends JFrame{
 JTable table;
 JScrollPane scroll; // 테이블 위에 열 라벨을 넣어주자~ scroll
 String[] [] data;
 String[] title = {"월","화","수","목","금","토","일"}; //컬럼의 제목 정보를 표현할 1차원 배열
 
 public void data(String[] args) {
	 int n;
	 String subject[] = null;
	 int time[] = null;
	 String intro;
	 String buffer;
	 
	 Scanner sc = new Scanner(System.in);
	 System.out.println("과목 수를 입력하세요: ");
	 n = sc.nextInt();
	 for (int i = 1; i < n; i++) {
		 System.out.println("과목 이름을 입력하세요: ");
		 subject[i] = sc.next();
		 System.out.println("과목에 소요되는 시간을 구하세요: ");
		 time[i] = sc.nextInt();
		 add(new JFrame(subject[i]));
	 }
	 buffer = sc.nextLine();
	 intro = sc.nextLine();
	
	 System.out.println("과목수"+n+"과목"+subject[1]+subject[2]);
	 System.out.println(intro);
	 
 }
 public table2(){
  // table = new JTable(3,4); //컬럼을 지정할수 없고, 데이터를 넣을수 없다.
  //3번방법으로 가보자.

  data = new String[3][7];
  data[0][0]="월";
  data[0][1]="화";
  data[0][2]="수";
  data[0][3]="목";
  data[0][4]="금";
  data[0][5]="토";
  data[0][6]="일";

  data[1][0]="월";
  data[1][1]="화";
  data[1][2]="수";
  data[1][3]="목";
  data[1][4]="금";
  data[1][5]="토";
  data[1][6]="일";
 
  data[2][0]="월";
  data[2][1]="화";
  data[2][2]="수";
  data[2][3]="목";
  data[2][4]="금";
  data[2][5]="토";
  data[2][6]="일";
  
        //title = new String[4]; 다르게 해보자. 선언과 동시에 생성해보자.
  
  table=new JTable(data,title); // table=new JTable(데이터-2차원배열, 컬럼배열);
  scroll = new JScrollPane(table);
  add(scroll);
  
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  pack();
  //setSize(400,150);
  setVisible(true);
 }

 public static void main(String[] args) {
  new table2();

 }

}