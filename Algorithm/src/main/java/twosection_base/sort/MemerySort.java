package twosection_base.sort;

import org.junit.Test;

/**
 * Created by wanglei on 2014/12/1.
 * 采用“分治法”，先分割，然后排序后，再合并成需要的有序数据
 */
public class MemerySort {

    public void mergeSort(int[] arr){
        int[] temp=new int[arr.length];
        internal(arr,temp,0,arr.length-1);
    }

    //分割数据,采用递归
    private void internal(int[] a,int[] b,int left,int right){
        //当left==right的时，已经不需要再划分了
        if (left<right){
            int middle=(left+right)/2;
            internal(a,b,left,middle);          //左子数组
            internal(a,b,middle+1,right);       //右子数组
            mergeSortedArray(a,b,left,middle,right);
        }
    }

    // 合并两个有序子序列 arr[left, ..., middle] 和 arr[middle+1, ..., right]。temp是辅助数组。
    private void mergeSortedArray(int[] arr,int[] temp,int left,int middle,int right){
        int i=left;
        int j=middle+1;
        int k=0;
        //针对两个子序列，arr[left, ..., middle] 和 arr[middle+1, ..., right]，该代码原理与合并两个数组的原理相同
        while (i<=middle && j<=right){
            if (arr[i] <= arr[j]){
                temp[k++]=arr[i++];
            }else {
                temp[k++]=arr[j++];
            }
        }

        while (i<=middle){
            temp[k++]=arr[i++];
        }

        while (j<=right){
            temp[k++]=arr[j++];
        }

        //以上代码，数据存放在temp[]中，需要复制回原来的数组。
        for (i=0;i<k;++i){
            arr[left+i]=temp[i];
        }
    }

    //合并两个数组
    public void memeryArray(int[] a,int[] b,int[] c){
        int i=0;
        int j=0;
        int k=0;

        while (i<=a.length && j<=b.length){
            if (a[i] <= b[i]){
                c[k++]=a[i++];
            }else {
                c[k++]=b[j++];
            }
        }

        while (i<=a.length){
            c[k++]=a[i++];
        }

        while (j<=b.length){
            c[k++]=b[j++];
        }
    }

    @Test
    public void test(){
        int[] array={2,8,4,6,7,5};
        mergeSort(array);
        for (int a:array){
            System.out.println(a);
        }
    }
}
