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

public class PanelAuto_update extends JPanel {
	private JTextField nr;
	private JTextField marca;
	private JTextField an;
	private JTextField rulaj;
	private JTextField pret;
	private JTextField penalizare;
	private JTextField model;
	private JTextField nrInmatriculareCautat;
	/**
	 * Create the panel.
	 */
	public PanelAuto_update() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date noi despre autoturisumul dorit a fi modificat din evidenta:");
		lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroducetiNumeleClientului.setBounds(108, 0, 732, 75);
		add(lblIntroducetiNumeleClientului);
		
		nr = new JTextField();
		nr.setColumns(10);
		nr.setBounds(188, 89, 186, 39);
		add(nr);
		
		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(188, 152, 186, 39);
		add(marca);
		
		an = new JTextField();
		an.setColumns(10);
		an.setBounds(188, 277, 186, 39);
		add(an);
		
		rulaj = new JTextField();
		rulaj.setColumns(10);
		rulaj.setBounds(188, 334, 186, 39);
		add(rulaj);
		
		pret = new JTextField();
		pret.setColumns(10);
		pret.setBounds(637, 89, 186, 39);
		add(pret);
		
		penalizare = new JTextField();
		penalizare.setColumns(10);
		penalizare.setBounds(637, 152, 186, 39);
		add(penalizare);
		
		model = new JTextField();
		model.setColumns(10);
		model.setBounds(188, 212, 186, 39);
		add(model);
		
		String[] disp={"T", "F"};
		JComboBox comboBoxD = new JComboBox(disp);
		comboBoxD.setBounds(637, 333, 186, 40);
		add(comboBoxD);
		
		String[] categ={"SUV", "Coupe","Cabrio","Berlina","HatchBack"};
		JComboBox comboBoxC = new JComboBox(categ);
		comboBoxC.setBounds(637, 211, 186, 40);
		add(comboBoxC);
		
		String[] loc={"Ploiesti", "Bucuresti","Arad","Oradea","Timisoara"};
		JComboBox comboBoxL = new JComboBox(loc);
		comboBoxL.setBounds(637, 276, 186, 40);
		add(comboBoxL);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategorie.setBounds(521, 210, 104, 34);
		add(lblCategorie);
		
		JLabel lblLocatieID = new JLabel("Locatie:");
		lblLocatieID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocatieID.setBounds(540, 274, 91, 39);
		add(lblLocatieID);
		
		JLabel lblNr = new JLabel("Nr Inmatriculare:");
		lblNr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNr.setBounds(24, 94, 152, 34);
		add(lblNr);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMarca.setBounds(99, 151, 77, 34);
		add(lblMarca);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblModel.setBounds(99, 217, 66, 34);
		add(lblModel);
		
		JLabel lblAn = new JLabel("An Fabricatie:");
		lblAn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAn.setBounds(37, 281, 139, 34);
		add(lblAn);
		
		JLabel lblRulaj = new JLabel("Rulaj:");
		lblRulaj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRulaj.setBounds(112, 333, 52, 34);
		add(lblRulaj);
		
		JLabel lblPret = new JLabel("Pret/zi:");
		lblPret.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPret.setBounds(534, 88, 91, 34);
		add(lblPret);
		
		JLabel lblPenalizare = new JLabel("Penalizare:");
		lblPenalizare.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPenalizare.setBounds(505, 151, 104, 34);
		add(lblPenalizare);
	
		JLabel lblDisponibilitate = new JLabel("Disponibilitate:");
		lblDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisponibilitate.setBounds(470, 333, 139, 34);
		add(lblDisponibilitate);
		
		JLabel label = new JLabel("Introduceti date despre autoturisumul dorit a fi modificat din evidenta:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(87, 453, 884, 39);
		add(label);
		
		JLabel lblNr2 = new JLabel("Numar Inmatriculare Cautat:");
		lblNr2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNr2.setBounds(146, 504, 275, 34);
		add(lblNr2);
		nrInmatriculareCautat = new JTextField();
		nrInmatriculareCautat.setBounds(470, 505, 186, 39);
		add(nrInmatriculareCautat);
		nrInmatriculareCautat.setColumns(10);
		
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
				String NrInmatrCautat=nrInmatriculareCautat.getText();
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					Statement s = con.createStatement();
					if(!Categorie.isEmpty())
					{
						String query="Update Autoturism Set CategorieID=(Select C.CategorieID from CategorieAutoturism C where C.Denumire=('" + Categorie +"')) Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s.execute(query);
					}
					con.close();
					Connection con1 = DriverManager.getConnection(connectionUrl); 
					Statement s1 = con1.createStatement();
					if(!Locatie.isEmpty())
					{
						String query="Update Autoturism Set LocatieID=(Select L.LocatieID from Locatie L where L.Oras=('" + Locatie +"')) Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s1.execute(query);
					}
					con1.close();
					Connection con2 = DriverManager.getConnection(connectionUrl); 
					Statement s2 = con2.createStatement();
					if(!Nr.isEmpty())
					{
						String query="Update Autoturism Set NrInmatriculare=('" + Nr +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s2.execute(query);
					}
					con2.close();
					Connection con3 = DriverManager.getConnection(connectionUrl); 
					Statement s3 = con3.createStatement();
					if(!Marca.isEmpty())
					{
						String query="Update Autoturism Set Marca=('" + Marca +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s3.execute(query);
					}
					con3.close();
					Connection con4 = DriverManager.getConnection(connectionUrl); 
					Statement s4 = con4.createStatement();
					if(!Model.isEmpty())
					{
						String query="Update Autoturism Set Model=('" + Model +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s4.execute(query);
					}
					con4.close();
					Connection con5 = DriverManager.getConnection(connectionUrl); 
					Statement s5 = con5.createStatement();
					if(!An.isEmpty())
					{
						String query="Update Autoturism Set AnFabricatie=('" + Integer.parseInt(An) +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s5.execute(query);
					}
					con5.close();
					Connection con6 = DriverManager.getConnection(connectionUrl); 
					Statement s6 = con6.createStatement();
					if(!Rulaj.isEmpty())
					{
						String query="Update Autoturism Set Rulaj=('" + Integer.parseInt(Rulaj) +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s6.execute(query);
					}
					con6.close();
					Connection con7 = DriverManager.getConnection(connectionUrl); 
					Statement s7 = con7.createStatement();
					if(!Pret.isEmpty())
					{
						String query="Update Autoturism Set Pret_per_zi=('" + Integer.parseInt(Pret) +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s7.execute(query);
					}
					con7.close();
					Connection con8 = DriverManager.getConnection(connectionUrl); 
					Statement s8 = con8.createStatement();
					if(!Penalizare.isEmpty())
					{
						String query="Update Autoturism Set Taxa_intarziere_returnare=('" + Integer.parseInt(Penalizare) +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s8.execute(query);
					}
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					con8.close();
					Connection con9 = DriverManager.getConnection(connectionUrl); 
					Statement s9 = con9.createStatement();
					if(!Disponibilitate.isEmpty())
					{
						String query="Update Autoturism Set Status_Disponibilitate=('" + Disponibilitate +"') Where NrInmatriculare=('" + NrInmatrCautat +"')";
						s9.execute(query);
					}
					con9.close();
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
					JOptionPane.showMessageDialog(null, "UnSuccessfully Updated!");
				}
			}
		});
		btnNewButton.setBounds(348, 407, 186, 31);
		add(btnNewButton);
		
	
		
	}

}
