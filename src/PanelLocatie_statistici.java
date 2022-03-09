import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelLocatie_statistici extends JPanel {
	private JTable table_1;
	private JTable table_2;
	/**
	 * Create the panel.
	 */
	public PanelLocatie_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 89, 842, 105);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Ordinea crescatoare dupa pret a vehiculelor care se afla in orasul Ploiesti si care au 5 locuri:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 33, 863, 32);
		add(lblNewLabel);
		
		JLabel lblFisatiInOrdeine = new JLabel("Locatiile de inchiriere in care au fost inchiriate cele mai multe masini incepand cu anul 2013:");
		lblFisatiInOrdeine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFisatiInOrdeine.setBounds(22, 218, 863, 32);
		add(lblFisatiInOrdeine);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 277, 842, 105);
		add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
		
		
		DefaultTableModel model=new DefaultTableModel();
		model.addColumn("Marca");
		model.addColumn("Model");
		model.addColumn("NrInmatriculare");
		model.addColumn("Pret_per_zi");
		
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		DefaultTableModel model2=new DefaultTableModel();
		model2.addColumn("Oras");
		model2.addColumn("NrClienti");
		
		try
		{
			Connection con = DriverManager.getConnection(connectionUrl); 
			String query="Select A.Marca, A.Model, A.NrInmatriculare, A.Pret_per_zi\r\n" + 
					"From Autoturism A inner join Locatie L on A.LocatieID=L.LocatieID\r\n" + 
					"				  inner join CategorieAutoturism CA on A.CategorieID=CA.CategorieID\r\n" + 
					"Where CA.Nr_Locuri=5\r\n" + 
					"Order By A.Pret_per_zi ASC";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] 
						{
						rs.getString("Marca"),
						rs.getString("Model"),
						rs.getString("NrInmatriculare"),
						rs.getString("Pret_per_zi"),
				});
			}
			rs.close();
			st.close();
			con.close();
			
			Connection con1 = DriverManager.getConnection(connectionUrl); 
			String query1="Select L.Oras, count(I.ClientID) as NrClienti\r\n" + 
					"From Locatie L inner join Autoturism A on A.LocatieID=L.LocatieID\r\n" + 
					"			   inner join Inchiriere I on I.AutoturismID=A.AutoturismID\r\n" + 
					"			   inner join Factura F on F.FacturaID=I.FacturaID\r\n" + 
					"Where Year(F.Data)>2013\r\n" + 
					"group by L.Oras\r\n" + 
					"order by count(I.ClientID) DESC";
			java.sql.Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(query1);
			while(rs1.next())
			{
				model2.addRow(new Object[] 
						{
						rs1.getString("Oras"),
						rs1.getString("NrClienti"),
				});
			}
			rs1.close();
			st1.close();
			con1.close();
			
			table_2.setModel(model2);
			table_2.setAutoResizeMode(0);
			table_2.getColumnModel().getColumn(0).setPreferredWidth(80);
			table_2.getColumnModel().getColumn(1).setPreferredWidth(80);
			
			table_1.setModel(model);
			table_1.setAutoResizeMode(0);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(110);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
			
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
	}

}
