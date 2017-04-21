package cambio.patientregistration.view;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;

public class Bottom extends JPanel{
	private JButton bClose;

	public JPanel createBottomPanel(){
		final JPanel panelBottom = new JPanel(new GridBagLayout());
		GridBagConstraints bottomC = new GridBagConstraints();
		this.bClose = new JButton("Close");
		bottomC.gridx = 0;
		bottomC.gridy = 0;
		panelBottom.add(bClose,bottomC);

		/*close button actions.*/
		bClose.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String[] option = {"Yes", "No"};//JOption Pane, option lists
				/*JOption Pane stuffs*/
				
				int optionResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?",
						"Patient Registration", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						null, option, option[1]);
				if (optionResult == 0) {
					System.exit(0);//dispose the window, if the option is "yes".
				}
			}
		});

		return panelBottom;
	}


}
