package model;


import java.util.LinkedList;
import java.util.List;

import tools.ChessPieceFactory;
import tools.ChessSinglePieceFactory;

/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public class Jeu  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected List<Pieces> pieces;
	protected Couleur couleur;

	// Toutes les variables suivantes sont partag�es
	// entre les 2 instances de jeu (noir et blanc)
	
	private static boolean isMoveOk;
	private static boolean isPieceToCatch;
	private static boolean isLastPion;
	private static boolean isPromotion;
	private boolean isCastling;

	// en cas d'annulation si le d�placement met le roi en �chec
	private static Pieces pieceToMoveUndo; 
	private static int xInitUndo;
	private static int yInitUndo;
	private static Pieces pieceToCatchUndo; 
	private static int xFinalUndo;
	private static int yFinalUndo;


	
	/**
	 * Le constructeur de jeu fait appel � la fabrique de pi�ces
	 * @param couleur
	 * 
	 */
	public Jeu(Couleur couleur){
		this.pieces = ChessPieceFactory.newPieces(couleur);
		this.couleur = couleur;
		isPieceToCatch = isMoveOk = false;
	}


	/**
	 * @param x
	 * @param y
	 * @return true si une pi�ce se trouve aux coordonn�es indiqu�es
	 */
	public boolean isPieceHere(int x, int y) {
		boolean ret = false;

		if(this.findPiece(x, y) != null){
			ret = true;
		}
		return ret;
	}

	/**
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true si  piece du jeu peut �tre d�plac�e aux coordonn�es finales,
	 *  false sinon
	 */
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal){

		Pieces pieceToMove = null;
		isLastPion = false;
		isMoveOk = false;
	
		pieceToMove = this.findPiece(xInit, yInit);

		// verif d�placement autoris� ds cas g�n�ral
		if (pieceToMove != null && pieceToMove.isMoveOk(xFinal, yFinal)) {
			isMoveOk = true;
		}

		// verif d�placement autoris� en diagonale ds cas particulier du pion
		if (pieceToMove != null &&  pieceToMove instanceof Pions){		

			Pions pion = (Pions) pieceToMove;
			isLastPion = true;	 // pratique en cas de promotion du pion

			// si les coordonn�es finales correspondent � un deplacement en
			// diagonale du pion  et
			// s'il existe une pi�ce d'une autre couleur � prendre aux coordonn�es finales
			if( isPieceToCatch) {
				isMoveOk = false;
				if (pion.isMoveDiagOk(xFinal, yFinal)) {
					isMoveOk = true;					
				}
				if (!isMoveOk ){
					isPieceToCatch = false;
				}
			}			
		} 

		// verif d�placement autoris� ds cas roque du roi

		// TODO 
		
		return isMoveOk;
	}

	/**
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true si d�placement pi�ce effectu�
	 */
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret = false;
		Pieces pieceToMove = null;

		pieceToMove = this.findPiece(xInit, yInit);
		if (pieceToMove!=null){
			ret = pieceToMove.move(xFinal, yFinal);

			// Sauvegarde dans l'hypoth�se o� le d�placement 
			// mettrait le roi en �chec
			pieceToMoveUndo = pieceToMove;
			xInitUndo = xInit;
			yInitUndo = yInit;
		}
		isMoveOk = false;
		isPieceToCatch = false;
		isPromotion = false;
		
		return ret;
	}

	/**
	 * Si une capture d'une pi�ce de l'autre jeu est possible
	 * met � jour 1 bool�en
	 */
	public void setPossibleCapture() {
		isPieceToCatch = true;
	}

	/**
	 * @param xCatch
	 * @param yCatch
	 * @return true si la piece aux coordonn�es finales
	 * a �t� captur�e
	 */
	public boolean capture(int xCatch, int yCatch) {
		boolean ret = false;
		Pieces pieceToCatch;

		pieceToCatch = this.findPiece(xCatch, yCatch);
		
		// Pour rembobiner si le roi oppos� est mis en �chec
		pieceToCatchUndo = pieceToCatch; 
		xFinalUndo = pieceToCatch.getX();
		yFinalUndo = pieceToCatch.getY();
		
		ret = pieceToCatch.capture();
		isPieceToCatch = false;	

		return ret;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String S="";

		for (Pieces piece : pieces){
			S+="(";
			S=S.concat(piece.toString());
			S+=" ";
			S+=piece.getX() + " " + piece.getY() + ")";
			S+="\n";
		}
		return S;
	}


	/**
	 * @param x
	 * @param y
	 * @return nom de la pi�ce aux coordonn�es x,y
	 */
	public String getPieceName(int x, int y) {
		String ret = null;
		Pieces piece = this.findPiece(x, y);
		if(piece != null){
			ret = piece.getName();
		}
		return ret;
	}

	/**
	 * @param x
	 * @param y
	 * @return type de la pi�ce aux coordonn�es x,y
	 * c'est � dire le nom de la classe : 
	 * maPiece.getClass().getSimpleName();
	 */
	public String getPieceType(int x, int y) {
		String ret = null;
		Pieces piece = this.findPiece(x, y);
		if(piece != null){
			ret =  piece.getClass().getSimpleName();
		}
		return ret;
	}
	/**
	 * @return couleur du jeu
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * @return une vue de la liste des pi�ces en cours
	 * ne donnant que des acc�s en lecture sur des PieceIHM
	 * (type piece + couleur + coordonn�es)
	 */
	public List<PieceIHM> getPiecesIHM(){
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();
		
		for (Pieces piece : pieces){
			boolean existe = false;
			// si le type de piece existe d�j� dans la liste de PieceIHM
			// ajout des coordonn�es de la pi�ce dans la liste de Coord de ce type 
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){				
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
					existe = true;
					if (piece.getX() != -1){
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));
					}
				}			
			}
			// sinon, cr�ation d'une nouvelle PieceIHM si la pi�ce est toujours en jeu
			if (! existe) {
				if (piece.getX() != -1){					
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(), piece.getCouleur());
					newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
					list.add(newPieceIHM);
				}
			}
		}
		return list;
	}


	/**
	 * @param x
	 * @param y
	 * @return la r�f�rence vers la pi�ce cherch�e, null sinon
	 */
	private Pieces findPiece(int x, int y){
		Pieces pieceToFind = null;

		for (Pieces piece : pieces){
			if (piece.getX()==x && piece.getY()==y){
				pieceToFind = piece;
			}
		}
		return pieceToFind;
	}

	/**
	 * met � jour un bool�en pour activer l'hypoth�se d'un roque du roi
	 */
	public void setCastling() {
		this.isCastling = true;

	}


	//	public static void main(String[] args) {
	//		Jeu jeu = new Jeu(Couleur.BLANC);
	//		System.out.println(jeu);
	//	}

}

