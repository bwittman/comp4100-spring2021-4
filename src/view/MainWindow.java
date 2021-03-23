package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the main window named "Tensile Testing"
 */

public class MainWindow extends JFrame {

    private final int frameHeight;
    private final int frameWidth;

    private static final int VERTICAL_BUFFER = 10;
    private static final int HORIZONTAL_BUFFER = 10;

    private JButton startButton;
    private JButton graphReset;
    private JPanel valuePanel;
    private JPanel eastPanel;
    private JPanel optionsPanel;
    private JPanel graphPanel;
    private JMenuBar menuBar;
    private JMenuItem settings;
    private JMenuItem exit;
    private JMenuItem export;
    private JMenuItem input;
    private JFreeChart chart;
    private JMenuItem reset;
    private XYSeries series = new XYSeries("Stress-Strain Curve");

    public MainWindow(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameHeight = (int) (screenSize.getHeight() * .95);
        frameWidth = (int) (screenSize.getWidth() * .95);

        setTitle("Tensile Testing");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setResizable(true);

        setupGraphPanel();
        setupEastPanel();

        add(graphPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);

        setupMenuBar();
        this.setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * Creates graph panel
     */
    private void setupGraphPanel(){
        graphPanel = new JPanel();
        graphPanel.setSize(new Dimension((int) ( frameWidth * .75), frameHeight));
        graphPanel.setLayout(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(HORIZONTAL_BUFFER,VERTICAL_BUFFER,HORIZONTAL_BUFFER,VERTICAL_BUFFER), BorderFactory.createLineBorder(Color.BLACK, 1)));

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(null,"Strain","Stress",dataset, PlotOrientation.VERTICAL,true,true,true);

        ChartPanel chartPanel = new ChartPanel(chart);

        graphPanel.add(chartPanel,BorderLayout.CENTER);
        graphPanel.validate();
    }

    /*
     * Creates value panel and start/stop button
     */
    private void setupValuePanel(){
        valuePanel = new JPanel();
        valuePanel.add(new JLabel("Wombats"));
        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.Y_AXIS));
        valuePanel.add(Box.createVerticalGlue());

        valuePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Critical Values"),BorderFactory.createEmptyBorder(50,50,50,50)));
    }

    private void setupOptionPanel(){
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        startButton = new JButton("Start");
        graphReset = new JButton("Clear");
        graphReset.setEnabled(false);
        startButton.setFocusable(false);
        graphReset.setFocusable(false);

        optionsPanel.add(startButton);
        optionsPanel.add(Box.createVerticalStrut(VERTICAL_BUFFER));
        optionsPanel.add(graphReset);
    }

    private void setupEastPanel(){
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBorder(BorderFactory.createEmptyBorder(HORIZONTAL_BUFFER,0,HORIZONTAL_BUFFER,VERTICAL_BUFFER));

        setupValuePanel();
        setupOptionPanel();

        eastPanel.add(valuePanel);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(optionsPanel);
    }

    /*
     * Creates menu bar
     */
    private void setupMenuBar(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        settings = new JMenuItem("Settings");
        export = new JMenuItem("Export");
        exit = new JMenuItem("Exit");
        file.add(export);
        file.add(settings);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        input = new JMenuItem("Input Measurements");
        reset = new JMenuItem("Reset");
        edit.add(input);
        edit.add(reset);

        menuBar.add(file);
        menuBar.add(edit);
    }
    
    public int getFrameHeight() {
        return frameHeight;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getClearButton(){ return graphReset;}

    public JPanel getValuePanel() {
        return valuePanel;
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public JFreeChart getChart(){
        return chart;
    }

    public JMenuItem getSettings() {
        return settings;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JMenuItem getExport() {
        return export;
    }

    public JMenuItem getReset() { return reset; }

    public JMenuItem getInput() {
        return input;
    }

    public XYSeries getSeries() {
        return series;
    }

}
