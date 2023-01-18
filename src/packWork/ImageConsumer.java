package packWork;


import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.Serializable;


public class ImageConsumer extends Thread implements Serializable{

	String fileName;
	protected ConvertToGrayscale obj;
	protected BufferedImage[] imageQuarters;
	protected Buffer buffer;
    protected DataOutputStream oos;
	private static final long serialVersionUID = 1L;
	
	public ImageConsumer(ConvertToGrayscale obj, Buffer buffer, PipedOutputStream out,String fileName) throws IOException {
		this.obj = obj;
		this.buffer = buffer;
		this.imageQuarters = new BufferedImage[4];
		oos = new DataOutputStream(out);
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 4; i++) {

				Thread.sleep(1000);
				imageQuarters[i] = buffer.get(i);
				imageQuarters[i] = obj.processImage(imageQuarters[i]);
				System.out.println("Consumer: s-a procesat sfertul  " + (int) (i + 1));

				for (int x = 0; x < imageQuarters[i].getWidth(); x++){
					for(int y = 0; y < imageQuarters[i].getHeight(); y++){
						try{
							oos.writeInt(imageQuarters[i].getRGB(x, y));
						}
						catch(IOException e){
							e.printStackTrace();
						}
					}
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ConvertToGrayscale getObj() {
		return obj;
	}

	public void setObj(ConvertToGrayscale obj) {
		this.obj = obj;
	}

	public BufferedImage[] getImageQuarters() {
		return imageQuarters;
	}

	public void setImageQuarters(BufferedImage[] imageQuarters) {
		this.imageQuarters = imageQuarters;
	}

	public Buffer getBuffer() {
		return buffer;
	}

	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}

	public DataOutputStream getOos() {
		return oos;
	}

	public void setOos(DataOutputStream oos) {
		this.oos = oos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
