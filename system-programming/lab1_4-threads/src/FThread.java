/**
 * Created by tedromanus on 12/3/16.
 */
public class FThread extends Thread {

    private double x;

    FThread(double _x){
        super();

        x = _x;
    }

    private double f(double x){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return x;
    }

    @Override
    public void run(){
        Storage.setF(f(x));
    }
}
