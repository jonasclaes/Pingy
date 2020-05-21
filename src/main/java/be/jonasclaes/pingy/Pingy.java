package be.jonasclaes.pingy;

import be.jonasclaes.pingy.filestore.FileStore;
import be.jonasclaes.pingy.timertask.ICMP6Task;
import be.jonasclaes.pingy.timertask.ICMPTask;

public class Pingy {
    public static void main(String[] args) {
        System.out.println("Starting up Pingy...");

        // Initialize program.

        // Open file;
        FileStore fileStore;
        if (System.getProperty("filename") != null) {
            fileStore = FileStore.getInstance(System.getProperty("filename"));
        } else {
            fileStore = FileStore.getInstance("conn_lost.json");
        }

        // Add shutdown hook.
        Runtime.getRuntime().addShutdownHook(new Thread(fileStore::write));

        System.out.println("Pingy has been started.");

        // Create new ICMP ping task.
        boolean testICMP = Boolean.getBoolean("testICMP");
        if (testICMP) {
            System.out.println("Doing ICMPv4 test.");

            ICMPTask icmpTask;
            if (System.getProperty("ipv4addr") != null) {
                icmpTask = new ICMPTask(System.getProperty("ipv4addr"), 500);
            } else {
                icmpTask = new ICMPTask("8.8.8.8", 500);
            }
        }

        // Create new ICMP6 ping task.
        boolean testICMP6 = Boolean.getBoolean("testICMP6");
        if (testICMP6) {
            System.out.println("Doing ICMPv6 test.");

            ICMP6Task icmp6Task;
            if (System.getProperty("ipv6addr") != null) {
                icmp6Task = new ICMP6Task(System.getProperty("ipv6addr"), 500);
            } else {
                icmp6Task = new ICMP6Task("2001:4860:4860::8888", 500);
            }
        }
    }
}
