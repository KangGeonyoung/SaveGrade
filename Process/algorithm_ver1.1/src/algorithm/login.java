package algorithm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class login extends JFrame {

	public login() {
		super.setLayout(new GridLayout(5, 2));
		JPanel blank = new JPanel();
		JPanel panel = new JPanel();
		
		JPanel IDPanel = new JPanel();
		JLabel label = new JLabel("ID                 : ");
		JTextField txtID = new JTextField(10);
		
		JPanel PWPanel = new JPanel();
		JLabel pswrd = new JLabel("Password : ");
		JPasswordField txtPass = new JPasswordField(10);
		
		JPanel BtnPanel = new JPanel();
		JButton logBtn = new JButton("Log In");
		JButton cancel = new JButton("Cancel");
		
		IDPanel.add(label);
		IDPanel.add(txtID);
		PWPanel.add(pswrd);
		PWPanel.add(txtPass);
		BtnPanel.add(logBtn);
		BtnPanel.add(cancel);
		
		logBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pw = String.valueOf(txtPass.getPassword());
				crowling ck = new crowling(txtID.getText(), pw);
						
				try {
					if (ck.crowling()) {//로그인 성공하면
						first.f.dispose();
						dispose();
						
						infoSubmit frame = new infoSubmit();
						
					} else {
						JOptionPane.showMessageDialog(null,  "로그인 실패!");
						dispose();
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		
		add(blank);
		add(IDPanel);
		add(PWPanel);
		add(BtnPanel);
		
		setVisible(true);
		setSize(300, 220);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
