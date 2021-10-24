package study;

import java.util.Scanner;

public class Reverse {
    public static void main(StringOfficial[] args){
        System.out.print("请输入一个整数：");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        System.out.println("反数: "+new Reverse().reverse(num));
    }
    public int reverse(int number){
        if(number==0){
            return 0;
        }
        int b = 0;
        while(number!=0){
            b = b*10 + number%10;
            number = number/10;
        }

        return b;
    }
}
