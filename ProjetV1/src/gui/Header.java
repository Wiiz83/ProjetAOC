package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.RGBImageFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.w3c.dom.css.RGBColor;

import navigation.Navigation;
import navigation.Page;
import program.Program;
import utilities.Button;
import utilities.Vector2;

public class Header extends JPanel implements ActionListener {
	private static final long		serialVersionUID	= 1L;
	private static final boolean	DEBUG				= false;
	private static final int POS_Y	= 10;

	// Prend le curseur pr�d�fini
	public static Cursor newCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	// L'image de fond
	private Image background;
	
	// Logo
	private Image logo;
	
	// Le bouton d'accueil
	public static Button buttonHomePage;

	/**
	 * Constructeur de GraphicsPanel
	 */
	public Header() {
		super();
		try {
			//this.background = ImageIO.read(getClass().getResource("/images/background.png"));
			this.buttonHomePage = new Button("/boutons/accueil.png");
			add(buttonHomePage);
			this.logo = ImageIO.read(getClass().getResource("/images/logo.png"));
		}
		catch (IOException e) {
			System.out.println("erreur chargement fond");
			e.printStackTrace();
		}	
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		batch.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		batch.setColor(new Color(0, 72, 136));
		batch.fillRect(0, 0, 1280, 70);
		
		g.drawImage(logo, 0, POS_Y, 136, 50, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	

		

}
