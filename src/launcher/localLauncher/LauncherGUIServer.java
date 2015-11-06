package launcher.localLauncher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;


import controler.controlerLocal.*;
import model.observable.ChessGame;
import vue.ChessGameGUI;
//import socket.server.*;
//import socket.client.*;

public class LauncherGUIServer {

	
	public static void main(String[] args) {		
		ServerSocket ss = null;
		Socket socket = null;
		
		
		ChessGame chessGame;
		ChessGameControlers chessGameControler;		
		
		chessGame = new ChessGame();
		
		try {
			ss = new ServerSocket(2009);
	        System.out.println("Le serveur est à l'écoute "+ss.getLocalPort());	        
	        socket = ss.accept();
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
		frame.setTitle("Server");
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
