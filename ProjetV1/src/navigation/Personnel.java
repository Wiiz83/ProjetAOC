package navigation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import csv.CSVException;
import csv.CSVObjects;
import gui.Button;
import gui.Titre;
import models.Competence;
import models.Employee;

/**
 * Page "Personnel" de l'application contenant la liste du
 * personnel avec possibilit� d'ajout, suppression et modification
 * 
 * 	TODO ATTENTION : Impl�mentation diff�rente de Comp�tences et Personnel 
 * 		- Utilisation de la m�thode AffichageListe() qui actualise la liste en cas de modification
 * 
 */
public class Personnel extends JPanel implements MouseListener {
	Button boutonNouveau;
	Button boutonModifier;
	Button boutonSupprimer;
	Button boutonEnregistrer;
	Button boutonAnnuler;
	JTable listePersonnel;
	Vector<int[]> selectedCells = new Vector<int[]>();
	JScrollPane jsPersonnel;
	int IDSelect;
	JTextField nom;
	JTextField prenom;
	JTextField date;
	JTable competences;
	String mode;
	private static final long serialVersionUID = 1L;
	

	public Personnel() {
		setOpaque(false);
		setLayout(null);
		setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));

		/**
		 *  Affichage de la liste gauche
		 */
		AffichageListe();

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
		Titre titre = new Titre(" D�tails du salari� :");
		titre.setBounds(330, 10, 930, 20);
		add(titre);

		JLabel labelNom = new JLabel("Nom :");
		labelNom.setBounds(350, 50, 150, 25);
		add(labelNom);

		JLabel labelPrenom = new JLabel("Pr�nom :");
		labelPrenom.setBounds(350, 80, 150, 25);
		add(labelPrenom);

		JLabel labelDate = new JLabel("Date d'entr�e :");
		labelDate.setBounds(350, 110, 150, 25);
		add(labelDate);

		JLabel labelCompetences = new JLabel("Liste des comp�tences :");
		labelCompetences.setBounds(350, 140, 150, 25);
		add(labelCompetences);

		this.nom = new JTextField();
		this.nom.addMouseListener(this);
		this.nom.setBounds(450, 50, 150, 25);
		add(this.nom);

		this.prenom = new JTextField();
		this.prenom.addMouseListener(this);
		this.prenom.setBounds(450, 80, 150, 25);
		add(this.prenom);

		this.date = new JTextField();
		this.date.addMouseListener(this);
		this.date.setBounds(450, 110, 150, 25);
		add(this.date);

		this.competences = new JTable();
		this.competences.addMouseListener(this);
		this.competences.setFillsViewportHeight(true);
		JScrollPane js = new JScrollPane(this.competences);
		js.setVisible(true);
		js.setBounds(350, 170, 350, 350);
		add(js);

		ChargementConsultation();
	}
	
	
	/**
	 * Affiche la liste 
	 */
	public void AffichageListe() {
		this.listePersonnel = new JTable();
		try {
			this.listePersonnel = JTableRequests.tousLesEmployes();
			this.listePersonnel.setFillsViewportHeight(true);
			this.listePersonnel.addMouseListener(this);
			this.jsPersonnel = new JScrollPane(this.listePersonnel);
			this.jsPersonnel.setVisible(true);
			this.jsPersonnel.setBounds(10, 10, 300, 600);
			add(this.jsPersonnel);
		} catch (CSVException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Supprime la liste et reinitialise l'ID s�l�ctionn�
	 */
	public void Reinitialiser() {
		remove(this.listePersonnel);
		remove(this.jsPersonnel);
		this.IDSelect = 0;
	}
	
	/**
	 * Mode Consultation : - Lecture des informations d�taill�s de l'�l�ment
	 * s�lectionn� - Les �l�ments du formulaire ne sont pas modifiables
	 */
	public void ChargementConsultation() {
		this.nom.setEditable(false);
		this.prenom.setEditable(false);
		this.date.setEditable(false);
		this.competences.setEnabled(false);

		this.boutonEnregistrer.setVisible(false);
		this.boutonAnnuler.setVisible(false);
		this.boutonNouveau.setVisible(true);
		this.boutonModifier.setVisible(true);
		this.boutonSupprimer.setVisible(true);
		this.listePersonnel.setEnabled(true);

		this.mode = "consultation";
	}
	
	/**
	 * Mode Modification : - Possibilit� de modifier les informations d�taill�s
	 * de l'�l�ment s�lectionn� - Les �l�ments de la liste ne sont pas
	 * s�l�ctionnables
	 */
	public void ChargementModification() {
		this.nom.setEditable(true);
		this.prenom.setEditable(true);
		this.date.setEditable(true);
		this.competences.setEnabled(true);

		this.boutonEnregistrer.setVisible(true);
		this.boutonAnnuler.setVisible(true);
		this.boutonNouveau.setVisible(false);
		this.boutonModifier.setVisible(false);
		this.boutonSupprimer.setVisible(false);
		this.listePersonnel.setEnabled(false);

		this.mode = "modification";
	}

	/**
	 * Mode Nouveau : - On vide tous les �l�ments contenus dans les champs du
	 * formulaire - Les �l�ments de la liste ne sont pas s�l�ctionnables
	 */
	public void ChargementNouveau() {
		this.nom.setText("");
		this.prenom.setText("");
		this.date.setText("");
		ChargementModification();

		this.mode = "nouveau";
	}

	/**
	 * Enregistrement des informations dans le cas d'une modification ou d'un ajout
	 */
	public void Enregistrement() {
		CSVObjects<Employee> employes_csv;

		switch (this.mode) {
		case "nouveau":
			try {
				employes_csv = new CSVObjects<>(Employee.class);
				Employee emp = new Employee(this.nom.getText(), this.prenom.getText(), this.date.getText());
				employes_csv.add(emp);
			} catch (CSVException e) {
				e.printStackTrace();
			}
			break;

		case "modification":
			try {
				employes_csv = new CSVObjects<>(Employee.class);
				Employee emp = new Employee(this.nom.getText(), this.prenom.getText(), this.date.getText());
				employes_csv.modify(emp);
			} catch (CSVException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
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
				CSVObjects<Employee> employes_csv;
				try {
					employes_csv = new CSVObjects<>(Employee.class);
					Employee emp = employes_csv.getByID(String.valueOf(this.IDSelect));
					employes_csv.delete(emp);
					Reinitialiser();
					AffichageListe();
				} catch (CSVException e1) {
					e1.printStackTrace();
				}
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
				Enregistrement();
				ChargementConsultation();
			}
		}

		/**
		 * Actualisation des champs du formulaire TODO : Faire passer la
		 * r�f�rence de l'objet !
		 */
		if (e.getSource() instanceof JTable) {
			int ligneSelectionne = this.listePersonnel.getSelectedRow();
			this.nom.setText((String) listePersonnel.getValueAt(ligneSelectionne, 0));
			this.prenom.setText((String) listePersonnel.getValueAt(ligneSelectionne, 1));
			this.date.setText((String) listePersonnel.getValueAt(ligneSelectionne, 2));
			this.IDSelect = (int) listePersonnel.getValueAt(ligneSelectionne, 3);
			@SuppressWarnings("unchecked")
			TableModel dataModel = ListToJTable
					.competences((ArrayList<Competence>) listePersonnel.getValueAt(ligneSelectionne, 4)).getModel();
			this.competences.setModel(dataModel);
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
