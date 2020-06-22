package com.nannan.test;

/**
 * @program:demo
 * @description:
 * @author:Juwenchao
 * @date:2020-06-22 19:04:41
 */
public class June22 {


    public static void main(String[] args) {
        int a[] = {1, 9, 2, 10, 3, 11, 4, 12, 5, 13, 6, 14, 7, 15, 8, 16};
        int res[] = shellSort(a);
        for (int s :
                res) {
            System.out.println("s = " + s);
        }
    }

    /*  递归法 */

    /**
     * 爬楼梯问题
     */
    public static int lift(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return n;
        } else {
            return lift(n - 1) + lift(n - 2);
        }
    }

    /**
     * 插入排序
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0)
            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 冒泡排序
     */
    public static int[] bubbling(int a[]) {
        if (a.length == 0) {
            return a;
        }
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }

    /**
     * 选择排序
     */
    public static int[] selectSort(int a[]) {
        if (a.length == 0) {
            return a;
        }
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int index = i;
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    index = j;
                }
            }
            int temp = a[index];
            a[index] = a[i];
            a[i] = temp;

        }
        return a;
    }

    /**
     * 希尔排序
     */
    public static int[] shellSort(int a[]) {
        int j;
        int len = a.length;
        int gap = len / 2;
        int temp;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = a[i];
                for (j = i; j >= gap && temp < a[j - gap]; j -= gap)
                    a[j] = a[j - gap];
                a[j] = temp;
            }
            gap/=2;
        }
        return a;
    }

    /**
     * 快速排序
     */
    public static void quickSort(int a[],int left,int right){
        if (left>right){
            return;
        }
        // base存放基准数
        int base = a[left];
        int i = left,j =right;
        while (i!=j){
            while (a[j]>=base&&i<j){
                j--;
            }
            while (a[i]<=base&&i<j){
                i++;
            }
            if(i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[left]=a[i];
        a[i] =base;
        quickSort(a,left,i-1);
        quickSort(a,i+1,right);
    }
}
