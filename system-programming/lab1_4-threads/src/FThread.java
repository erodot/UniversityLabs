/**
 * Created by tedromanus on 12/3/16.
 */
public class FThread extends Thread {

    private double x;
    public volatile double result;

    FThread(double _x){
        super();

        x = _x;
    }

    private double f(double x) throws InterruptedException {
       for(int i=0; i<5000; i++) {
           if(interrupted())
               throw new InterruptedException();
           sleep(50);
       }

        return x*x;
    }

    @Override
    public void run(){
        try {
            result = f(x);
        } catch(InterruptedException ex){
            return;
        }
    }
}
