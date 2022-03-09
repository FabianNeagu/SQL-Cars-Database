import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelAuto_statistici extends JPanel {
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField an1;
	private JTextField an2;
	/**
	 * Create the panel.
	 */
	public PanelAuto_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 58, 842, 105);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Topul celor mai preferate marci auto inchiriate pana in anul:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 13, 646, 32);
		add(lblNewLabel);
		
		JLabel lblFisatiInOrdeine = new JLabel("Masinile fabricate dupa anul 2014 de tip SUV sau Coupe aflate in orasul:");
		lblFisatiInOrdeine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFisatiInOrdeine.setBounds(12, 176, 720, 32);
		add(lblFisatiInOrdeine);
		
		JLabel label = new JLabel("fabricata dupa anul: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(243, 365, 196, 32);
		add(label);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 221, 842, 105);
		add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JLabel lblMasinileCu = new JLabel("Masinile cu 2 locuri care sunt mai ieftine decat cea mai ieftina masina in 5 locuri");
		lblMasinileCu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMasinileCu.setBounds(12, 339, 863, 32);
		add(lblMasinileCu);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 406, 842, 96);
		add(scrollPane_2);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
		
			
		
		String[] cities={"Bucuresti", "Ploiesti", "Arad", "Timisoara", "Oradea"};
		JComboBox comboBox = new JComboBox(cities);
		comboBox.setBounds(670, 176, 135, 32);
		add(comboBox);
		
		an1 = new JTextField();
		an1.setBounds(565, 17, 135, 30);
		add(an1);
		an1.setColumns(10);
	
		
		an2 = new JTextField();
		an2.setColumns(10);
		an2.setBounds(446, 369, 135, 30);
		add(an2);
		
		JButton button = new JButton("Executa");
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(383, 515, 186, 31);
		add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					table_1 = new JTable();
					DefaultTableModel model=new DefaultTableModel();
					model.addColumn("Marca");
					model.addColumn("NrClienti");
					
					
					table_2 = new JTable();
					scrollPane_1.setViewportView(table_2);
					DefaultTableModel model2=new DefaultTableModel();
					model2.addColumn("Marca");
					model2.addColumn("Model");
					model2.addColumn("Categorie");
					model2.addColumn("NrInmatriculare");
					model2.addColumn("Oras");
					
					table_3 = new JTable();
					scrollPane_2.setViewportView(table_3);
					DefaultTableModel model3=new DefaultTableModel();
					model3.addColumn("Marca");
					model3.addColumn("Model");
					model3.addColumn("Pret_per_zi");
					scrollPane.setViewportView(table_1);
					
					String An1=an1.getText();
					String An2=an2.getText();
					int a1=0;
					int a2=0;
					if(!An1.isEmpty())
						a1=Integer.parseInt(An1); 
					if(!An2.isEmpty())
						a2=Integer.parseInt(An2); 
					String Oras=(String) comboBox.getSelectedItem();
					System.out.println(a1);
					Connection con = DriverManager.getConnection(connectionUrl); 
					String query="Select A.Marca, Count(I.InchiriereID) as NrClienti\r\n" + 
							"from Autoturism A inner join Inchiriere I on A.AutoturismID=I.AutoturismID\r\n" + 
							"				  inner join CategorieAutoturism CA on CA.CategorieID=A.CategorieID\r\n" + 
							"				  inner join Factura F on F.FacturaID=I.FacturaID\r\n" + 
							"Where YEAR(F.Data)<=('" + a1 +"')\r\n" + 
							"group by A.Marca\r\n" + 
							"order by Count(I.InchiriereID) DESC";
					java.sql.Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[] 
								{
								rs.getString("Marca"),
								rs.getString("NrClienti"),
						});
					}
					rs.close();
					st.close();
					con.close();
					
					Connection con1 = DriverManager.getConnection(connectionUrl); 
					String query1="Select A.Marca, A.Model, A.NrInmatriculare, CA.Denumire, L.Oras\r\n" + 
							"From Autoturism A inner join CategorieAutoturism CA on A.CategorieID=CA.CategorieID\r\n" + 
							"				  inner join Locatie L on A.LocatieID=L.LocatieID\r\n" + 
							"Where L.Oras=('" + Oras +"') AND (CA.Denumire='SUV' or CA.Denumire='Coupe') AND A.AnFabricatie>2014 \r\n" + 
							"Order by A.Pret_per_zi DESC";
					java.sql.Statement st1=con1.createStatement();
					ResultSet rs1=st1.executeQuery(query1);
					while(rs1.next())
					{
						model2.addRow(new Object[] 
								{
								rs1.getString("Marca"),
								rs1.getString("Model"),
								rs1.getString("NrInmatriculare"),
								rs1.getString("Denumire"),
								rs1.getString("Oras"),
						});
					}
					rs1.close();
					st1.close();
					con1.close();
					
					Connection con2 = DriverManager.getConnection(connectionUrl); 
					String query2="Select A.Marca, A.Model, A.Pret_per_zi\r\n" + 
							"From Autoturism A inner join CategorieAutoturism CA on CA.CategorieID=A.CategorieID\r\n" + 
							"where CA.Nr_Locuri=2  AND  A.Pret_per_zi > (Select MIN(A1.Pret_per_zi) from Autoturism A1 \r\n" + 
							"														               inner join CategorieAutoturism CA1 on CA1.CategorieID=A1.CategorieID\r\n" + 
							"																	   Where CA1.Nr_Locuri=5 and A1.AnFabricatie >= ('" + a2 +"'))";
					java.sql.Statement st2=con2.createStatement();
					ResultSet rs2=st2.executeQuery(query2);
					while(rs2.next())
					{
						model3.addRow(new Object[] 
								{
								rs2.getString("Marca"),
								rs2.getString("Model"),
								rs2.getString("Pret_per_zi"),
						});
					}
					rs2.close();
					st2.close();
					con2.close();
					
					table_1.setModel(model);
					table_1.setAutoResizeMode(0);
					table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
					
					table_2.setModel(model2);
					table_2.setAutoResizeMode(0);
					table_2.getColumnModel().getColumn(0).setPreferredWidth(80);
					table_2.getColumnModel().getColumn(1).setPreferredWidth(80);
					table_2.getColumnModel().getColumn(2).setPreferredWidth(110);
					table_2.getColumnModel().getColumn(3).setPreferredWidth(100);
					table_2.getColumnModel().getColumn(4).setPreferredWidth(100);
					
					table_3.setModel(model3);
					table_3.setAutoResizeMode(0);
					
					
					table_3.getColumnModel().getColumn(0).setPreferredWidth(80);
					table_3.getColumnModel().getColumn(1).setPreferredWidth(80);
					table_3.getColumnModel().getColumn(2).setPreferredWidth(80);
					
				}
				catch(Exception e)
				{
					System.out.println("error"+e);
				}
			}
		});
		
	}
}
