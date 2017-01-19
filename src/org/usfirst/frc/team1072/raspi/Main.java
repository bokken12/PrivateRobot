/**
 * 
 */
package org.usfirst.frc.team1072.raspi;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.usfirst.frc.team1072.shared.Networking;
import org.usfirst.frc.team1072.shared.ObjectListener;
import org.usfirst.frc.team1072.shared.Respondable;
import org.usfirst.frc.team1072.shared.Server;

/**
 * @author joelmanning
 *
 */
public class Main implements ObjectListener {
	
	public static final int ROWS = 480;
	public static final int COLUMNS = 520;
	
	private Server stream;
	private Server processed;
	private VideoCapture vc;
	private Mat mat;
	private MatOfInt compressionRate;
	private MatOfByte buffer;
	private List<Respondable> toStream;
	private List<Respondable> toOutput;
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		(new Main()).run();
	}
	
	public Main(){
		stream = new Server(Networking.RASPI_STREAM);
		processed = new Server(Networking.RASPI_PROCESSED);
		vc = new VideoCapture(0);
		mat = new Mat(ROWS, COLUMNS, CvType.CV_64FC4);
		compressionRate = new MatOfInt(Highgui.CV_IMWRITE_PNG_COMPRESSION,9);
		buffer = new MatOfByte();
	}
	
	public void run(){
		while(true){
			vc.read(mat);
			Highgui.imencode(".png", mat, buffer, compressionRate);
			byte[] bytes = buffer.toArray();
			stream.broadcast(bytes);
		}
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1072.shared.ObjectListener#objectRecieved(java.lang.Object, org.usfirst.frc.team1072.shared.Respondable)
	 */
	@Override
	public void objectRecieved(Object obj, Respondable source) {
		
	}
}
