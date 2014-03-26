package geanology.gui;

import geanology.Person;
import geanology.packets.responses.SearchForPersonResponse;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	private final ArrayList<Person> people;

	private boolean DEBUG = false;

	/**
	 * Create the frame.
	 */
	public ResultsFrame(ArrayList<Person> _people) {
		this.people = _people;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));

		String[] columnNames = { "Person ID", "First Name", "Last Name",
				"Date of Birth", "Place of Birth" };

		Object[][] data = new Object[people.size()][5];
		for (int i = 0; i < data.length; i++) {
			Person person = people.get(i);
			data[i][0] = person.getPerson_ID();
			data[i][1] = person.getFirst_Name();
			data[i][2] = person.getLast_Name();
			data[i][3] = person.getDate_Of_Birth();
			data[i][4] = person.getPlace_Of_Birth();
		}

		table = new JTable(data, columnNames) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		contentPane.add(table, "cell 0 0,grow");
		table.setPreferredScrollableViewportSize(new Dimension(600, 80));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					// int column = target.getSelectedColumn();
					Person p = people.get(row);

					PersonFrame pF = new PersonFrame(p);
					pF.setVisible(true);
					// do some action
				}
			}
		});

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
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
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + model.getValueAt(i, j));
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
}
