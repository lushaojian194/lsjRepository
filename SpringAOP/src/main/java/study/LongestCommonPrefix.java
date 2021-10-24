package study;

public class LongestCommonPrefix {
    public static void main(String[] args){
        String [] str = {"flwer","flowest","fotect","flocwwc"};
        String st = new LongestCommonPrefix().LongestCommon(str);
        System.out.println(st);
    }

    public String LongestCommon(String[] str){
        if(str.length == 0){
            return "";
        }
        String s = str[0];
        for(String st:str){
            if(st.length()==0){
                return "";
            }
            while(!st.startsWith(s)){
                s = s.substring(0,s.length()-1);
            }
        }
        return s;
    }
}
