package br.edu.ftlf.ads.pbd.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

public class MenuFactory {

	private Map<String, Action> actions;

	public Map<String, Action> getActions() {
		return actions;
	}

	public MenuFactory(ApplicationContext applicationContext) {
		actions = new HashMap<String, Action>();
		Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(Controller.class);
		for (Object controller : controllers.values()) {
			for (Method method : controller.getClass().getDeclaredMethods()) {
				if (method.isAnnotationPresent(MenuAction.class)) {
					MenuAction menuAction = method.getAnnotation(MenuAction.class);
					String text = menuAction.text();
					String icon = menuAction.icon();
					String root = rootName(controller, method, menuAction);
					int mnemonic = menuAction.mnemonic();
					Action action = new Action(text, icon, root, mnemonic);
					action.setListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent paramActionEvent) {
							try {
								method.invoke(controller);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								throw new IllegalStateException("invalid action", e);
							}
						}
					});
					actions.put(root, action);
				}
			}
		}
	}

	private String rootName(Object controller, Method method, MenuAction menuAction) {
		return menuAction.root() 
				+ "." + 
				controller.getClass().getSimpleName().replace("Controller", "").toLowerCase() 
				+ "." +  
				method.getName().toLowerCase();
	}

	public class Action {
		private String text;
		private String icon;
		private String root;
		private int mnemonic;
		private ActionListener listener;
		public Action(String text, String icon, String root, int mnemonic) {
			this.text = text;
			this.icon = icon;
			this.root = root;
			this.mnemonic = mnemonic;
		}
		public String getText() {
			return text;
		}
		public String getIcon() {
			return icon;
		}
		public String getRoot() {
			return root;
		}
		public int getMnemonic() {
			return mnemonic;
		}
		public ActionListener getListener() {
			return listener;
		}
		public void setListener(ActionListener action) {
			this.listener = action;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((root == null) ? 0 : root.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Action other = (Action) obj;
			if (root == null) {
				if (other.root != null)
					return false;
			} else if (!root.equals(other.root))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Action [text=" + text + ", root=" + root + "]";
		}
	}
}
