package model;

/**
 * @author francoise.perrin
 * Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 */
public interface Pieces {

	/**
	 * @return indice de la colonne où est positionnée la piece
	 */
	public int getX();
	
	/**
	 * @return indice de la ligne où est positionnée la piece
	 */
	public int getY();
	
	/**
	 * @return couleur de la piece
	 */
	public Couleur getCouleur();
	
	/**
	 * @return le nom de la piece
	 */
	public String getName() ;
	
	/**
	 * @param xFinal
	 * @param yFinal
	 * @return true si dï¿½placement lï¿½gal
	 */
	public  boolean isMoveOk(int xFinal, int yFinal) ;
	
	/**
	 * @param xFinal
	 * @param yFinal
	 * @return true si dï¿½placement effectuï¿½
	 */
	public boolean move(int xFinal, int yFinal);
	
	/** 
	 * @return true si piece effectivement capturée
	 * Positionne x et y Ã  -1
	 */
	public boolean capture();
};

