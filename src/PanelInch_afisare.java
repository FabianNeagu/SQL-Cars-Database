import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInch_afisare extends JPanel {
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public PanelInch_afisare() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 842, 461);
		add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
		
		

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRefresh.setBounds(385, 506, 144, 33);
		add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=new DefaultTableModel();
				model.addColumn("Nume_Client");
				model.addColumn("Prenume_Client");
				model.addColumn("Marca");
				model.addColumn("Model");
				model.addColumn("Data_inceput_inchiriere");
				model.addColumn("Data_terminare_inchiriere");
				model.addColumn("Locatie_colectare");
				model.addColumn("Locatie_predare");
				model.addColumn("NumeAsigurare");
				model.addColumn("Total_plata");
				model.addColumn("Observatii");
				
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					String query="select C.Nume as NumeC, C.Prenume, A.Marca, A.Model, I.Data_inceput_inchiriere, I.Data_terminare_inchiriere, "
							+ " L1.Oras as Numel1, L2.Oras as Numel2, Asg.Nume as Numeas, F.Total_plata, I.Observatii "
							+ "from Inchiriere I inner join Autoturism A on A.AutoturismID=I.AutoturismID "
							+ "inner join Factura F on F.FacturaID = I.FacturaID "
							+ "inner join Locatie L1 on I.ID_Locatie_colectare=L1.LocatieID "
							+ "inner join Locatie L2 on I.ID_Locatie_predare=L2.LocatieID "
							+ "inner join Asigurare Asg on Asg.AsigurareID=I.AsigurareID "
							+ "inner join Client C on C.ClientID=I.ClientID ";
					java.sql.Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[] 
								{rs.getString("NumeC"),
								rs.getString("Prenume"),
								rs.getString("Marca"),
								rs.getString("Model"),
								rs.getString("Data_inceput_inchiriere"),
								rs.getString("Data_terminare_inchiriere"),
								rs.getString("Numel1"),
								rs.getString("Numel2"),
								rs.getString("Numeas"),
								rs.getString("Total_plata"),
								rs.getString("Observatii"),
						});
					}
					rs.close();
					st.close();
					con.close();
					
					table_1.setModel(model);
					table_1.setAutoResizeMode(0);
					table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(4).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(5).setPreferredWidth(150);
					table_1.getColumnModel().getColumn(6).setPreferredWidth(150);
					table_1.getColumnModel().getColumn(7).setPreferredWidth(150);
					table_1.getColumnModel().getColumn(8).setPreferredWidth(150);
					table_1.getColumnModel().getColumn(9).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(10).setPreferredWidth(80);
					
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
				}
			}});
		
	
	}
}