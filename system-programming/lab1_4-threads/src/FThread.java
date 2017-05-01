/**
 * Created by tedromanus on 12/3/16.
 */
public class FThread extends Thread {

    private double x;
    public volatile double result = 1;

    FThread(double _x){
        super();

        x = _x;
    }

    private double f(double x) throws InterruptedException {
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
