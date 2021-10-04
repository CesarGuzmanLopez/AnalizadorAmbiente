package LauCesar.AnalizadorAmbiente.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Graficar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graficar frame = new Graficar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Graficar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	
		
        XYSeriesCollection data = new XYSeriesCollection();
    	SensorEstatico tempe = new SensorEstatico("temperatura");
        data.addSeries(tempe);	
		JFreeChart chart = ChartFactory.createXYStepChart(  "temperatura","x","y",data,PlotOrientation.VERTICAL,true, true, false);
	

		ChartPanel panel = new ChartPanel(chart);
		
		//JFreeChart a2 = ChartFactory.createLineChart(  "","x","y",temperatura,PlotOrientation.VERTICAL,true, true, false);
	
		panel.setBackground(Color.WHITE);
		panel.setBounds(53, 100, 503, 307);
		
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempe.reset();
			}
		});
		btnNewButton.setBounds(29, 26, 89, 23);
		contentPane.add(btnNewButton);
		
		setVisible(true);
	}
}

class SensorEstatico extends XYSeries {

	public SensorEstatico(Comparable key) {
		super(key);
		   add(0, 1);
	       add(1, 5);
	       add(2, 5);
	       add(3, 5);
	       add(4, 41);
	       add(5, 22);
	       add(6, 151);
	       add(7, 40);
	       add(8, 151);
	}
	public void reset(){
		System.out.println(getMinX());
		this.remove(this.getMinX());
        Random random = new Random();
		this.add(this.getMaxX()+1,random.nextInt(100));
	}
}


class UpdatingRandomCategoryDataset extends AbstractDataset implements CategoryDataset{
    private int columnCount;
    private int rowCount;
    private int maxValue;
    private String[] columnKeys;
    private String[] rowKeys;
    private double[][] values;
    
    UpdatingRandomCategoryDataset(int rowCount, int columnCount, int maxValue){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.rowKeys = new String[rowCount];
        for(int r = 0; r < rowCount; r++){
            rowKeys[r] = "Row "  + r;
        }
        this.columnKeys = new String[columnCount];
        for(int c = 0; c < columnCount; c++){
            columnKeys[c] = "Column "  + c;
        }
        values = new double[rowCount][columnCount];
        this.maxValue = maxValue;
        update();
    }
    public void update(){
        Random random = new Random();
        for(int c = 0; c < columnCount; c++){
            for(int r = 0; r < rowCount; r++){
                double value = random.nextDouble() * maxValue;
                values[r][c] = value;
            }
        }
        fireDatasetChanged();
    }
    public int getColumnCount(){
        return this.columnCount;
    }
    public int getRowCount(){
        return this.rowCount;
    }
    public Number getValue(int row, int column){
        return new Double(values[row][column]);
    }
    public int getColumnIndex(Comparable key){
        int result = -1;
        for(int i = 0; i < this.columnCount; i++){
            if(this.columnKeys[i].equals(key)){
                result = i;
                break;
            }
        }
        return result;
    }
    public Comparable getColumnKey(int index){
        return columnKeys[index];
    }
    public List getColumnKeys(){
        return Arrays.asList(this.columnKeys);
    }
    public int getRowIndex(Comparable key){
        int result = -1;
        for(int i = 0; i < this.rowCount; i++){
            if(this.rowKeys[i].equals(key)){
                result = i;
                break;
            }
        }
        return result;
    }
    public Comparable getRowKey(int index){
        return rowKeys[index];
    }
    public List getRowKeys(){
        return Arrays.asList(this.rowKeys);
    }
    public Number getValue(Comparable rowKey, Comparable columnKey){
        Number result = null;
        int rowIndex = getRowIndex(rowKey);
        int columnIndex = getColumnIndex(columnKey);
        if(rowIndex > -1 && columnIndex > -1){
            result = new Double(values[rowIndex][columnIndex]);
        }
        return result;
    }
}