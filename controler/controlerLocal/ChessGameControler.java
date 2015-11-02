package controler.controlerLocal;

import model.*;
import model.observable.*;

public class ChessGameControler implements ChessGameControlers{
	

	private ChessGame chessGame;

	public ChessGameControler() {
		chessGame = new ChessGame(new Echiquier());
	}

	@Override
	public boolean move(Coord initCoord, Coord finalCoord) {
		return chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
	}

	@Override
	public String getMessage() {
		return ChessGame.getMessage();
	}

	@Override
	public boolean isEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couleur getColorCurrentPlayer() {
		return chessGame.getColorCurrentPlayer();
	}
	
	



}
