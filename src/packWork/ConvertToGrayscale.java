package packWork;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ConvertToGrayscale extends ReadImage {

	protected String fileName;
	protected ReadImage readImage;
	protected BufferedImage grayscaleImage;
	static int type;
	
	static{
		type = BufferedImage.TYPE_INT_RGB;
	}
	
	public ConvertToGrayscale( String fileName) {
		super();
		this.fileName = fileName;
		
	}
	
	public BufferedImage reconstructImage(BufferedImage[] image){
		
		int width = 2*image[0].getWidth();
		int height = 2*image[0].getHeight();
		
		BufferedImage finalImage = new BufferedImage(width, height, type);
		Graphics graphics = finalImage.getGraphics();

		graphics.drawImage(image[0], 0, 0, null);
		graphics.drawImage(image[1], width/2, 0, null);
		graphics.drawImage(image[2], 0, height/2, null);
		graphics.drawImage(image[3], width/2, height/2, null);
		
		return finalImage;
	}
	
	public BufferedImage processImage(BufferedImage image ){
		long startTime = System.nanoTime();
		
		int width = image.getWidth();
		int height = image.getHeight();
		
		for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Luam valorile RGB ale pixelui curent
                int red = (image.getRGB(i, j) >> 16) & 0xff;
                int green = (image.getRGB(i, j) >> 8) & 0xff;
                int blue = image.getRGB(i, j) & 0xff;

                // Cosntruim culoarea gri folosind metoda luminozitatii
                int gray = (int)(0.21 * red + 0.71 * green + 0.07 * blue);

                // Setez valorea gri pixelui curent in imaginea grayscale
                image.setRGB(i, j, (gray << 16) | (gray << 8) | gray);
            }
		}
		long endTime = System.nanoTime();
		double totalTime = endTime - startTime; 
		System.out.println("Timp de Conversie " + totalTime/1000000000 + "secunde");
		return image;
	}

	public ReadImage getReadImage() {
		return readImage;
	}

	public void setReadImage(ReadImage readImage) {
		this.readImage = readImage;
	}

	public BufferedImage getGrayscaleImage() {
		return grayscaleImage;
	}

	public void setGrayscaleImage(BufferedImage grayscaleImage) {
		this.grayscaleImage = grayscaleImage;
	}

	public int getWidth() {
		return grayscaleImage.getWidth();
	}
	
	public int getHeight(){
		return grayscaleImage.getHeight();
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
