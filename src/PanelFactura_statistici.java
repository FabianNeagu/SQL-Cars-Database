import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelFactura_statistici extends JPanel {
	private JTable table_1;
	/**
	 * Create the panel.
	 */
	public PanelFactura_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 257, 391, 105);
		add(scrollPane);
		
		JLabel label = new JLabel("Platile efectuate cu cardul mai mari decat toate platile efectuate folosind cash: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(84, 158, 761, 54);
		add(label);
		
		
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
		model.addColumn("Total_plata");
		model.addColumn("Data");
		
		
		
		
		try
		{
			Connection con = DriverManager.getConnection(connectionUrl); 
			String query="Select C.Nume, C.Prenume, F.Total_Plata, YEAR(F.Data) as Ddata\r\n" + 
					"from Factura F inner join Inchiriere I on F.FacturaID=I.FacturaID\r\n" + 
					"				inner join Client C on C.ClientID=I.ClientID\r\n" + 
					"where F.Metoda_plata='Card' and F.Total_plata > ALL( Select Total_plata From Factura where Metoda_plata='Cash')";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] 
						{
						rs.getString("Nume"),
						rs.getString("Prenume"),
						rs.getString("Total_Plata"),
						rs.getString("Ddata"),
				});
			}
			rs.close();
			st.close();
			con.close();
			
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