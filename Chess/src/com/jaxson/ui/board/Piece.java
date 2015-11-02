package com.jaxson.ui.board;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jaxson.board.IntPiece;
import com.jaxson.geom.Point;
import com.jaxson.ui.Panel;
import com.jaxson.ui.Window;

public class Piece<T extends Window> extends Panel
{
	public static final int KING   = 1;
	public static final int QUEEN  = 2;
	public static final int ROOK   = 3;
	public static final int BISHOP = 4;
	public static final int KNIGHT = 5;
	public static final int PAWN   = 6;
	public static final int WHITE  = 1;
	public static final int BLACK  = 2;

	private static final double SCALE = 0.8;

	public int type, color, direction, turn;
	public int passingIndex;
	private Image image;

	public Piece(int type, int color)
	{
		this(type, color, 0);
	}

	public Piece(int type, int color, int turn)
	{
		super();
		this.type = type;
		this.color = color;
		this.direction = getStartDirection();
		this.turn = turn;
		passingIndex = -1;
		setOpaque(false);
		String path = "assets/images/pieces/" + color + "_" + type + ".png";
		try
		{
		  image = ImageIO.read(new File(path));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private int getStartDirection()
	{
		if (color == Piece.BLACK)
		{
			return +1;
		}
		return -1;
	}

	public Boolean isFriendly(int color)
	{
		return this.color == color;
	}

	@Override
	public void paint(Graphics g)
	{
		int width, height, x, y;
		width = toInt(getWidth() * SCALE);
		height = toInt(getHeight() * SCALE);
		x = toInt((getWidth() - width) / 2);
		y = toInt((getHeight() - height) / 2);
		g.drawImage(image, x, y, width, height, null);
	}

	public Piece promote(T window)
	{
		PromotionWindow promotionWindow = new PromotionWindow(300, 300, color, window);
		return new Piece(promotionWindow.getResult(), color);
	}

	private int toInt(double i)
	{
		return (int)(Math.floor(i));
	}

	public IntPiece toIntPiece(Point location)
	{
		IntPiece newIntPiece = new IntPiece(type, color, location, direction, turn);
		newIntPiece.passingIndex = passingIndex;
		return newIntPiece;
	}
}