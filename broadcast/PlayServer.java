package broadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.*;

public class Server {

	public static void main(String[] args) {
		//createAndShowGUI();
		// TODO Auto-generated method stub
		try {
			DatagramSocket serverSocket = new DatagramSocket(15002);
			byte[] receiveData = new byte[1024];
			String message = JOptionPane.showInputDialog("Please input a value");
			while (!message.equalsIgnoreCase("Quit")) {
				//recieve DatagramPacket
				// DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				// serverSocket.receive(receivePacket);
				// System.out.println("Packet recieved");
				// byte[] sendData = new byte[receivePacket.getLength()];
				// sendData = receivePacket.getData();
				// System.out.println(new String(sendData));
				//broadcast DatagramPacket
				// Scanner in = new Scanner(System.in);
				// System.out.print(" \033[2J\033[H\n\n\n\n\n\n\n\n\n\n\n\n                        Enter message to broadcast to clients:\n\n\n\n\n");
				// System.out.print("                        ");
				// String message = in.nextLine();
				byte[] sendData = message.getBytes();
				// InetAddress groupAddress = InetAddress.getByName("255.255.255.255");
				InetAddress groupAddress = InetAddress.getByName("10.26.42.255");
				// InetAddress groupAddress = InetAddress.getByName("10.36.179.191");
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, groupAddress, 15001);
				serverSocket.send(sendPacket);
				message = JOptionPane.showInputDialog("Please input a value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}