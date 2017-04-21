package cambio.patientregistration.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HighlightRenderer extends DefaultTableCellRenderer{

	@Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

	        String monthOrYear = (String)table.getModel().getValueAt(row, 5);
	        
	        if (Integer.parseInt(monthOrYear) == 2) {
	            setBackground(Color.RED);
	        }else if(Integer.parseInt(monthOrYear) == 3){
	        	 setBackground(Color.GREEN);
	        } else {
	            setBackground(table.getBackground());
	            setForeground(table.getForeground());
	        }       
	        return this;
	    }  
}
