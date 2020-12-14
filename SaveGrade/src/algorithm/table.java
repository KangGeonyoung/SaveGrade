package algorithm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;


public class table extends JFrame{
	 private static JTable table;
	 private static JScrollPane scroll; 
	 private static String[] [] data; 
	 private static String[] title = {"월","화","수","목","금","토","일"}; //컬럼의 제목 정보를 표현할 1차원 배열
	 
	 private static int[] basicinfo;
	 private static float[] studytime ;
	 private static String[] subjectitle; 
	 private static int[] greedyoutput;
	 
	 public table(){
	
		new SaveGrade();
		
		basicinfo = SaveGrade.getBasicInfo();
		studytime = SaveGrade.getStudyTime();
		subjectitle = SaveGrade.getSubjectTitle();
		greedyoutput = SaveGrade.getGreedyOutput();
		
		//System.out.println("남은날짜:" + basicinfo[0] +"\n");
		//System.out.println("주중 공부량:" + basicinfo[1] +"\n");
		//System.out.println("주말 공부량:" + basicinfo[2] +"\n");
		
		int d = basicinfo[0]; // 남은 날짜
		int sw = basicinfo[1];  // 주중 공부량
		int sd = basicinfo[2];  // 주말 공부량
		int w = (int) Math.ceil((float)d/7);    // 시험까지 남은 주차(반올림 함수로 바꿈)
		int i;
		
		data = new String[w*2][7];
		
		float[] st_cal = new float[studytime.length];
		for(i = 0; i < studytime.length; i++) st_cal[i] = studytime[i]; 
		String [] sub_study = new String[d];
		Arrays.fill(sub_study,"");
		
		
		
		for(i = 0; i < d; i++) {
			int wday = i % 7;
			int atime = (wday == 5 || wday == 6 )? sd :sw;
			
			for (int j=0; j < st_cal.length;j ++) {
				int g = greedyoutput[j];
				if (st_cal[g]> 0) {
					st_cal[g] = st_cal[g] - atime;
					float st_done = Math.min(studytime[g] - st_cal[g], studytime[g]); 
					sub_study[i] = "<html>"+ subjectitle[g] + "<br />(" + st_done +"/" + studytime[g] + " hr.)" + "</html>" ;
					//sub_study[i] = subjectitle[j] + "\n (" + st_done +"/" + studytime[j] + " hr.)" ;
					//System.out.println("Day:" + i +":"+subjectitle[j] + " (" + st_done +"/" + studytime[j] + " hr.)" + "\tdata[week][day]:" + "?" + ":" +  i % 7  );
					break;
				}
			}
		}
						
		int day = (7-d% 7) %7 ; //날짜가 줄어드는 것을 보여줌
		int week = 0; //다음 주로 넘어가는 것을 보여줌
		

		i = d;
		while (i > 0) {
	
			String dday = "D-"+String.valueOf(i); // int 값이였던 d를 string으로 바꾼다
			//System.out.println(dday + "/" + week + "/" +day);
			data[week][day] = dday; // 값 입력
			data[week+1][day] = sub_study[d-i];
			
			day++;
			i--;
			if (i % 7 == 0 ) {
				day = 0;
				week = week + 2;
			}
		}
		
		  
		table=new JTable(data,title); // table=new JTable(데이터-2차원배열, 컬럼배열);
		
		for ( i = 0; i < w*2;i ++) {
			if (i % 2 ==1 ) {
				table.setRowHeight(i, 60);
				Font font = new Font("맑은고딕", Font.BOLD,16);
				table.setFont(font);
			}
			else {
				table.setRowHeight(i, 30);
			}				
		}
			
		//table.setBackground(Color.lightGray);
		//table.setForeground(Color.orange);
		scroll = new JScrollPane(table);
		scroll.setSize(1200, 600);
		add(scroll,BorderLayout.CENTER);

		
		String output ="<html>";
		for ( i=0; i < greedyoutput.length; i++) {
			output = output + "<br>" +
					(i+1) +  "순위 : " + subjectitle[greedyoutput[i]] + " : " + studytime[greedyoutput[i]] + " 시간" + "<br>" ;	
		}
		output=output + "</html>";
		System.out.println(output);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10)); //상하좌우 10씩 띄우기
		JLabel label = new JLabel(output); 
		Font font = new Font("맑은고딕", Font.BOLD,16);
		label.setFont(font);
		panel.add(label);
		
		add(panel,BorderLayout.EAST);
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		setVisible(true);
		setSize(1800, 600);
		
		table.setBackground(new Color(169,245,248));
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
 }

 public static void main(String[] args) {
	 new table();
 }

}