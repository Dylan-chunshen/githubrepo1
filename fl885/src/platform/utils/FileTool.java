package platform.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileTool {

	public boolean saveUrlAs(String photoUrl, String fileName) {
	     try {
	      URL url = new URL(photoUrl);
	      HttpURLConnection connection = (HttpURLConnection) url
	        .openConnection();
	      DataInputStream in = new DataInputStream(connection
	        .getInputStream());
	      DataOutputStream out = new DataOutputStream(new FileOutputStream(
	        fileName));
	      byte[] buffer = new byte[4096];
	      int count = 0;
	      while ((count = in.read(buffer)) > 0) {
	       out.write(buffer, 0, count);
	      }
	      out.close();
	      in.close();
	      return true;
	     } catch (Exception e) {
	      System.out.println(e);
	      return false;
	     }
	    }
	    /**
	     * ºÊ»›HTTP∫ÕFTP–≠“È
	     * @param urlString
	     * @return
	     */
	    public String getDocumentAt(String urlString) {
	     StringBuffer document = new StringBuffer();
	     try {
	      URL url = new URL(urlString);
	      URLConnection conn = url.openConnection();
	      BufferedReader reader = new BufferedReader(new InputStreamReader(
	        conn.getInputStream()));
	      String line = null;
	      while ((line = reader.readLine()) != null) {
	       document.append(line + "\n");
	      }
	      reader.close();
	     } catch (MalformedURLException e) {
	      System.out.println("Unable to connect to URL: " + urlString);
	     } catch (IOException e) {
	      System.out.println("IOException when connecting to URL: "
	        + urlString);
	     }
	     return document.toString();
	    }
	
}
