package bitcamp.java110.cms.context;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;

@Component
public class LogBeanPostProcessor implements BeanPostProcessor {
    ApplicationContext beanContainer;

    public void postProcess(ApplicationContext beanContainer) {
        // objPool에 보관된 객체 목록을 꺼낸다.
        Collection<Object> objList = beanContainer.objPool.values();

        System.out.println("==========================");
        for (Object obj : objList) {
            System.out.println(obj.getClass().getName());
        }
        System.out.println("==========================");
    }
}