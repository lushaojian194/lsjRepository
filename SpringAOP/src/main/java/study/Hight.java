package study;

import java.util.Scanner;

public class Hight {
    public static void main(String[] args){
      System.out.print("输入一个整数(高度)：");
      Scanner input = new Scanner(System.in);
      double hight = input.nextDouble();
      System.out.print("输入次数：");
      int n = input.nextInt();
      new Hight().hightBall(hight,n);
    }
    public void hightBall(double hight,int n){
        double total = hight;//第一次落地的路程
        for(int i=0;i<n;i++) {
            hight = hight / 2; //每次弹起的高度
            if(i!=n-1){
                total = total + hight*2;  //每次落地总共走的路程
            }
        }
        System.out.println("第"+n+"次反弹的高度:"+hight);
        System.out.println("第"+n+"总的路程:"+total);
    }
}
