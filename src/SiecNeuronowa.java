import java.awt.EventQueue;

import view.Chart;
import view.Window;


public class SiecNeuronowa {


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		final Chart chart = new Chart();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window(chart);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
