package cambio.patientregistration.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cambio.patientregistration.controller.*;
import cambio.patientregistration.model.SearchPatient;

public class Search extends JPanel{
	private JLabel lSearchName;
	private JLabel lSearchYear;
	private JTextField tSearchName;
	private JTextField tSearchYear;
	private JRadioButton cSearchMale;
	private JRadioButton cSearchFemale;
	private JButton bSearch;
	private MyButtonGroup bgSearch;	
	private PatientRegController pr;
	private JPanel panelSearch; 
	public Search (PatientRegController pr){
		this.pr = pr;
	}

	public JPanel createSearchPanel(){		
		//search bar. 
		addFields();
		panelSearch = new JPanel(new GridBagLayout());
		configureLayoutManager();		

		bSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				SearchPatient spt = getSearchPatient();
				ActionEvent eSearch = new ActionEvent(spt, -1, "");
				pr.fireSearchActionPerformed(eSearch);
				setSearchFieldClear();
			}
		});	

		return panelSearch;
	}
	
	private void addFields(){
		lSearchName = new JLabel ("Name");
		tSearchName = new JTextField(8);
		lSearchYear = new JLabel ("Birth year");
		tSearchYear = new JTextField(8);
		cSearchMale = new JRadioButton("Male");
		cSearchFemale = new JRadioButton("Female");
		bSearch = new JButton("Search");
	}

	private void configureLayoutManager(){
		JLabel ls1 = new JLabel(" ");//these two labels are used to add space between component.
		JLabel ls2 = new JLabel("   ");
		GridBagConstraints searchC = new GridBagConstraints();//specifies constraints for components.
		searchC.gridx = 0;
		searchC.gridy = 0;
		searchC.anchor = GridBagConstraints.WEST;//align labels in to left hand side.
		panelSearch.add(lSearchName,searchC);//Name label.		
		searchC.gridx++;
		panelSearch.add(ls1,searchC);
		searchC.gridx++;
		panelSearch.add(tSearchName,searchC);//Name text field.
		searchC.gridx++;
		panelSearch.add(ls2,searchC);
		searchC.gridx++;
		panelSearch.add(lSearchYear,searchC);//Birth year label.		
		searchC.gridx++;
		panelSearch.add(ls1,searchC);
		searchC.gridx++;
		panelSearch.add(tSearchYear,searchC);//Birth year text field.
		searchC.gridx++;

		bgSearch = new MyButtonGroup();
		bgSearch.add(cSearchMale);
		bgSearch.add(cSearchFemale);
		panelSearch.add(cSearchMale);//Male radio button.
		searchC.gridx++;
		panelSearch.add(cSearchFemale);//Female radio button.
		searchC.gridx++;
		panelSearch.add(bSearch);//Search button. 
		searchC.gridx++;
	}
	private SearchPatient getSearchPatient(){
		String name = tSearchName.getText();
		String age = tSearchYear.getText();
		String gender = null;
		if(cSearchMale.isSelected()){
			gender = "Male";
		}
		else{
			gender = "Female";
		}

		return new SearchPatient(name, age, gender);
	}

	private void setSearchFieldClear(){
		tSearchName.setText("");
		tSearchYear.setText("");
		bgSearch.clearSelection();
	}
}
