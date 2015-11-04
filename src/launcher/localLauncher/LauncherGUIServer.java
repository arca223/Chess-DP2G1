package launcher.localLauncher;

import javax.swing.JFrame;


import controler.controlerLocal.*;
import model.observable.ChessGame;
import vue.ChessGameGUI;
import socket.server.*;
import socket.client.*;

public class LauncherGUIServer {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameMultiControler chessGameControler;		
		
		chessGame = new ChessGame();
		Serveur srv = new Serveur();
		chessGameControler = new ChessGameMultiControler(chessGame, srv);
		
		ChessGameGUI gui = new ChessGameGUI();
		chessGame.addObserver(gui);
		JFrame frame = gui;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
