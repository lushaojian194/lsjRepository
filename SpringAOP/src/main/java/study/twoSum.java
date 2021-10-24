package study;

import java.util.HashMap;
import java.util.Map;

public class twoSum {
    public static void main(StringOfficial[] args){
        int [] number = {2,5,7,9,6,8};
        int target = 9;
        int [] index = new twoSum().twoIndex(target,number);
        System.out.println("下标:"+index[0]+",下标："+index[1]);
    }

    public int[] twoIndex(int target,int[] number){
        int [] index = new int[2];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<number.length;i++){
            if(map.containsKey(number[i])){
                index[0] = map.get(number[i]);
                index[1] = i;
                return index;
            }else{
                map.put(target-number[i],i);
            }
        }
        return null;
    }
}
