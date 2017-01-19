/**
 * 
 */
package org.usfirst.frc.team1072.dashboard;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.usfirst.frc.team1072.shared.ObjectListener;
import org.usfirst.frc.team1072.shared.Respondable;

/**
 * @author joelmanning
 *
 */
public class ImageDisplay extends Component implements ObjectListener {
	
	private BufferedImage image;
	
	public ImageDisplay(int x, int y){
		super();
		setLocation(x, y);
	}
	
	@Override
	public void paint(Graphics g){
		if(image != null){
			g.drawImage(image, getX(), getY(), null);
		}
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1072.shared.ObjectListener#objectRecieved(java.lang.Object, org.usfirst.frc.team1072.shared.Respondable)
	 */
	@Override
	public void objectRecieved(Object obj, Respondable source) {
		System.out.println("recieved");
		if(obj instanceof byte[]){
			System.out.println("a byte array");
			try {
				image = ImageIO.read(new ByteArrayInputStream((byte[]) obj));
				System.out.println("and made it into an image with no problems");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
