import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelClienti_delete extends JPanel {
	private JTextField nume;
	private JTextField prenume;

	/**
	 * Create the panel.
	 */
	public PanelClienti_delete() 
	{
		setBounds(0, 0, 891, 552);
		setLayout(null);
		
		JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date despre clientul dorit a fi eliminat din evidenta:");
		lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIntroducetiNumeleClientului.setBounds(109, 92, 669, 75);
		add(lblIntroducetiNumeleClientului);
		
		
		JLabel lblNume = new JLabel("Nume:");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNume.setBounds(263, 212, 66, 34);
		add(lblNume);
		nume = new JTextField();
		nume.setBounds(364, 213, 186, 39);
		add(nume);
		nume.setColumns(10);

		
		JLabel lblPrenume = new JLabel("Prenume:");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrenume.setBounds(238, 284, 114, 39);
		add(lblPrenume);
		prenume = new JTextField();
		prenume.setColumns(10);
		prenume.setBounds(364, 287, 186, 39);
		add(prenume);
		
		JButton btnNewButton = new JButton("Executa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String Nume=nume.getText();
				String Prenume=prenume.getText();
				String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
				try
				{
					Connection con = DriverManager.getConnection(connectionUrl); 
				
					String query="Delete From Client Where Nume=('" + Nume +"') And Prenume=('" + Prenume +"')";
					Statement s = con.createStatement();
		            s.execute(query);
					JOptionPane.showMessageDialog(null, "Successfully deleted");
					con.close();
				}
				catch(Exception e1)
				{
					System.out.println("error"+e1);
					JOptionPane.showMessageDialog(null, "UnSuccessfully deleted");
				}
			
			}
		});
		btnNewButton.setBounds(330, 421, 186, 31);
		add(btnNewButton);
		

		JLabel label = new JLabel("Prenume:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(238, 284, 114, 39);
		add(label);
	
		
	
	}
}
