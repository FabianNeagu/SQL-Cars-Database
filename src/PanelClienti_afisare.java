import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelClienti_afisare extends JPanel {
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public PanelClienti_afisare() 
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
				model.addColumn("Nume");
				model.addColumn("Prenume");
				model.addColumn("CNP");
				model.addColumn("Judet");
				model.addColumn("Oras");
				model.addColumn("Strada");
				model.addColumn("Numar");
				model.addColumn("Telefon");
				model.addColumn("Email");
				
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					String query="select * from Client";
					java.sql.Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[] 
								{
										rs.getString("Nume"),
										rs.getString("Prenume"),
										rs.getString("CNP"),
										rs.getString("Judet"),
										rs.getString("Oras"),
										rs.getString("Strada"),
										rs.getString("Numar"),
										rs.getString("Telefon"),
										rs.getString("Email"),		
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
					table_1.getColumnModel().getColumn(5).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(6).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(7).setPreferredWidth(80);
					table_1.getColumnModel().getColumn(8).setPreferredWidth(80);
					
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
				}
			}});
		
		
	}

}
