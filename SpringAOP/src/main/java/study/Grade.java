package study;

import java.util.Scanner;

public class Grade {
    public static void main(StringOfficial[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你的分数：");
        int gra = input.nextInt();
        System.out.println(new Grade().grade(gra));
    }
    public String grade(int yourGrade){
        return((yourGrade>90)?"等级优秀":((yourGrade>60)?"等级良好":"不及格，还需继续努力"));
    }
}
