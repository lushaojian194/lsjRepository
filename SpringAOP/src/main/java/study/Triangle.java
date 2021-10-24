package study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Triangle {
    public static void main(String[] args){
        System.out.print("输入一个正整数：");
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        List<int[]> listArray = new ArrayList<int[]>();
        int[] b = {1};
        int[] c ={1,1};
        listArray.add(b);
        listArray.add(c);

        for(int i=2;i<a;i++){
            int[] d = new int[i+1];
            for(int j=0;j<=i;j++){
                if(j==0 || j==i){
                    d[j] = 1;
                }else{
                    d[j] = listArray.get(i-1)[j-1] + listArray.get(i-1)[j];
                }
            }
            listArray.add(d);
        }

        for(int i=0;i<listArray.size();i++){
            //输出空格
            for(int k=i;k<listArray.size();k++){
                System.out.print(" ");
            }
             for(int j=0;j<listArray.get(i).length;j++){
                 System.out.print(listArray.get(i)[j]+" ");
             }
             System.out.println();
        }
    }
}
