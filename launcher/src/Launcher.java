import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class Launcher {
	public static String path = defaultDirectory()+"/SupremeOrb";
	
	protected static String defaultDirectory() {
		String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN"))
			return System.getenv("APPDATA");
		else if (OS.contains("MAC"))
			return System.getProperty("user.home") + "/Library/Application "
			+ "Support";
		else if (OS.contains("NUX"))
			return System.getProperty("user.home");
		return System.getProperty("user.dir");
	}
	
	public static JButton launchButton;
	public static void loadFrame(){
		JFrame frame = new JFrame("SupremeOrb Launcher");
	    frame.setDefaultCloseOperation(3);
	    frame.setSize(1280, 720);
	    frame.setLocationRelativeTo(null);
	    frame.setLayout(null);
	    frame.setResizable(false);
	    frame.setBackground(Color.BLACK);
	    
	    BufferedImage barImg = null;
	    BufferedImage titleImg = null;
	    Image icon = null;
	    try {
	    	icon = ImageIO.read(Launcher.class.getResource("/icon.png"));
	    	barImg = ImageIO.read(Launcher.class.getResource("/back.png"));
	    	titleImg = ImageIO.read(Launcher.class.getResource("/overlay.png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    frame.setIconImage(icon);
	    JLabel barLabel = new JLabel(new ImageIcon(barImg));
	    barLabel.setSize(1280, 128);
	    barLabel.setLocation(0, 720-128);
	    JLabel titleLabel = new JLabel(new ImageIcon(titleImg));
	    titleLabel.setSize(1280, 128);
	    titleLabel.setLocation(0, 720-128);

	    JEditorPane jep = new JEditorPane();
	    jep.setEditable(false);
	    jep.setContentType("text/html");
	    jep.setText("<html> Loading Development Log... </html>");
	    jep.setBorder(new EmptyBorder(0, 0, 0, 0));
	    try
	    {
	      jep.setPage("fail");
	    }
	    catch (IOException e)
	    {
	      jep.setContentType("text/html");
	      jep.setText("<html>Could not load development blog </html>");
	    }
	    JScrollPane scrollPane = new JScrollPane(jep);
	    scrollPane.setBackground(Color.BLACK);

	    scrollPane.setSize(frame.getWidth() - 5, 720-128);
	    scrollPane.setLocation(0, 0);
	    scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
	    
	    launchButton = new JButton("Play");
	    launchButton.setSize(100, 24);
	    launchButton.setLocation(24, 720-110);
	    launchButton.setVisible(true);
	    launchButton.setEnabled(true);
	    launchButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e) {
	       updateAndStart();
	      }
	    });
	    JButton exitButton = new JButton("Quit");
	    exitButton.setSize(100, 24);
	    exitButton.setLocation(24, 720-74);
	    exitButton.setVisible(true);
	    exitButton.setEnabled(true);
	    exitButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e) {
	       System.exit(0);
	      }
	    });
	    
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    frame.add(launchButton);
	    frame.add(exitButton);
	    //frame.add(titleLabel);
	    frame.add(barLabel);
	    frame.getContentPane().add(scrollPane);
	    frame.setVisible(true);
	}
	public static void updateAndStart(){
		launchButton.setEnabled(false);
		launchButton.setText("Checking");
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				getVersion();
				
				try{
					MessageDigest md = MessageDigest.getInstance("MD5");
					try (InputStream is = new FileInputStream((new File(path+"/game.exe")))) {
					  DigestInputStream dis = new DigestInputStream(is, md);
					}
					byte[] digest = md.digest();
				}catch (Exception e){
					
				}
				
				try {
					Process p = Runtime.getRuntime().exec(path+"/game.exe");
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
	
	private static void transfer() {
		launchButton.setText("Updating");
		Transfer t = new Transfer("https://dl.dropbox.com/u/63217995/Space%20Sim%20Game.exe", path+"/game.exe");
		t.transfer();
		Transfer t2 = new Transfer("https://dl.dropbox.com/u/63217995/GameVersion.txt", path+"/game.txt");
		t2.transfer();
	}
	private static void getVersion() {
		String s1 = "";
		try{
			File f = new File(path+"/game.txt");
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				s1 = scanner.nextLine();
			}
		}catch(Exception e){
			
		}
		File f = new File(path+"/game.exe");
		Transfer t = new Transfer("https://dl.dropbox.com/u/63217995/GameVersion.txt", path+"/gameTemp.txt");
		if(t.failed && f.exists()){
			return;
		}else if(t.failed){
			JOptionPane.showMessageDialog(null, "Connection Required For First Launch", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		t.transfer();
		String s2 = t.readFile();
		if(!s1.equals(s2)){
			boolean get = false;
			if(!f.exists()){
				get = true;
			}else{
				int reply = JOptionPane.showConfirmDialog(null, "There is a newer version available, you will not be able to play multiplayer without it. Would you like to update?", "Update Available", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	get = true;
		        }
			}
			if(get)transfer();
		}
	}
	
	public static void main(String[] args){
		loadFrame();
	}
}
