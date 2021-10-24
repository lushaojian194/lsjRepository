package study;

import java.util.Scanner;

public class CharToNumber {
      public static void main(StringOfficial[] args){
          String [] english = { "zero","one","two","three","four","five",
                  "six","seven","eight","nine"
          };
          Scanner input = new Scanner(System.in);
          System.out.print("请输入一个字符串: ");
          String str = input.next();
          String st = new CharToNumber().EnligshToNumber(str,english);
          System.out.println(st);
      }

      public String EnligshToNumber(String str, String [] english){
          for(int i=0;i< english.length;i++){
              str = str.replace(english[i], String.valueOf(i));
          }
          return str;
      }
}
