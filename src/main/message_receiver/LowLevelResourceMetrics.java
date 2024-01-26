package main.message_receiver;

/**
 * This class represents low-level resource metrics for monitoring a virtual machine.
 * <p>
 * It includes various metrics such as CPU utilization, network traffic, memory, disk space, and system load.
 *
 * @author Martin Bullman
 * @version 1.0
 * @since 2016-08-04
 */
public class LowLevelResourceMetrics {
	/**
	 * The unique identifier for this metric (used by Hibernate).
	 */
	private long id;

	/**
	 * The IP address of the virtual machine.
	 */
	private long vmIp;

	/**
	 * The percentage of CPU idle time.
	 */
	private double cpuIdle;

	/**
	 * The percentage of CPU time spent in the "aidle" state.
	 */
	private double cpuAIdle;

	/**
	 * The percentage of CPU usage at the user level.
	 */
	private double cpuUserLevel;

	/**
	 * The percentage of CPU usage at the system level.
	 */
	private double cpuSystemLevel;

	/**
	 * The percentage of CPU time stolen by other virtual machines.
	 */
	private double cpuSteal;

	/**
	 * The time when the system was last booted.
	 */
	private long bootTime;

	/**
	 * The number of CPUs available.
	 */
	private long numCpu;

	/**
	 * The rate of incoming network packets per second.
	 */
	private double inPackets;

	/**
	 * The rate of outgoing network packets per second.
	 */
	private double outPackets;

	/**
	 * The current system clock time.
	 */
	private long systemClock;

	/**
	 * The rate of outgoing bytes per second.
	 */
	private double outBytes;

	/**
	 * The rate of incoming bytes per second.
	 */
	private double inBytes;

	/**
	 * The amount of available free disk space.
	 */
	private double freeDisk;

	/**
	 * The CPU speed in megahertz.
	 */
	private double cpuSpeed;

	/**
	 * The total available memory in megabytes.
	 */
	private double totalMemory;

	/**
	 * The amount of free available memory in megabytes.
	 */
	private double freeMemory;

	/**
	 * The total disk size in gigabytes.
	 */
	private double totalDiskSize;

	/**
	 * The maximum used partition size in gigabytes.
	 */
	private double maxUsedPartition;

	/**
	 * The network Maximum Transfer Unit (MTU).
	 */
	private long maxTransferUnit;

	/**
	 * The name of the operating system.
	 */
	private String osName;

	/**
	 * The release version of the operating system.
	 */
	private String osRelease;

	/**
	 * The type or architecture of the machine.
	 */
	private String machineType;

	/**
	 * The percentage of time the CPU is idle while waiting for outstanding I/O requests.
	 */
	private double cpuWio;

	/**
	 * The percentage of CPU utilization while executing with nice priority.
	 */
	private double cpuNice;

	/**
	 * The amount of shared memory.
	 */
	private double sharedMemory;

	/**
	 * The amount of cached memory.
	 */
	private double cachedMemory;

	/**
	 * The amount of memory used for buffering.
	 */
	private double memoryBuffer;

	/**
	 * The total available swap space in megabytes.
	 */
	private double totalSwapSpace;

	/**
	 * The free swap space in megabytes.
	 */
	private double freeSwapSpace;

	/**
	 * The total number of processes running on the system.
	 */
	private long totalProcess;

	/**
	 * The number of processes in the "running" state.
	 */
	private long totalRunProcess;

	/**
	 * The one-minute load average.
	 */
	private double oneMinuteLoad;

	/**
	 * The five-minute load average.
	 */
	private double fiveMinutesLoad;

	/**
	 * The fifteen-minute load average.
	 */
	private double fifteenMinutesLoad;

	/**
	 * Constructs a new instance of LowLevelResourceMetrics and initializes all member variables to default values.
	 */
	public LowLevelResourceMetrics () {
		init();
	}

	/**
	 * Initializes all member variables to their default values.
	 */
	public void init () {
		vmIp = 0;
		cpuIdle = 0;
		cpuUserLevel = 0;
		cpuSystemLevel = 0;
		cpuAIdle = 0;
		cpuSteal = 0;
		bootTime = 0;
		numCpu = 0;
		inPackets = 0;
		outPackets = 0;
		systemClock = 0;
		outBytes = 0;
		inBytes = 0;
		freeDisk = 0;
		cpuSpeed = 0;
		totalMemory = 0;
		freeMemory = 0;
		totalDiskSize = 0;
		maxUsedPartition = 0;
		maxTransferUnit = 0;
		osName = "";
		osRelease = "";
		machineType = "";
		cpuWio = 0;
		cpuNice = 0;
		sharedMemory = 0;
		cachedMemory = 0;
		memoryBuffer = 0;
		totalSwapSpace = 0;
		freeSwapSpace = 0;
		totalProcess = 0;
		totalRunProcess = 0;
		oneMinuteLoad = 0;
		fiveMinutesLoad = 0;
		fifteenMinutesLoad = 0;
	}

