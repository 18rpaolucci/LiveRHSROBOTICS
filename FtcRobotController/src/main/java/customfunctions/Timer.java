package customfunctions;

/**
 * Created by cad1 on 10/6/2016.
 */

public class Timer extends Thread {
    private long seconds;
    public Timer(long seconds){
        this.seconds = seconds;
        this.start();
    }
    @Override
    public void run() {
        try{
            Thread.sleep(seconds);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
