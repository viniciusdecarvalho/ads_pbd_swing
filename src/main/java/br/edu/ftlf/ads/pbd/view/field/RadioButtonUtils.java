package br.edu.ftlf.ads.pbd.view.field;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;

public class RadioButtonUtils {
	private RadioButtonUtils() {
	}

	public enum Orientation {
		HORIZONTAL, VERTICAL
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Enumeration getSelectedElements(Container container) {
		Vector selections = new Vector();
		Component components[] = container.getComponents();
		for (int i = 0, n = components.length; i < n; i++) {
			if (components[i] instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) components[i];
				if (button.isSelected()) {
					selections.addElement(button.getText());
				}
			}
		}
		return selections.elements();
	}

	public static Container createRadioButtonGrouping(Object[] elements, Orientation orientation) {
		return createRadioButtonGrouping(elements, orientation, null, null, null, null);
	}

	public static Container createRadioButtonGrouping(Object[] elements,
			Orientation orientation, String title) {
		return createRadioButtonGrouping(elements, orientation, title, null, null, null);
	}

	public static Container createRadioButtonGrouping(Object[] elements,
			Orientation orientation, String title, ItemListener itemListener) {
		return createRadioButtonGrouping(elements, orientation, title, null, itemListener,
				null);
	}

	public static Container createRadioButtonGrouping(Object[] elements,
			Orientation orientation, String title, ActionListener actionListener) {
		return createRadioButtonGrouping(elements, orientation, title, actionListener, null,
				null);
	}

	public static Container createRadioButtonGrouping(Object[] elements,
			Orientation orientation, String title, ActionListener actionListener,
			ItemListener itemListener) {
		return createRadioButtonGrouping(elements, orientation, title, actionListener,
				itemListener, null);
	}

	public static Container createRadioButtonGrouping(Object[] elements, 
			Orientation orientation, String title, ActionListener actionListener,
			ItemListener itemListener, ChangeListener changeListener) {
		GroupPanel panel = null;
		if (orientation == Orientation.HORIZONTAL) {
			panel = new GroupPanel(new GridLayout(1, 0));
		} else {
			panel = new GroupPanel(new GridLayout(0, 1));
		}
		// If title set, create titled border
		if (title != null) {
			panel.setBorder(BorderFactory.createTitledBorder(title));
		}
		// Create group
		ButtonGroup group = new ButtonGroup();
		panel.setGroup(group);
		JRadioButton aRadioButton;
		// For each String passed in:
		// Create button, add to panel, and add to group
		for (int i = 0, n = elements.length; i < n; i++) {
			aRadioButton = new JRadioButton(elements[i].toString());
			panel.add(aRadioButton);
			group.add(aRadioButton);
			if (actionListener != null) {
				aRadioButton.addActionListener(actionListener);
			}
			if (itemListener != null) {
				aRadioButton.addItemListener(itemListener);
			}
			if (changeListener != null) {
				aRadioButton.addChangeListener(changeListener);
			}
			aRadioButton.setSelected(i == 0);
		}
		return panel;
	}

	private static class GroupPanel extends JPanel {

		private static final long serialVersionUID = -672593531514598573L;
		private ButtonGroup group;

		public GroupPanel(LayoutManager layoutManager) {
			super(layoutManager);
		}
		
		@SuppressWarnings("unused")
		public ButtonGroup getGroup() {
			return group;
		}

		public void setGroup(ButtonGroup group) {
			firePropertyChange("group", this.group, this.group = group);
		}
		
	}
	
}