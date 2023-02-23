package com.sillypantscoder.processingclone;

public class Main extends Processing {
	public static void main(String[] args) { runApplication(new Main()); }
	// TEST
	public void setup() {
		size(1000, 1000);
		background(100, 0, 0);
	}
	public void draw() {
		fill(255, 255, 255);
		stroke(0, 255, 0);
		strokeWeight(100);
		circle(50, 50, 100);
	}
}