package cambio.patientregistration.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cambio.patientregistration.controller.PatientRegController;
import cambio.patientregistration.model.MyTableModel;
import cambio.patientregistration.model.Patient;
import cambio.patientregistration.model.SearchPatient;



public class Table extends JPanel{
	PatientRegController pr = new PatientRegController();
	private JTable table;

	public Table (PatientRegController pr){
		this.pr = pr;
	}

	private void showSeachFilter(final SearchPatient spt){
		final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		final RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>()
		{
			// include method returns true to show the row and false to not show it
			@Override
			public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry)
			{
				boolean checked;
				if(!(spt.getName()).equals("")){
					int modelRow = entry.getIdentifier(); //row index
					checked = (entry.getModel().getValueAt(modelRow, 0)).equals(spt.getName());
				}
				if(spt.getGender()!= null){
					int modelRow = entry.getIdentifier(); //row index
					checked = (entry.getModel().getValueAt(modelRow, 4)).equals(spt.getGender());
				}
				if(!spt.getAge().equals("")){
					int modelRow = entry.getIdentifier(); //row index
					checked = (entry.getModel().getValueAt(modelRow, 1)).equals(spt.getAge());
				}	
				else{
					checked = true;
				}
				return checked;

			}

		};
		rowSorter.setRowFilter(filter);
		table.setRowSorter(rowSorter);
		
		
	}

	private Table createTable(ArrayList<Patient> data){
		final String[] header = {"Name","Age","Address","Emp.Status","Gender"};
		table = new JTable(new MyTableModel(data,header));
		JScrollPane scrollPane = new JScrollPane();
		table.setPreferredScrollableViewportSize(new Dimension(480, 100));
		table.setFillsViewportHeight(true);
		empStatusColumn(table, table.getColumnModel().getColumn(3));
		table.setDefaultRenderer(Object.class, new HighlightRenderer());
		scrollPane.setViewportView(table);
		this.setLayout(new GridBagLayout());
		GridBagConstraints tablC = new GridBagConstraints();
		tablC.anchor = GridBagConstraints.NORTHWEST;//align content of main panel in to left top corner.
		this.add(scrollPane,tablC);
		return this;
	}

	private void empStatusColumn(JTable table, TableColumn sportColumn) {
		//Set up the editor for the sport cells.
		JComboBox comboBox = new JComboBox();
		String[] status = {"Part time","Retired","Student","Unemployed"};
		comboBox.addItem(status[0]);;
		comboBox.addItem(status[1]);
		comboBox.addItem(status[2]);
		comboBox.addItem(status[3]);
		
		sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

		//Set up tool tips for the sport cells.
		DefaultTableCellRenderer renderer =	new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		sportColumn.setCellRenderer(renderer);
	}	

	private void mouseClickAction(){
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e){
				if(e.getClickCount() == 1){
					final JTable target = (JTable)e.getSource();
					final int row = target.getSelectedRow();
					Patient patient = ((MyTableModel)target.getModel()).getValue(row);
					ActionEvent eClick = new ActionEvent(patient, -1, "");
					pr.fireRowClickActionPerformed(eClick);
				}
			}
		});
	}
	
	private void saveActionPerformed(){
		pr.registerSaveListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof Patient) {
					updateTable((Patient)e.getSource());	
				}

			}
		});		
	}

	private void updateTable(Patient patient){
		MyTableModel tbModel = (MyTableModel)table.getModel();
		tbModel.updateTable(patient);
	}
	
	public Table createTablePanel(ArrayList<Patient> data){
		createTable(data);
		mouseClickAction();
		saveActionPerformed();	

		pr.registerSearchListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof SearchPatient) {
					SearchPatient spt = (SearchPatient)e.getSource();
					showSeachFilter(spt);	
				}

			}
		});
		return this;
	}	

	public JTable getTable(){
		return this.table;
	}
}