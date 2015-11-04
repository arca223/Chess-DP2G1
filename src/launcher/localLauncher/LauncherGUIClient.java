package launcher.localLauncher;

import javax.swing.JFrame;


import controler.controlerLocal.*;
import model.observable.ChessGame;
import vue.ChessGameGUI;
import socket.server.*;
import socket.client.*;

public class LauncherGUIClient {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameMultiControler chessGameControler;		
		
		chessGame = new ChessGame();
		Client cli = new Client();
		chessGameControler = new ChessGameMultiControler(chessGame, cli);
		
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
