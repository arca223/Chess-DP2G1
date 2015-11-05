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
	private Serveur server = null;
	private Client client = null;
	public static ServerSocket ss = null;
	private PrintWriter out;
	private BufferedReader in;
	private Scanner sc = null;
	public boolean isSender;
	private String  message = null;
	
	public ChessGameMultiControler(ChessGame chessGame, Serveur srv) {
		this.chessGame = chessGame;
		this.server = srv;
		try {
		ss = new ServerSocket(2009);
	        System.out.println("Le serveur est à l'écoute "+ss.getLocalPort());
	
	        Socket socket = ss.accept();
	        
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream());
	
	        sc = new Scanner(System.in);
		} catch (IOException e) {
            System.err.println("Le port "+ss.getLocalPort()+" est déjà  utilisé !");
        } 
	}
	
	public ChessGameMultiControler(ChessGame chessGame, Client c) {
		this.chessGame = chessGame;
		this.client = c;
	}
	
	public ChessGameMultiControler(ChessGame chessGame) {
		this.chessGame = chessGame;
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		if (server != null) {
			//server.ss.
		} else {
			
		}
		return this.chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
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
        while(true){
            while(isSender){
                while(true){
                    try {
                        message = in.readLine();
                        System.out.println("\n"+message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(message != null){ isSender = !isSender;}
                }
            }
            while(!isSender){
                out.println(message);
                out.flush();
                if(message != null){ isSender = !isSender;}
            }
        }
	}

}

