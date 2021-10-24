package study;

import java.util.Scanner;

public class CountNumAndLetter {
    public static void main(StringOfficial[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入：");
        String str = input.next();
        int letter = 0;
        int num = 0;
        StringBuffer stNum = new StringBuffer();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch>='0' && ch<='9'){
                num = num + 1;
                stNum = stNum.append(ch);
            }
            if((ch>='A' && ch<='Z') || (ch>='a' && ch<='z')){
                letter = letter + 1;
            }
        }
        String afterStr = str.replace("a","b");
        System.out.println("英文个数："+letter);
        System.out.println("数字个数："+num);
        System.out.println("把a替换为b的字符串为："+afterStr);
        System.out.println("字符串里的所有数字为："+stNum);
    }
}
