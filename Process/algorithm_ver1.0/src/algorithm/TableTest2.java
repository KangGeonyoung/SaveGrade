package algorithm;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest2 extends JFrame{
 JTable table;
 JScrollPane scroll; // 테이블 위에 열 라벨을 넣어주자~ scroll
 String[] [] data; // 3명의 정보를 담을 2차원 배열을 생성한다.
 String[] title = {"번호","이름","연락처","메일", }; //컬럼의 제목 정보를 표현할 1차원 배열
 public TableTest2(){
  // table = new JTable(3,4); //컬럼을 지정할수 없고, 데이터를 넣을수 없다.
  //3번방법으로 가보자.
  data = new String[3][4];
  data[0][0]="1";
  data[0][1]="김연아";
  data[0][2]="010-2888-0077";
  data[0][3]="yuna@naver.com";
  
  data[1][0]="2";
  data[1][1]="박태환";
  data[1][2]="011-748-5236";
  data[1][3]="park@hanmail.net";
  
  data[2][0]="3";
  data[2][1]="박찬호";
  data[2][2]="010-5685-5258";
  data[2][3]="chanho@nate.com";
  
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
  new TableTest2();

 }

}