package be.jonasclaes.pingy.timertask;

import be.jonasclaes.pingy.network.ICMP;

import java.util.Timer;
import java.util.TimerTask;

public class ICMPTask extends TimerTask {
    String ipAddress;
    Timer timer;

    public ICMPTask(String ipAddress, int period) {
        this.ipAddress = ipAddress;

        this.timer = new Timer();
        this.timer.schedule(this, 0, period);
    }

    @Override
    public void run() {
        ICMP.sendRequest(this.ipAddress);
    }
}
