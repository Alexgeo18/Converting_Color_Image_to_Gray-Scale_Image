package packTest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import packWork.*;

public class Main {
	public static void main(String[] args) throws IOException {

		String inputFile;
		String fileName;
		if(args.length == 0){
			Scanner sc = new Scanner(System.in);

			System.out.println("Introduceti adresa fisierului de intrare .bmp: (Fara Ghilimele)");
			inputFile = sc.nextLine();

			System.out.println("Introduceti numele fisierului output sau si adresa daca nu vreti sa se salveze in acelasi loc: ");
			fileName = sc.nextLine();

			sc.close();
		} else
		{
			inputFile = args[0];
			fileName = args[1];
		}
		Buffer buffer = new Buffer();
		
		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream(out);

		ConvertToGrayscale img = new ConvertToGrayscale(inputFile);
		
		ImageProducer producer = new ImageProducer(img,  buffer);
		producer.start();
		
		ImageConsumer consumer = new ImageConsumer(img, buffer, out, fileName);
		consumer.start();

		WriterResults writerResults = new WriterResults(fileName, in, img);
		writerResults.start();
	}
}
