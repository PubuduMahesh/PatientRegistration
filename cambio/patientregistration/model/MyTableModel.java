package cambio.patientregistration.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

	private String [] columnNames;
	private java.util.List<Patient> tableData = new ArrayList<Patient>();
	
	public MyTableModel(ArrayList<Patient>data,String[] columnNames){
		this.columnNames = columnNames;
		this.tableData = data;
	}
	
	@Override
	public int getRowCount() {
		return tableData.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient pat = tableData.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return pat.getName();
		case 1:
			return pat.getAge();
		case 2:
			return pat.getAddress();
		case 3:
			return pat.getEmpStatus();
		case 4:
			return pat.getGender();
		case 5:
			return pat.getMonthOrYear();
		default:
			return null;
		}		
	}
	
	public Patient getValue(int rowIndex){
		Patient patient = tableData.get(rowIndex);
		return patient;
		
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
		Patient patient = tableData.get(rowIndex);
		if(columnIndex == 2)			
			patient.setAddress(value);				
		else if(columnIndex == 3)
			patient.setEmpStatus(value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
	public void updateTable(Patient patient){	
		tableData.add(patient);
		int row = tableData.size()-1;// New data added to the first row each time. 
		fireTableRowsInserted(row, row);//fire row inserted. 
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
		return columnIndex == 2 || columnIndex==3  ? true : false;
	}
}
