package navigation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import csv.CSVException;
import data.Data;
import gui.Button;
import gui.Titre;

/**
 * Page "Comp�tences" de l'application contenant la liste de toutes les
 * comp�tences avec possibilit� d'ajout, suppression et modification
 * 
 */
public class Competences extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	Button			boutonNouveau;
	Button			boutonModifier;
	Button			boutonSupprimer;
	Button			boutonEnregistrer;
	Button			boutonAnnuler;
	JTable			listeCompetences;
	Vector<int[]>	selectedCells	= new Vector<int[]>();
	JTextField		code;
	JTable			listeLangues;
	private Data	data;
	
	public Competences(Data data) {
		this.data = data;
		setOpaque(false);
		setLayout(null);
		setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
		
		/**
		 * JTable contenant toutes les comp�tences
		 */
		this.listeCompetences = new JTable();
		try {
			listeCompetences = JTables.competences(data.Competences().tous());
		} catch (CSVException e) {
			e.printStackTrace();
		}
		listeCompetences.setFillsViewportHeight(true);
		listeCompetences.addMouseListener(this);
		JScrollPane js = new JScrollPane(listeCompetences);
		js.setVisible(true);
		js.setBounds(10, 10, 300, 600);
		add(js);
		
		/**
		 * Cr�ation et positionnement des boutons de gestion de formulaire
		 */
		this.boutonNouveau = new Button("/boutons/nouveau.png");
		this.boutonNouveau.setBounds(330, 560);
		this.boutonNouveau.addMouseListener(this);
		this.boutonModifier = new Button("/boutons/modifier.png");
		this.boutonModifier.setBounds(510, 560);
		this.boutonModifier.addMouseListener(this);
		this.boutonSupprimer = new Button("/boutons/supprimer.png");
		this.boutonSupprimer.setBounds(690, 560);
		this.boutonSupprimer.addMouseListener(this);
		this.boutonEnregistrer = new Button("/boutons/enregistrer.png");
		this.boutonEnregistrer.setBounds(885, 560);
		this.boutonEnregistrer.addMouseListener(this);
		this.boutonAnnuler = new Button("/boutons/annuler.png");
		this.boutonAnnuler.setBounds(1100, 560);
		this.boutonAnnuler.addMouseListener(this);
		add(this.boutonNouveau);
		add(this.boutonModifier);
		add(this.boutonSupprimer);
		add(this.boutonEnregistrer);
		add(this.boutonAnnuler);
		
		/**
		 * Cr�ation et positionnement des �l�ments du formulaire
		 */
		Titre titre = new Titre(" D�tails de la comp�tence :");
		titre.setBounds(330, 10, 930, 20);
		add(titre);
		
		JLabel labelNom = new JLabel("Code :");
		labelNom.setBounds(350, 50, 150, 25);
		add(labelNom);
		
		JLabel labelCompetences = new JLabel("Liste des langues :");
		labelCompetences.setBounds(350, 80, 150, 25);
		add(labelCompetences);
		
		this.code = new JTextField();
		this.code.addMouseListener(this);
		this.code.setBounds(400, 50, 150, 25);
		add(this.code);
		
		this.listeLangues = new JTable();
		this.listeLangues.addMouseListener(this);
		this.listeLangues.setFillsViewportHeight(true);
		JScrollPane jsLangues = new JScrollPane(this.listeLangues);
		jsLangues.setVisible(true);
		jsLangues.setBounds(350, 110, 350, 350);
		add(jsLangues);
		
		ChargementConsultation();
	}
	
	/**
	 * Mode Consultation : - Lecture des informations d�taill�s de l'�l�ment
	 * s�lectionn� - Les �l�ments du formulaire ne sont pas modifiables
	 */
	public void ChargementConsultation() {
		this.code.setEditable(false);
		this.listeLangues.setEnabled(false);
		
		this.boutonEnregistrer.setVisible(false);
		this.boutonAnnuler.setVisible(false);
		this.boutonNouveau.setVisible(true);
		this.boutonModifier.setVisible(true);
		this.boutonSupprimer.setVisible(true);
		this.listeCompetences.setEnabled(true);
	}
	
	/**
	 * Mode Modification : - Possibilit� de modifier les informations d�taill�s
	 * de l'�l�ment s�lectionn� - Les �l�ments de la liste ne sont pas
	 * s�l�ctionnables
	 */
	public void ChargementModification() {
		this.code.setEditable(true);
		this.listeLangues.setEnabled(true);
		
		this.boutonEnregistrer.setVisible(true);
		this.boutonAnnuler.setVisible(true);
		this.boutonNouveau.setVisible(false);
		this.boutonModifier.setVisible(false);
		this.boutonSupprimer.setVisible(false);
		this.listeCompetences.setEnabled(false);
	}
	
	/**
	 * Mode Nouveau : - On vide tous les �l�ments contenus dans les champs du
	 * formulaire - Les �l�ments de la liste ne sont pas s�l�ctionnables
	 */
	public void ChargementNouveau() {
	}
	
	/**
	 * Dessine le fond blanc du formulaire
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D batch = (Graphics2D) g;
		batch.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		batch.setColor(Color.WHITE);
		batch.fillRect(330, 40, 930, 510);
	}
	
	/**
	 * Clic sur la souris : - Si c'est un bouton de gestion du formulaire - Si
	 * c'est un �l�ment de la liste
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof Button) {
			
			/**
			 * TODO Formulaire pr�t � l'ajout d'un nouvel �l�ment
			 */
			if (e.getSource().equals(this.boutonNouveau)) {
				ChargementNouveau();
			}
			
			/**
			 * Formulaire pr�t � la modification d'un �l�ment existant
			 */
			if (e.getSource().equals(this.boutonModifier)) {
				ChargementModification();
			}
			
			/**
			 * TODO Suppression d'un �l�ment existant : doit demander la
			 * confirmation de l'utilisateur
			 */
			if (e.getSource().equals(this.boutonSupprimer)) {
				
			}
			
			/**
			 * TODO On annule toutes les modifications faites par l'utilisateur
			 * depuis l'activation du mode modification / du mode nouveau
			 */
			if (e.getSource().equals(this.boutonAnnuler)) {
				ChargementConsultation();
			}
			
			/**
			 * TODO On enregistre les modifications ou le nouvel �l�ment
			 */
			if (e.getSource().equals(this.boutonEnregistrer)) {
				
			}
		}
		
		/**
		 * Actualisation des champs du formulaire TODO : Faire passer la
		 * r�f�rence de l'objet !
		 */
		if (e.getSource() instanceof JTable) {
			int ligneSelectionne = this.listeCompetences.getSelectedRow();
			this.code.setText((listeCompetences.getValueAt(ligneSelectionne, 0)).toString());
			// @SuppressWarnings("unchecked")
			// TableModel dataModel =
			// ListToJTable.LanguesCompetence((ArrayList<String>)
			// this.listeCompetences.getValueAt(ligneSelectionne,
			// 1)).getModel();
			// this.listeLangues.setModel(dataModel);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
