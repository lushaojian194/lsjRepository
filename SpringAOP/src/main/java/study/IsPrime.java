package study;

public class IsPrime {
    public static void main(StringOfficial[] args){
        new IsPrime().isprime();
    }

    public void isprime(){
        for(int i=2;i<100;i++){
            boolean flag = true;
            for(int j=2;j<=Math.sqrt(i);j++){
                if(i%j==0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.print(i+"  ");
            }
        }
    }
}
