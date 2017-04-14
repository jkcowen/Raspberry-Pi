package FileUploadTCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		String fileName;
		int fileLength;
		System.out.println("File Name: ");
		Scanner nameInput = new Scanner(System.in);
		fileName = nameInput.next();
		nameInput.close();
		
		try {
			// read file into byte array
			File file = new File("C:/users/jkc11/onedrive/documents/cpre186/" + fileName);
			fileLength = (int) file.length();
			System.out.println("FileName: " + fileName + "  Length: " + fileLength);
			byte[] fileBuffer = new byte[fileLength];
			DataInputStream inFromFile = new DataInputStream(new FileInputStream(file));
			inFromFile.read(fileBuffer);
			inFromFile.close();
			boolean exception = true;
			do {
				Socket clientSocket = null;
				DataOutputStream outToServer = null;
				BufferedReader inFromServer = null;
				try {
					// create tcp/ip socket
					// clientSocket = new Socket("10.36.179.191", 15001);
					// clientSocket = new Socket("10.26.42.47", 15001);
					clientSocket = new Socket("10.24.84.226", 15001);
					// clientSocket = new Socket("127.0.0.1", 15001);
					// create input and output streams from socket
					outToServer = new DataOutputStream(clientSocket.getOutputStream());
					inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					// send file name and wait for response
					outToServer.writeBytes(fileName+"\n");
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
