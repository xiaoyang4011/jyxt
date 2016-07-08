package crds.pub.util;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;


/**
 * @specification :jfreeChar 构建图表
 * @version : 1.0 
 * @author : liuxx
 * @date : Oct 23, 2008 4:51:05 PM
 * @email : liuxx.adam@gmail.com
 */
public class ChartBuilder {
	static Logger logger = Logger.getLogger(ChartBuilder.class);
	public JFreeChart chart;//图表结果

	private String title;//标题

	private String chartType;//类型

	private String infoX;//x轴说明

	private String infoY;//y轴说明

	@SuppressWarnings("unchecked")
	private List classifyMapList;//结果集集合,里面的每一个对象为一个结果集dataset.

	private String infoY2;//第二y轴说明
	private double infoY2Up;//第二y轴上限
	private double infoY2Down;//第二y轴下限

	
	/**
	 * @specification :构造函数，设置必须属性
	 * @param :String title, String chartType, String infoX,
			String infoY, List classifyMapList
	 * @exception :NAN
	 */
	@SuppressWarnings("unchecked")
	public ChartBuilder(String title, String chartType, String infoX,
			String infoY, List classifyMapList) {
		this.title = title;
		this.chartType = chartType;
		this.infoX = infoX;
		this.infoY = infoY;
		this.classifyMapList = classifyMapList;
	}

	public String getInfoY2() {
		return infoY2;
	}

	public void setInfoY2(String infoY2) {
		this.infoY2 = infoY2;
	}

	public double getInfoY2Up() {
		return infoY2Up;
	}

	public void setInfoY2Up(double infoY2Up) {
		this.infoY2Up = infoY2Up;
	}

	public double getInfoY2Down() {
		return infoY2Down;
	}

	public void setInfoY2Down(double infoY2Down) {
		this.infoY2Down = infoY2Down;
	}

	
	/**
	 * @specification :根据图表类型取得图表对象
	 * @param :NAN
	 * @return :JFreeChart 图表对象
	 * @exception :NAN
	 */
	@SuppressWarnings("unchecked")
	public JFreeChart getChart() {

		DefaultCategoryDataset dataSet;
		DefaultPieDataset pieSet;
		XYDataset pointSett;
		switch (Integer.parseInt(chartType)) {
		case 0: // 线图
			dataSet = classifyMapList!=null?this.buildDataForLine((Map) classifyMapList.get(0)):null;
			this.buildLine(dataSet);
			break;
		case 1: // 双纵轴线图
			dataSet = classifyMapList!=null?this.buildDataForLine((Map) classifyMapList.get(0)):null;
			DefaultCategoryDataset dataSet2 = this.buildDataForLine((Map) classifyMapList.get(1));
			this.buildLineOfTwoY(dataSet, dataSet2);
			break;
		case 2: // 柱图
			dataSet = classifyMapList!=null?this.buildDataForBarChart((Map) classifyMapList.get(0)):null;
			this.buildBarChart(dataSet);
			break;
		case 3: // 饼状图
			pieSet = classifyMapList!=null?this.buildDataForPieChart((Map) classifyMapList.get(0)):null;
			this.buildPieChart(pieSet);
			break;
		case 4: // 点状分布图
		pointSett = classifyMapList!=null?this.buildDataForPointChart((Map) classifyMapList.get(0)):null;
			this.buildXYPointChart(pointSett);
			break;
		}
		return this.chart;
	}

	//柱图
	private void buildBarChart(DefaultCategoryDataset aSedataSett) {
		this.chart = ChartFactory.createBarChart(title, // chart title
				infoX, // domain axis label
				infoY, // range axis label
				aSedataSett, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
				);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setNoDataMessage("没有数据显示!");
	}
	
	//饼状图
	private void buildPieChart(DefaultPieDataset   pieSett) {
		this.chart = ChartFactory.createPieChart(title, // chart title
				 pieSett, // data
				true, // include legend
				true, // tooltips
				true // urls
				);
	}
	
