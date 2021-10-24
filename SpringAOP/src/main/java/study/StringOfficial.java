package study;

public class StringOfficial {
    public static void main(String[] args){

        new StringOfficial().strReplaceAll();
    }
    //长度
    public void StringLeagth(){
      String str = "lushaojian";
      int length = str.length();
      System.out.println("长度："+length);
    }
    //比较是否相等
    public void StringEquals(){
        String str = "lu";
        String st1 = "lu";
        String st2 = "sh";
        boolean flag = str.equals(st1);
        boolean flag2 = st1.equals(st2);
        System.out.println(flag);
        System.out.println(flag2);
    }
    //返回字符出现的位置的下标
    public void Location(){
        String str = "lunshaojsian";
        int num1 = str.indexOf("n");
        int num2 = str.indexOf("s",4);
        int num3 = str.lastIndexOf("s");
        int num4 = str.lastIndexOf("s",5);
        System.out.println(num1);  // 2
        System.out.println(num2);  // 8
        System.out.println(num3);  // 8
        System.out.println(num4);  // 3
    }
    //获取字符串中某个位置的字符
    public void getChar(){
        String str = "lushaojian";
        char ch = str.charAt(3);
        System.out.println(ch); //h
    }
    //截取字符串
    public void SubStringt(){
        String str = "lushaojian";
        String st = str.substring(2,6);
        String st1 = str.substring(4);
        System.out.println(st);  // shao
        System.out.println(st1); // aojian
    }
    //字符串以什么开始或结尾
    public void valid(){
        String str = "lushaojian";
        boolean flag = str.startsWith("l");
        boolean flag2 = str.endsWith("o");
        System.out.println(flag);   //true
        System.out.println(flag2);  //false
    }
    //字符串连接
    public void stConcat(){
        String st = "lushao";
        String str = "jian";
        String stre = st.concat(str);
        System.out.println(stre);  // lushaojian
    }
    //字符串的大小写转换
    public void stUpper(){
        String str = "luSHAOjian";
        String st = str.toUpperCase();
        String stt = str.toLowerCase();
        System.out.println(st);  //LUSHAOJIAN
        System.out.println(stt); //lushaojian
    }
    //字符串的替换
    public void strReplace(){
        String str = "lushaojian";
        String st = str.replace("shao","hai");
        String stt = str.replaceFirst("s","y");
        System.out.println(st);  // luhaijian
        System.out.println(stt); //luyhaoyjian
    }

    public void strReplaceAll(){
        String str = "com.hell.";
        //replaceAll的第一个参数是匹配正则表达式的,正则表达式中 . 代表任何的字符
        String st = str.replaceAll(".","a");
        System.out.println(st);   //   aaaaaaaaa

    }
    //字符串清除前后的空格
    public void clear(){
        String str = "  lushao  jian  ";
        System.out.println(str.length()); //16
        String st = str.trim();   //去除字符串前后的空格
        System.out.println(st);  //lushao jian
        System.out.println(st.length());  //12
    }
    //字符串的分割
    public void StrSplit(){
        String str = "lu,shao,jian;fly;swin";
        String [] st = str.split(",|;"); // 按照 符合  , 或 ; 进行分割
        for(String stre :st){
            System.out.println(stre);
        }
    }
    //查看字符串中是否含有指定字符串
    public void stContains(){
        String str = "lushaojian";
        boolean flag = str.contains("seh");
        System.out.println(flag);  //false
    }
    //转为字符数组
    public void toCharArray(){
        String str = "lushaojian";
        char[] st = str.toCharArray();
        for(char ch:st){
            System.out.println(ch);
        }
    }
}
