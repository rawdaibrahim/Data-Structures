package DataStructures;
import com.company.*;

public class DivideConquer {
    public static void main(String[] args) {
        StopWatch w = new StopWatch();
        w.start();
        System.out.println( power(5,222)) ;
        w.stop();
        System.out.println(w.getElapsedTime());
        w.start();
        System.out.println( powerBad(5,222)) ;
        w.stop();
        System.out.println(w.getElapsedTime());

    }
    public static double power(double base, int power){
        double num =0;
        if(power==0) return 1;
        num=power(base,power/2);
        if (power%2==0) return num*num;
        else return num*num*base;
    }
    public static double powerBad(double base, int power){
        double num=1;
        for(int i=1; i<=power; i++){
            num*=base;
        }
        return num;
    }
}
