
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Game extends JFrame{
	private static final long serialVersionUID = 1L;

	int gWidth = 500;
	int gHeight = 400;
	Dimension screenSize = new Dimension(gWidth, gHeight);

	Image dbImage;
	Graphics dbGraphics;

	static boolean jogo = true;
	boolean gameOver;

	static Bola b = new Bola(250, 200);

	public Game() {
		this.setTitle("Pong!");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new AL());
	}

	public static void main(String[] args) {

		Game pg = new Game();
		Thread bola = new Thread(b);
		bola.start();
		Thread p1 = new Thread(b.p1);
		Thread p2 = new Thread(b.p2);
		p2.start();
		p1.start();

	}

	@Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbGraphics = dbImage.getGraphics();
		draw(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void draw(Graphics g) {
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		getScore(g);
		repaint();
	}

	private void getScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Jogador 1 " + b.p1ponto, 40, 40);
		g.drawString("Jogador 2 " + b.p2ponto, 385, 40);
		g.setColor(Color.WHITE);
		if (b.p1ponto >= 6 || b.p2ponto >= 6) {
			setJogo(false);
			gameOver = true;
		}
		if (gameOver)
			g.drawString("Game Over", 200, 200);
		g.setColor(Color.WHITE);
	}

	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			b.p1.keyPressed(e);
			b.p2.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			b.p1.keyReleased(e);
			b.p2.keyReleased(e);
		}

	}

	public static boolean isJogo() {
		return jogo;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public static void setJogo(boolean jogo) {
		Game.jogo = jogo;
	}
}
