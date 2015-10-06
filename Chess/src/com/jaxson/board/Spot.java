package com.jaxson.board;

import com.jaxson.ui.Panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Spot extends Panel
{
	private static final Color selectedColor = new Color(43, 177, 94);
	private static final Color holdSelectedColor = selectedColor.darker();
	private static final Color moveColor = new Color(224, 65, 100);
	private static final Color holdMoveColor = moveColor.darker();

	private Board board;
	private Piece piece;
	private Color color;
	private Spot leftSpot, rightSpot, topSpot, bottomSpot;

	public Spot(Color color, Board board)
	{
		super(new BorderLayout());
		this.board = board;
		this.color = color;
		setBackground(color);
		addMouseListener(new MyMouseAdapter(this));
	}

	public void setLink(Spot leftSpot, Spot rightSpot, Spot topSpot, Spot bottomSpot)
	{
		this.leftSpot = leftSpot;
		this.rightSpot = rightSpot;
		this.topSpot = topSpot;
		this.bottomSpot = bottomSpot;
	}

	public void select()
	{
		if (!isEmpty())
		{
			setBackground(selectedColor);
			displayMoves();
		}
	}

	public void holdSelect()
	{
		if (!isEmpty())
		{
			setBackground(holdSelectedColor);
		}
	}

	public void moveSelect()
	{
		setBackground(moveColor);
	}

	public void holdMoveSelect()
	{
		setBackground(holdMoveColor);
	}

	public void deselect()
	{
		setBackground(color);
	}

	public Boolean isSelected()
	{
		Color color = getBackground();
		return color == selectedColor || color == holdSelectedColor;
	}

	public Boolean isMoveSelected()
	{
		Color color = getBackground();
		return color == moveColor || color == holdMoveColor;
	}

	private void displayMoves()
	{
		Spot[] legalMoves = getLegalMoves();
		for (int i = 0; i < legalMoves.length; i ++)
		{
			if (legalMoves[i] != null)
			{
				legalMoves[i].moveSelect();
			}
		}
	}

	private void hideMoves()
	{
		Spot[] legalMoves = getLegalMoves();
		for (int i = 0; i < legalMoves.length; i ++)
		{
			if (legalMoves[i] != null)
			{
				legalMoves[i].deselect();
			}
		}
	}

	private Spot[] getLegalMoves()
	{
		Spot[] moves = new Spot[7];

		switch (piece.type)
		{
			case Piece.KING:
				moves = getSurrondingSpots();
				break;
			case Piece.QUEEN:
				break;
			case Piece.ROOK:

				break;
			case Piece.BISHOP:
				break;
			case Piece.KNIGHT:
				break;
			case Piece.PAWN:
				break;
			default:
				break;
		}
		return moves;
	}

	public void createPiece(int type, int color)
	{
		removePiece();
		piece = new Piece(type, color);
		add(piece);
	}

	public void setPiece(Piece piece)
	{
		removePiece();
		this.piece = piece;
		add(piece);
	}

	public void removePiece()
	{
		if (isEmpty())
		{
			return;
		}
		remove(piece);
		piece = null;
	}

	public Boolean isEmpty()
	{
		return piece == null;
	}

	private Spot[] getSurrondingSpots()
	{
		Spot[] spots = new Spot[7];
		spots[0] = getTopLeft();
		spots[1] = getTopMiddle();
		spots[2] = getTopRight();
		spots[3] = getMiddleLeft();
		spots[4] = getMiddleRight();
		spots[5] = getBottomLeft();
		spots[6] = getBottomMiddle();
		spots[7] = getBottomRight();
		return spots;
	}

	public Spot[] getAllTopLeft()
	{
		return null;
	}

	public Spot[] getAllTopMiddle()
	{
		return null;
	}

	public Spot[] getAllTopRight()
	{
		return null;
	}

	public Spot[] getAllMiddleLeft()
	{
		return null;
	}

	public Spot[] getAllMiddleRight()
	{
		return null;
	}

	public Spot[] getAllBottomLeft()
	{
		return null;
	}

	public Spot[] getAllBottomMiddle()
	{
		return null;
	}

	public Spot[] getAllBottomRight()
	{
		return null;
	}

	public Spot getTopLeft()
	{
		return topSpot.getMiddleLeft();
	}

	public Spot getTopMiddle()
	{
		return topSpot;
	}

	public Spot getTopRight()
	{
		return topSpot.getMiddleRight();
	}

	public Spot getMiddleLeft()
	{
		return leftSpot;
	}

	public Spot getMiddleRight()
	{
		return rightSpot;
	}

	public Spot getBottomLeft()
	{
		return bottomSpot.getMiddleLeft();
	}

	public Spot getBottomMiddle()
	{
		return bottomSpot;
	}

	public Spot getBottomRight()
	{
		return bottomSpot.getMiddleRight();
	}

	public void onCick()
	{
		if (isSelected())
		{
			board.deselect();
		}
		else if (isMoveSelected())
		{

		}
		else
		{
			board.deselect();
			holdSelect();
		}
	}

	public void onRelease()
	{
		if (isSelected())
		{
			select();
		}
		else if (isMoveSelected())
		{

		}
	}
}

class MyMouseAdapter extends MouseAdapter
{
	private Spot object;

	public MyMouseAdapter(Spot object)
	{
		this.object = object;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		object.onCick();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		object.onRelease();
	}
}
