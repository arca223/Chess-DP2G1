package controler.controlerLocal;

import java.util.LinkedList;
import java.util.List;

import model.*;
import model.observable.*;

public class ChessGameControler implements ChessGameControlers{
	

	private ChessGame chessGame;

	public ChessGameControler(ChessGame chessGame) {
		this.chessGame = chessGame;
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
