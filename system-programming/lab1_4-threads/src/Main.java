/**
 * Created by tedromanus on 12/3/16.
 */
public class Main {
    public static void main(String[] args){

        Thread main = Thread.currentThread();

        new FThread(2.0).start();
        new GThread(3.0).start();

        do try {
            main.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } while(!Storage.isSet());

        System.out.println(Storage.fresult * Storage.gresult);
    }
}
