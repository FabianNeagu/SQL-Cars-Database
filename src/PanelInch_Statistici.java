import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInch_Statistici extends JPanel {
	private JTable table_1;
	private JTable table_2;
	private JTextField an1;
	private JTextField an2;
	/**
	 * Create the panel.
	 */
	public PanelInch_Statistici() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 89, 842, 105);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Cele mai lungi contracte de inchiriere pentru masinile cu 2 locuri pana in anul:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 44, 717, 32);
		add(lblNewLabel);
		
		JLabel lblFisatiInOrdeine = new JLabel("Cele mai scumpe contracte incheiate de la infiintarea companiei pana in anul:");
		lblFisatiInOrdeine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFisatiInOrdeine.setBounds(22, 218, 707, 32);
		add(lblFisatiInOrdeine);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 277, 842, 105);
		add(scrollPane_1);
		
		//table_1 = new JTable();
		//scrollPane.setViewportView(table_1);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
		
		
		/*DefaultTableModel model=new DefaultTableModel();
		model.addColumn("Marca");
		model.addColumn("Model");
		model.addColumn("NrInmatriculare");
		model.addColumn("NrZile");*/
		
		
		/*table_2 = new JTable();
		//scrollPane_1.setViewportView(table_2);
		DefaultTableModel model2=new DefaultTableModel();
		model2.addColumn("Nume");
		model2.addColumn("Prenume");
		model2.addColumn("Total_plata");
		model2.addColumn("Marca");
		model2.addColumn("Model");
		model2.addColumn("Data");*/
		
		
		an1 = new JTextField();
		an1.setBounds(737, 44, 116, 32);
		add(an1);
		an1.setColumns(10);
		
		an2 = new JTextField();
		an2.setColumns(10);
		an2.setBounds(738, 221, 116, 32);
		add(an2);
		
		
		JButton button = new JButton("Executa");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				table_1 = new JTable();
				DefaultTableModel model=new DefaultTableModel();
				model.addColumn("Marca");
				model.addColumn("Model");
				model.addColumn("NrInmatriculare");
				model.addColumn("NrZile");
				
				table_2 = new JTable();
				//scrollPane_1.setViewportView(table_2);
				DefaultTableModel model2=new DefaultTableModel();
				model2.addColumn("Nume");
				model2.addColumn("Prenume");
				model2.addColumn("Total_plata");
				model2.addColumn("Marca");
				model2.addColumn("Model");
				model2.addColumn("Data");
				
				String An1=an1.getText();
				String An2=an2.getText();
				int a1=0;
				int a2=0;
				scrollPane.setViewportView(table_1);
				scrollPane_1.setViewportView(table_2);
				if(!An1.isEmpty())
					a1=Integer.parseInt(An1); 
				if(!An2.isEmpty())
					a2=Integer.parseInt(An2); 
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
					String query="Select A.Marca, A.Model, A.NrInmatriculare, DATEDIFF(day,I.Data_Inceput_inchiriere ,I.Data_terminare_inchiriere) as NrZile \r\n" + 
							"From Autoturism A inner join Inchiriere I on A.AutoturismID=I.AutoturismID \r\n" + 
							"				  inner join CategorieAutoturism CA on A.CategorieID=CA.CategorieID \r\n"
							+ 					"inner join Factura F on F.FacturaID=I.FacturaID \r\n" + 
							"Where CA.Nr_Locuri=2 AND YEAR(F.Data) <= ('" + a1 +"') \r\n" + 
							"Order by DATEDIFF(day,I.Data_Inceput_inchiriere ,I.Data_terminare_inchiriere) DESC \r\n";
					java.sql.Statement st=con.createStatement();
					ResultSet rs=st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[] 
								{
								rs.getString("Marca"),
								rs.getString("Model"),
								rs.getString("NrInmatriculare"),
								rs.getString("NrZile"),
						});
					}
					rs.close();
					st.close();
					con.close();
					
					Connection con1 = DriverManager.getConnection(connectionUrl); 
					String query1="Select C.Nume, C.Prenume, F.Total_plata, A.Marca, A.Model, F.Data\r\n" + 
							"From Client C inner join Inchiriere I on C.ClientID=I.ClientID\r\n" + 
							"			  inner join Factura F on I.FacturaID=F.FacturaID\r\n" + 
							"			  inner join Autoturism A on A.AutoturismID=I.AutoturismID\r\n" + 
							"Where YEAR(F.Data) <= ('" + a2 +"') \r\n" + 
							"Order By F.Total_plata DESC \r\n";
					java.sql.Statement st1=con1.createStatement();
					ResultSet rs1=st1.executeQuery(query1);
					while(rs1.next())
					{
						model2.addRow(new Object[] 
								{
								rs1.getString("Nume"),
								rs1.getString("Prenume"),
								rs1.getString("Total_plata"),
								rs1.getString("Marca"),
								rs1.getString("Model"),
								rs1.getString("Data"),
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
					table_2.getColumnModel().getColumn(3).setPreferredWidth(100);
					table_2.getColumnModel().getColumn(4).setPreferredWidth(100);
					table_2.getColumnModel().getColumn(5).setPreferredWidth(100);
					
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
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(345, 454, 186, 31);
		add(button);
		
		
	}

}
