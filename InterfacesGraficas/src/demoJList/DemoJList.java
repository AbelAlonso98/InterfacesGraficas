package demoJList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


import demo2d.Lienzo;



public class DemoJList extends JFrame {
	private static final long serialVersionUID = 1L;

	DemoJList(String[] args) throws IOException {
		super("Demo JList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Lienzo lienzo = new Lienzo();
		setContentPane(lienzo);
		

		lienzo.setPreferredSize(new Dimension(500, 500));
		DefaultListModel<PaisDemo> model = new DefaultListModel<>();
		
		// Cargar datos de paises
		try(BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/capitales/capitales.csv"))))
		{
			String linea;
			String[] datos;
			while((linea = in.readLine()) != null) {
				datos=linea.split(";");
				model.addElement(new PaisDemo(datos[1], datos[2], datos[0]));
			}
			
		} catch (Exception e) {
		}
		

		JList<PaisDemo> list = new JList<>();
		list.setOpaque(false);
		list.setForeground(Color.WHITE);
		list.setCellRenderer(new PaisListCellRendererDemo());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setModel(model);
		list.setVisibleRowCount(10);
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(list);
		
		// JScrollPane y JList transparentes
		listScroller.setOpaque(false);
		listScroller.getViewport().setOpaque(false);
		listScroller.setOpaque(false);
		
		add(listScroller, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new DemoJList(args).setVisible(true);
			} catch (IOException e) {
			}
		});
	}
	
	static private class MyRenderer extends JLabel implements ListCellRenderer<String>{
		
		public MyRenderer() {
			setOpaque(false);
		}
		
		@Override
		public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(value);
			return this;
		}
	}
}
