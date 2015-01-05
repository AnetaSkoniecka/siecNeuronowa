import java.awt.EventQueue;

import network.Network;
import view.Chart;
import view.Window;


public class SiecNeuronowa {


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Network network = new Network();
		final Chart chart = new Chart(network);
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
