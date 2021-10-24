package study;

public class IsCompSqrt {
    public static void main(String[] args){
    for(int i=1;i<100;i++){
       if(new IsCompSqrt().isCompSqrt(i+10)){
           System.out.println(i);
       }
    }
       // new IsCompSqrt().isCompSqrt(90+10);
    }
    public boolean isCompSqrt(int n){
        boolean flag = false;
        for(int i=1;i<=Math.sqrt(n);i++){
            if(n==Math.pow(i,2)){
                flag = true;
                break;
            }
        }
       return flag;
    }
}
