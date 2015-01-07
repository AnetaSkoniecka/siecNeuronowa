package network;

import java.util.Random;

public class Neuron {
	
	private Layer lay;
	private double[] weights;
	private double stimulation;
	private double activation;
	private double delta = 0.0;
	private double learningRatio = 0.5;
	private Random random;
	private double bias = 1.0;

	public Neuron(Layer lay, int inputs) {
		random = new Random();
		this.lay = lay;
		weights = new double[inputs+1];
		for (int i = 0; i < inputs+1; ++i)
			if (lay.getType() == LayerType.INPUT)
				weights[i] = 1;
			else
				weights[i] = (random.nextDouble() - 0.5)*2.0;
		
	}

	public void countValue() {
		if (lay.getPrevLayer() == null){
			System.out.println("To nie powinno sie wyswietlic");
			return;
		}
		countStimulation();
		countActivation();
	}
	
	private void countStimulation() {
		double sum = 0;
		for (int i = 0; i < lay.getPrevLayer().getNeurons().size(); ++i){
			sum += lay.getPrevLayer().getNeurons().get(i).getValue()
					* weights[i];
//			if(lay.getType() == LayerType.HIDDEN)
//				System.out.println(weights[i]);
		}
		sum += bias * weights[weights.length-1];
//		System.out.print("old stimulation " + stimulation + " new ");
		stimulation = sum;if(lay.getType() == LayerType.OUTPUT)System.out.println("suma " + stimulation);
//		System.out.println(stimulation);
	}
	
	private void countActivation() {
//		System.out.print("old activation " + activation + " new ");
		if(lay.getType() == LayerType.OUTPUT)
			activation = Math.exp(stimulation) / (1 + Math.exp(stimulation));
		else
			activation = stimulation;
//		System.out.println(activation);
		//if(lay.getType() == LayerType.OUTPUT)System.out.println("activation " + activation);
	}
	
	public void updateWeights() {
		for(int i = 0 ; i < weights.length ; i++) {
			//System.out.print("Stara w " + weights[i]);
			weights[i] = weights[i] + learningRatio * delta * activation;
			//System.out.println(" nowa w " + weights[i] );
		}
	}
	
	public void countDelta(double expected) {
		if(lay.getType() == LayerType.OUTPUT){
			//System.out.println("Expected " + expected + " activation " + activation + " oldDelta "  + delta);
			delta = expected - activation;
			//System.out.println("nowa delta " + delta);
		}
		else if(lay.getType() == LayerType.HIDDEN) {
			double sum = 0;
			for (int i = 0; i < lay.getNextLayer().getNeurons().size(); ++i)
				sum += lay.getNextLayer().getNeurons().get(i).getDelta()
						* lay.getNextLayer().getNeurons().get(i).getWeights()[lay.getNeurons().indexOf(this)];
			sum += bias * weights[weights.length-1];
			delta = ( Math.exp(stimulation) / ((1+Math.exp(stimulation)) * (1+Math.exp(stimulation))))
					* ( sum );
		}
		else
			delta = 0.0;
		
	}

	public Layer getLay() {
		return lay;
	}

	public void setLay(Layer lay) {
		this.lay = lay;
	}

	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public double getValue() {
		return activation;
	}

	public void setValue(double value) {
		this.activation = value;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

}
