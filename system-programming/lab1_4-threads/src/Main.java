import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Created by tedromanus on 12/3/16.
 */
public class Main {

    public static final int SECONDS_DELAY = 10;

    public static void main(String []args){

        Scanner keyboard = new Scanner(System.in);
        double x;
        boolean exitFlag = false;
        boolean interrupt = true;
        int i = 1;

        System.out.printf("Enter x: ");
        x = keyboard.nextDouble();

        FThread fThread = new FThread(x);
        GThread gThread = new GThread(x);

        fThread.start();
        gThread.start();

        do try {
            sleep(500);
            if (i % (SECONDS_DELAY * 2) == 0 && interrupt) { // 10 seconds
                System.out.printf("Calculations take longer than expected. Do you want to continue?\ny - yes, yy - continue and never ask again, other - terminate\n");
                String str = keyboard.next();
                switch (str) {
                    case "y":
                        break;
                    case "yy":
                        interrupt = false;
                        break;
                    default:
                        exitFlag = true;
                        fThread.stop();
                        gThread.stop();
                        break;
                }
            }
            i++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } while(((fThread.isAlive() || gThread.isAlive()) && !exitFlag) && fThread.result * gThread.result != 0);

        if(!exitFlag)
            System.out.println("f(x)*g(x) = " + fThread.result * gThread.result);
    }
}
