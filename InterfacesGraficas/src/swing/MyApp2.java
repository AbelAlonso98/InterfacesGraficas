package swing;

import javax.swing.SwingUtilities;

public class MyApp2 {
	public void run() {
		// Invoked on the event dispatching thread.
		// Construct and show GUI.
		Thread.currentThread().setName("Hilo de la cola de eventos");
		while(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		});
	}
}
