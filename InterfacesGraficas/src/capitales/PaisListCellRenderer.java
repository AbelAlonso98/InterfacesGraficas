package capitales;

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

public class PaisListCellRenderer implements ListCellRenderer<Pais>{
	
	private JPanel panel = new JPanel();
	private JLabel pais;
	private JLabel capital;
	private JLabel bandera;
	
	
	public PaisListCellRenderer() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		

		constraints.gridx = 1;
		constraints.gridy = 0;
		panel.add(pais = new JLabel(), constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		panel.add(capital = new JLabel(), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 2;
		panel.add(bandera = new JLabel(), constraints);
		panel.setOpaque(false);
		pais.setOpaque(false);
		capital.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Pais> list, Pais value, int index, boolean isSelected,
			boolean cellHasFocus) {
		pais.setText(value.getNombre());
		capital.setText(value.getCapital());
		bandera.setIcon(new ImageIcon(getClass().getResource("/capitales/img/banderas/" + value.getCodigo() + ".png")));
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
