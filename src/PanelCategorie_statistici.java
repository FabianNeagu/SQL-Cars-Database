import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelCategorie_statistici extends JPanel {
	private JTable table_1;
	/**
	 * Create the panel.
	 */
	public PanelCategorie_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 257, 391, 105);
		add(scrollPane);
		
		JLabel label = new JLabel("Categoriile cu cele mai multe masini disponibile: ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(233, 167, 761, 54);
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
		model.addColumn("Denumire");
		model.addColumn("Nr_locuri");
		model.addColumn("Nr_masini_disp");
		
		
		
		
		try
		{
			Connection con = DriverManager.getConnection(connectionUrl); 
			String query="Select CA.Denumire, CA.Nr_Locuri, count(A.AutoturismID) as Nr_masini_disp\r\n" + 
					"from CategorieAutoturism CA inner join Autoturism A on CA.CategorieID=A.CategorieID\r\n" + 
					"group by CA.Denumire, CA.Nr_Locuri\r\n" + 
					"order by count(A.AutoturismID) DESC";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] 
						{
						rs.getString("Denumire"),
						rs.getString("Nr_Locuri"),
						rs.getString("Nr_masini_disp"),
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
			
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
	}

}