package br.edu.ftlf.ads.pbd.view.field;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

public class EnumSetUtils<E extends Enum<E>> extends JComponent {

	private static final long serialVersionUID = 1L;
	private static final String ENUM_SET_PROPERTY_NAME = "enumSet";
	
	private EnumSet<E> enumSet;
	private Class<E> enumType;
	private List<JCheckBox> elements = new ArrayList<JCheckBox>();
	private JCheckBox markAllButton;
	
	public static <E extends Enum<E>> EnumSetUtils<E> createEnumSetContainer(Class<E> enumType) {
		return new EnumSetUtils<E>(enumType);		
	}
	
	public static <E extends Enum<E>> EnumSetUtils<E> createWithMarkAllButton(Class<E> enumType) {
		EnumSetUtils<E> enumSetContainer = createEnumSetContainer(enumType);
		enumSetContainer.getMarkAllButton().setVisible(true);
		return enumSetContainer;
	}
	
	private EnumSetUtils(final Class<E> enumType) {
		this.enumType = enumType;
		this.enumSet = EnumSet.noneOf(enumType);
		
		setLayout(new BorderLayout());
		Container container = new Container();
		container.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Iterator<E> it = EnumSet.allOf(enumType).iterator();
		while(it.hasNext()) {
			E element = it.next();
			String name = element.name();
			JCheckBox box = new JCheckBox(name);
			box.setHorizontalAlignment(SwingConstants.LEFT);
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JCheckBox box = (JCheckBox) e.getSource();
					String name = box.getText().toUpperCase();
					E value = Enum.valueOf(enumType, name);
					if (box.isSelected()) {
						select(value);
					} else {
						deselect(value);
					}
				}
			});
			container.add(box);
			elements.add(box);
		}
		
		add(container, BorderLayout.CENTER);
		
		markAllButton = new JCheckBox("Marcar Todos");
		markAllButton.setVisible(false);
		markAllButton.setHorizontalAlignment(SwingConstants.CENTER);
		markAllButton.setVerticalAlignment(SwingConstants.BOTTOM);
		markAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox all = (JCheckBox) e.getSource();
				if (all.isSelected()) {
					selectAll();
				} else {
					selectNone();
				}
			}
		});
		add(markAllButton , BorderLayout.WEST);
	}

	public void select(E enumValue) {
		EnumSet<E> oldValue = getEnumSet().clone();
		getEnumSet().add(enumValue);
		firePropertyChange(ENUM_SET_PROPERTY_NAME, oldValue, enumSet);
		update();
	}
	
	public void deselect(E enumValue) {
		EnumSet<E> oldValue = getEnumSet().clone();
		getEnumSet().remove(enumValue);
		firePropertyChange(ENUM_SET_PROPERTY_NAME, oldValue, enumSet);
		update();
	}
	
	public void selectAll() {
		EnumSet<E> oldValue = getEnumSet().clone();
		getEnumSet().addAll(EnumSet.allOf(enumType));
		firePropertyChange(ENUM_SET_PROPERTY_NAME, oldValue, enumSet);
		update();
	}
	
	public void selectNone() {
		EnumSet<E> oldValue = getEnumSet().clone();
		getEnumSet().removeAll(EnumSet.allOf(enumType));
		firePropertyChange(ENUM_SET_PROPERTY_NAME, oldValue, enumSet);
		update();
	}
	
	private void update() {
		for (JCheckBox box : elements) {
			E value = Enum.valueOf(enumType, box.getText().toUpperCase());
			boolean contains = getEnumSet().contains(value);
			box.setSelected(contains);
		}
		repaint();
	}
	
	public EnumSet<E> getEnumSet() {
		if (enumSet == null) {
			enumSet = EnumSet.noneOf(enumType);
		}
		return enumSet;
	}

	public void setEnumSet(EnumSet<E> enumSet) {
		firePropertyChange("enumSet", this.enumSet, this.enumSet = enumSet);
		update();
	}
	
	public JCheckBox getMarkAllButton() {
		return markAllButton;
	}
	
}
