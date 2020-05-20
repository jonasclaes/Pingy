package be.jonasclaes.pingy.timertask;

import be.jonasclaes.pingy.network.ICMP6;

import java.util.Timer;
import java.util.TimerTask;

public class ICMP6Task extends TimerTask {
    String ipAddress;
    Timer timer;

    public ICMP6Task(String ipAddress, int period) {
        this.ipAddress = ipAddress;

        this.timer = new Timer();
        this.timer.schedule(this, 0, period);
    }

    @Override
    public void run() {
        ICMP6.sendRequest(this.ipAddress);
    }
}
