package algorithm;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class infoSubmit {
	JFrame f;
	
	String credit_list[] = {"1", "2", "3", "4", "5"};
	String week_list[] = {"15", "16"};
	String day_list[] = {"월", "화", "수", "목", "금"};
	String time_list[] = {"9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
	String min_list[] = {"00", "10", "20", "30", "40", "50"};
	String PPT_amount[] = {"적음", "조금 적음", "보통", "많음", "매우 많음"};
	String difficulty[] = {"상", "중", "하"};
	
	
	//최종적으로 정보가 저장되는 배열 두개
	protected static int basicInfo[] = new int[4];
	//남은 일수 (일), 하루 공부량 - 주중 (시간), 하루 공부량 - 주말(시간), 전공/교양 선호도(1/0)
	protected static datasaver[] subjectInfo = new datasaver[10];
	//과목 이름, 학점, 요일(1~5까지의 숫자로 지정됨), 시간(HHMM), 전공여부(전이 들어가면 전공, 교가 들어가면 교양)
	//ppt, diff (정확한 형식은 해당 파일 참조 - datasaver.java)
	
	static int len = 0; //등록된 과목의 수
	
	
	//로그인 시, 정보를 받아오는 배열
	String result[][] = new String[10][6];
	//과목 이름, 학점, 요일(1~5까지의 숫자로 지정됨), 시간(HHMM), 전공여부(전이 들어가면 전공, 교가 들어가면 교양)

	
	public infoSubmit() throws IOException {
		f = new JFrame();
		f.setTitle("Priority setting assistant");
		
		JPanel p[] = new jtab1[9];

		//true라면 로그인 후 이 창이 뜨는 것 => 정보가 입력되어있어야 함.
		if(crowling.state) {
			result = crowling.info;
			for(int i = 0 ; i < crowling.len ; i++) {
				p[i] = new jtab1(i, true);
			}
			for(int i = crowling.len ; i < 9 ; i++) {
				p[i] = new jtab1(i, false);
			}
		}
		else {
			for(int i = 0 ; i < 9 ; i++) {
				p[i] = new jtab1(i, false);
			}
		}

		
		JTabbedPane tp = new JTabbedPane();
		tp.setBounds(14, 217, 416, 291);
		for(int i = 0 ; i < 9 ; i++) {
			tp.add(Integer.toString(i+1), p[i]);
		}


		JPanel basicinfo = new JPanel();
		basicinfo.setBounds(0, 30, 440, 180);
		basicinfo.setLayout(new GridLayout(3, 1));
		
		JPanel nothing = new JPanel(); // 줄띄우기 용도
		JPanel DatePanel = new JPanel();
		JPanel DaystudyamountPanel = new JPanel();
		JPanel PreferPanel = new JPanel();

		JLabel labelDate = new JLabel("D - ");
		JLabel labelDA = new JLabel("하루 공부량 - ");
		JLabel labelDaystudyamount = new JLabel("주중 : ");

		JLabel labelWeekenstudyamount = new JLabel("   주말 : ");
		JLabel labelPrefer = new JLabel("선호도 :   전공  ");
		JLabel labelPrefer2 = new JLabel("  교양");
		
		JTextField Day = new JTextField(5);
		JTextField Daystudyamount = new JTextField(5);
		JTextField Weekenstudyamount = new JTextField(5);
		JToggleButton PreferToggle = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("togle_L.png"))));
		PreferToggle.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("togle_R.png"))));
		PreferToggle.setPreferredSize(new Dimension(40, 30));
		
		DatePanel.add(labelDate);
		DatePanel.add(Day);
		DaystudyamountPanel.add(labelDA);
		DaystudyamountPanel.add(labelDaystudyamount);
		DaystudyamountPanel.add(Daystudyamount);
		DaystudyamountPanel.add(labelWeekenstudyamount);
		DaystudyamountPanel.add(Weekenstudyamount);
		PreferPanel.add(labelPrefer);
		PreferPanel.add(PreferToggle);
		PreferPanel.add(labelPrefer2);
		
		basicinfo.add(DatePanel);
		basicinfo.add(DaystudyamountPanel);
		basicinfo.add(PreferPanel);

		
		JButton submit = new JButton("submit");
		submit.setBounds(163, 520, 108, 33);

		
		f.getContentPane().add(basicinfo);
		f.getContentPane().add(tp);
		f.getContentPane().add(submit);

		f.setSize(450, 600);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//제출 버튼 이벤트
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//남은 일수, 하루 공부량 - 주중, 하루 공부량 - 주말, 전공/교양 선호도,
				try {
					basicInfo[0] = Integer.parseInt(Day.getText());
					basicInfo[1] = Integer.parseInt(Daystudyamount.getText());
					basicInfo[2] = Integer.parseInt(Weekenstudyamount.getText());
					
					if(PreferToggle.isSelected())
						basicInfo[3] = 1;
					else
						basicInfo[3] = 0;
								
					
					for(int i=0;i<9;i++) {
						jtab1 a = (jtab1)p[i];
						datasaver tt = a.getInfo();
						if(tt.getCredit() == -1) continue;
						
						subjectInfo[len] = tt;
						len++;
					}
					
					//cmd test용
					for(int j=0;j<4;j++) {
						System.out.println(basicInfo[j]);
					}
					
					for(int j=0;j<len;j++) {
						subjectInfo[j].getall();
					}
					
					
					if(len == 0) {
						JOptionPane.showMessageDialog(null,  "선택된 과목이 없습니다.");
					}
					else {
						
						f.dispose();
						// + 결과창으로 넘어가기 (전종민님이 구현하시는 부분)


						table result = new table(); 

					}
	
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,  "남은 일 수, 공부량 칸에는 숫자만 입력하세요!");
				}
			}
		});
	}
	

	
	public class jtab1 extends JPanel{
		int idx = 0;
		JCheckBox include;
		JTextField Name;
		JComboBox<String> Credit;
		JComboBox<String> Week;
		JComboBox<String> Day;
		JComboBox<String> Time;
		JComboBox<String> min;
		JToggleButton MajorToggle;
		JRadioButton PPT[] = new JRadioButton[5];
		JRadioButton diff[] = new JRadioButton[3];

		
		public jtab1(int k, boolean state) throws IOException {
			setLayout(new GridLayout(6, 2));
			idx = k;

			JPanel NamePanel = new JPanel();
			JPanel CreditPanel = new JPanel();
			JPanel TimehPanel = new JPanel();
			JPanel MajorPanel = new JPanel();
			JPanel PPTPanel = new JPanel();
			JPanel scalePanel = new JPanel();

			JLabel labelName = new JLabel("과목 이름 : ");
			JLabel labelCredit = new JLabel("학점          :      ");
			JLabel labelTime = new JLabel("주차  ");
			JLabel labelTime1 = new JLabel("요일    ");
			JLabel labelTime2 = new JLabel("시 ");
			JLabel labelTime3 = new JLabel("분 ");
			
			JLabel labelMajor = new JLabel("전공  ");
			JLabel labelMajor2 = new JLabel(" 교양");
			JLabel labelPPT = new JLabel("PPT 양 : ");
			JLabel labelscale = new JLabel("난이도 : ");
		
			
			include = new JCheckBox();
			Name = new JTextField(15);
			
			Credit = new JComboBox<String>(credit_list);
			Credit.setPreferredSize(new Dimension(50, 20));
	
			Week = new JComboBox<String>(week_list);
			Week.setPreferredSize(new Dimension(50, 20));
			
			Day = new JComboBox<String>(day_list);
			Day.setPreferredSize(new Dimension(50, 20));
			
			Time = new JComboBox<String>(time_list);
			Time.setPreferredSize(new Dimension(50, 20));
			
			min = new JComboBox<String>(min_list);
			min.setPreferredSize(new Dimension(50, 20));

			
			MajorToggle = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("togle_L.png"))));
			MajorToggle.setSelectedIcon(new ImageIcon(ImageIO.read(getClass().getResource("togle_R.png"))));
			MajorToggle.setPreferredSize(new Dimension(40, 30));
		
	        ButtonGroup group_ppt = new ButtonGroup();
			PPTPanel.add(labelPPT);
	        for(int i = 0 ; i < PPT.length ; i++){
	        	PPT[i] = new JRadioButton(PPT_amount[i]);
	        	group_ppt.add(PPT[i]);
	        	PPTPanel.add(PPT[i]);
	        }
	        PPT[0].setSelected(true);
	        
	        
	        ButtonGroup group_diff = new ButtonGroup();
			scalePanel.add(labelscale);
	        for(int i = 0 ; i < diff.length ; i++){
	        	diff[i] = new JRadioButton(difficulty[i]);
	        	group_diff.add(diff[i]);
	        	scalePanel.add(diff[i]);
	        }
	        diff[0].setSelected(true);	
			
			NamePanel.add(labelName);
			NamePanel.add(Name);
			NamePanel.add(include);


			CreditPanel.add(labelCredit);
			CreditPanel.add(Credit);
			
			TimehPanel.add(Week);
			TimehPanel.add(labelTime);
			TimehPanel.add(Day);
			TimehPanel.add(labelTime1);
			TimehPanel.add(Time);
			TimehPanel.add(labelTime2);
			TimehPanel.add(min);
			TimehPanel.add(labelTime3);

			MajorPanel.add(labelMajor);
			MajorPanel.add(MajorToggle);
			MajorPanel.add(labelMajor2);

			add(NamePanel);
			add(CreditPanel);
			add(TimehPanel);
			add(MajorPanel);
			add(PPTPanel);
			add(scalePanel);
			
			
			if(state) {
				this.setInfo(result[k]);
			}
		}
		
		
		//info => 과목 이름, 시간, 전공여부, 학점, 학수번호, 요일(1~5까지의 숫자로 지정됨)
		public void setInfo(String info[]) {
			
			HashMap<String, String> daylink = new HashMap<String, String>();
			daylink.put("1", "월");
			daylink.put("2", "화");
			daylink.put("3", "수");
			daylink.put("4", "목");
			daylink.put("5", "금");
			
			Name.setText(info[0]);
			Credit.setSelectedItem(info[3]);
			
			String T[] = info[1].split(":");
			Time.setSelectedItem(T[0]);
			min.setSelectedItem(T[1]);
			Day.setSelectedItem(daylink.get(info[5]));
			
			if(info[2].contains("전")) {
				MajorToggle.setSelected(false);
			}
			else {
				MajorToggle.setSelected(true);
			}
			
			include.setSelected(true);
		}
		
		public datasaver getInfo() {
			datasaver wa = new datasaver();
			
			if(!include.isSelected()) { //사용안된 곳이라면 더미데이터넣어서 넘김
				wa.setIntValue(-1, -1, -1, -1);
				return wa;
			}
			
			String tm = (String)Time.getSelectedItem() + (String)min.getSelectedItem();
			wa.setStringValue(Name.getText(), (String) Day.getSelectedItem(), tm);
			wa.setBoolValue(MajorToggle.isSelected());
			
			int ppt = 0;
			int d = 0;
			
	        for(int i = 0 ; i < 5 ; i++){
	        	if(PPT[i].isSelected())
	        		ppt = i + 1;
	        	
	        	if(i < 3 && diff[i].isSelected())
	        		d = i + 1;
	        }
			
			wa.setIntValue(Credit.getSelectedIndex() + 1, ppt, d, Week.getSelectedIndex());
			return wa;
		}
	}

}