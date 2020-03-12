package com.example.leetcode.sort;


public class SortList {
    /**
     * 注意，这里所有的 low 和 high 都是闭区间！
     */

    /**
     * 快速排序
     *
     * low 和high都是闭区间
     * @param nums
     * @param low
     * @param high
     */
    private void quickSort(int[] nums, int low, int high) {
        //注意这里有条件判断low <high
        if(low < high) {
            int mid = getMiddle(nums, low, high);   //将数组进行一分为二
            quickSort(nums, low, mid - 1);
            quickSort(nums, mid + 1, high);
        }
    }

    /**
     * 查找出中轴的位置
     */
    private int getMiddle(int[] nums, int low, int high) {
        int tmp = nums[low];    //数组的第一个作为中轴
        while (low < high){
            //这里要有 >= 号而不是 >
            while (low < high && nums[high] >= tmp) {
                high --;
            }
            nums[low] = nums[high]; //比中轴小的记录移到低端
            while (low < high && nums[low] < tmp) {
                low ++;
            }
            nums[high] = nums[low]; //比中轴大的记录移到高端
        }
        nums[low] = tmp;    //中轴记录到位
        return low;     //返回中轴位置
    }

    /**
     * 归并排序
     */
    private void mergeSort(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;
        if(low < high) {
            // 左边
            mergeSort(nums, low, mid);
            // 右边
            mergeSort(nums, mid + 1, high);
            // 左右归并
            merge(nums, low, high, mid);
        }
    }

    /**
     * 将数组中low到high位置的数进行排序
     * @param nums 待排序数组
     * @param low 待排的开始位置
     * @param mid 待排中间位置
     * @param high 待排结束位置
     */
    private void merge(int[] nums, int low, int high, int mid) {
        int i = low;    // 左指针
        int j = mid + 1;    // 右指针
        int[] tmp = new int[high - low + 1];
        int index = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if(nums[i] < nums[j]) tmp[index++] = nums[i++];
            else tmp[index++] = nums[j++];
        }

        // 把左边剩余的数移入数组
        while (i <= mid) tmp[index ++] = nums[i++];

        // 把右边边剩余的数移入数组
        while (j <= high) tmp[index++] = nums[j++];

        // 把新数组中的数覆盖nums数组
        for (int k = 0; k < tmp.length; k++) {
            nums[low + k] = tmp[k];
        }
    }

    /**
     * 堆排序
     */
    private void heapSort(int[] nums) {
        // 循环建堆
        for(int i = 0; i < nums.length - 1; i++) {
            // 建堆
            buildHeap(nums, nums.length - 1 - i);
            // 交换堆顶和最后一个元素
            swap(0, nums.length - 1 - i, nums);
        }
    }

    // 对nums数组从0到lastIndex建大顶堆
    private void buildHeap(int[] nums, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) /2; i >= 0; i--) {
            // k保存正在判断的节点
            int k = i;
            while ((2 * k + 1) <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                if(biggerIndex < lastIndex && nums[biggerIndex] < nums[biggerIndex + 1]) {
                    // biggerIndex总是记录较大子节点的索引
                    biggerIndex ++;
                }

                // 如果k节点的值小于其较大的子节点的值
                if(nums[k] < nums[biggerIndex]){
                    // 交换他们
                    swap(k, biggerIndex, nums);
                    // 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                }else {
                    break;
                }
            }
        }
    }

    // 交换
    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 冒泡排序
     * @param nums
     */
    private void bubbleSort(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            //优化，设置一个boolean标记是否交换过
            boolean flag = false;
            for (int j = nums.length - 1; j > i; j--) {
                if(nums[j] < nums[j-1]) {
                    swap(j, j-1, nums);
                    //更改标志位
                    flag = true;
                }
            }
            //没有交换说明已经有序，提前结束
            if(flag == false) return;
        }
    }
}
