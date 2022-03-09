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

public class PanelAuto_afisare extends JPanel {
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public PanelAuto_afisare() 
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
			public void actionPerformed(ActionEvent e) 
			{
				DefaultTableModel model=new DefaultTableModel();
				model.addColumn("Marca");
				model.addColumn("Model");
				model.addColumn("NrInmatriculare");
				model.addColumn("AnFabricatie");
				model.addColumn("Rulaj");
				model.addColumn("Pret_per_zi");
				model.addColumn("Taxa_intarziere_returnare");
				model.addColumn("Categorie");
				model.addColumn("Locatie");
				model.addColumn("Status_Disponibilitate");
				
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					String query="select  A.Marca, A.Model,A.NrInmatriculare, A.AnFabricatie, A.Rulaj, A.Pret_per_zi, "
							+ " A.Taxa_intarziere_returnare,  C.Denumire, L.Oras,A.Status_Disponibilitate "
							+ " from Autoturism A inner join Locatie L on A.LocatieID=L.LocatieID "
							+ "inner join CategorieAutoturism C on C.CategorieID=A.CategorieID ";
					java.sql.Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[] 
								{
								rs.getString("Marca"),
								rs.getString("Model"),
								rs.getString("NrInmatriculare"),
								rs.getString("AnFabricatie"),
								rs.getString("Rulaj"),
								rs.getString("Pret_per_zi"),
								rs.getString("Taxa_intarziere_returnare"),
								rs.getString("Denumire"),
								rs.getString("Oras"),
								rs.getString("Status_Disponibilitate"),
						});
					}
					rs.close();
					st.close();
					con.close();
					
					table_1.setModel(model);
					table_1.setAutoResizeMode(0);
					
					JButton btnRefresh = new JButton("Refresh");
					btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 20));
					btnRefresh.setBounds(385, 506, 144, 33);
					add(btnRefresh);
					
					table_1.getColumnModel().getColumn(0).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(2).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
					table_1.getColumnModel().getColumn(4).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(5).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(6).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(7).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(8).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(9).setPreferredWidth(150);
					
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
				}
			}
		});
	}
}
