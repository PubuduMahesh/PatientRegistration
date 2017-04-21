package cambio.patientregistration.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PatientRegController {

	private List<ActionListener> listenerSave = new ArrayList<>();
	private List<ActionListener> listenerClick = new ArrayList<>();
	private List<ActionListener> listenerSearch = new ArrayList<>();


	public void registerSaveListeners(ActionListener listener)
	{
		listenerSave.add(listener);
	}

	public void fireSaveActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerSave) {
			actionListener.actionPerformed(e);
		}
	}

	public void registerRowClickListeners(ActionListener listener)
	{
		listenerClick.add(listener);
	}

	public void fireRowClickActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerClick) {
			actionListener.actionPerformed(e);
		}
	}
	public void registerSearchListeners(ActionListener listener)
	{
		listenerSearch.add(listener);
	}

	public void fireSearchActionPerformed(ActionEvent e)
	{
		for (ActionListener actionListener : listenerSearch) {
			actionListener.actionPerformed(e);
		}
	}
}
