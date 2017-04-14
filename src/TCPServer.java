package src;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.awt.Desktop;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		String fileName = "";
		String fileLength = "";
		ServerSocket welcomeSocket = new ServerSocket(15001);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataInputStream binaryInFromClient = new DataInputStream(new BufferedInputStream(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			try {
				fileName = inFromClient.readLine();
				System.out.println("Recieved: " + fileName);
				outToClient.writeBytes(fileName+"\n");
				fileLength = inFromClient.readLine();
				System.out.println("Length: " + fileLength);
				outToClient.writeBytes(fileLength+"\n");
				Scanner lengthInput = new Scanner(fileLength);
				int length = lengthInput.nextInt();
				lengthInput.close();
				File file = new File("C:/users/jkc11/onedrive/documents/cpre186/Server/"+fileName);
				DataOutputStream fileOutput = new DataOutputStream(new FileOutputStream(file));
				byte[] cbuf = new byte[length];
				int bytesRead = 0;
				long percent = 0;
				while (bytesRead < length) {
					int bytesRecieved = binaryInFromClient.read(cbuf, bytesRead, length-bytesRead);
					if (bytesRecieved > 0) {
						bytesRead += bytesRecieved;
						percent = 100L*bytesRead/length;
						System.out.print("\r Percent Recieved: " + percent + "%");
					} else
						throw new java.lang.NullPointerException();
				}
				System.out.println("");
				fileOutput.write(cbuf, 0, length);
				fileOutput.close();
			} catch (java.lang.NullPointerException e) {
				System.out.println("Client intitiated disconnect.");
				fileName = "";
			}
			try {
				connectionSocket.close();
			} finally {
				System.out.println("Connection socket closed");
			}
			/**
			try{
				//Process process = new ProcessBuilder("C:/Program Files (x86)/Windows Media Player/T-shirt.mp3").start();
				//Desktop.getDesktop().open(new File("C:/users/jkc11/onedrive/documents/cpre186/server/"+fileName));
				Thread t = new Thread(new Playmusic(fileName));
				t.start();
			} catch(Exception e){
				e.printStackTrace();
			}*/
		}
	}

}