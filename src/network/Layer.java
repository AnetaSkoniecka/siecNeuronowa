package network;

import java.util.ArrayList;
import java.util.List;

public class Layer {
	
	private Layer nextLayer;
	private Layer prevLayer;
	private LayerType type;
	private List<Neuron> neurons = new ArrayList<Neuron>();
	
	public Layer(int neuronAmount, LayerType type, int inputAmount) {
		this.nextLayer = null;
		this.prevLayer = null;
		this.type = type;
		neurons = new ArrayList<Neuron>();
		for(int i = 0; i < neuronAmount; ++i)
			neurons.add(new Neuron(this,inputAmount));
	}

	public void calculateNeurons(){
		for(Neuron x:neurons)
			x.countValue();
	}
	
	public void calculateDelta(ArrayList<Double> expectedValues) {
		if(type == LayerType.OUTPUT) {
			for(int i = 0 ; i < neurons.size() ; i++) {
				neurons.get(i).countDelta(expectedValues.get(i));
			}
		} else if(type == LayerType.HIDDEN) {
			for(int i = 0 ; i < neurons.size() ; i++) {
				neurons.get(i).countDelta(0.0);
			}
		}
	}
	
	public Double calculateAim(ArrayList<Double> expectedValues) {
		Double aim = new Double(0.0);
		for(int i = 0 ; i < expectedValues.size() ; ++i) {
			System.out.println("Neuron"+i+": " + neurons.get(i).getValue() );
			aim += (expectedValues.get(i) - neurons.get(i).getValue());
			aim *= aim;
		}
		aim /= 2.0;
		return aim;
	}
	
	public void updateWeights() {
		for(Neuron x:neurons)
			x.updateWeights();
	}
	
	public Layer getNextLayer() {
		return nextLayer;
	}

	public void setNextLayer(Layer nextLayer) {
		this.nextLayer = nextLayer;
	}

	public Layer getPrevLayer() {
		return prevLayer;
	}

	public void setPrevLayer(Layer prevLayer) {
		this.prevLayer = prevLayer;
	}

	public LayerType getType() {
		return type;
	}

	public void setType(LayerType type) {
		this.type = type;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}
	

}
