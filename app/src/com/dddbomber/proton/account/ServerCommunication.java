package com.dddbomber.proton.account;

import java.io.DataInputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ServerCommunication {
	
	public static String webAddress = "192.168.1.92";
	
	public static long lastAuthentication = 0;
	
	public static boolean login(Account account, String username, String password){
		try {
			String[] s = requestPage("http://"+webAddress+"/user/confirm_login.php", "username="+username+"&password="+password);
			if(!s[0].equals("Failed")){
				account.userID = Integer.parseInt(s[0]);
				account.username = s[1];
				account.private_key = s[2];
				lastAuthentication = System.currentTimeMillis()/1000;
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String[] requestPage(String url, String body) throws Exception{
		URL req = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) req.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.addRequestProperty("Content-Length", ""+body.length());
		
		OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream());
        outStream.write(body);
        outStream.flush();
        outStream.close();
        
		DataInputStream inStream = new DataInputStream(connection.getInputStream());
		
		ArrayList<String> lines = new ArrayList<String>();
		
        String buffer;
        while((buffer = inStream.readLine()) != null) {
        	lines.add(buffer);
        }
        
        inStream.close();
        
        String[] s = new String[lines.size()];
        for(int i = 0; i < lines.size(); i++){
        	s[i] = lines.get(i);
        	System.out.println(s[i]);
        }
        
        return s;
	}
}
