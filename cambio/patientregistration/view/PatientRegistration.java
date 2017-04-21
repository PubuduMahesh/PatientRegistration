package cambio.patientregistration.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import cambio.patientregistration.controller.PatientRegController;
import cambio.patientregistration.model.Patient;



public class PatientRegistration extends JFrame {
	private Bottom bottomPanel;
	private Register registerPanel;
	private Search searchPanel;
	private Table tablePanel;
	private JPanel panelRegContent;
	private JTable table;

	PatientRegController pr = new PatientRegController();
	public PatientRegistration(){
		initUI();		//Initialize the frame.
		createLayout();//add relevant component to the main frame.
	}

	private void initUI() {	
		setTitle("Patient Registration");
		setLocationRelativeTo(null);//Frame will be centered on the screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setResizable(false);
		setVisible(true);
	}

	@SuppressWarnings("empty-statement")
	private void createLayout(){
		GridBagConstraints mainC = new GridBagConstraints();//specifies constraints for components.

		//main panel
		final JPanel panelMain = new JPanel(new GridBagLayout());//create Main panel.;//create sub panel-Register details.

		getContentPane().add(panelMain);//add panel to the container.	

		mainC.weightx = 0.5;//distributed space for the component horizontally.
		mainC.weighty = 0.5;//distributed space for the component vertically .		
		mainC.gridy = 0;
		mainC.gridx = 0;
		mainC.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.

		//add register panel to main panel.
		registerPanel = new Register(pr);
		panelRegContent = registerPanel.createRegisterPanel();
		panelMain.add(panelRegContent,mainC);


		//add search panel to the main panel.
		mainC.gridy = 1;
		searchPanel = new Search(pr);
		JPanel panelSearch = searchPanel.createSearchPanel();
		panelMain.add(panelSearch,mainC);

		//add table panel to the main panel
		mainC.gridy = 2;//place the table in 3rd row.
		ArrayList<Patient> data = new ArrayList<Patient>();//data to store in table
		tablePanel = new Table(pr).createTablePanel(data);
		table = tablePanel.getTable();
		panelMain.add(tablePanel, mainC);//panelTable adding to the main panel.

		//bottom Panel.
		mainC.gridy = 3;//place the bottom panel in the 3rd row of main panel.
		bottomPanel = new Bottom();
		JPanel panelBottom = bottomPanel.createBottomPanel(); 
		panelMain.add(panelBottom,mainC);//add the panel bottom to the main JPanel.

	}	
}
