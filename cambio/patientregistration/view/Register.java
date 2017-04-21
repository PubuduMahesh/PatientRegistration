package cambio.patientregistration.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import cambio.patientregistration.controller.*;
import cambio.patientregistration.model.Patient;
import uitl.*;;

public class Register extends JPanel{	
	private JLabel lName;
	private JLabel lTp;
	private JLabel lBirthday;
	private JLabel lGender;
	private JLabel lAddress;
	private JLabel lEmpStatus;
	private JButton bSave;
	private JButton bClear;
	private JTextField tName;
	private JTextField tTp;
	private JTextField tBirthday;
	private JButton bBirthday;
	private MyButtonGroup bgGender;
	private JTextArea tAddress;
	private JPanel panelRegContent;
	private JComboBox cEmpStatus;
	private JRadioButton male;
	private JRadioButton feMale;
	private PatientRegController pr;
	private Calculator ageCalc;

	public Register (PatientRegController pr){
		this.pr = pr;
	}



	public JPanel createRegisterPanel(){
		addLabel();
		addFields();
		addDatePicker();
		addSaveClearButton();
		configureLayoutManager();		

		/*Clear button action performed*/
		this.bClear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				clearField();
			}
		});

		//Birthday selection validation
		bBirthday.addActionListener(new ActionListener() {//call to action listener of the Date picker button.
			@Override
			public void actionPerformed(ActionEvent ae) {
				birthdayValidation();
			}
		});
		
		/*Disabling Save button until required field are satisfied.*/
		saveButtonDisable();
		return panelRegContent;
	}
	
	private void addLabel(){
		lName = new JLabel();
		lTp = new JLabel("Phone Number");
		lBirthday = new JLabel("Birthday");
		lGender = new JLabel("Gender");
		lAddress = new JLabel("address");
		lEmpStatus = new JLabel("Employment Status");
		
		lName.setText("<html>Name <font color='red'> *</font></html>");
		lBirthday.setText("<html>Birthday <font color='red'> *</font></html>");
		lGender.setText("<html>Gender <font color='red'> *</font></html>");
	}
	
	private void addFields(){		
		/*Add text field for the form*/
		this.tName = new JTextField(21);
		this.tTp = new JTextField(10);
		this.tBirthday = new JTextField(10);
		male = new JRadioButton("Male");
		male.setActionCommand("Male");

		feMale = new JRadioButton("Female");
		feMale.setActionCommand("Female");
		tAddress = new JTextArea(3,16);		
		cEmpStatus = new JComboBox();
	}
	
	private void addDatePicker(){
		//Date picker stuffs. 
		this.bBirthday = new JButton(":P");
	}
	
	private void addSaveClearButton(){
		//save clear button. 
		this.bSave = new JButton("Save");
		this.bClear = new JButton("Clear");
	}
	
	private void configureLayoutManager(){
		panelRegContent = new JPanel(new GridBagLayout());//create sub panel-Register details.
		GridBagConstraints regC = new GridBagConstraints();//GridBagConstraints for register panel.

		/*initialize initial coordinates for register panel.*/
		regC.weightx = 1.0;//distributed space for the register panel component. 
		regC.weighty = 1.0;
		regC.gridx = 0;//initialize the origin for register panel. 
		regC.gridy = 0;
		regC.anchor = GridBagConstraints.WEST;//align all components of register panel in to LEFT corner. 
		//add each component to the register panel. 
		panelRegContent.add(lName,regC);//name
		regC.gridy++;//increment the row value. 
		panelRegContent.add(lTp,regC);//phone number.
		regC.gridy++;
		panelRegContent.add(lBirthday,regC);//birthday.
		regC.gridy++;
		panelRegContent.add(lGender,regC);//gender.
		regC.gridy++;
		panelRegContent.add(lAddress,regC);//address.
		regC.gridy++;
		panelRegContent.add(lEmpStatus,regC);//employment status.
		regC.gridy++;

		regC.gridy = 0;
		regC.gridx = 1;//increment the column value. 
		panelRegContent.add(tName,regC);//name
		regC.gridy++;
		panelRegContent.add(tTp,regC);//phone number.
		regC.gridy++;
		panelRegContent.add(tBirthday,regC);//birthday.
		regC.gridy++;
		panelRegContent.add(male,regC);//gender, male.
		regC.gridx++;
		panelRegContent.add(feMale,regC);//gender, female.
		regC.gridx--;
		regC.gridy++;		
		panelRegContent.add(tAddress,regC);//address.
		//Going to add scroll bar when the size is exceeded. 
		//add scroll bar when they wanted. 
		JScrollPane jsp = new JScrollPane(tAddress,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelRegContent.add(jsp,regC);
		regC.gridy++;
		panelRegContent.add(cEmpStatus,regC);//Employ status
		//initialize the Combo box content. 
		String[] status = {"Part time","Retired","Student","Unemployed"};
		cEmpStatus.addItem(status[0]);;
		cEmpStatus.addItem(status[1]);
		cEmpStatus.addItem(status[2]);
		cEmpStatus.addItem(status[3]);

		//Date picker things. 
		regC.gridy = 2;
		regC.gridx = 2;
		panelRegContent.add(bBirthday,regC);

		//gender radio button grouping. 
		bgGender = new MyButtonGroup();
		bgGender.add(male);
		bgGender.add(feMale);

		//Save clear button.
		regC.gridy = 6;
		regC.gridx = 2;
		panelRegContent.add(bClear,regC);//clear button.
		regC.gridx = 3;
		panelRegContent.add(bSave,regC);//save button.
	}
	
	private void clearField() {
		String option[] = {"Yes", "No"};//JOption Pane, option lists
		/*JOption Pane stuffs*/
		int optionResult = JOptionPane.showOptionDialog(null, "Are you sure you want to clear?",
				"Patient Registration", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, option, option[1]);
		if (optionResult == 0) {
			this.setPatientFieldClear();
		} 		
	}

	private void birthdayValidation(){
		try {
			String selDate = new DatePicker(panelRegContent).setPickedDate();//get the selected date.
			Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(selDate);//convert the selected Date in to the "Date" type
			if (birthday.before(new Date())) {//check whether the selected date is grater than with respect to the current date. 
				tBirthday.setText(selDate);//set the date to the birthday text field.
			} else {//if validation is failed, warning message. 
				JOptionPane.showMessageDialog(null, "Birthday should be previous date", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ParseException ex) {
			Logger.getLogger(PatientRegController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void saveButtonDisable(){
		bSave.setEnabled(false);//At the very beginning, save button will be disabled. 
		tName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				enableSaveButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				enableSaveButton();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableSaveButton();
			}


		});
		tBirthday.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				enableSaveButton();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				enableSaveButton();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableSaveButton();
			}			
		});

		bgGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableSaveButton();
			}
		});

		bSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonActionPerformed();
			} 
		});	
		
		pr.registerRowClickListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Patient) {
					Patient patient = (Patient)e.getSource();
					setPatientField(patient);
				}

			}
		});

	}

	private void enableSaveButton() {	
		if (!tName.getText().isEmpty() && !tBirthday.getText().isEmpty() && (male.isSelected()|| feMale.isSelected())) {//if requirement are not fulfilled. 
			bSave.setEnabled(true);
		} else {
			bSave.setEnabled(false);
		}
	}

	private void saveButtonActionPerformed(){
		saveButtonValidation(this.getPatient());		
		this.setPatientFieldClear();	//clear the patient field. 	
	}
	
	private void saveButtonValidation(Patient patient){
		if(patient.getAddress().length() <10){//address length is less than 10, give an alert. 
			String[] option = {"Yes", "No"};//JOption Pane, option lists
			int optionResult = JOptionPane.showOptionDialog(null, "Are you sure you want to save? Address field contained less than"
					+ "10 characters.",
					"Patient Registration", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, option, option[1]);

			if(optionResult == 0){//if user accept to proceed. 
				ActionEvent e = new ActionEvent(patient, -1, "");
				pr.fireSaveActionPerformed(e);
			}
		}
		else{//no, constraints, save data in to JTable.
			ActionEvent e = new ActionEvent(patient, -1, "");
			pr.fireSaveActionPerformed(e);
		}
	}
	
	private void setPatientField(Patient patient){
		tName.setText(patient.getName());
		tAddress.setText(patient.getAddress());
		tTp.setText(patient.getTP());
		tBirthday.setText(patient.getBirthday());
		cEmpStatus.setSelectedItem(patient.getEmpStatus());
	}
	
	public Patient getPatient(){
		Patient patient;
		String name = tName.getText();
		String address = tAddress.getText();
		String tpNumber = tTp.getText();
		String birthday = tBirthday.getText();
		String empStatus = cEmpStatus.getSelectedItem().toString();
		String gender = bgGender.getSelection().getActionCommand();
		int [] age = Calculator.ageCalculator(birthday);
		patient = new Patient(name,String.valueOf(age[0]),address,empStatus,gender,String.valueOf(age[1]),tpNumber,birthday);

		return patient;
	}

	public void setPatientFieldClear(){
		tName.setText("");
		tAddress.setText("");
		tTp.setText("");
		tBirthday.setText("");
		bgGender.clearSelection();

	}

	

}
