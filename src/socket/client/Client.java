package socket.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements Runnable{

	public static Socket socket = null;
	public static Thread t1;
	private String login = "zero";
	private PrintWriter out = null;
	private BufferedReader in = null;
	private Scanner sc;
	private Thread tIn, tOut;


	public Client() {
		try {
			socket = new Socket("127.0.0.1", 2009);
			System.out.println("Connexion établie avec le serveur, authentification :"); 
			// Si le message s'affiche c'est que je suis connecté

			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			sc = new Scanner(System.in);

			Thread tOut = new Thread(new Emission(out));
			tOut.start();
			Thread tIn = new Thread(new Reception(in));
			tIn.start();

		} catch (UnknownHostException e) {
			System.err.println("Impossible de se connecter à l'adresse " + socket.getLocalAddress());
		} catch (IOException e) {
			System.err.println("Aucun serveur à l'écoute du port " + socket.getLocalPort());
		}
	}

	public void send(String s) {
		out.println(s);
		out.flush();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
