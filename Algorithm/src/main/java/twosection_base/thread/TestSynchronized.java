package twosection_base.thread;

/**
 * Created by wl on 2014/12/4.
 */
class Test_Synchronized extends Thread {

    public Test_Synchronized(String name) {
        super(name);
    }

    public synchronized static void prt() throws InterruptedException {
        for (int i=10;i<20;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            Thread.sleep(100);
        }
    }

    public synchronized void run(){
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class TestSynchronized{
    public static void main(String[] args) throws InterruptedException {
        Test_Synchronized t1=new Test_Synchronized("t1");
        Test_Synchronized t2=new Test_Synchronized("t2");
       /* t1.start();
        t1.prt();*/
        t2.start();
        t2.prt();
    }
}
