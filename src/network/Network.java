package network;

import java.util.ArrayList;
import java.util.Random;

import view.Chart;

public class Network {
	
	private Layer inputLayer;
	private Layer hiddenLayer;
	private Layer outputLayer;
	private ArrayList data;
	private Chart chart;
	private Boolean trainMode;
	private Random random;

	public Network() {
		random = new Random();
		this.inputLayer = new Layer(1, LayerType.INPUT,1);
		this.hiddenLayer = new Layer(20, LayerType.HIDDEN,1);
		this.outputLayer = new Layer(2, LayerType.OUTPUT,20);
		inputLayer.setNextLayer(hiddenLayer);
		hiddenLayer.setNextLayer(outputLayer);
		hiddenLayer.setPrevLayer(inputLayer);
		outputLayer.setPrevLayer(hiddenLayer);
		data = new ArrayList<Double[]>();
		trainMode = new Boolean(false);
	}
	
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	
	public void setMode(Boolean bool) {
		trainMode = bool;
	}
	
	public Boolean getMode() {
		return trainMode;
	}
	
	public void add(Double x, Double y1, Double y2) {
		Double[] values = new Double[3];
		values[0] = x;
		values[1] = y1;
		values[2] = y2;
			//System.out.println("Oczytano: " + x + " " + y1 + " " + y2);
			ArrayList<Double> inputValues = new ArrayList<Double>();
			inputValues.add(x);
			ArrayList<Double> outputValues = calculate(inputValues);
			//System.out.println("wyliczono: " + outputValues.get(0) + " " + outputValues.get(1));
		data.add(values);
		train(x, y1, y2);
		chart.setLines(getCharts());
	}
	
	public ArrayList<Double[]> getCharts() {
		ArrayList<Double[]> results = new ArrayList<Double[]>();
		Double[] result;
		for(Double i = 0.0 ; i <= 1 ; i += 0.001) {
			ArrayList<Double> inputValues = new ArrayList<Double>();
			inputValues.add(i);
			ArrayList<Double> outputValues = calculate(inputValues);
			result = new Double[3];
			result[0] = i;
			result[1] = outputValues.get(0);
			result[2] = outputValues.get(1);
			results.add(result);
		}
		
		return results;
	}
	
	private ArrayList<Double> calculate(ArrayList<Double> inputValues) {
		inputLayer.getNeurons().get(0).setValue(inputValues.get(0));		
		hiddenLayer.calculateNeurons();
		outputLayer.calculateNeurons();
		ArrayList<Double> output = new ArrayList<Double>();
		output.add(outputLayer.getNeurons().get(0).getValue());
		output.add(outputLayer.getNeurons().get(1).getValue());
		return output;
	}
	
	private void train(Double x, Double y1, Double y2) {
		Double aim = 1.0;
		while(aim > 0.0002) {
			Double[] values = (Double[])data.get(random.nextInt(data.size()));
			ArrayList<Double> inputValues = new ArrayList<Double>();
			inputValues.add(values[0]);
			ArrayList<Double> outputValues = calculate(inputValues);
			ArrayList<Double> expectedValues = new ArrayList<Double>();
			expectedValues.add(values[1]);
			expectedValues.add(values[2]);
			outputLayer.calculateDelta(expectedValues);
			hiddenLayer.calculateDelta(expectedValues);
			outputLayer.updateWeights();
			hiddenLayer.updateWeights();
			aim = outputLayer.calculateAim(expectedValues);
		}
		
	}
	

}
