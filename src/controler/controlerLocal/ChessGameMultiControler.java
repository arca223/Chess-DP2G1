package controler.controlerLocal;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import socket.server.*;
import socket.client.*;

import model.*;
import model.observable.*;

public class ChessGameMultiControler implements ChessGameControlers, Runnable{
	

	private ChessGame chessGame;
	private Serveur server = null;
	private List<Client> listClient = null;

	public ChessGameMultiControler(ChessGame chessGame, Serveur srv) {
		this.chessGame = chessGame;
		this.server = srv;
	}
	
	public ChessGameMultiControler(ChessGame chessGame, Client c) {
		this.chessGame = chessGame;
		this.listClient.add(c);
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
		
	}
	

}

