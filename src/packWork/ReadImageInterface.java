package packWork;

import java.awt.image.BufferedImage;

public interface ReadImageInterface {
	public BufferedImage getImage() ;
	public String getFileName();
	public void setFileName(String fileName);
	public void setImage(BufferedImage image);
}