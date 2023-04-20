package chatting.Application;
import java.awt.event.*;

import java.awt.event.ActionListener;
//import java.io.DataOutputStream;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.text.*;
import java.io.*;
import java.net.*;

public class Server implements ActionListener {
	JPanel a1;
	JTextField text;
	static Box vertical= Box.createVerticalBox();
	static DataOutputStream dout;
	static JFrame f= new JFrame();
	
	Server(){
		f.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.GREEN);
		p1.setBounds(0,0,450,70);
		p1.setLayout(null);
		f.add(p1);
		
		//ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/left.png"));
		//Image i2= i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		Image icon = Toolkit.getDefaultToolkit().getImage("C:/Users/Amit Kumar/OneDrive/Pictures/lef.png");
		ImageIcon i1 = new ImageIcon(icon);
		JLabel back =new JLabel(i1);
		back.setBounds(5, 20, 40, 40);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		Image i2 = Toolkit.getDefaultToolkit().getImage("C:/Users/Amit Kumar/OneDrive/Pictures/zoom.png");
		ImageIcon i3 = new ImageIcon(i2);
		JLabel video =new JLabel(i3);
		video.setBounds(350, 20, 40, 40);
		p1.add(video);
		
		Image i4 = Toolkit.getDefaultToolkit().getImage("C:/Users/Amit Kumar/OneDrive/Pictures/phone-call.png");
		ImageIcon i5 = new ImageIcon(i4);
		JLabel call =new JLabel(i5);
		call.setBounds(280, 20, 40, 40);
		p1.add(call);
		
		Image i6 = Toolkit.getDefaultToolkit().getImage("C:/Users/Amit Kumar/OneDrive/Pictures/more.png");
		ImageIcon i7 = new ImageIcon(i6);
		JLabel dot =new JLabel(i7);
		dot.setBounds(390, 20, 40, 40);
		p1.add(dot);
		
		Image i8 = Toolkit.getDefaultToolkit().getImage("C:/Users/Amit Kumar/OneDrive/Pictures/pic.jpg");
		ImageIcon i9 = new ImageIcon(i8);
		JLabel pic =new JLabel(i9);
		pic.setBounds(50, 20, 40, 40);
		p1.add(pic);
		
		JLabel name = new JLabel("Amit");
		name.setBounds(100, 15, 100, 40);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
		p1.add(name);
		
		a1 = new JPanel();
	    a1.setBounds(5, 75, 440, 480);
	    f.add(a1);
		
		text= new JTextField();
		text.setBounds(5, 555, 340, 40);
		text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
		f.add(text);
		
		JButton send= new JButton("Send");
		send.setBounds(350,555,100,40);
		send.setBackground(Color.LIGHT_GRAY);
		send.setForeground(Color.BLACK);
		send.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		send.addActionListener(this);
		f.add(send);
		
		
		f.setSize(450,600);
		f.setLocation(200,20);
		f.getContentPane().setBackground((Color.WHITE));
		f.setUndecorated(true);
		
		f.setVisible(true); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String out = text.getText();
//			JLabel l = new JLabel(out);
			JPanel p2= format(out);
//			p2.add(output);
		
			a1.setLayout(new BorderLayout());
			
			JPanel right= new JPanel(new BorderLayout());
			right.add(p2, BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(6));
			
			a1.add(vertical, BorderLayout.PAGE_START);
			
			dout.writeUTF(out);
			text.setText("");
			
			f.repaint();
			f.invalidate();
			f.validate();
			
		}catch(Exception e1) {
            e1.printStackTrace();
        }
			
		}
	
	public static JPanel format(String out) {
		JPanel panel = new JPanel ();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel(out);
		output.setBackground(Color.YELLOW);
		output.setFont(new Font("Tahoma", Font.PLAIN, 16));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		panel.add(output);
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
		
		JLabel time=new JLabel();
		time.setText(sdf.format(cal.getTime()));
		
		panel.add(time);
		
		return panel;
	}
	public static void main(String[] args) {
		Server a=new Server();
		
		try {
			ServerSocket skt = new ServerSocket(6001);
			while(true) {
				Socket s = skt.accept();
				DataInputStream din= new DataInputStream(s.getInputStream());
				dout=new DataOutputStream(s.getOutputStream());
				
				while(true) {
					String msg = din.readUTF();
					JPanel panel = format(msg);
					
					JPanel left = new JPanel(new BorderLayout());
					left.add(panel, BorderLayout.LINE_START);
					vertical.add(left);
					f.validate();
				}
				}
			

	}catch(Exception e) {
		e.printStackTrace();
	}
	
	}
}
