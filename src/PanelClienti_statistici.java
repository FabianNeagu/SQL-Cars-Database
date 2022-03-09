import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelClienti_statistici extends JPanel {
	private JTable table_1;
	private JTable table_2;
	/**
	 * Create the panel.
	 */
	public PanelClienti_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 89, 842, 105);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Clientii care au cheltuit pe inchirierea masinilor mai mult decat media:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 44, 863, 32);
		add(lblNewLabel);
		
		JLabel lblFisatiInOrdeine = new JLabel("Clientii care au inchiriat cel putin 2 masini care au un rulaj mai mare decat media:");
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
		model.addColumn("Nume");
		model.addColumn("Prenume");
		model.addColumn("Total_plate");
		model.addColumn("Marca");
		model.addColumn("Model");
		
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		DefaultTableModel model2=new DefaultTableModel();
		model2.addColumn("Nume");
		model2.addColumn("Prenume");
		model2.addColumn("An");
		
		try
		{
			Connection con = DriverManager.getConnection(connectionUrl); 
			String query="Select C.Nume, C.Prenume, F.Total_plata, A.Marca, A.Model\r\n" + 
					"From Client C inner join Inchiriere I on C.ClientID=I.ClientID\r\n" + 
					"			  inner join Factura F on F.FacturaID=I.FacturaID\r\n" + 
					"			  inner join Autoturism A on A.AutoturismID=I.AutoturismID\r\n" + 
					"Where F.Total_plata > ALL( Select AVG(F1.Total_plata) from Factura F1)";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] 
						{
						rs.getString("Nume"),
						rs.getString("Prenume"),
						rs.getString("Total_plata"),
						rs.getString("Marca"),
						rs.getString("Model"),
				});
			}
			rs.close();
			st.close();
			con.close();
			
			Connection con1 = DriverManager.getConnection(connectionUrl); 
			String query1="Select C.Nume, C.Prenume, Year(F.Data) as An\r\n" + 
					"From Client C inner join Inchiriere I on I.ClientID=C.ClientID\r\n" + 
					"			  inner join Autoturism A ON A.AutoturismID=I.AutoturismID\r\n" + 
					"			  inner join Factura F on F.FacturaID=I.FacturaID\r\n" + 
					"Where A.Rulaj > (Select AVG(A1.Rulaj) from Autoturism A1)\r\n" + 
					"Group By C.Nume,C.Prenume,Year(F.Data)\r\n" + 
					"Having count(A.AutoturismID)>=2";
			java.sql.Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(query1);
			while(rs1.next())
			{
				model2.addRow(new Object[] 
						{
						rs1.getString("Nume"),
						rs1.getString("Prenume"),
						rs1.getString("An"),
				});
			}
			rs1.close();
			st1.close();
			con1.close();
			
			
			table_2.setModel(model2);
			table_2.setAutoResizeMode(0);
			table_2.getColumnModel().getColumn(0).setPreferredWidth(80);
			table_2.getColumnModel().getColumn(1).setPreferredWidth(80);
			table_2.getColumnModel().getColumn(2).setPreferredWidth(110);
			
			table_1.setModel(model);
			table_1.setAutoResizeMode(0);
			table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
			table_1.getColumnModel().getColumn(4).setPreferredWidth(80);
			
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
	}

}
