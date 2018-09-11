package bitcamp.java110.cms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;

import bitcamp.java110.cms.annotation.Autowired;
import bitcamp.java110.cms.annotation.Component;

public class ApplicationContext {
    HashMap<String,Object> objPool=new HashMap<>();
    List<Class<?>> classes=new ArrayList<>();
    
    public ApplicationContext(String packageName) throws Exception {
        //패키지이름을 파일경로로 바꾼다
        String path=packageName.replace(".","/");
        //패키지 경로를 가지고 전체파일 경로를 알아낸다.
        File file= Resources.getResourceAsFile(path);

        //패키지 폴더에 들어 있는 클래스를 찾아 클래스를 로딩한 후 목록을 리턴한다.
       findClass(file, path);
        
       //로딩된 클래스 목록을 뒤져서 @Component가 붙은 
       //클래스에 대해 인스턴스를 생성하여 objpool에 보관한다
       createInstance();
       
       //objpool에 보관된 객체를 꺼내 @Autowired가 붙은 셋터를 찾아 호출한다. 
       //=>의존객체주입
       injectDependency();
    }
    //objpool에 보관된 객체를 이름으로 찾아 리턴한다
    public Object getBean(String name) {
        return objPool.get(name);
        //objpool에서 주어진 이름의 객체를 찾아 리턴한다
    }
    //객체의 타입으로 objpool에 보관된 객체를 찾아 리턴한다.
    public Object getBean(Class<?> type) {
        Collection<Object> objList=objPool.values();
        for(Object obj:objList) {
            if(type.isInstance(obj))
                return obj;
        }
        return null;
    }
    
    public String[] getBeanDefinitionNames() {
       Set<String> keySet = objPool.keySet();
       String[] names =new String[keySet.size()];
       keySet.toArray(names);
       return names;
       
    }
    private void findClass(File path, String packagePath){
        File[] files = path.listFiles();
        for(File file:files) {
            if(file.isDirectory()) {
                findClass(file, packagePath+"/"+file.getName());
            }else {
                String className=
                (packagePath+"/"+file.getName()).replace("/",".")
                        .replace(".class","");
                try {
                //1)클래스 이름을 가지고 .class 파일을 찾아 메모리에 로딩한다.
               Class<?> clazz= Class.forName(className);
               //=>인터페이스인경우 무시한다.
               
               classes.add(clazz);     //로딩한 클래스 정보를 목록에 보관한다    
                }catch (Exception e) {}
        }
    }
   }
    private void createInstance() {
        for (Class<?> clazz:classes) {
            //=>인터페이스인 경우 무시한다
            if (clazz.isInterface()) continue;
            
            Component anno=clazz.getAnnotation(Component.class);
            //@Component 애노테이션이 붙지않은 클래스는 객체를 생성하지 않는다.
            if(anno==null) continue;
            
            
            try {
            //2)로딩된 클래스 정보를 가지고 인스턴스를 생성한다.
            //=>먼저 해당 클래스의 생성자 정보를 얻는다.
            Constructor<?> consructor= clazz.getConstructor();
            //생성자 가지고 인스턴스를 생성
            Object instance= consructor.newInstance();
            //이름으로 인스턴스의 필드 정보를 찾는다.
            
            //=>클래스에서 Component 애노테이션을 추출한다.
            //System.out.println(clazz.getName()+"==>"+name);
            
            //=> Component 애노테이션이 vlaue 값이 있으면 그 값으로 객체를 저장!
            //없으면 클래스 이름으로 객체를 저장
            if(anno.value().length() >0) {
                //Component 애노테이션 value 값으로 인스턴스를 objpool에 저장한다.
                objPool.put(anno.value(), instance);
            }else {
                objPool.put(clazz.getName(),instance);
                
            }
            
            
            }catch (Exception e) {
                e.printStackTrace();
                System.out.printf("%s 클래스는 기본 생성자가 없습니다",
                        clazz.getName());
            }
            
        }
    }
    private void injectDependency() {
        //objpool에 보관된 객체 목록을 꺼댄다
        Collection<Object> objList=objPool.values();
        
        for( Object obj:objList) {
            //목록에서 객체를 꺼내 @Autowired가 붙은 메서드를 찾는다.

            Method[] methods=obj.getClass().getDeclaredMethods();
            for (Method m:methods) {
                if(!m.isAnnotationPresent(Autowired.class)) continue;
                
                //setter 메서드의 파라미터 타입을 알아낸다.
                Class<?> paramType=m.getParameterTypes()[0];
                
                //그 파라미터 타입과 일치하는 객체가 objpool에서 꺼낸다.
                Object dependency = getBean(paramType);
                
                if(dependency==null)continue;
                try {
                m.invoke(obj, dependency);
                System.out.printf("%s() 호출됨\n",getName());
                }catch (Exception e) {}
            }
        }
    }
    private Object getName() {
        // TODO Auto-generated method stub
        return null;
    }
}
