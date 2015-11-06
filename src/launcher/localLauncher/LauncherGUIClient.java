package launcher.localLauncher;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;


import controler.controlerLocal.*;
import model.observable.ChessGame;
import vue.ChessGameGUI;
//import socket.server.*;
//import socket.client.*;

public class LauncherGUIClient {
	
	
	public static void main(String[] args) {	
		
		Socket socket = null;
		
		ChessGame chessGame;
		ChessGameControlers chessGameControler;		
		
		chessGame = new ChessGame();		

		try {
			socket = new Socket("127.0.0.1", 2009);
			System.out.println("Connexion etablie avec le serveur, authentification :"); 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chessGameControler = new ChessGameMultiControler(chessGame, socket);
		
		ChessGameGUI gui = new ChessGameGUI(chessGameControler);
		chessGame.addObserver(gui);
		JFrame frame = gui;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setTitle("Client");
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}
}
