package pizzas;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;


	Main(String[] args) {
		super("PizzaDAM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		JPanel tamaño = new JPanel();
		tamaño.setLayout(new GridLayout(0, 1));
		tamaño.setBorder(BorderFactory.createTitledBorder("Tamaño"));
		JRadioButton peq = new JRadioButton("Pequeña");
		peq.setMnemonic(KeyEvent.VK_P);
		JRadioButton med = new JRadioButton("Mediana");
		med.setMnemonic(KeyEvent.VK_M);
		JRadioButton fam = new JRadioButton("Familiar");
		fam.setMnemonic(KeyEvent.VK_F);
		ButtonGroup botonesTamaño = new ButtonGroup();
		botonesTamaño.add(peq);
		botonesTamaño.add(med);
		botonesTamaño.add(fam);
		tamaño.add(peq);
		tamaño.add(med);
		tamaño.add(fam);

		JPanel ingredientes = new JPanel();
		ArrayList<JCheckBox> botonesIngredientes = new ArrayList<>();
		ingredientes.setLayout(new GridLayout(2, 3));
		ingredientes.setBorder(BorderFactory.createTitledBorder("Ingredientes"));
		JCheckBox pepperoni = new JCheckBox("Pepperoni");
		pepperoni.setMnemonic(KeyEvent.VK_O);
		JCheckBox champiñones = new JCheckBox("Champiñones");
		champiñones.setMnemonic(KeyEvent.VK_C);
		JCheckBox anchoas = new JCheckBox("Anchoas");
		anchoas.setMnemonic(KeyEvent.VK_A);
		JCheckBox cebolla = new JCheckBox("Cebolla");
		cebolla.setMnemonic(KeyEvent.VK_B);
		JCheckBox piña = new JCheckBox("Piña");
		piña.setMnemonic(KeyEvent.VK_I);
		JCheckBox pimientos = new JCheckBox("Pimientos");
		pimientos.setMnemonic(KeyEvent.VK_T);
		botonesIngredientes.add(pepperoni);
		botonesIngredientes.add(champiñones);
		botonesIngredientes.add(anchoas);
		botonesIngredientes.add(cebolla);
		botonesIngredientes.add(piña);
		botonesIngredientes.add(pimientos);
		ingredientes.add(pepperoni);
		ingredientes.add(champiñones);
		ingredientes.add(anchoas);
		ingredientes.add(cebolla);
		ingredientes.add(piña);
		ingredientes.add(pimientos);

		JPanel masa = new JPanel();
		masa.setLayout(new GridLayout(2, 1));
		masa.setBorder(BorderFactory.createTitledBorder("Masa"));
		JRadioButton fina = new JRadioButton("Fina");
		fina.setMnemonic(KeyEvent.VK_N);
		JRadioButton gruesa = new JRadioButton("Gruesa");
		gruesa.setMnemonic(KeyEvent.VK_G);
		ButtonGroup botonesMasa = new ButtonGroup();
		botonesMasa.add(fina);
		botonesMasa.add(gruesa);
		masa.add(fina);
		masa.add(gruesa);

		JPanel tipoPedido = new JPanel();
		tipoPedido.setLayout(new GridLayout(2, 1));
		JRadioButton local = new JRadioButton("Local");
		local.setMnemonic(KeyEvent.VK_L);
		JRadioButton recoger = new JRadioButton("Recoger");
		recoger.setMnemonic(KeyEvent.VK_R);
		ButtonGroup botonesTipo = new ButtonGroup();
		botonesTipo.add(local);
		botonesTipo.add(recoger);
		tipoPedido.add(local);
		tipoPedido.add(recoger);

		JTextArea pedido = new JTextArea(5, 0);
		pedido.setEditable(false);
		pedido.setBorder(BorderFactory.createTitledBorder("Pedido"));

		JButton realizarPedido = new JButton("REALIZAR PEDIDO");
		realizarPedido.addActionListener(e -> {
			boolean algunaTamaño = false;
			boolean algunIngrediente = false;
			boolean algunaMasa = false;
			boolean algunTipoPedido = false;

			StringBuilder stringPedido = new StringBuilder();
			stringPedido.append("Tamaño: ");
			if (peq.isSelected()) {
				stringPedido.append("pequeña");
				algunaTamaño = true;
			}
			if (med.isSelected()) {
				stringPedido.append("mediana");
				algunaTamaño = true;
			}
			if (fam.isSelected()) {
				stringPedido.append("familiar");
				algunaTamaño = true;
			}
			stringPedido.append("\n");

			stringPedido.append("Masa: ");
			if (fina.isSelected()) {
				stringPedido.append("fina");
				algunaMasa = true;
			}
			if (gruesa.isSelected()) {
				stringPedido.append("gruesa");
				algunaMasa = true;
			}
			stringPedido.append("\n");

			stringPedido.append("Ingredientes: ");
			if (pepperoni.isSelected()) {
				stringPedido.append("pepperoni, ");
				algunIngrediente = true;
			}
			if (champiñones.isSelected()) {
				stringPedido.append("champiñones, ");
				algunIngrediente = true;
			}
			if (anchoas.isSelected()) {
				stringPedido.append("anchoas, ");
				algunIngrediente = true;
			}
			if (cebolla.isSelected()) {
				stringPedido.append("cebolla, ");
				algunIngrediente = true;
			}
			if (piña.isSelected()) {
				stringPedido.append("piña, ");
				algunIngrediente = true;
			}
			if (pimientos.isSelected()) {
				stringPedido.append("pimientos, ");
				algunIngrediente = true;
			}
			if (stringPedido.charAt(stringPedido.length() - 2) == ',') {
				stringPedido.deleteCharAt(stringPedido.length() - 1);
				stringPedido.deleteCharAt(stringPedido.length() - 1);
			}
			stringPedido.append("\n");

			stringPedido.append("Tipo de pedido: ");
			if (local.isSelected()) {
				stringPedido.append("local");
				algunTipoPedido = true;
			}
			if (recoger.isSelected()) {
				stringPedido.append("recoger");
				algunTipoPedido = true;
			}
			if (algunaTamaño && algunIngrediente && algunaMasa && algunTipoPedido) {
				pedido.setText(stringPedido.toString());
				botonesTamaño.clearSelection();
				botonesTipo.clearSelection();
				botonesMasa.clearSelection();
				botonesIngredientes.stream().forEach(b -> b.setSelected(false));
			} else if (!algunaTamaño) {
				JOptionPane.showMessageDialog(this, "No ha seleccionado ningún tamaño.", "Pedido",
						JOptionPane.ERROR_MESSAGE);

			} else if (!algunIngrediente) {
				JOptionPane.showMessageDialog(this, "No ha seleccionado ningún ingrediente.", "Pedido",
						JOptionPane.ERROR_MESSAGE);

			} else if (!algunaMasa) {
				JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna masa.", "Pedido",
						JOptionPane.ERROR_MESSAGE);

			} else if (!algunTipoPedido) {
				JOptionPane.showMessageDialog(this, "Seleccione local o recoger.", "Pedido", JOptionPane.ERROR_MESSAGE);

			}

		});

		constraints.insets = new Insets(10,10,10,10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPane.add(tamaño, constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		contentPane.add(ingredientes, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(masa, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		contentPane.add(tipoPedido, constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;
		contentPane.add(realizarPedido, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.BOTH;
		contentPane.add(pedido, constraints);


		pack();
		setLocationRelativeTo(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> new Main(args).setVisible(true));
	}



}
