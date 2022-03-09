import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelAsigurare_statistici extends JPanel {
	private JTable table_1;
	/**
	 * Create the panel.
	 */
	public PanelAsigurare_statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 257, 391, 105);
		add(scrollPane);
		
		JLabel label = new JLabel("Masinile fabricate dupa 2014 care au dispus de asigurare de tip Full si care sunt mai ieftine ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(33, 103, 834, 54);
		add(label);
		
		JLabel lblNewLabel = new JLabel("decat toate masinile care au dispus de asigurare de tip Semi:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(151, 157, 561, 54);
		add(lblNewLabel);
		
		
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
		
		
		
		
		try
		{
			Connection con = DriverManager.getConnection(connectionUrl); 
			String query="Select distinct A.Marca, A.Model, A.NrInmatriculare, A.Pret_per_zi\r\n" + 
					"From Autoturism A inner join Inchiriere I on A.AutoturismID=I.AutoturismID\r\n" + 
					"				  inner join Asigurare ASG on I.AsigurareID=ASG.AsigurareID\r\n" + 
					"Where A.AnFabricatie>2014 AND ASG.Tip='Full' AND A.Pret_per_zi < ALL (Select A1.Pret_per_zi from Autoturism A1 inner join Inchiriere I1 on A1.AutoturismID=I1.AutoturismID\r\n" + 
					"																  inner join Asigurare ASG1 on ASG1.AsigurareID=I1.AsigurareID\r\n" + 
					"						 											Where ASG1.Tip='Semi')\r\n" + 
					"Order by A.Pret_per_zi, A.Marca, A.Model, A.NrInmatriculare ASC";
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
