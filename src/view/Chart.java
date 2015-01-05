 package view;


import java.util.ArrayList;
import java.util.LinkedList;

import network.Network;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart {

	private Network network;
	private Double tempY1;
	private JFreeChart chart;
	private Panel panel;
	private XYSeries series1;
	private XYSeries series2;
	private XYSeries seriesY1;
	private XYSeries seriesY2;
	
	public Chart(Network network) {
		this.network = network;
		network.setChart(this);
		tempY1 = new Double(0);
		
		series1 = new XYSeries("function 1");
		series2 = new XYSeries("function 2");
		seriesY1 = new XYSeries("Y1");
		seriesY2 = new XYSeries("Y2");
		
		final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        collection.addSeries(seriesY1);
        collection.addSeries(seriesY2);
        
        chart = ChartFactory.createXYLineChart("siec neuronowa", "", "", collection);
        panel = new Panel(this, chart);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getDomainAxis().setRange(0.0, 1.0);
        plot.getRangeAxis().setRange(0.0, 1.0);
        
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		renderer.setSeriesShapesVisible(2, true);
		renderer.setSeriesShapesVisible(3, true);
		renderer.setSeriesLinesVisible(2, false);
		renderer.setSeriesLinesVisible(3, false);

		setLines(network.getCharts());
	}
	
	public Panel getPanel() {
		return panel;
	}
	
	/**
	 * Czycci obecny bufor punktów i laduje nowe dla obydwoch lini.
	 * @param points1
	 * @param points2
	 */
	public void setLines(LinkedList<Point2D> points1, LinkedList<Point2D> points2) {
		series1.clear();
		series2.clear();
		for(int i = 0 ; i < points1.size() ; i++) {
			series1.add(points1.get(i).x, points1.get(i).y);	
		}
		for(int i = 0 ; i < points2.size() ; i++) {
			series2.add(points2.get(i).x, points2.get(i).y);	
		}
		
		
	}
	
	/**
	 * Czysci obecny bufor puntkow i laduje nowe dla obydwoch lini.
	 * @param points
	 */
	public void setLines(ArrayList<Double[]> points) {
		series1.clear();
		series2.clear();
		System.out.println("////////////////////////");
		for(int i = 0 ; i < points.size(); i++) {
			series1.add(points.get(i)[0], points.get(i)[1]);System.out.println("x " + points.get(i)[0] + " y1 " + points.get(i)[1]);
			series2.add(points.get(i)[0], points.get(i)[2]);System.out.println("x " + points.get(i)[0] + " y2 " + points.get(i)[2]);
		}
	}
	
	public void addY1(double x, double y) {
		seriesY1.add(x, y);
		tempY1 = y;
	}
	
	public void addY2(double x, double y) {
		seriesY2.add(x, y);
		network.add(x, tempY1, y);
	}
	
	/**
	 * Funkcja dodajaca jakies punkty dla testow
	 */
	private void setPoints1() {
//		LinkedList<Point2D> a1 = new LinkedList<Point2D>();
//		a1.add(new Point2D(10, 12353));
//		a1.add(new Point2D(20, 13353));
//		a1.add(new Point2D(30, 14353));
//		a1.add(new Point2D(40, 12353));
//		a1.add(new Point2D(50, 11353));
//		a1.add(new Point2D(60, 10353));
//		LinkedList<Point2D> a2 = new LinkedList<Point2D>();
//		a2.add(new Point2D(10, 12843));
//		a2.add(new Point2D(20, 13843));
//		a2.add(new Point2D(30, 14843));
//		a2.add(new Point2D(40, 12843));
//		a2.add(new Point2D(50, 11843));
//		a2.add(new Point2D(60, 10843));
//		setLines(a1, a2);
//		setLines(a1, a2);	
		
		series1.add(0.1, 0.2);
        series1.add(0.2, 0.2);
//        series1.add(30.0, 14525.3);
//        series1.add(40.0, 13984.3);
//        series1.add(50.0, 12999.4);
//        series1.add(60.0, 14274.3);
//        series1.add(70.0, 15943.5);
//        series1.add(80.0, 14845.3);
//        series1.add(90.0, 14645.4);
//        series1.add(100.0, 16234.6);
//        series1.add(110.0, 17232.3);
//        series1.add(120.0, 14232.2);
//        series1.add(130.0, 13102.2);
//        series1.add(140.0, 14230.2);
//        series1.add(150.0, 11235.2);
        
        series2.add(0.3, 0.3);
        series2.add(0.45, 0.55);
//        series2.add(30.0, 17000.3);
//        series2.add(40.0, 15000.3);
//        series2.add(50.0, 14000.4);
//        series2.add(60.0, 12000.3);
//        series2.add(70.0, 11000.5);
//        series2.add(80.0, 12000.3);
//        series2.add(90.0, 13000.4);
//        series2.add(100.0, 12000.6);
//        series2.add(110.0, 13000.3);
//        series2.add(120.0, 17000.2);
//        series2.add(130.0, 18000.2);
//        series2.add(140.0, 16000.2);
//        series2.add(150.0, 17000.2);
	}
	

}
