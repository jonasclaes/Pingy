package be.jonasclaes.pingy.network;

import be.jonasclaes.pingy.filestore.FileStore;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ICMP6 {
    @SuppressWarnings("unchecked")
    public static void sendRequest(String ipAddress) {
        try {
            // Parse IPv6 address.
            InetAddress address = Inet6Address.getByName(ipAddress);

            // Check if IPv6 address is reachable.
            long startTime = System.currentTimeMillis();
            if (address.isReachable(500)) {
                long endTime = System.currentTimeMillis();
                long timeDiff = endTime - startTime;

                // Get boolean from System.getProperty().
                boolean printICMP6Success = Boolean.getBoolean("printICMP6Success");
                if (printICMP6Success) {
                    Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
                    ZoneId zoneId = ZoneId.systemDefault();
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d HH:mm:ss.SSS");
                    String timeString = localDateTime.format(dateTimeFormatter);

                    System.out.println(String.format("[%s] %s reachable using IPv6 with a round-trip time of %dms.", timeString, ipAddress, timeDiff));
                }
            } else {
                Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d HH:mm:ss.SSS");
                String timeString = localDateTime.format(dateTimeFormatter);

                System.out.println(String.format("[%s] %s unreachable using IPv6 within 500ms.", timeString, ipAddress));
                JSONObject object = new JSONObject();
                object.put("timestamp", System.currentTimeMillis());
                object.put("protocol", "IPv6");
                FileStore.getInstance().addRecord(object);
            }
        } catch (UnknownHostException e) {
            System.err.println(String.format("Unknown host %s", ipAddress));
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(String.format("IOException occurred whilst trying to reach %s", ipAddress));
            e.printStackTrace();
        }
    }
}
