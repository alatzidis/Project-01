package eu.quietroom.emp.utils.downloadUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadUtils {
	public static boolean downloadFile(String url, String savePath){
		boolean out;
		URL website;
		FileOutputStream fos = null;
		try {
			website = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(savePath);
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			out = true;
		}catch(MalformedURLException e){
			System.out.println("\t\turl: " + url + " e: MalformedURLException");
//			e.printStackTrace();
			out = false;
		}catch(FileNotFoundException e){
			System.out.println("\t\turl: " + url + " e: FileNotFoundException");
//			e.printStackTrace();
			out = false;
		}catch(IOException e){
			System.out.println("\t\turl: " + url + " e: IOException");
//			e.printStackTrace();			
			out = false;
		}finally{
			try {
				if(fos != null){
					fos.close();
				}
			} catch (IOException e) {
				System.out.println("\t\turl: " + url + " e: IOException");
//				e.printStackTrace();
			}
		}
		return out;
	}
}
