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

	              	
	            for(int i=0 ; i < 165 ; i++){
	        		int	qtd = (Integer) table.getValueAt(i, 2);
	        		int	minimo = (Integer) table.getValueAt(i, 3);
	        		if(qtd < minimo){
	                c.setBackground(Color.PINK);}
	                else{
	                c.setBackground(Color.GREEN);
	                }

	        		}
	    				return c;
	            		         		
	    }

	}