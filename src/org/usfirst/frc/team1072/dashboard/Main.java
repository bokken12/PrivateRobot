/**
 * 
 */
package org.usfirst.frc.team1072.dashboard;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.usfirst.frc.team1072.shared.Client;
import org.usfirst.frc.team1072.shared.Networking;

/**
 * @author joelmanning
 *
 */
public class Main extends JFrame {
	
	private Client raspi;
	private ImageDisplay cameraFeed;
	private JPanel panel;
	
	public static void main(String[] args){
		(new Main()).run();
	}
	
	public Main(){
		System.out.println("d");
		raspi = new Client(Networking.RASPI_IP, Networking.RASPI_STREAM);
		System.out.println("c");
		panel = new JPanel();
		setVisible(true);
		System.out.println("b");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("a");
		cameraFeed = new ImageDisplay(0, 0);
		add(panel);
		panel.add(cameraFeed);
		raspi.addListener(cameraFeed);
		System.out.println("Finished constructor");
	}
	
	public void run(){
		raspi.start();
		while(true){
			repaint();
		}
	}
}
