package controler.controlerLocal;

import model.*;

public interface ChessGameControlers {

	public boolean move(Coord initCoord, Coord finalCoord);
	public String getMessage();	
	public boolean isEnd();	
	public Couleur getColorCurrentPlayer(); 
	
}
