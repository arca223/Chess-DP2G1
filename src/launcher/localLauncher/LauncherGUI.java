package launcher.localLauncher;

import javax.swing.JFrame;


import controler.controlerLocal.ChessGameControler;
import model.observable.ChessGame;
import vue.ChessGameGUI;

public class LauncherGUI {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameControler chessGameControler;		
		
		chessGame = new ChessGame();
		chessGameControler = new ChessGameControler(chessGame);
		
		ChessGameGUI gui = new ChessGameGUI(chessGameControler);
		chessGame.addObserver(gui);
		JFrame frame = gui;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
