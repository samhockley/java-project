package geanology.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WarningFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8519371621931567692L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarningFrame frame = new WarningFrame();
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
	public WarningFrame() {
		super("Delete User");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 317, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to proceed?");
		lblAreYouSure.setBounds(36, 91, 248, 15);
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAreYouSure);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				setVisible(false);															//need to add method to make sure action isn't carried out
			}
		});
		btnCancel.setBounds(52, 131, 92, 25);
		contentPane.add(btnCancel);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.setBounds(169, 131, 92, 25);
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblWarningThisMay = new JLabel("Warning: this user will be deleted.");
		lblWarningThisMay.setBounds(36, 64, 289, 15);
		contentPane.add(lblWarningThisMay);
	}
}
