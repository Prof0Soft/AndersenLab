package util;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Data
public class ViewSpringInfo implements ApplicationContextAware {

    private static ApplicationContext context = ApplicationContextProvider.getApplicationContext();

    public static void showSpringBeansInfo() {
        System.out.println("-------------------------------------------------");
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        System.out.println("-------------------------------------------------");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        return;
    }
}
