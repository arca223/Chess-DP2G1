package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controler.controlerLocal.ChessGameControler;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import tools.ChessImageProvider;
import tools.ChessPieceImage;

public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

	ChessGameControler cgc;
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	int xMemo;
	int yMemo;
	Couleur jeuCourant;

	public ChessGameGUI(ChessGameControler cgc) {
		
		this.cgc = cgc;
		Dimension boardSize = new Dimension(600, 600);

		// Use a Layered Pane for this this application
		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		// Add a chess board to the Layered Pane

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				square.setBackground(i % 2 == 0 ? Color.blue : Color.white);
			else
				square.setBackground(i % 2 == 0 ? Color.white : Color.blue);
		}

		JPanel panel = new JPanel();
		for (PieceIHM p : this.cgc.getPiecesIHM()) {
			List<Coord> list = p.getList();
			for (Coord c : list) {
				JLabel piece = new JLabel(
						new ImageIcon(ChessImageProvider.getImageFile(p.getTypePiece(), p.getCouleur())));
				int pos = 63 - (8 * c.y + c.x);
				panel = (JPanel) chessBoard.getComponent(pos);
				panel.add(piece);
			}

		}
		
		this.jeuCourant = Couleur.BLANC;

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent me) {
		if (chessPiece == null)
			return;
		chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		chessPiece = null;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();
		chessPiece = (JLabel) c;
		chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		
		this.xMemo = e.getX()/75;
		this.yMemo = e.getY()/75;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Coord initCoord = new Coord(xMemo, yMemo);
		System.out.println(xMemo + yMemo);
		Coord finalCoord = new Coord((e.getX() + xAdjustment)/75, (e.getY() + yAdjustment)/75);
		System.out.println(initCoord.toString() + "---------" + finalCoord.toString());
		System.out.println("test");
		if (this.cgc.move(initCoord, finalCoord)) {
			if (chessPiece == null)
			return;
			System.out.println("test");
			chessPiece.setVisible(false);
			Component c = chessBoard.findComponentAt(e.getX(), e.getY());
	
			if (c instanceof JLabel) {
				Container parent = c.getParent();
				parent.remove(0);
				parent.add(chessPiece);
			} else {
				Container parent = (Container) c;
				parent.add(chessPiece);
			}
	
			chessPiece.setVisible(true);	
		}
		System.out.println(this.cgc.getMessage());

	}


}
