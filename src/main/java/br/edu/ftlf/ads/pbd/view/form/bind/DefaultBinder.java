package br.edu.ftlf.ads.pbd.view.form.bind;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.ELProperty;

public class DefaultBinder implements IBinder {

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BindingGroup bind(Object bean, Object target) {
        
        final BindingGroup bindingGroup = new BindingGroup();
        final List<TargetItem> targetItems = resolvePropertys(target.getClass());
        
        for (TargetItem targetItem : targetItems)  {
            
            final Field field = targetItem.getField();
            final Bind bind = targetItem.getAnnotation();
            
            try {
                Object targetField = field.get(target);
                String expression = bind.property();
                String property = bind.expression();
                String name = (	bind.name().isEmpty() ? 
                				bean.getClass().getName().concat(field.getName()).concat(bind.toString()) : 
                				bind.name()
                				);
                
                UpdateStrategy updateStrategy = AutoBinding.UpdateStrategy.valueOf(bind.strategy().toUpperCase());
                if (updateStrategy == null) {
                	updateStrategy = AutoBinding.UpdateStrategy.READ_WRITE;
                }                              
                
                ELProperty beanProperty = ELProperty.create("${" + expression + "}");
                ELProperty targetProperty = ELProperty.create("${" + property + "}");
				AutoBinding binding = Bindings.createAutoBinding(updateStrategy, bean, beanProperty, targetField, targetProperty, name);
				binding.setSourceUnreadableValue("null");
				
                if (!bind.converter().isAssignableFrom(Converter.class)) {
                    try {
                        binding.setConverter(bind.converter().newInstance());
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger("FormBinder").log(Level.SEVERE, 
                                "can't instantiate converter for binding {0}", name);
                        System.err.println(ex);
                    }
                }

                if (!bind.whenSourceIsNull().isEmpty()) {
                    binding.setSourceNullValue(bind.whenSourceIsNull());
                }
                
                if (!bind.whenTargetIsNull().isEmpty()) {
                    binding.setTargetNullValue(bind.whenTargetIsNull());
                }

                try {
					binding.bind();
					bindingGroup.addBinding(binding);
				} catch (Exception e) {
					e.printStackTrace();
				}
                
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger("FormBinder").log(Level.SEVERE, 
                                "can't bind field {0}", field.getName());
                System.err.println(ex);
            }
        }
        
        return bindingGroup;
    }
    
    /**
     * Metodo que retorna os campos e suas propriedades para binding
     * 
     * @param clazz Class with configurations from binding
     * @return List<FormItem> 
     */
    private static List<TargetItem> resolvePropertys(Class<?> clazz) {
        final List<TargetItem> formItems = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
        	if (!field.isAccessible()) {
        		field.setAccessible(true);
        	}
            if (field.isAnnotationPresent(Bind.class)) {
				formItems.add(new TargetItem(field, field.getAnnotation(Bind.class)));
            }
            if (field.isAnnotationPresent(Binds.class)) {
            	Bind[] bindings = field.getAnnotation(Binds.class).value();
            	for (Bind bind : bindings) {
            		formItems.add(new TargetItem(field, bind));
				}
            }
            
        }
        return formItems;
    }
    
    /**
     * Inner class 
     */
    protected static class TargetItem {

        private Field field;
        private Bind annotation;

        public TargetItem(Field component, Bind annotation) {
            this.field = component;
            this.annotation = annotation;
        }

		public Field getField() {
			return field;
		}
		public Bind getAnnotation() {
			return annotation;
		}
    }
}
