/**
 * MyNotePad
 * GUI + Function
 * 
 */
package com.hehan;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MyNotePad extends JFrame implements ActionListener {
	
	//define components
	JTextArea jta = null;
	
	//menubar->menu->menuitem
	JMenuBar jmb = null;
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	
	public static void main(String[] args) {
		MyNotePad mnp = new MyNotePad();
	}
	
	//constructor, initialize
	public MyNotePad() {
		//creage components
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("File");
		
		//set Mnemonic
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("Open");
		jmi2 = new JMenuItem("Save as");
		
		//register listener
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		//add
		this.setJMenuBar(jmb);
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		//add to JFrame
		this.add(jta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,500);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("open")) {
			//JFileChooser
			JFileChooser jfc1 = new JFileChooser();
			jfc1.setDialogTitle("Please choose file");
			jfc1.showOpenDialog(null);
			jfc1.setVisible(true);
			
			//get file absolute path
			String filename = jfc1.getSelectedFile().getAbsolutePath();
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				
				String s = "";
				String content = "";
				while((s=br.readLine())!=null) {
					content += s + "\r\n";
				}
				
				//add info to jta
				jta.setText(content);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		} else if(e.getActionCommand().equals("save")) {
				JFileChooser jfc2 = new JFileChooser();
				jfc2.setDialogTitle("Save as");
				jfc2.showSaveDialog(null);
				jfc2.setVisible(true);
				
				//get path
				String file = jfc2.getSelectedFile().getAbsolutePath();
				
				//write to selected file
				FileWriter fw = null;
				BufferedWriter bw = null;
				
				try {
					fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					bw.write(this.jta.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						//take attation to the order first bw then fw
						bw.close();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
	}
}
