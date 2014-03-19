package geanology.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;

public class ResultsFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2978255820270824608L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsFrame frame = new ResultsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private boolean DEBUG = false;
	
	/**
	 * Method to add entry to database.
	 */
	public void addPerson(){
				
	}

	/**
	 * Create the frame.
	 */
	public ResultsFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		String[] columnNames = {"Person ID",
                "First Name",
                "Last Name",
                "Date of Birth",
                "Place of Birth"};
		Object[][] data = {
		{"116", "Bryony",
		"Holst", "13/05/1993", "London, UK"},
		{"245", "Zoe",
		"Paterson", new Integer(3), new Boolean(true)},
		{"354", "Sumire",
		"Tsukatani", new Integer(2), new Boolean(false)},
		{"578", "Sam",
		"Hockley", new Integer(20), new Boolean(true)},
		{"947", "Lenka",
		"Mudrova", new Integer(10), new Boolean(false)}
		};
		
		
		table = new JTable(data, columnNames);
		contentPane.add(table, "cell 0 0,grow");
		table.setPreferredScrollableViewportSize(new Dimension(600, 80));
	    table.setFillsViewportHeight(true);
	    table.setAutoCreateRowSorter(true);
	    
	    
	    if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
        
        setTitle("Search results");
        pack();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
