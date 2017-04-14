package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class UserUtil {
	/*
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
	}*/
	
	public static void playFile(String filename) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(15003);
		byte[] sendData = filename.getBytes();
		InetAddress groupAddress = InetAddress.getByName("255.255.255.255");
		// InetAddress groupAddress = InetAddress.getByName("10.26.42.255");
		// InetAddress groupAddress = InetAddress.getByName("10.36.179.191");
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, groupAddress, 15002);
		serverSocket.send(sendPacket);
		serverSocket.close();
	}
	
	public static void uploadFile(InetAddress[] addresses, File file) throws Exception{
		// read file into byte array
		int fileLength = (int) file.length();
		System.out.println("FileName: " + file.getName() + "  Length: " + fileLength);
		byte[] fileBuffer = new byte[fileLength];
		DataInputStream inFromFile = new DataInputStream(new FileInputStream(file));
		inFromFile.read(fileBuffer);
		inFromFile.close();
		for(InetAddress i: addresses){
			boolean exception = true;
			do {
				Socket clientSocket = null;
				DataOutputStream outToServer = null;
				BufferedReader inFromServer = null;
				try {
					// create tcp/ip socket
					clientSocket = new Socket(i, 15001);
					// create input and output streams from socket
					outToServer = new DataOutputStream(clientSocket.getOutputStream());
					inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					// send file name and wait for response
					outToServer.writeBytes(file.getName()+"\n");
					System.out.println("Server recieved: " + inFromServer.readLine());
					// send file length and wait for response
					outToServer.writeBytes(fileLength+"\n");
					System.out.println("Server recieved: " + inFromServer.readLine());
					// send file byte by byte
					outToServer.write(fileBuffer);
					inFromServer.readLine();
					exception = false;
				} catch (java.net.ConnectException e) {
//					e.printStackTrace();
//					System.exit(0);
					exception = true;
				} finally{
					if(outToServer != null)
						outToServer.close();
					if(inFromServer != null)
						inFromServer.close();
					if(clientSocket != null)
						clientSocket.close();
				}
			} while (exception);
		}
	}
	
}