	/**
	 * Sets the ID used by Hibernate.
	 *
	 * @param value The ID to set.
	 */
	public void setId(long value) {
		id = value;
	}

	/**
	 * Sets the IP of the virtual machine.
	 *
	 * @param value The virtual machine's IP.
	 */
	public void setVmIp(long value) {
		vmIp = value;
	}

	/**
	 * Sets the value for CPU idle.
	 *
	 * @param value The CPU idle value to set.
	 */
	public void setCpuIdle(double value) {
		cpuIdle = value;
	}

	/**
	 * Sets the value for CPU aidle.
	 *
	 * @param value The CPU aidle value to set.
	 */
	public void setCpuAIdle(double value) {
		cpuAIdle = value;
	}

	/**
	 * Sets the value for CPU usage at the user level.
	 *
	 * @param value The CPU usage at the user level to set.
	 */
	public void setCpuUserLevel(double value) {
		cpuUserLevel = value;
	}

	/**
	 * Sets the value for CPU usage at the system level.
	 *
	 * @param value The CPU usage at the system level to set.
	 */
	public void setCpuSystemLevel(double value) {
		cpuSystemLevel = value;
	}

	/**
	 * Sets the value for CPU steal.
	 *
	 * @param value The CPU steal value to set.
	 */
	public void setCpuSteal(double value) {
		cpuSteal = value;
	}

	/**
	 * Sets the value for the system boot time.
	 *
	 * @param value The system boot time to set.
	 */
	public void setBootTime(double value) {
		bootTime = (long) value;
	}

	/**
	 * Sets the value for the number of CPUs.
	 *
	 * @param value The number of CPUs to set.
	 */
	public void setNumCpu(double value) {
		numCpu = (long) value;
	}

	/**
	 * Sets the value for incoming packets per second.
	 *
	 * @param value The incoming packets per second to set.
	 */
	public void setInPackets(double value) {
		inPackets = value;
	}

	/**
	 * Sets the value for outgoing packets per second.
	 *
	 * @param value The outgoing packets per second to set.
	 */
	public void setOutPackets(double value) {
		outPackets = value;
	}

	/**
	 * Sets the value for the current system clock.
	 *
	 * @param value The current system clock to set.
	 */
	public void setSystemClock(double value) {
		systemClock = (long) value;
	}

	/**
	 * Sets the value for outgoing bytes per second.
	 *
	 * @param value The outgoing bytes per second to set.
	 */
	public void setOutBytes(double value) {
		outBytes = value;
	}

	/**
	 * Sets the value for incoming bytes per second.
	 *
	 * @param value The incoming bytes per second to set.
	 */
	public void setInBytes(double value) {
		inBytes = value;
	}

	/**
	 * Sets the value for available free disk space.
	 *
	 * @param value The available free disk space to set.
	 */
	public void setFreeDisk(double value) {
		freeDisk = value;
	}

	/**
	 * Sets the value for CPU speed.
	 *
	 * @param value The CPU speed to set.
	 */
	public void setCpuSpeed(double value) {
		cpuSpeed = value;
	}

	/**
	 * Sets the value for the total available memory.
	 *
	 * @param value The total available memory to set.
	 */
	public void setTotalMemory(double value) {
		totalMemory = value;
	}

	/**
	 * Sets the value for the free available memory.
	 *
	 * @param value The free available memory to set.
	 */
	public void setFreeMemory(double value) {
		freeMemory = value;
	}

	/**
	 * Sets the value for the total disk size.
	 *
	 * @param value The total disk size to set.
	 */
	public void setTotalDiskSize(double value) {
		totalDiskSize = value;
	}

	/**
	 * Sets the value for the maximum used disk partition.
	 *
	 * @param value The maximum used disk partition to set.
	 */
	public void setMaxUsedPartition(double value) {
		maxUsedPartition = value;
	}

	/**
	 * Sets the value for the network maximum transfer unit.
	 *
	 * @param value The network maximum transfer unit to set.
	 */
	public void setMaxTransferUnit(double value) {
		maxTransferUnit = (long) value;
	}

	/**
	 * Sets the value for the operating system name.
	 *
	 * @param value The operating system name to set.
	 */
	public void setOsName(String value) {
		osName = value;
	}

