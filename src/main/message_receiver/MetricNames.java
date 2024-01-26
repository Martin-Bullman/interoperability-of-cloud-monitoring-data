package main.message_receiver;

/**
 * An enumerator listing important virtual machine metric values.
 * <p>
 * These values represent various metrics related to system performance and configuration.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public enum MetricNames {
	// Memory Metrics
	mem_free, mem_total, mem_shared, mem_cached, mem_buffers, swap_free, swap_total,

	// Boot Time Metric
	boottime,

	// CPU Metrics
	cpu_idle, cpu_user, cpu_system, cpu_wio, cpu_nice, cpu_num, cpu_speed, pkts_in, pkts_out, bytes_in, bytes_out, cpu_steal, cpu_aidle,

	// Process Metrics
	proc_total, proc_run, app_cpu, app_mem, app_ops,

	// System Load Metrics
	load_one, load_five, load_fifteen,

	// Disk Metrics
	part_max_used, disk_free, disk_total,

	// Network Metrics
	mtu, vm_ip,

	// Operating System Metrics
	os_name, os_release, machine_type,

	// Default Value for Unrecognized Metrics
	unknown;

	/**
	 * Get the MetricNames enumeration value based on a string representation.
	 *
	 * @param str The string representation of the metric name.
	 * @return The corresponding MetricNames value, or 'unknown' if not found.
	 */
	public static MetricNames thisName (String str) {
		try {
			return valueOf(str.toLowerCase());
		}
		catch (Exception ex) {
			return unknown;
		}
	}
}
