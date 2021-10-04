package LauCesar.AnalizadorAmbiente.views;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.xy.DefaultXYDataset;

public class Graficar {
    public static void main(String[] args) {
        UpdatingRandomCategoryDataset set = new UpdatingRandomCategoryDataset(5, 5, 100);
        JFreeChart chart = ChartFactory.createBarChart(
            "Dynamic Data","x","y",
            set,PlotOrientation.VERTICAL,true, true, false);
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new ChartPanel(chart));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        for (int i = 1; i < 200; i++){
            set.update();
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException e){
            }       
        }
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