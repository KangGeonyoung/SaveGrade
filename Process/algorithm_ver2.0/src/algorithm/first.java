package algorithm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class first {
	static JFrame f;
	
	/*패널정의*/
	static JPanel page1=new JPanel() {
		Image background=new ImageIcon(first.class.getResource("SB.png")).getImage();
		public void paint(Graphics g) {//그리는 함수
			Dimension d = getSize();
			g.drawImage(background, 0, 0, d.width, d.height, null);//background를 그려줌		
		}
	};

	public first() {
		f = new JFrame();
		f.setSize(500, 500);
		f.setTitle("Priority setting assistant");

		
		//logo image
		page1.setLayout(null); //레이아웃 설정하기 위해.
		page1.setBounds(0, 0, 500, 220);//패널의 위치와 크기.
		
		
		JPanel basicinfo = new JPanel();
		basicinfo.setBounds(0, 218, 494, 247);
		basicinfo.setLayout(new GridLayout(1, 2));
		
		JButton login = new JButton("login해서 시간표 불러오기");
		JButton just = new JButton("직접 시간표 입력하기");

		basicinfo.add(login);
		basicinfo.add(just);

		
		f.add(page1);//프레임에 패널을 추가.
		f.add(basicinfo);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login frame = new login(); //main으로 고고씽~
			}
		});
		
		
		just.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					infoSubmit frame = new infoSubmit();
					f.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) throws IOException {
		new first();
	}
}
