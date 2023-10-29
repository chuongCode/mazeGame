import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;

public class mazeMaker extends Rectangle {
	
	int x;
	int y;
	int width;
	int height;
	
	mazeMaker(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw (Graphics g) {
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
}
