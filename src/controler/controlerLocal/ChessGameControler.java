package controler.controlerLocal;

import model.*;
import model.observable.*;

public class ChessGameControler implements ChessGameControlers{
	

	private ChessGame chessGame;

	public ChessGameControler() {
		this.chessGame = new ChessGame();
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
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

}
