/**
 * Created by tedromanus on 12/3/16.
 */
public class GThread extends Thread {
    private double x;
    public volatile double result;

    GThread(double _x){
        super();

        x = _x;
    }

    private double g(double x) throws InterruptedException {
        for(int i=0; i<500; i++) {
            if(interrupted())
                throw new InterruptedException();
            sleep(50);
        }

        return 1 / x;
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
