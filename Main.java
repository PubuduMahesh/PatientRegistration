import javax.swing.SwingUtilities;

import cambio.patientregistration.view.*;

public class Main {


	public static void main(String[] args) {  
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
				PatientRegistration pr = new PatientRegistration();//Main Frame/Panel reference object.
			}			
		});
	}
	
	

}
