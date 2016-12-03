/**
 * Created by tedromanus on 12/3/16.
 */
public class GThread extends Thread {

    private double x;
    public volatile double result = 1;

    GThread(double _x){
        super();

        x = _x;
    }

    private double g(double x) throws InterruptedException {
//        if(interrupted())
//            throw new InterruptedException();

        return 1/x;
    }

    @Override
    public void run(){
        try {
            result = g(x);
        } catch(InterruptedException ex){
            return;
        }
    }
}
