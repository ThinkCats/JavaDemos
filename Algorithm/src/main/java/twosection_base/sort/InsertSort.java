package twosection_base.sort;

import org.junit.Test;

/**
 * Created by wanglei on 2014/12/1.
 */
public class InsertSort {

    //升序排序
    public int[] insertSort(int[] A){
        for (int j=1;j<A.length;j++){
            int key=A[j];               //初始将下一位数设置为key（example:key=A[1]=3）
            int i=j-1;                  //(example:i=0)
            //while循环保证初始和循环过程中，排序都是正确的（循环不变式）
            while (i>=0 && A[i]>key){        //如果i是大于等于0的，并且key前面的这个数大于key，就开始循环(example:A[0]=4>3)
                A[i+1]=A[i];                //循环时，将i后一位改成大数 (example:A[1]=A[0]=4)
                i=i-1;                      //将i减少1，维持该while循环继续下去，直到不满足条件
            }
            A[i+1]=key;                     //顺序已经是正确的情况下，下一位就是正常的key
        }
        return A;
    }

    //降序排序
    public int[] insertSortDesc(int[] A){
        for (int j=1;j<A.length;j++){
            int key=A[j];
            int i=j-1;
            while (i>=0 && A[i]<key){
                A[i+1]=A[i];
                i=i-1;
            }
            A[i+1]=key;
        }
        return A;
    }


    @Test
    public void test(){
        int[] A={4,3,1,9,6};
        int[] B=insertSort(A);
        int[] C={1,5,2,4};
        int[] D=insertSortDesc(C);
        for (int a:B){
            System.out.print(a + " ");
        }
        for (int a:D){
            System.out.print(a +" ");
        }
    }
}
