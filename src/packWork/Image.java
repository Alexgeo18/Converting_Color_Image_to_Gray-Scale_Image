package packWork;

import java.awt.image.BufferedImage;

public abstract class Image {
	protected String fileName;
	protected BufferedImage image;
	
	public Image(){
	}
	
	public Image(String fileName){
		this.fileName = fileName;
	}
	
	public abstract BufferedImage readImageQuarter(int quarter, String fileName);
	
	
	public BufferedImage getImage() {
		return image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
