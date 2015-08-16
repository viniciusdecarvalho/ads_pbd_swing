package br.edu.ftlf.ads.pbd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.EventListener;
import java.util.List;

import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;

public class ControllerListener implements EventListener {

	public class Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {}
	}
	
	public class Mouse extends MouseAdapter {}
	
	public class Window extends WindowAdapter { }
	
	public class Focus extends FocusAdapter {}
	
	public class Key extends KeyAdapter {}
	
	@SuppressWarnings("rawtypes")
	public class Observable implements ObservableListListener {
		@Override
		public void listElementsRemoved( ObservableList list, int index, List oldElements) {}
		
		@Override
		public void listElementsAdded(ObservableList list, int index, int length) {}
		
		@Override
		public void listElementReplaced(ObservableList list, int index, Object oldElement) {}
		
		@Override
		public void listElementPropertyChanged(ObservableList list, int index) {}
	}
	
}
