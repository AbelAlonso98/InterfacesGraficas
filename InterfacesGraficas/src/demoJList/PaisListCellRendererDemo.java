package demoJList;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class PaisListCellRendererDemo implements ListCellRenderer<PaisDemo>{
	
	private JPanel panel = new JPanel(new GridLayout(0, 1));
	private JLabel pais;
	private JLabel capital;
	private JLabel bandera;
	
	
	public PaisListCellRendererDemo() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		

		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(pais = new JLabel());
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(capital = new JLabel());
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 2;
		panel.add(bandera = new JLabel());
		panel.setOpaque(false);
		pais.setOpaque(false);
		capital.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends PaisDemo> list, PaisDemo value, int index, boolean isSelected,
			boolean cellHasFocus) {
		pais.setText(value.getNombre());
		capital.setText(value.getCapital());

		if(index%2 == 0) {
			pais.setForeground(Color.RED);
			capital.setForeground(Color.RED);
		}
		else {
			pais.setForeground(Color.BLUE);
			capital.setForeground(Color.BLUE);
		}
		
			
		return panel;
	}

}
