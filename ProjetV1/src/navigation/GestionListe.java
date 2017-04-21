package navigation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import gui.ProgramFrame;
import models.Competence;

public class GestionListe implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Choices";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Choices";
	
/*	private JLabel sourceLabel;
	private JList sourceList;
	private SortedListModel sourceListModel;
	private JList destList;
	private SortedListModel destListModel;
	private JLabel destLabel;
	private JButton addButton;
	private JButton removeButton;
*/
	
	private JLabel ElementPossLabel;
	private JLabel ElementNonPossLabel;
	JTable	ElementPoss;
	JTable	ElementNonPoss;
	private JButton addButton;
	private JButton removeButton;

	public GestionListe(JTable Poss, JTable nonPoss) {
		this.ElementPoss = Poss;
		this.ElementNonPoss = nonPoss;
		this.ElementNonPoss.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.ElementNonPoss.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}
	
	public void displayGUI(){
		
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.setTitle("Skill Expert");
		frame.setResizable(false);
		frame.setLocation(5, 5);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
            	ProgramFrame.frame.setEnabled(true);
            	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        }
	    });
		
        JPanel content = new JPanel();
		content.setBorder(BorderFactory.createEtchedBorder());
		content.setLayout(new GridBagLayout());
		
		ElementPossLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);

		content.add(ElementPossLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
		content.add(new JScrollPane(ElementPoss), new GridBagConstraints(0, 1, 1, 5, .5, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));

		addButton = new JButton(ADD_BUTTON_LABEL);
		content.add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));

		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		content.add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,new Insets(0, 5, 0, 5), 0, 0));

		ElementNonPossLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
		content.add(ElementNonPossLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,EMPTY_INSETS, 0, 0));
		content.add(new JScrollPane(ElementNonPoss), new GridBagConstraints(2, 1, 1, 5, .5, 1.0, GridBagConstraints.CENTER,GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
		
		
		frame.getContentPane().add(content);
		frame.setVisible(true);
        
        
        
        
        
        
        
		/*JPanel content = new JPanel();
		content.setBorder(BorderFactory.createEtchedBorder());
		content.setLayout(new GridBagLayout());
		sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
		sourceListModel = new SortedListModel();
		sourceList = new JList(sourceListModel);
		content.add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));
		content.add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));

		addButton = new JButton(ADD_BUTTON_LABEL);
		content.add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));

		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		content.add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 5, 0, 5), 0, 0));

		destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
		destListModel = new SortedListModel();
		destList = new JList(destListModel);
		content.add(destLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));
		content.add(new JScrollPane(destList), new GridBagConstraints(2, 1, 1, 5, .5, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
		frame.getContentPane().add(content);
		frame.setVisible(true);*/
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*if (e.getSource().equals(addButton)) {
			Object selected[] = sourceList.getSelectedValues();
			addDestinationElements(selected);
			clearSourceSelected();
		}

		if (e.getSource().equals(removeButton)) {
			Object selected[] = destList.getSelectedValues();
			addSourceElements(selected);
			clearDestinationSelected();
		}*/
	}
	/*

	public void addDestinationElements(Object newValue[]) {
		fillListModel(destListModel, newValue);
	}

	private void clearSourceSelected() {
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	private void clearDestinationSelected() {
		Object selected[] = destList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			destListModel.removeElement(selected[i]);
		}
		destList.getSelectionModel().clearSelection();
	}

	public void addSourceElements(Object newValue[]) {
		fillListModel(sourceListModel, newValue);
	}

	private void fillListModel(SortedListModel model, Object newValues[]) {
		model.addAll(newValues);
	}*/

}
