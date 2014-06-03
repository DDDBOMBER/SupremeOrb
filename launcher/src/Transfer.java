import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;


public class Transfer {
	public FileOutputStream fos;
	public ReadableByteChannel rbc;
	public URL website;
	public String target;
	public boolean failed;
	public Transfer(String path, String target){
		try {
			this.website = new URL(path);
			this.rbc = Channels.newChannel(website.openStream());
			this.target = target;
			fos = new FileOutputStream(target);
		}catch(Exception e){
			e.printStackTrace();
			failed = true;
		}
	}
	
	public String readFile() {
	       try {
	         File file = new File(target);
	         Scanner scanner = new Scanner(file);
	         while (scanner.hasNextLine()) {
	           return scanner.nextLine();
	         }
	         scanner.close();
	       } catch (FileNotFoundException e) {
	         return null;
	       }
	       return null;
	     }

	public void transfer(){
		try {
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			fos.getChannel().close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
