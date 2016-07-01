package com.qq.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.qq.biz.ServerBiz;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class QQServerJFame extends JFrame {

	private JPanel contentPane;
    private  ServerBiz serverBiz  = new ServerBiz();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					QQServerJFame frame = new QQServerJFame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QQServerJFame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 399, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	   
		JButton button1 = new JButton("\u542F\u52A8\u670D\u52A1");
		JButton button2 = new JButton("\u505C\u6B62\u670D\u52A1");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				start(event);
				button1.setEnabled(false);
				button2.setEnabled(true);
				System.out.println("启动服务器成功");
				
			}
		});
		button1.setBounds(63, 97, 107, 43);
		contentPane.add(button1);

		
		button2.setEnabled(false);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				stop(event);
				button1.setEnabled(true);
				button2.setEnabled(false);
				System.out.println("服务关闭");
			}
		});
		
		button2.setBounds(250, 97, 93, 43);
		contentPane.add(button2);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 424, 200);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\\u670D\u52A1\u5668.png"));
		panel.add(lblNewLabel);
	}
//=====================================================================================================================//
	/*
	 * 服务开始事件，开始servercocket服务
	 */
	public void start(ActionEvent event){
		new Thread(){
			public void run(){
				
				try {
					serverBiz.startService();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
		}.start();
		
	}

	/*
	 * 服务停止事件，停止servercocket服务
	 */
	public void stop(ActionEvent event) {
		new Thread(){public void run(){
			try {
				serverBiz.stopServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}.start();
					
		
	}
}
