package launcher.localLauncher;

import controler.controlerLocal.ChessGameControler;
import model.observable.ChessGame;
import vue.*;

public class LauncherGUI {
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameControler chessGameControler;		
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame);
		
		new ChessGameDemo(chessGameControler);	
	}
}
