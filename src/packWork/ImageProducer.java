package packWork;

import java.awt.image.BufferedImage;

public class ImageProducer extends Thread {
	protected Image img;
	protected Buffer buffer;

	public ImageProducer(Image img, Buffer buffer) {
		this.img = img;
		this.buffer = buffer;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 4; i++) {
				System.out.println("Producer: s-a citit sfertul " +  (int)(i+1));
				
				BufferedImage image = img.readImageQuarter(i, img.getFileName());
				//System.out.println(img.getFileName());
				buffer.put(image);
				buffer.setCurrentQuarter(i+1);
				
				Thread.sleep(1000);
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
