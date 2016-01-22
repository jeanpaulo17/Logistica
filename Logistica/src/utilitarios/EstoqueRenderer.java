package utilitarios;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

		     
	public class EstoqueRenderer extends DefaultTableCellRenderer  {

		public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus, 
	            int row, int column) {

	            Component c = super.getTableCellRendererComponent(table,
	                value, isSelected, hasFocus, row, column);

	            
	              	
	            for(int i=0 ; i < table.getRowCount(); i++){
	            	String st = (String) table.getValueAt(row, 6);  
	        		
	        		if(st == "Cancelado") {

						setBackground(Color.RED); 
						}
						else if(st =="Concluido"){
				 		setBackground(Color.GREEN);
						}
						else if(st == "Pendente"){
						setBackground(Color.YELLOW);
						}
		                  
		              return this;  
	            		         		
	    }
				return c;

	}
		
	}