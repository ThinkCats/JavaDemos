package twosection_base.thread;

/**
 * Created by wanglei on 2014/12/4.
 * This is not a real mulit thread
 * 这不是一个真正的多线程
 */
public class TestThread {
    int i=0;
    int j=0;

    public void go(int flag){
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (flag == 0){
                i++;
                System.out.println("i="+i);
            }
            else {
                j++;
                System.out.println("j="+j);
            }
        }
    }

    public static void main(String[] args) {
        new TestThread().go(0);
        new TestThread().go(1);
    }


}
