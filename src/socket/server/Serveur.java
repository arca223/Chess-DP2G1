package socket.server;
import java.io.*;
import java.net.*;

public class Serveur implements Runnable{
	public static ServerSocket ss = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String login = "zero";


	public Serveur() {

		try {
			ss = new ServerSocket(2009);
			System.out.println("Le serveur est à l'écoute "+ss.getLocalPort());

			Socket socket = ss.accept();
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

			Thread tIn = new Thread(new Reception(in,login));
			tIn.start();
			Thread tOut = new Thread(new Emission(out));
			tOut.start();

		} catch (IOException e) {
			System.err.println("Le port "+ss.getLocalPort()+" est déjà  utilisé !");
		}

	}

}
