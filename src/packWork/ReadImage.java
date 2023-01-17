package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

public class ReadImage extends Image implements ReadImageInterface {
	
	BufferedImage img;
	
	public ReadImage() {
		super();
	}
	
	public BufferedImage readImageQuarter(int quarter, String fileName){ //Aceasta functie citeste imaginea pe sferturi
		try{
			File inputFile = new File(fileName);
			image = ImageIO.read(inputFile);
			
			int width = image.getWidth();
			int height = image.getHeight();

			if(quarter == 0){
				image = image.getSubimage(0,  0, width/2, height/2);
			}else if(quarter == 1){
				image = image.getSubimage(width/2, 0, width/2, height/2);
			}else if(quarter == 2){
				image = image.getSubimage(0,  height/2, width/2, height/2);
			}else if(quarter == 3){
				image = image.getSubimage(width/2, height/2, width/2,  height/2);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
			return image;
	}
		



}
