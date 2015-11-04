package model.observable;

import java.util.List;

import com.sun.prism.paint.Color;

import javafx.beans.InvalidationListener;
import java.util.Observable;
import java.util.Observer;

import model.Couleur;
import model.Echiquier;
import model.Jeu;
import model.PieceIHM;

public class ChessGame extends Observable{
	
	private Echiquier echiquier;
	
	
	public ChessGame(){
		echiquier = new Echiquier();
	}
	
	public String toString(){
		String retour = "";
		retour += echiquier.toString();
		retour += "\n";
		retour += "D�placement de " + /*position initiale +*/ " vers " + /*position finale +*/ " : " + /*position statut +*/ " : d�placement simple";
		return retour;
	}
	
	public boolean move(int xInit, int yInit, int xFinal, int yFinal){
		boolean ret = false;;
		if (this.echiquier.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			ret = this.echiquier.move(xInit, yInit, xFinal, yFinal);
			if (ret) {
				this.echiquier.switchJoueur();
			}			
		} else {
			ret =  false;
		}
		this.setChanged();
		this.notifyObservers(this.echiquier.getPiecesIHM());
		this.clearChanged();
		return ret;
	}
	
	public boolean isEnd(){
		return this.echiquier.isEnd();
	}
	
	public String getMessage(){
		return this.echiquier.getMessage();
	}
	

	public Couleur getColorCurrentPlayer(){
		return this.echiquier.getColorCurrentPlayer();
	}	
	
	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
		this.setChanged();
		this.notifyObservers(this.echiquier.getPiecesIHM());
		this.clearChanged();
	}

}
