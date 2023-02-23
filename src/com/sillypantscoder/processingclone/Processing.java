package com.sillypantscoder.processingclone;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.MouseInfo;
import java.awt.PointerInfo;

public abstract class Processing extends RepaintingPanel {
	// Setup
	// To run your program, make your main class extend Processing, then add this to your class:
	// 		public static void main(String[] args) { runApplication(new Main()); }
	// where "new Main()" can be substituted for the class name (e.g. "new MyCoolApp()").
	public static void runApplication(Processing inst) {
		inst.setup();
	}
	public static Processing mainFrame = null;
	public Processing() {
		Processing.mainFrame = this;
	}
	// Painting
	protected Graphics currentGraphics = null;
	public void painter(Graphics g) {
		// Draw background
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		// Draw the custom "draw" function
		currentGraphics = g;
		draw();
		currentGraphics = null;
	}
	// Event handlers
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	// Processing pre-declared methods
	public abstract void setup();
	public abstract void draw();
	// Extra Processing methods
	public static double cos(double x) { return Math.cos(x); }
	public static int abs(int x) { return Math.abs(x); }
	public static float abs(double x) { return (float)(Math.abs(x)); }
	public static float map(float x, float inMin, float inMax, float outMin, float outMax) {
		return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
	}
	public static float dist(float x1, float y1, float x2, float y2) {
		return (float)(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}
	// Processing constants
	public static final double PI = 3.14159265358979323846;
	// Processing settings
	public Color backgroundColor = new Color(255, 255, 255);
	public Color fillColor = null;
	public Color strokeColor = null;
	public int strokeWidth = 0;
	public int width = -1;
	public int height = -1;
	public int mouseX = 0;
	public int mouseY = 0;
	// Processing methods!
	public static void size(int width, int height) {
		mainFrame.run();
		mainFrame.frame.setSize(width, height);
		mainFrame.width = width;
		mainFrame.height = height;
	}
	public static void background(int r, int g, int b) {
		mainFrame.backgroundColor = new Color(r, g, b);
	}
	public static void background(int g) {
		mainFrame.backgroundColor = new Color(g, g, g);
	}
	public static void circle(int x, int y, int r) {
		if (mainFrame.fillColor != null) {
			mainFrame.currentGraphics.setColor(mainFrame.fillColor);
			mainFrame.currentGraphics.fillOval(x - r, y - r, r * 2, r * 2);
		}
		if (mainFrame.strokeColor != null) {
			mainFrame.currentGraphics.setColor(mainFrame.strokeColor);
			Graphics2D g2d = (Graphics2D)(mainFrame.currentGraphics);
			g2d.setStroke(new BasicStroke(mainFrame.strokeWidth));
			mainFrame.currentGraphics.drawOval(x - r, y - r, r * 2, r * 2);
		}
	}
	public static void fill(int r, int g, int b) {
		mainFrame.fillColor = new Color(r, g, b);
	}
	public static void fill(int g) {
		mainFrame.fillColor = new Color(g, g, g);
	}
	public static void noFill() {
		mainFrame.fillColor = null;
	}
	public static void stroke(int r, int g, int b) {
		mainFrame.strokeColor = new Color(r, g, b);
	}
	public static void stroke(int g) {
		mainFrame.strokeColor = new Color(g, g, g);
	}
	public static void stroke(float g) {
		mainFrame.strokeColor = new Color((int)(g), (int)(g), (int)(g));
	}
	public static void noStroke() {
		mainFrame.strokeColor = null;
	}
	public static void strokeWeight(int w) {
		mainFrame.strokeWidth = w;
	}
	public static void point(int x, int y) {
		if (mainFrame.strokeColor != null) {
			int r = mainFrame.strokeWidth / 2;
			mainFrame.currentGraphics.setColor(mainFrame.strokeColor);
			mainFrame.currentGraphics.drawOval(x - r, y - r, r * 2, r * 2);
		}
	}
	public static void line(int x1, int y1, int x2, int y2) {
		if (mainFrame.strokeColor != null) {
			mainFrame.currentGraphics.setColor(mainFrame.strokeColor);
			Graphics2D g2d = (Graphics2D)(mainFrame.currentGraphics);
			g2d.setStroke(new BasicStroke(mainFrame.strokeWidth));
			mainFrame.currentGraphics.drawLine(x1, y1, x2, y2);
		}
	}
	public static void rect(int x, int y, int w, int h) {
		if (mainFrame.fillColor != null) {
			mainFrame.currentGraphics.setColor(mainFrame.fillColor);
			mainFrame.currentGraphics.fillRect(x, y, w, h);
		}
		if (mainFrame.strokeColor != null) {
			mainFrame.currentGraphics.setColor(mainFrame.strokeColor);
			Graphics2D g2d = (Graphics2D)(mainFrame.currentGraphics);
			g2d.setStroke(new BasicStroke(mainFrame.strokeWidth));
			mainFrame.currentGraphics.drawRect(x, y, w, h);
		}
	}
}
