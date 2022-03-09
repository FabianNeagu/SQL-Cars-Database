import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelHome() {
		setBounds(0, 0, 915, 655);
		setVisible(true);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOME");
		lblNewLabel.setBounds(386, 199, 141, 46);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 34));
		add(lblNewLabel);
		
		JLabel lblEurocarsOferO = new JLabel("Aceasta este pagina principala");
		lblEurocarsOferO.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblEurocarsOferO.setBounds(231, 345, 643, 118);
		add(lblEurocarsOferO);

	}
}
