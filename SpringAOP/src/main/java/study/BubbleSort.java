package study;

public class BubbleSort {
    public static void main(String[] args){
        int[] num = {2,1,3,8,5,6,4,7,9,10};
        System.out.print("排序前：");
        for(int i=0;i<num.length;i++){
            System.out.print(num[i]+",");
        }
        System.out.print("排序后：");
        int[] number = new BubbleSort().bubbleSort(num);
        for(int i=0;i<number.length;i++){
            System.out.print(number[i]+",");
        }
    }

    public int[] bubbleSort(int [] num){
        int temp = 0;
        for(int i=0;i<num.length-1;i++){
            for(int j=i;j<num.length-1;j++){
               if(num[j+1]<num[j]){
                   temp = num[j];
                   num[j] = num[j+1];
                   num[j+1] = temp;
               }
            }
        }
        return num;
    }
}
