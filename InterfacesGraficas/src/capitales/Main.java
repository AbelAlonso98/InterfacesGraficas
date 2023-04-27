package capitales;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Map<String, ArrayList<Pais>> paisesContinente = new TreeMap<>();

	Main(String[] args) throws IOException {

		super("Capitales del mundo");
		
		// Cargo los datos de los paises en el mapa.
		cargarDatosPaises();
		DefaultListModel<Pais> model = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Creo el panel de pestañas, le asigno tamaño y hago que sea el contentPane de la app.
		JTabbedPane panelPestañas = new JTabbedPane(JTabbedPane.TOP);
		panelPestañas.setPreferredSize(new Dimension(700, 700));
		setContentPane(panelPestañas);

		// Creo el panel 1 que será donde este la lista por continentes
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		
		// Creo el panel 2 que sera el panel donde figure el test
		JPanel panel2 = new JPanel();
		
		// Añado los dos paneles al panel de pestañas asignandoles ya los iconos.
		panelPestañas.addTab(null, new ImageIcon(getClass().getResource("/capitales/ico/estudio.png")), panel1,
				"Estudio");
		panelPestañas.addTab(null, new ImageIcon(getClass().getResource("/capitales/ico/test.png")), panel2, "Test");
		
		
		// Creo el combobox con las opciones ya asignadas.
		String[] opciones = { "EUROPA", "ASIA", "AMÉRICA", "OCEANÍA", "ÁFRICA" };
		JComboBox<String> continentes = new JComboBox<>(opciones);
		// Creo el actionListener para que al cambiar de opcion, cambie la lista tambien.
		
//		panel1.setFondo(continentes.getSelectedItem().toString().toLowerCase());
		
		// Creo la lista
		JList<Pais> list = new JList<>();
		list.setOpaque(false);
		list.setCellRenderer(new PaisListCellRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		model.addAll(paisesContinente.get(continentes.getSelectedItem().toString()));
		list.setModel(model);
		list.setVisibleRowCount(10);
//		list.setPreferredSize(new Dimension(500, 500));
		
		// Creo el panel de scroll para meter la lista dentro.
		Lienzo listScroller = new Lienzo(list);


		// JScrollPane y JList transparentes
		listScroller.setOpaque(false);
		listScroller.getViewport().setOpaque(false);
		listScroller.setOpaque(false);

		ActionListener al1 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				model.addAll(paisesContinente.get(continentes.getSelectedItem().toString()));
				try {
					listScroller.setFondo(continentes.getSelectedItem().toString().toLowerCase());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		continentes.addActionListener(al1);
		
		// Añado el JComboBox y el JList al panel1
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		panel1.add(continentes, constraints);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel1.add(listScroller, constraints);

		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				new Main(args).setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	private static void cargarDatosPaises() {
		ArrayList<Pais> europa = new ArrayList<>();
		ArrayList<Pais> asia = new ArrayList<>();
		ArrayList<Pais> america = new ArrayList<>();
		ArrayList<Pais> oceania = new ArrayList<>();
		ArrayList<Pais> africa = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(Main.class.getResourceAsStream("/capitales/capitales.csv")))) {
			String linea;
			String[] datos;
			while ((linea = in.readLine()) != null) {

				datos = linea.split(";");

				if (datos[0].equals("ÁFRICA"))
					africa.add(new Pais(datos[2], datos[3], datos[1]));
				if (datos[0].equals("EUROPA"))
					europa.add(new Pais(datos[2], datos[3], datos[1]));
				if (datos[0].equals("ASIA"))
					asia.add(new Pais(datos[2], datos[3], datos[1]));
				if (datos[0].equals("AMÉRICA"))
					america.add(new Pais(datos[2], datos[3], datos[1]));
				if (datos[0].equals("OCEANÍA"))
					oceania.add(new Pais(datos[2], datos[3], datos[1]));

			}
			paisesContinente.put("ÁFRICA", africa);
			paisesContinente.put("EUROPA", europa);
			paisesContinente.put("ASIA", asia);
			paisesContinente.put("AMÉRICA", america);
			paisesContinente.put("OCEANÍA", oceania);

		} catch (Exception e) {
		}

	}
}
