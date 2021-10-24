package study;

import java.util.Scanner;

public class Rabbit {
    public static void main(StringOfficial[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入月份：");
        int month = input.nextInt();
        System.out.println("兔子对数为:"+ new Rabbit().rabbit(month));
    }

    public int rabbit(int month){
        if(month==1 || month==2){
            return 1;
        }else{
            return rabbit(month-2) + rabbit(month-1);
        }
    }
}
