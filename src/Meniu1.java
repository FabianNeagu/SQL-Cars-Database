import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class Meniu1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meniu1 window = new Meniu1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Meniu1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1111, 755);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnHome = new JMenu("Home ");
		mnHome.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnHome);
		
		JMenu mnInchiriere = new JMenu(" Inchiriere ");
		mnInchiriere.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnInchiriere);
		
		JMenuItem menuItem_10 = new JMenuItem("Afisare");
		menuItem_10.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnInchiriere.add(menuItem_10);
		
		JMenuItem menuItem_11 = new JMenuItem("Exit");
		menuItem_11.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnInchiriere.add(menuItem_11);
		
		JMenu mnClient = new JMenu("Client ");
		mnClient.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnClient);
		
		JMenuItem menuItem_8 = new JMenuItem("Afisare");
		menuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnClient.add(menuItem_8);
		
		JMenuItem menuItem_9 = new JMenuItem("Exit");
		menuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnClient.add(menuItem_9);
		
		JMenu mnAutoturism = new JMenu("Autoturism ");
		mnAutoturism.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnAutoturism);
		
		JMenuItem menuItem_6 = new JMenuItem("Afisare");
		menuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAutoturism.add(menuItem_6);
		
		JMenuItem menuItem_7 = new JMenuItem("Exit");
		menuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAutoturism.add(menuItem_7);
		
		JMenu mnCategorieAutoturism = new JMenu("Categorie Autoturism ");
		mnCategorieAutoturism.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnCategorieAutoturism);
		
		JMenuItem menuItem_4 = new JMenuItem("Afisare");
		menuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCategorieAutoturism.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Exit");
		menuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCategorieAutoturism.add(menuItem_5);
		
		JMenu mnFactura = new JMenu("Factura ");
		mnFactura.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnFactura);
		
		JMenuItem mntmAfisare = new JMenuItem("Afisare");
		mntmAfisare.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFactura.add(mntmAfisare);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnFactura.add(mntmExit);
		
		JMenu mnAsigurare = new JMenu("Asigurare ");
		mnAsigurare.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnAsigurare);
		
		JMenuItem menuItem = new JMenuItem("Afisare");
		menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAsigurare.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Exit");
		menuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnAsigurare.add(menuItem_1);
		
		JMenu mnLocatie = new JMenu("Locatie");
		mnLocatie.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnLocatie);
		
		JMenuItem menuItem_2 = new JMenuItem("Afisare");
		menuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnLocatie.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Exit");
		menuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnLocatie.add(menuItem_3);
	}

}