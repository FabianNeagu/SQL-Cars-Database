import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PanelAuto_insert extends JPanel {
	private JTextField nr;
	private JTextField marca;
	private JTextField an;
	private JTextField rulaj;
	private JTextField pret;
	private JTextField penalizare;
	private JTextField model;
	/**
	 * Create the panel.
	 */
	public PanelAuto_insert() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date despre autoturisumul dorit a fi introdus in evidenta:");
		lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroducetiNumeleClientului.setBounds(123, 0, 669, 75);
		add(lblIntroducetiNumeleClientului);
		
		nr = new JTextField();
		nr.setColumns(10);
		nr.setBounds(176, 88, 186, 39);
		add(nr);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(176, 147, 186, 39);
		add(marca);
		
		an = new JTextField();
		an.setColumns(10);
		an.setBounds(176, 274, 186, 39);
		add(an);
		
		rulaj = new JTextField();
		rulaj.setColumns(10);
		rulaj.setBounds(176, 343, 186, 39);
		add(rulaj);
		
		pret = new JTextField();
		pret.setColumns(10);
		pret.setBounds(637, 89, 186, 39);
		add(pret);
		
		penalizare = new JTextField();
		penalizare.setColumns(10);
		penalizare.setBounds(637, 147, 186, 39);
		add(penalizare);
		
		model = new JTextField();
		model.setColumns(10);
		model.setBounds(176, 215, 186, 39);
		add(model);
		
		String[] categ={"SUV", "Coupe","Cabrio","Berlina","HatchBack"};
		JComboBox comboBoxC = new JComboBox(categ);
		comboBoxC.setBounds(637, 279, 186, 40);
		add(comboBoxC);
		
		String[] loc={"Ploiesti", "Bucuresti","Arad","Oradea","Timisoara"};
		JComboBox comboBoxL = new JComboBox(loc);
		comboBoxL.setBounds(637, 214, 186, 40);
		add(comboBoxL);
		
		String[] disp={"T", "F"};
		JComboBox comboBoxD = new JComboBox(disp);
		comboBoxD.setBounds(637, 342, 186, 40);
		add(comboBoxD);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategorie.setBounds(527, 279, 104, 34);
		add(lblCategorie);
		
		JLabel lblLocatieID = new JLabel("Locatie:");
		lblLocatieID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocatieID.setBounds(540, 212, 91, 39);
		add(lblLocatieID);
		
		JLabel lblNr = new JLabel("Nr Inmatriculare:");
		lblNr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNr.setBounds(22, 88, 152, 34);
		add(lblNr);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMarca.setBounds(98, 152, 77, 34);
		add(lblMarca);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblModel.setBounds(98, 220, 66, 34);
		add(lblModel);
		
		JLabel lblAn = new JLabel("An Fabricatie:");
		lblAn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAn.setBounds(33, 279, 139, 34);
		add(lblAn);
		
		JLabel lblRulaj = new JLabel("Rulaj:");
		lblRulaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRulaj.setBounds(107, 342, 57, 34);
		add(lblRulaj);
		
		JLabel lblPret = new JLabel("Pret/zi:");
		lblPret.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPret.setBounds(550, 88, 69, 34);
		add(lblPret);
		
		JLabel lblPenalizare = new JLabel("Penalizare:");
		lblPenalizare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPenalizare.setBounds(518, 146, 104, 34);
		add(lblPenalizare);
	
		JLabel lblDisponibilitate = new JLabel("Disponibilitate:");
		lblDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisponibilitate.setBounds(480, 342, 139, 34);
		add(lblDisponibilitate);
		

		
		JButton btnNewButton = new JButton("Executa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String Categorie=(String) comboBoxC.getSelectedItem();
				String Locatie=(String) comboBoxL.getSelectedItem();
				String Nr=nr.getText();
				String Marca=marca.getText();
				String Model=model.getText();
				String An=an.getText();
				String Rulaj=rulaj.getText();
				String Pret=pret.getText();
				String Penalizare=penalizare.getText();
				String Disponibilitate=(String) comboBoxD.getSelectedItem();
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
				
					String query="Insert into Autoturism Values((Select C.CategorieID from CategorieAutoturism C where C.Denumire=('" + Categorie +"')),(Select L.LocatieID from Locatie L where L.Oras=('" + Locatie +"')),('" + Nr +"'),('" + Marca +"'),('" + Model +"'),('" + An +"'),('" + Rulaj +"'),('" + Pret +"'),('" + Penalizare +"'),('" + Disponibilitate +"'))";
					Statement s = con.createStatement();
		            s.execute(query);
					JOptionPane.showMessageDialog(null, "Successfully inserted");
					con.close();
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
					JOptionPane.showMessageDialog(null, "UnSuccessfully inserted");
				}
			}
		});
		btnNewButton.setBounds(337, 462, 186, 31);
		add(btnNewButton);
		
		
		
	}

}
