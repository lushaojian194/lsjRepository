package study;

import java.util.Scanner;

public class Decompose {
    public static void main(StringOfficial[] args){
        System.out.print("请输入一个大于1的正整数：");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        new Decompose().decompose(number);
    }
    public void decompose(int number){
        System.out.print(number+"=");

        for(int i=2;i<=number;i++){
            while(number%i==0 && number!=i){
                number = number/i;
                System.out.print(i+"*");
            }
            if(number==i){
                System.out.println(i);
                break;
            }
        }

    }

}

