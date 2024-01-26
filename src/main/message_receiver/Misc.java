package main.message_receiver;

import java.util.*;

/**
 * The "Misc" class is responsible for handling miscellaneous operations related to monitoring and extracting
 * performance metrics from virtual machines. It contains methods for printing metrics and extracting
 * detailed metrics for each virtual machine. Additionally, it includes a constructor for initializing
 * instances of this class.
 * <p>
 * This class plays a crucial role in processing and managing performance data from multiple VMs,
 * making it a fundamental component of the monitoring system.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class Misc {

	/**
	 * Class constructor for the "Misc" class. It doesn't perform specific actions but allows
	 * the creation of instances of this class.
	 */
	public Misc() {

	}

	/**
	 * Prints the metrics for monitored virtual machines.
	 *
	 * @param hostDetails A map containing monitored VMs and their metrics.
	 * @param rate The monitoring rate.
	 * @param time The timestamp for monitoring.
	 */
	public void printMetrics(Map<String, Map<String, String>> hostDetails, String rate, String time) {
		Map<String, String> host = null; // Stores metrics for a specific host
		Set<String> key1 = null; // Set of keys in the hostDetails map
		Set<String> key2 = null; // Set of keys for metrics within a host
		int count = 0; // Count of host machines

		System.out.println("Time Stamp for Monitoring is: " + time + " and Monitoring Intervals in seconds is: " + Integer.parseInt(rate) / 1000);

		// Extract the IP address of the machine
		key1 = hostDetails.keySet();
		for (String inkey : key1) {
			count++;
			System.out.println("Host machine " + count + " details: ");
			System.out.println("    IP address: " + inkey);

			// Extract the metric-value buffer for each VM identified by IP
			host = hostDetails.get(inkey);
			key2 = host.keySet();

			// Extract and print the metric values of the machine
			for (String inkey2 : key2) {
				// Print the metric name and its value for the current VM
				System.out.println("     " + inkey2 + " : " + host.get(inkey2));
			}
		}
	}

	/**
	 * Extracts metrics for each virtual machine and stores them in a "LowLevelResourceMetrics" object.
	 *
	 * @param hostDetails A map containing monitored VMs and their metrics.
	 */
	public void extractVmMetrics (Map<String, Map<String, String>> hostDetails) {
         LowLevelResourceMetrics metrics = new LowLevelResourceMetrics();
		 Map<String, String> host = null;
		 Set<String> key1 = null;
		 Set<String> key2 = null;
		 int count = 0;
		 String strip;
		 long ip = 0;

		 // extract the IP address of the machine
		 key1 = hostDetails.keySet();

		 for(String inkey:key1){
			 count++;

			 try {
		 	 	 strip = removeChar(inkey, '.'); // remove the points in the ip address.
				 ip = Long.parseLong(strip.trim());
				 metrics.setVmIp(ip); // add the striped IP to the low level metrics
			 }
			 catch (NumberFormatException ne) {
		 		 System.err.println("Converting string IP to double failed: " + ne.getMessage());
			 }

			 // extract the metric-value buffer for each Vm identified by IP
			 host = hostDetails.get(inkey);
			 key2 = host.keySet();

			 // extract the metric values of each identified VM and add them to the class object
			 for(String inkey2 : key2) {
				 switch (MetricNames.thisName(inkey2)) {
					case cpu_idle:
						metrics.setCpuIdle(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_aidle:
						metrics.setCpuAIdle(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_steal:
						metrics.setCpuSteal(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_user:
						metrics.setCpuUserLevel(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_system:
						metrics.setCpuSystemLevel(Double.parseDouble(host.get(inkey2)));
						break;
					case mem_free:
						metrics.setFreeMemory(Double.parseDouble(host.get(inkey2)));
						break;
					case mem_total:
						metrics.setTotalMemory(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_num:
						metrics.setNumCpu(Double.parseDouble(host.get(inkey2)));
						break;
					case pkts_in:
						metrics.setInPackets(Double.parseDouble(host.get(inkey2)));
						break;
					case boottime:
						metrics.setBootTime(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_speed:
						metrics.setCpuSpeed(Double.parseDouble(host.get(inkey2)));
						break;
					case pkts_out:
						metrics.setOutPackets(Double.parseDouble(host.get(inkey2)));
						break;
					case disk_free:
						metrics.setFreeDisk(Double.parseDouble(host.get(inkey2)));
						break;
					case part_max_used:
						metrics.setMaxUsedPartition(Double.parseDouble(host.get(inkey2)));
						break;
					case bytes_in:
						metrics.setInBytes(Double.parseDouble(host.get(inkey2)));
						break;
					case bytes_out:
						metrics.setOutBytes(Double.parseDouble(host.get(inkey2)));
						break;
					case disk_total:
						metrics.setTotalDiskSize(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_wio:
						metrics.setCpuWio(Double.parseDouble(host.get(inkey2)));
						break;
					case cpu_nice:
						metrics.setCpuNice(Double.parseDouble(host.get(inkey2)));
						break;
					case mem_shared:
						metrics.setSharedMemory(Double.parseDouble(host.get(inkey2)));
						break;
					case mem_cached:
						metrics.setCachedMemory(Double.parseDouble(host.get(inkey2)));
						break;
					case mem_buffers:
						metrics.setMemoryBuffer(Double.parseDouble(host.get(inkey2)));
						break;
					case swap_total:
						metrics.setTotalSwapSpace(Double.parseDouble(host.get(inkey2)));
						break;
					case swap_free:
						metrics.setFreeSwapSpace(Double.parseDouble(host.get(inkey2)));
						break;
					case proc_total:
						metrics.setTotalProcess(Double.parseDouble(host.get(inkey2)));
						break;
					case proc_run:
						metrics.setTotalRunProcess(Double.parseDouble(host.get(inkey2)));
						break;
					case load_one:
						metrics.setOneMinuteLoad(Double.parseDouble(host.get(inkey2)));
						break;
					case load_five:
						metrics.setFiveMinutesLoad(Double.parseDouble(host.get(inkey2)));
						break;
					case load_fifteen:
						metrics.setFifteenMinutesLoad(Double.parseDouble(host.get(inkey2)));
						break;
					default:
						//System.err.println("Unknown metric name");
						break;
				 }
			 }

			 System.out.println("Virtual machine " + count +" with IP address: " + inkey + " extracted");
			 // Add here the Hibernate code to input each vm into your DB
		 }
	 }

	/**
	 * Removes specified characters from a given string.
	 *
	 * @param string The input string.
	 * @param character The character to be removed.
	 * @return A new string with the specified characters removed.
	 */
	private String removeChar(String string, char character) {
		StringBuilder cleanedString = new StringBuilder();

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != character) {
				cleanedString.append(string.charAt(i));
			}
		}

		return cleanedString.toString();
	}
}
