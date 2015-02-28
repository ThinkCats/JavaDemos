import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by wanglei on 15-2-27.
 */
public class Java8Demo {

    @Test
    public void testLamBase(){
        String[]  name={"wang","zhang","liu","zhao","hu"};
        List<String> names=new ArrayList<>(Arrays.asList(name));
        names.forEach((people) -> System.out.println(people+";"));
        names.forEach(System.out::println);
    }

    @Test
    public void  testPredicate(){
        List<Integer> numbers=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        System.out.println(sum(numbers, n -> true));
        System.out.println(sum(numbers, n -> n==3));
        System.out.println(sum(numbers, n -> n>2));
    }

    public int sum(List<Integer> numbers,Predicate<Integer> p){
        int total=0;
        for (int number:numbers){
            if (p.test(number)){
                total+=number;
            }
        }
        return total;
    }

    @Test
    public void testStream(){
        distinctPrimary("1","1","3","5","9","9");
        distinctByReduce("1","1","3","6");
    }

    public void distinctPrimary(String... numbers){
        List<String> nums=new ArrayList<>(Arrays.asList(numbers));
        List<Integer> r=nums.stream()
                .map(e -> new Integer(e))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct number:"+r);
    }

    public void distinctByReduce(String... numbers){
        List<String> nums=new ArrayList<>(Arrays.asList(numbers));
        int sum=nums.stream()
                .map(e -> new Integer(e))
                .distinct()
                .reduce(0,(x,y) -> x+y);
        System.out.println("sum:"+sum);
    }

    @Test
    //test function
    public void testFunction(){
        MyFunction<Integer,Integer> f=this::sums;
        System.out.println(f.apply(1,2));
    }

    public int sums(int x,int y){
        return x+y;
    }

    @Test
    public void testFF(){
        Function<String,String> f1=(x) -> {System.out.print(x + " :");return "F1";};
        System.out.println(f1.apply("hello"));

        Predicate<String> pre=(x) -> {System.out.print(x); return false;};
        System.out.println(": "+pre.test("hello"));


    }
}
