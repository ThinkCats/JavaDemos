import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglei on 15-2-27.
 */
public class Java8Demo {

    @Test
    // this method can not print "lambda thread"
    public void runThread(){
        System.out.println("begin");
        new Thread(() -> System.out.println("lambda thread")).start();
    }

    @Test
    public void testList(){
        List<String> names=new ArrayList<>();
        names.add("zhang");
        names.add("wang");

    }

    @Test
    public void testMethod(){
        //doSomething();
    }

    public void doSomething(Sing sing){
        sing.sing();
    }
}
