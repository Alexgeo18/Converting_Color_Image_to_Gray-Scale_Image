package packWork;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;

import javax.imageio.ImageIO;

public class WriterResults extends ReadImage implements Runnable {

	DataInputStream in;
	String outputFileName;
	ConvertToGrayscale img;
	Thread thread;
	
	public WriterResults(String outputFileName, PipedInputStream in, ConvertToGrayscale image) throws IOException {
		super();
		this.outputFileName = outputFileName;
		this.in = new DataInputStream(in);
		this.img = image;
		thread = new Thread(this);
	}

	@Override
	public void run() {

		BufferedImage processedImage = null;
		BufferedImage[] processedImageQuarters = new BufferedImage[4];
		String file = img.getFileName();
		//System.out.println(file);
		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			for(int i = 0; i < 4; i++){
				processedImageQuarters[i] = new BufferedImage(image.getWidth()/2, image.getHeight()/2, BufferedImage.TYPE_INT_RGB);
				for (int x = 0 ; x < image.getWidth()/2; x++){
					for (int y = 0; y < image.getHeight()/2; y++){
						int pixel = in.readInt();
						processedImageQuarters[i].setRGB(x, y, pixel);
					}
				}
			}
			processedImage = img.reconstructImage(processedImageQuarters);//Reconstruim Imaginea
			ImageIO.write(processedImage, "bmp", new File(outputFileName + ".bmp"));
			System.out.println("Imaginea a fost procesata");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		// TODO Auto-generated method stub
		thread.start();
	}
	

}
