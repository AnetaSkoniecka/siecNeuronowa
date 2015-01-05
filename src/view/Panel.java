package view;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

public class Panel extends ChartPanel {
	
	Chart parent;
	ChartPanel panel;
	Boolean isLeftClicked = false;
	double tempX = 0;
	double tempY1 = 0;
	double tempY2 = 0;
	
	public Panel(Chart parent, JFreeChart chart) {
		super(chart);
		
		this.parent = parent;
		this.panel = this;
		this.setPopupMenu(null);
		this.setMouseZoomable(false);
		addListener();	
	}
	
	private void addListener() {		
		this.addChartMouseListener(new ChartMouseListener() {
			
			@Override
			public void chartMouseMoved(ChartMouseEvent event) {}
			
			@Override
			public void chartMouseClicked(ChartMouseEvent event) {
				Point2D p = panel.translateScreenToJava2D(event.getTrigger().getPoint());
				Rectangle2D plotArea = panel.getScreenDataArea();
				XYPlot plot = (XYPlot) event.getChart().getPlot(); // your plot
				double chartX = plot.getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
				double chartY = plot.getRangeAxis().java2DToValue(p.getY(), plotArea, plot.getRangeAxisEdge());
				double x = Math.floor(chartX * 100) / 100;
				double y = Math.floor(chartY * 100) / 100;
				//System.out.println(x + " " + y);		
				
				if(event.getTrigger().getButton() == MouseEvent.BUTTON1) {
					if(!isLeftClicked) {
						isLeftClicked = true;
						tempX = x;
						parent.addY1(x, y);
					} 
				}
				if(event.getTrigger().getButton() == MouseEvent.BUTTON3) {
					if(isLeftClicked) {
						isLeftClicked = false;
						parent.addY2(tempX, y);
					}
				}
			}
		});
	}
}
