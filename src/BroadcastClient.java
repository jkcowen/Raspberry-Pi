package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
import java.net.InetSocketAddress;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Desktop;
import java.io.File;

public class BroadcastClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Scanner in = new Scanner(System.in);
		// System.out.println("Enter message to broadcast to all clients:");
		// String message = in.nextLine();
		// in.close();
		String filename = "";
		// byte[] sendData = filename.getBytes();
		byte[] receiveData = new byte[1000];
		try {
			DatagramSocket clientSocket = new DatagramSocket(null);
			clientSocket.setReuseAddress(true);
			clientSocket.bind(new InetSocketAddress(15002));   
			//System.out.print("\033[2J\033[H");
			while(true){
				receiveData = new byte[1000];
				// send message to server
				// InetAddress localhost = InetAddress.getByName("127.0.0.1");
				// DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, localhost, 15002);
				// clientSocket.send(sendPacket);
				// receive message from server
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				filename = new String(receivePacket.getData());
				// System.out.println("               " + message);
				// JOptionPane.showMessageDialog(null, message, "Message from Server", JOptionPane.PLAIN_MESSAGE);
				// final JOptionPane pane = new JOptionPane(message);
				// final JDialog d = pane.createDialog((JFrame)null, "Message from Server");
				// d.setLocation(50,100);
				// d.setVisible(true);
				Desktop.getDesktop().open(new File("C:/users/jkc11/onedrive/documents/cpre186/Server/"+filename));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}