	/**
	 * Sets the value for the operating system release version.
	 *
	 * @param value The operating system release version to set.
	 */
	public void setOsRelease(String value) {
		osRelease = value;
	}

	/**
	 * Sets the value for the machine type.
	 *
	 * @param value The machine type to set.
	 */
	public void setMachineType(String value) {
		machineType = value;
	}

	/**
	 * Sets the value for the percentage of time idle CPU with outstanding I/O requests.
	 *
	 * @param value The percentage of time idle CPU with outstanding I/O requests to set.
	 */
	public void setCpuWio(double value) {
		cpuWio = value;
	}

	/**
	 * Sets the value for the percentage of CPU utilization while executing with nice priority.
	 *
	 * @param value The percentage of CPU utilization while executing with nice priority to set.
	 */
	public void setCpuNice(double value) {
		cpuNice = value;
	}

	/**
	 * Sets the value for the amount of shared memory.
	 *
	 * @param value The amount of shared memory to set.
	 */
	public void setSharedMemory(double value) {
		sharedMemory = value;
	}

	/**
	 * Sets the value for the amount of cached memory.
	 *
	 * @param value The amount of cached memory to set.
	 */
	public void setCachedMemory(double value) {
		cachedMemory = value;
	}

	/**
	 * Sets the value for the amount of memory buffer.
	 *
	 * @param value The amount of memory buffer to set.
	 */
	public void setMemoryBuffer(double value) {
		memoryBuffer = value;
	}

	/**
	 * Sets the value for the total swap space.
	 *
	 * @param value The total swap space to set.
	 */
	public void setTotalSwapSpace(double value) {
		totalSwapSpace = value;
	}

	/**
	 * Sets the value for the free swap space.
	 *
	 * @param value The free swap space to set.
	 */
	public void setFreeSwapSpace(double value) {
		freeSwapSpace = value;
	}

	/**
	 * Sets the value for the total number of processes.
	 *
	 * @param value The total number of processes to set.
	 */
	public void setTotalProcess(double value) {
		totalProcess = (long) value;
	}

	/**
	 * Sets the value for the number of running processes.
	 *
	 * @param value The number of running processes to set.
	 */
	public void setTotalRunProcess(double value) {
		totalRunProcess = (long) value;
	}

	/**
	 * Sets the value for the one-minute load average.
	 *
	 * @param value The one-minute load average to set.
	 */
	public void setOneMinuteLoad(double value) {
		oneMinuteLoad = value;
	}

	/**
	 * Sets the value for the five-minute load average.
	 *
	 * @param value The five-minute load average to set.
	 */
	public void setFiveMinutesLoad(double value) {
		fiveMinutesLoad = value;
	}

	/**
	 * Sets the value for the fifteen-minute load average.
	 *
	 * @param value The fifteen-minute load average to set.
	 */
	public void setFifteenMinutesLoad(double value) {
		fifteenMinutesLoad = value;
	}

	/**
	 * Gets the ID used by Hibernate.
	 *
	 * @return The ID used by Hibernate.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Gets the value of the virtual machine IP.
	 *
	 * @return The virtual machine IP.
	 */
	public long getVmIp() {
		return vmIp;
	}

	/**
	 * Gets the value of the CPU idle metric.
	 *
	 * @return The CPU idle metric.
	 */
	public double getCpuIdle() {
		return cpuIdle;
	}

	/**
	 * Gets the value of the CPU aidle metric.
	 *
	 * @return The CPU aidle metric.
	 */
	public double getCpuAIdle() {
		return cpuAIdle;
	}

	/**
	 * Gets the value of the CPU usage at user level.
	 *
	 * @return The CPU usage at user level.
	 */
	public double getCpuUserLevel() {
		return cpuUserLevel;
	}

	/**
	 * Gets the value of the CPU usage at system level.
	 *
	 * @return The CPU usage at system level.
	 */
	public double getCpuSystemLevel() {
		return cpuSystemLevel;
	}

	/**
	 * Gets the value of the CPU steal metric.
	 *
	 * @return The CPU steal metric.
	 */
	public double getCpuSteal() {
		return cpuSteal;
	}

	/**
	 * Gets the value of the last time the system was booted.
	 *
	 * @return The last boot time.
	 */
	public long getBootTime() {
		return bootTime;
	}

	/**
	 * Gets the value of the number of CPUs.
	 *
	 * @return The number of CPUs.
	 */
	public long getNumCpu() {
		return numCpu;
	}

	/**
	 * Gets the value of incoming packets per second.
	 *
	 * @return Incoming packets per second.
	 */
	public double getInPackets() {
		return inPackets;
	}

