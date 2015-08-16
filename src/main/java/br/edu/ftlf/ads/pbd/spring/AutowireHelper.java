package br.edu.ftlf.ads.pbd.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * Helper class which is able to autowire a specified class. It holds a static reference to the {@link org
 * .springframework.context.ApplicationContext}.
 */
public final class AutowireHelper implements ApplicationContextAware {
 
    private static final AutowireHelper INSTANCE = new AutowireHelper();
    private static ApplicationContext applicationContext;
 
    /**
     * Tries to autowire the specified instance of the class if one of the specified beans which need to be autowired
     * are null.
     *
     * @param bean the instance of the class which holds @Autowire annotations
     * @param beansToAutowireInClass the beans which have the @Autowire annotation in the specified {#classToAutowire}
     */
    public static void autowire(Object bean) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        AutowireHelper.applicationContext = applicationContext;
    }
 
    /**
     * @return the singleton instance.
     */
    public static AutowireHelper getInstance() {
        return INSTANCE;
    }
	
    @Bean
	public AutowireHelper autowireHelper(){
	    return new AutowireHelper();
	}
 
}