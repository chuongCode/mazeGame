import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.lang.*;
import java.util.Random;
import java.awt.Image;
import java.awt.Rectangle;

// basically the player is a square with some complicated hitboxes
public class Player extends Rectangle {
	private int dx, dy;
	
	public Player (int x, int y, int width, int height, int dx, int dy) {
		setBounds(x, y, width, height);
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void tick() {
		this.x += dx;
		this.y += dy;
	}
	
	public int getXCoord() {
		return this.x;
	}
	
	public int getYCoord() {
		return this.y;
	}
	
	public void draw(Graphics g) {
		g.fillRect(this.x, this.y, this.width, this.height);
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
