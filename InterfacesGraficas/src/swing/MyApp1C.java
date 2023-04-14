package swing;

import javax.swing.SwingUtilities;

public class MyApp1C {

	public void run() {
		// Invoked on the event dispatching thread.
		// Construct and show GUI.
		Thread.currentThread().setName("Hilo de la cola de eventos");
		while(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MyApp1C()::run);
	}

}
