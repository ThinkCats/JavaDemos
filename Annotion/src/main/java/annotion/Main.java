package annotion;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wl on 2014/12/19.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> useCases=new ArrayList<Integer>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCase(useCases, PasswordUtil.class);
    }

    public static void trackUseCase(List<Integer> useCases,Class<?> c){
        for (Method m:c.getDeclaredMethods()){
            UseCase useCase=m.getAnnotation(UseCase.class);
            if (useCase!=null){
                System.out.println("Found UseCase "+useCase.id()+" "+useCase.description());
                useCases.remove(new Integer(useCase.id()));
            }

            for (int i:useCases){
                System.out.println("Warning :Missing use case:"+i);
            }
        }
    }
}
