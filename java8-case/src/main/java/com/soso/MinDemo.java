package com.soso;

import java.util.stream.IntStream;

public class MinDemo {

    public static void main(String[] args) {

        int[] nums = {5,4,3,2,1};

        // 命令式
        int min = Integer.MAX_VALUE;

        for(int i:nums){
            if(i<min){
                min = i;
            }
        }
        System.out.println(min);

        // 函数式
        int min2 = IntStream.of(nums).min().getAsInt();
        System.out.println(min2);

    }
}
