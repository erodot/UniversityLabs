/**
 * Created by tedromanus on 12/3/16.
 */
public class GThread extends Thread {
    private double x;

    GThread(double _x){
        super();

        x = _x;
    }

    private double g(double x){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return x;
    }

    @Override
    public void run(){
        Storage.setG(g(x));
    }
}
