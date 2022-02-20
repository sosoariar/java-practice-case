package com.soso;

public class MinDemo {

    public static void main(String[] args) {

        int[] nums = {5,4,3,2,1};

        int min = Integer.MAX_VALUE;

        for(int i:nums){
            if(i<min){
                min = i;
            }
        }
        System.out.println(min);

    }
}
