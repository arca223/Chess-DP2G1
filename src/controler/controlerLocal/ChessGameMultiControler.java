package controler.controlerLocal;

import java.io.*;
import java.net.*;
import java.util.*;

import socket.server.*;
import socket.client.*;

import model.*;
import model.observable.*;

public class ChessGameMultiControler implements ChessGameControlers, Runnable{


	private ChessGame chessGame;
//	private Serveur server = null;
//	private Client client = null;
//	public static ServerSocket ss = null;
	private BufferedWriter out;
	private BufferedReader in;
	private String  message = null;
//	public static Socket socket;
	
	public ChessGameMultiControler(ChessGame chessGame, Socket socket) {
		this.chessGame = chessGame;
		
		try {			
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	
		} catch (IOException e) {
            System.err.println("Le port "+socket.getLocalPort()+" est déjà  utilisé !");
        } 
		Thread t1 = new Thread(this);
		t1.start();
	}
	



	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		
        String stringCoord = initCoord.x + ":"+ initCoord.y + ":" + finalCoord.x + ":"+ finalCoord.y;
        System.out.println("stringCoord : " + stringCoord);
		try {
			System.out.println("before write");
			out.write(stringCoord+"\n");
			out.flush();
			System.out.println("before chessgame move envoi");
			return this.chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public String getMessage() {
		return this.chessGame.getMessage();
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return this.chessGame.getColorCurrentPlayer();
	}

	@Override
    public void run() {
		System.out.println("WESH ON E DAN LE RUNE");

        while(true){
            try {
            	System.out.println("THE BIGININGE");
				message = in.readLine();
				System.out.println("Message : " + message);
				String coords[] = message.split(":");
				System.out.println("coords 0 : " + coords[0]);
				System.out.println("coords 1 : " + coords[1]);
				System.out.println("coords 2 : " + coords[2]);
				System.out.println("coords 3 : " + coords[3]);
				
				this.chessGame.move(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}

