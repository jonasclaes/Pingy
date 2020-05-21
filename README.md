# Pingy
A Java software tool to monitor a connection by sending out ICMP requests.

The software logs it to a JSON file, which has an array of objects which specify the protocol and timestamp of when the connection failed.
The ICMP request is executed every 500ms, this will be changeable in the future.

## Usage
Launch pingy using the following command.

`java [PARAMS] -jar .\Pingy-all-1.1-SNAPSHOT.jar`

Where `[PARAMS]` is you should use one of the following parameters.
- `-DtestICMP="true"` test for ICMPv4.
- `-DtestICMP6="true"` test for ICMPv6.
- `-Dipv4addr="xxx.xxx.xxx.xxx"` set the IPv4 address to ping to, default is 8.8.8.8.
- `-Dipv6addr="::xxxx"` set the IPv6 address to ping to, default is 2001:4860:4860::8888.
- `-Dfilename="xxx.json"` set the filename to log to, default is connection_lost.json.
- `-DprintICMPSuccess="true"` print out when ICMPv4 was successfull.
- `-DprintICMP6Success="true"` print out when ICMPv6 was successfull.
