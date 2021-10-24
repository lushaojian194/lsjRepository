package study;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args){
      // System.out.print("请输入一个正整数：");
        Scanner input = new Scanner(System.in);
       // int b = input.nextInt();
       // System.out.println("结果："+new Factorial().factorial(b));
        int sum = 0;
        Factorial fa = new Factorial();
        for(int i=1 ;i<=5; i++){
            sum = sum + fa.factorial(i);
        }
        System.out.println("result:"+sum);
    }
    //递归求整数n
    public  int factorial(int a){
        if(a==1){
            return 1;
        }
        return a*factorial(a-1);
    }

}