	/**
	 * @specification :构建类型为点状图的图表对象
	 * @param :XYDataset pointSett,数据结果集
	 * @return :void
	 * @exception :NAN
	 */
	private void buildXYPointChart(XYDataset pointSett) {
		
		this.chart = ChartFactory.createScatterPlot(title, // chart title
				infoX, // domain axis label
				infoY, // range axis label
				pointSett,
				PlotOrientation.VERTICAL,//orientation, 
				false,//legend,
				true,//tooltips, 
				true//urls
				);
	}
	
	/**
	 * @specification :构建类型为折线图的图表对象
	 * @param :DefaultCategoryDataset dataSet,数据结果集
	 * @return :void
	 * @exception :NAN
	 */
	private void buildLine(DefaultCategoryDataset dataSet) {
		// create the chart...
		this.chart = ChartFactory.createLineChart(title, // chart title
				infoX, // domain axis label
				infoY, // range axis label
				dataSet, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
				);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setNoDataMessage("没有数据显示!");
		// 设置 CategoryLabelPositions 文字的方位
//		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 0.1);

		//设置线的颜色顺序
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
				0.0f, Color.red);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
				0.0f, Color.blue);
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
				0.0f, Color.green);
		GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.black, 0.0f,
				0.0f, Color.black);
		GradientPaint gp4 = new GradientPaint(0.0f, 0.0f,
				new Color(255, 0, 255), 0.0f, 0.0f, new Color(255, 0, 255));
		GradientPaint gp5 = new GradientPaint(0.0f, 0.0f,
				new Color(0, 255, 255), 0.0f, 0.0f, new Color(0, 255, 255));
		GradientPaint gp6 = new GradientPaint(0.0f, 0.0f,
				new Color(206, 155, 0), 0.0f, 0.0f, new Color(206, 155, 0));
		GradientPaint gp7 = new GradientPaint(0.0f, 0.0f,
				new Color(102, 0, 51), 0.0f, 0.0f, new Color(102, 0, 51));
		GradientPaint gp8 = new GradientPaint(0.0f, 0.0f, new Color(0, 51, 0),
				0.0f, 0.0f, new Color(0, 51, 0));
		GradientPaint gp9 = new GradientPaint(0.0f, 0.0f,
				new Color(255, 255, 0), 0.0f, 0.0f, new Color(255, 255, 0));
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setSeriesPaint(3, gp3);
		renderer.setSeriesPaint(4, gp4);
		renderer.setSeriesPaint(5, gp5);
		renderer.setSeriesPaint(6, gp6);
		renderer.setSeriesPaint(7, gp7);
		renderer.setSeriesPaint(8, gp8);
		renderer.setSeriesPaint(9, gp9);		
		

		DecimalFormat decimalformat1 = new DecimalFormat("##.####");//数据点显示数据值的格式
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
                                          //设置数据项标签的生成器
        renderer.setBaseItemLabelsVisible(true);//基本项标签显示
        renderer.setBaseShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
        renderer.setBaseShapesVisible(true);//设置显示小图标

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
	}
	
	/**
	 * @specification :构建类型为双Y轴折线图的图表对象
	 * @param :DefaultCategoryDataset dataSet,数据结果集
	 * @return :void
	 * @exception :NAN
	 */
	private void buildLineOfTwoY(DefaultCategoryDataset dataSet,
			DefaultCategoryDataset dataSet2) {
		// create the chart...
		this.chart = ChartFactory.createLineChart(title, // chart title
				infoX, // domain axis label
				infoY, // range axis label
				dataSet, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
				);

		CategoryPlot plot = chart.getCategoryPlot();

		plot.setDataset(1, dataSet2);
		plot.mapDatasetToRangeAxis(1, 1);
		plot.setNoDataMessage("没有数据显示!");
		// 设置 CategoryLabelPositions 文字的方位
		//domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 0.1);
		

		ValueAxis axis1 = new NumberAxis(infoY);
		plot.setRangeAxis(1, axis1);

		ValueAxis axis2 = new NumberAxis(infoY2);
		axis2.setUpperBound(infoY2Up); // 右纵轴显示最大值
		axis2.setLowerBound(infoY2Down); // 右纵轴显示最小值

		// Sets a range axis and sends a PlotChangeEvent to all registered
		// listeners
		plot.setRangeAxis(1, axis2);
		// 绘制单元对象 LineAndShapeRenderer
		
		//设置线的颜色顺序
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
				0.0f, Color.red);
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
				0.0f, Color.blue);
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
				0.0f, Color.green);
		GradientPaint gp3 = new GradientPaint(0.0f, 0.0f, Color.black, 0.0f,
				0.0f, Color.black);
		GradientPaint gp4 = new GradientPaint(0.0f, 0.0f,
				new Color(255, 0, 255), 0.0f, 0.0f, new Color(255, 0, 255));
		GradientPaint gp5 = new GradientPaint(0.0f, 0.0f,
				new Color(0, 255, 255), 0.0f, 0.0f, new Color(0, 255, 255));
		GradientPaint gp6 = new GradientPaint(0.0f, 0.0f,
				new Color(206, 155, 0), 0.0f, 0.0f, new Color(206, 155, 0));
		GradientPaint gp7 = new GradientPaint(0.0f, 0.0f,
				new Color(102, 0, 51), 0.0f, 0.0f, new Color(102, 0, 51));
		GradientPaint gp8 = new GradientPaint(0.0f, 0.0f, new Color(0, 51, 0),
				0.0f, 0.0f, new Color(0, 51, 0));
		GradientPaint gp9 = new GradientPaint(0.0f, 0.0f,
				new Color(255, 255, 0), 0.0f, 0.0f, new Color(255, 255, 0));
		
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
		.getRenderer();
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);
		renderer.setSeriesPaint(3, gp3);
		renderer.setSeriesPaint(4, gp4);
		renderer.setSeriesPaint(5, gp5);
		renderer.setSeriesPaint(6, gp6);
		renderer.setSeriesPaint(7, gp7);
		renderer.setSeriesPaint(8, gp8);
		renderer.setSeriesPaint(9, gp9);

		DecimalFormat decimalformat1 = new DecimalFormat("##.####");//数据点显示数据值的格式
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
                                          //设置数据项标签的生成器
        renderer.setBaseItemLabelsVisible(true);//设置项标签显示
        renderer.setBaseShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
        renderer.setBaseShapesVisible(true);//设置显示小图标

      //设置对应第二Y轴的线的颜色顺序
		LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
		renderer2.setSeriesPaint(0, gp0);
		renderer2.setSeriesPaint(1, gp1);
		renderer2.setSeriesPaint(2, gp2);
		renderer2.setSeriesPaint(3, gp3);
		renderer2.setSeriesPaint(4, gp4);
		renderer2.setSeriesPaint(5, gp5);
		renderer2.setSeriesPaint(6, gp6);
		renderer2.setSeriesPaint(7, gp7);
		renderer2.setSeriesPaint(8, gp8);
		renderer2.setSeriesPaint(9, gp9);
		renderer2.setBaseShapesVisible(true);//设置数据点可见
		renderer2.setBaseLinesVisible(true);
		plot.setRenderer(1, renderer2);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
		
	}
	
	/**
	 * @specification :构建线图的结果集
	 * @param :Map<String,Map> fyMap,线图数据集合,key为分类,value为Map<String,String>,Map分别存放对应的x轴,y轴的数值
	 * @return :DefaultCategoryDataset 线图的结果集
	 * @exception :NAN
	 */
	@SuppressWarnings("unchecked")
	private DefaultCategoryDataset buildDataForLine(Map fyMap) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Set set = fyMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			String classify = (String) entry.getKey();//分类
			Map valueMap = (Map) entry.getValue();
			Set set2 = valueMap.entrySet();
			Iterator it2 = set2.iterator();
			while (it2.hasNext()) {
				Map.Entry entry2 = (Entry) it2.next();
				String x = (String) entry2.getKey();//x轴对应点
				String y = (String) entry2.getValue();//y轴对应点
				if(y == null){
					dataset.addValue(null, classify, x);
				}else{
					dataset.addValue(Double.parseDouble(y), classify, x);
				}
				
			}
		}
		return dataset;
	}
	
	//构建柱图的结果集
	@SuppressWarnings("unchecked")
	private DefaultCategoryDataset buildDataForBarChart(Map fyMap) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Set set = fyMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			String classify = (String) entry.getKey();//分类
			Map valueMap = (Map) entry.getValue();
			Set set2 = valueMap.entrySet();
			Iterator it2 = set2.iterator();
			while (it2.hasNext()) {
				Map.Entry entry2 = (Entry) it2.next();
				String x = (String) entry2.getKey();//x轴对应点
				String y = (String) entry2.getValue();//y轴对应点
				if(y == null){
					dataset.addValue(null, classify, x);
				}else{
					dataset.addValue(Double.parseDouble(y), classify, x);
				}
				
			}
		}
		return dataset;
	}
	
	//构建饼状图的结果集
	@SuppressWarnings("unchecked")
	private DefaultPieDataset buildDataForPieChart(Map fyMap) {

		  DefaultPieDataset dataset=new DefaultPieDataset();

		Set set = fyMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			Map valueMap = (Map) entry.getValue();
			Set set2 = valueMap.entrySet();
			Iterator it2 = set2.iterator();
			while (it2.hasNext()) {
				Map.Entry entry2 = (Entry) it2.next();
				String x = (String) entry2.getKey();//x对应的饼状图上显示的中文
				String y = (String) entry2.getValue();//y对应的值
				dataset.setValue(x, Double.valueOf(y));
			}
		}
		return dataset;
	}
	//构建点状图的结果集
	@SuppressWarnings("unchecked")
	private XYDataset buildDataForPointChart(Map fyMap) {

		  XYSeriesCollection xyCollection=new XYSeriesCollection();
	        XYSeries xyseries1=new XYSeries("");
		
		Set set = fyMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Entry) it.next();
			Map valueMap = (Map) entry.getValue();
			Set set2 = valueMap.entrySet();
			Iterator it2 = set2.iterator();
			while (it2.hasNext()) {
				Map.Entry entry2 = (Entry) it2.next();
				String x = (String) entry2.getKey();//x对应的饼状图上显示的中文
				String y = (String) entry2.getValue();//y对应的值
				  xyseries1.add(Double.valueOf(x), Double.valueOf(y));
				  xyCollection.addSeries(xyseries1);
			}
		}
		return xyCollection ;
	}
	
	/**
	 * @specification :将图片对象JFreeChart输出到流
	 * @param :OutputStream out,输出流
	 * @param :int width,图片宽度
	 * @param :int height,图片高度
	 * @return :void
	 * @exception :IOException
	 */
	public void write(OutputStream out, int width, int height)
			throws IOException {
		if (this.chart != null) {
			BufferedImage image = this.chart.createBufferedImage(width, height);
			try {
				//设定图片类型
				Jimi.putImage("image/jpeg", image, out);
			} catch (JimiException e) {
				logger.error("ChartBuilder.write():"+e.getMessage());
			}
		}
	}
	
	/**
	 * @specification :将图片对象JFreeChart输出到文件(高度问300,宽度为600)
	 * @param :String file,文件路径
	 * @return :void
	 * @exception :IOException
	 */
	public void write(String file) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			this.write(fos, 600, 300);
		} catch (Exception e) {
			logger.error("ChartBuilder.write():"+e.getMessage());
		}
		fos.close();
	}

}
