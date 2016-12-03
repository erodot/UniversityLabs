/**
 * Created by tedromanus on 12/3/16.
 */
public class Storage {
    private static Storage ourInstance = new Storage();

    public static Storage getInstance() {
        return ourInstance;
    }

    private Storage() {
    }

    public static volatile Double fresult = null;
    public static volatile Double gresult = null;

    public static void setF(double f){
        fresult = f;
    }

    public static void setG(double g){
        gresult = g;
    }

    public static boolean isSet(){
        return (fresult != null && gresult != null);
    }
}
