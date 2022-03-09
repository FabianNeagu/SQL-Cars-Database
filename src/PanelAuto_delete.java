import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAuto_delete extends JPanel {
	private JTextField nrInmatriculare;
	/**
	 * Create the panel.
	 */
	public PanelAuto_delete() 
	{
			setBounds(0, 0, 891, 552);
			setLayout(null);
			
			JLabel lblIntroducetiNumeleClientului = new JLabel("Introduceti date despre autoturismul dorit a fi eliminat din evidenta:");
			lblIntroducetiNumeleClientului.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblIntroducetiNumeleClientului.setBounds(109, 92, 669, 75);
			add(lblIntroducetiNumeleClientului);
			
			
			JLabel lblNr = new JLabel("Numar Inmatriculare:");
			lblNr.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNr.setBounds(188, 267, 192, 34);
			add(lblNr);
			nrInmatriculare = new JTextField();
			nrInmatriculare.setBounds(422, 268, 186, 39);
			add(nrInmatriculare);
			nrInmatriculare.setColumns(10);
			
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
					String NrInmatriculare=nrInmatriculare.getText();
					String connectionUrl = "jdbc:jtds:sqlserver://DESKTOP-53O130I;instanceName=SQLEXPRESS;databaseName=Centru_Inchirieri_Masini;integratedSecurity=true";
					try
					{
						Connection con = DriverManager.getConnection(connectionUrl); 
					
						String query="Delete From Autoturism Where NrInmatriculare=('" + NrInmatriculare +"')";
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
		
			
	}

}
