package swing;

import javax.swing.SwingUtilities;

public class MyApp4 {


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Thread.currentThread().setName("Hilo de la cola de eventos");
			while(true);
		});
	}
}
