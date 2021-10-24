package study;

public class IsLotus {
    public static void main(StringOfficial[] args){
         new IsLotus().isLotus();
    }
    public void isLotus(){
        for(int i=100;i<1000;i++){
            int a = i%10;
            int b = (i/10)%10;
            int c = (i/100)%10;
            int result = a*a*a + b*b*b + c*c*c;
            if(i==result){
               System.out.print(i+" ");
            }
        }
    }
}
