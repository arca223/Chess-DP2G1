package model.observable;

import com.sun.prism.paint.Color;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.Couleur;
import model.Echiquier;

public class ChessGame implements Observable{
	
	public Echiquier echiquier; 
	
	public ChessGame(){
		echiquier = new Echiquier();
	}
	
	public String toString(){
		String retour = "";
		retour += echiquier.toString();
		retour += "\n";
		retour += "Déplacement de " + /*position initiale +*/ " vers " + /*position finale +*/ " : " + /*position statut +*/ " : déplacement simple";
		return retour;
	}
	
	public boolean move (int xInit, int yInit, int xFinal, int yFinal){
		
	}
	
	public boolean isEnd(){
		this.echiquier.isEnd();
	}
	
	public String getMessage(){
		return this.echiquier.getMessage();
	}
	
	public Couleur getColorCurrentPlayer(){
		return ColorCurrentPlayer;
	}


	@Override
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

}
