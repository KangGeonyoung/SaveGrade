package algorithm;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.JFrame;

public class ShowGrade extends JFrame{
	
	 private static int[] basicinfo;
	 private static float[] studytime ;
	 private static String[] subjectitle; 
	 private static int[] greedyoutput;
	 public ShowGrade() {
		 
		new SaveGrade();
		basicinfo = SaveGrade.getBasicInfo();
		studytime = SaveGrade.getStudyTime();
		subjectitle = SaveGrade.getSubjectTitle();
		greedyoutput = SaveGrade.getGreedyOutput();

		
		Dimension dim = new Dimension(200, 100);
		JFrame frame = new JFrame("Grade");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		
		JLabel label = new JLabel("hi \n hi");
		
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
		
		 
	 }

}