	/**
	 * Gets the value of outgoing packets per second.
	 *
	 * @return Outgoing packets per second.
	 */
	public double getOutPackets() {
		return outPackets;
	}

	/**
	 * Gets the value of the current system clock.
	 *
	 * @return The current system clock.
	 */
	public long getSystemClock() {
		return systemClock;
	}

	/**
	 * Gets the value of outgoing bytes per second.
	 *
	 * @return Outgoing bytes per second.
	 */
	public double getOutBytes() {
		return outBytes;
	}

	/**
	 * Gets the value of incoming bytes per second.
	 *
	 * @return Incoming bytes per second.
	 */
	public double getInBytes() {
		return inBytes;
	}

	/**
	 * Gets the value of available free disk space.
	 *
	 * @return Available free disk space.
	 */
	public double getFreeDisk() {
		return freeDisk;
	}

	/**
	 * Gets the value of the CPU speed metric.
	 *
	 * @return CPU speed.
	 */
	public double getCpuSpeed() {
		return cpuSpeed;
	}

	/**
	 * Gets the value of the total available memory.
	 *
	 * @return Total available memory.
	 */
	public double getTotalMemory() {
		return totalMemory;
	}

	/**
	 * Gets the value of free available memory.
	 *
	 * @return Free available memory.
	 */
	public double getFreeMemory() {
		return freeMemory;
	}

	/**
	 * Gets the value of the total disk size metric.
	 *
	 * @return Total disk size.
	 */
	public double getTotalDiskSize() {
		return totalDiskSize;
	}

	/**
	 * Gets the value of the maximum used disk partition metric.
	 *
	 * @return Maximum used disk partition.
	 */
	public double getMaxUsedPartition() {
		return maxUsedPartition;
	}

	/**
	 * Gets the value of the network maximum transfer unit.
	 *
	 * @return Network maximum transfer unit.
	 */
	public long getMaxTransferUnit() {
		return maxTransferUnit;
	}

	/**
	 * Gets the value of the operating system name.
	 *
	 * @return Operating system name.
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * Gets the value of the operating system release version.
	 *
	 * @return Operating system release version.
	 */
	public String getOsRelease() {
		return osRelease;
	}

	/**
	 * Gets the value of the machine type.
	 *
	 * @return Machine type.
	 */
	public String getMachineType() {
		return machineType;
	}

	/**
	 * Gets the value of the percentage of CPU idle time with outstanding I/O requests.
	 *
	 * @return The percentage of CPU idle time with outstanding I/O requests.
	 */
	public double getCpuWio() {
		return cpuWio;
	}

	/**
	 * Gets the value of the percentage of CPU utilization while executing with nice priority.
	 *
	 * @return The percentage of CPU utilization while executing with nice priority.
	 */
	public double getCpuNice() {
		return cpuNice;
	}

	/**
	 * Gets the value of the total amount of shared memory.
	 *
	 * @return The total amount of shared memory.
	 */
	public double getSharedMemory() {
		return sharedMemory;
	}

	/**
	 * Gets the value of the amount of cached memory.
	 *
	 * @return The amount of cached memory.
	 */
	public double getCachedMemory() {
		return cachedMemory;
	}

	/**
	 * Gets the value of the amount of memory buffer.
	 *
	 * @return The amount of memory buffer.
	 */
	public double getMemoryBuffer() {
		return memoryBuffer;
	}

	/**
	 * Gets the value of total amount of swap space.
	 *
	 * @return Total amount of swap space.
	 */
	public double getTotalSwapSpace() {
		return totalSwapSpace;
	}

	/**
	 * Gets the value of the amount of free swap space.
	 *
	 * @return The amount of free swap space.
	 */
	public double getFreeSwapSpace() {
		return freeSwapSpace;
	}

	/**
	 * Gets the value of the total number of processes.
	 *
	 * @return The total number of processes.
	 */
	public long getTotalProcess() {
		return totalProcess;
	}

	/**
	 * Gets the value of the number of running processes.
	 *
	 * @return The number of running processes.
	 */
	public long getTotalRunProcess() {
		return totalRunProcess;
	}

	/**
	 * Gets the value of the one-minute load average.
	 *
	 * @return The one-minute load average.
	 */
	public double getOneMinuteLoad() {
		return oneMinuteLoad;
	}

	/**
	 * Gets the value of the five-minute load average.
	 *
	 * @return The five-minute load average.
	 */
	public double getFiveMinutesLoad() {
		return fiveMinutesLoad;
	}

	/**
	 * Gets the value of the fifteen-minute load average.
	 *
	 * @return The fifteen-minute load average.
	 */
	public double getFifteenMinutesLoad() {
		return fifteenMinutesLoad;
	}
}

