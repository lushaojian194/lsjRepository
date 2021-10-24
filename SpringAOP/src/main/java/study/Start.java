package study;

public class Start {
    public static void main(StringOfficial[] args) {
        int n = 3;
        printStar(n);

    }
      //打印星星
    private static void printStar(int n) {
      //打印上半部分
        for(int i=0;i<n;i++){
            for(int j=i;j<n-1;j++){
                System.out.print(" ");
            }
            if(i==0){
                System.out.println("*");
                continue;
            }
            System.out.print("*");
           /* for(int k=0;k<2*i+1;k++){
                System.out.print("*");
            }*/
            for(int k=0;k<2*(i-1)+1;k++){
                System.out.print(" ");
            }
            System.out.println("*");

        }

        for(int i=n-2;i>=0;i--){
            for(int j=i;j<=n-2;j++){
                System.out.print(" ");
            }

            if(i==0){
                System.out.println("*");
                break;
            }
            System.out.print("*");
          /*  for(int k=0;k<2*i+1;k++){
                System.out.print("*");
            }*/
            for(int k=0;k<i*2-1;k++){
                System.out.print(" ");
            }
            System.out.println("*");
        }

    }
}
