<p align="center">
    <img src="https://elementsofai.s3.amazonaws.com/EoAI-IE-UCC-grey.png" width="500" alt="University College Cork Logo" />
</p>

<h1 align="center">
    Interoperability of Cloud Monitoring Data
</h1>

<p align="center">
    <em>
        In the dynamic landscape of cloud computing, effective monitoring
        is vital for real-time insights. However, the surge in diverse 
        monitoring tools has led to incompatible data interchange formats.
        This project addresses this challenge by systematically evaluating 
        and selecting efficient human-readable and binary serialization 
        options, resulting in a novel data interchange format.
   </em>
</p>

<p align="center">
    <em>
        The project and its findings where also Published in: 2016 5th IEEE
        International Conference on Cloud Networking (CloudNet)
    </em>
</p>

<p align="center">
    <a href="https://ieeexplore.ieee.org/document/7776594">IEEE Publication</a> | 
    <a href="https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data/blob/main/docs/management_for_federated_cloud_services.pdf">Research Paper</a> |
    <a href="https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data/blob/main/docs/interoperability_of_cloud_monitoring_data_fyp_report.pdf">FYP Report</a>
</p>

<p align="center">
	<img src="https://img.shields.io/github/license/Martin-Bullman/interoperability-of-cloud-monitoring-Data" alt="last-commit">
    <img src="https://img.shields.io/github/last-commit/Martin-Bullman/interoperability-of-cloud-monitoring-Data/main?style=flat" alt="license">
    <img src="https://img.shields.io/github/languages/top/Martin-Bullman/interoperability-of-cloud-monitoring-Data" alt="repo-top-language">
    <img src="https://img.shields.io/github/issues/Martin-Bullman/interoperability-of-cloud-monitoring-Data" alt="repo-language-count">
<p>

<hr>

##  Quick Links

> - [Overview](#overview)
> - [Features](#features)
> - [Repository Structure](#repository-structure)
> -  [Data Sets](#data-sets)
> - [Getting Started](#getting-started)
>   - [Installation](#installation)
>   - [Running interoperability-of-cloud-monitoring-Data](#running-interoperability-of-cloud-monitoring-Data)
>   - [Tests](#tests)
> - [Contributing](#contributing)
> - [License](#license)
> - [Acknowledgments](#acknowledgments)

---

##  Overview

The main aim of this project is to implement a new platform-independent
and efficient data interchange format for serializing and structuring 
cloud monitoring data to enable interoperability and easy management of
multi cloud service deployments. The newly implemented data interchange 
format will be integrated into an existing monitoring system to evaluate
its performance in an OpenStack cloud platform.

This project is also trying solve the problem of vendor lock-in where
companies and users of cloud computing resources are tied to a single
cloud service provider. Customer using a product or service cannot easily
transition to a competitor's product or service as the costs may be too
high.

---

##  Features

1. <b>Efficient Data Interchange Formats:</b> Development of a new data 
interchange format for serializing and structuring cloud monitoring data. 
Focus on enhancing efficiency, interoperability, and
ease of management in multi-cloud service deployments. 


2. <b>Human Readable Data Formats:</b> Ability to handle diverse 
human-readable data interchange formats, including JSON, CSV, XML, HashMap,
and ArrayList. Seamless conversion between different formats.


3. <b>Binary Data Formats:</b> Implementation of various binary 
serialization formats, such as MessagePack, Java, Hessian, CBOR, BSON,
and KRYO. Goal to minimize data size for efficient transmission across 
networks.


4. <b>Dynamic Data Recognition:</b> Intelligent application capable of 
recognizing the format of input data and determining the most efficient
serialization format. 


5. <b>Cloud Communication RabbitMQ:</b> Integration with RabbitMQ as a
message broker for transmitting binary data between different clouds.
Leveraging RabbitMQ's role in OpenStack for streamlined integration.

---

##  Technologies Used

### Java Programming Language

The selection of Java as the programming language for this project was 
driven by several compelling reasons. Firstly, Java's platform 
independence was deemed crucial, ensuring the application's ability to
run seamlessly across various platforms. Additionally, Java was a project
prerequisite and boasts excellent performance. 

### RabbitMQ Message Broker Software

For the implementation of this application, the integration of a 
messaging bus was essential to facilitate testing. To transmit binary 
data between different clouds within the OpenStack environment,
RabbitMQ was chosen as the appropriate message broker software.

This decision was guided by the seamless integration with OpenStack, 
which already utilizes RabbitMQ for communication among its various 
components such as Neutron, Nova, and Horizon. Opting for RabbitMQ
aimed to streamline the integration process and ensure compatibility 
within the OpenStack cloud environment.

---

##  Repository Structure

```sh
└── interoperability-of-cloud-monitoring-data
    ├── config
        ├── broker.properties
        ├── sender.properties
    ├── datafiles
        ├── apache_monitor_data.csv
        ├── large_json_data.json
        ├── large_metric_csv_data.csv
        ├── large_xml_data.xml
        ├── medium_json_data.json
        ├── medium_metric_csv_data.csv
        ├── medium_xml_data.xml
        ├── one_week_resource_metrics.csv 
        ├── resource_metric.csv
        ├── small_json_data.json
        ├── small_metric_csv_data.csv
        ├── small_xml_data.xml
    ├── docs
        ├── evaluation_of_binary_serialization_formats .docx
        ├── interoperability_of_cloud_monitoring_data_fyp_report.pdf
        ├── management_for_federated_cloud_services.pdf
    ├── lib
        ├── bson4jackson-2.7.0.jar
        ├── cbor-0.4.jar
        ├── cbor-java-master.jar
        ├── hessian-4.0.33.jar
        ├── jackson-annotations-2.7.0.jar
        ├── jackson-core-2.7.0.jar
        ├── jackson-databind-2.7.0-rc1.jar
        ├── jackson-dataformat-cbor-2.5.2.jar
        ├── jackson-dataformat-csv-2.7.0.jar
        ├── jackson-dataformat-yaml-2.5.1.jar
        ├── javassist-3.12.1.GA.jar
        ├── jsonSimple.jar
        ├── kryo-2.24.0.jar
        ├── minlog-1.2.jar
        ├── msgpack-0.6.12.jar
        ├── objenesis-1.2.jar
        ├── rabbitmq-client.jar
    └── src
        └── main
            ├── deserializers
                ├── BsonDeserializer.java
                ├── CborDeserializer.java
                ├── HessianDeserializer.java
                ├── JavaDeserializer.java
                ├── KryoDeserializer.java
                ├── MessagePackDeserializer.java
            ├── formatters
                ├── ArrayListFormatter.java
                ├── CsvFormatter.java
                ├── DataInterchangeFormatter.java
                ├── HashMapFormatter.java
                ├── IArrayListFormatter.java
                ├── ICsvFormatter.java
                ├── IHashMapFormatter.java
                ├── IJsonFormatter.java
                ├── IXmlFormatter.java
                ├── JsonFormatter.java
                ├── XmlFormatter.java
            ├── helpers
                ├── FileHelper.java
                ├── MapConverter.java
            ├── message_receiver
                ├── LowLevelMsgFormat.java
                ├── LowLevelResourceMetrics.java
                ├── MessageReceiver.java
                ├── MetricNames.java
                ├── Misc.java
                ├── MsgReceiverMain.java
            ├── message_sender
                ├── MessageSender.java
                ├── MessageSenderMain.java
            └── serializers
                ├── BsonSerializer.java
                ├── CborSerializer.java
                ├── HessianSerializer.java
                ├── JavaSerializer.java
                ├── KryoSerializer.java
                ├── MessagePackSerializer.java
        └── tests 
            ├── DataInterchangeTester.java
```

## Data Sets

The project utilizes various data sets to demonstrate its capabilities
and test performance across different scenarios. Below are the key data
sets employed in the testing and evaluation of the application:

#### Small Data Set:

<b>Description:</b> A small-sized data set designed to assess the 
application's performance under minimal data loads.<br>

<b>Usage:</b> Ideal for quick testing and initial system validation.

#### Medium Data Set:

<b>Description:</b> A moderately sized data set representing a more 
realistic workload.

<b>Usage:</b> Provides a balance between performance testing and
simulation of real-world data volumes.

#### Large Data Set:

<b>Description:</b> A substantial data set designed to evaluate the 
application's scalability and efficiency with large volumes of monitoring 
data.

<b>Usage:</b> Intended for comprehensive performance testing and stress-
testing the system's handling of significant data loads. 

These data sets, varying in size, enable a thorough assessment of the 
application's functionality and performance across different scales. Users
can select the appropriate data set based on their testing requirements 
and scenarios. Detailed information on the data set formats and structures
can be found in the project's documentation.

##  Getting Started

***To get started with the project, follow the steps outlined below***

### Prerequisites
Ensure you have the following dependencies installed on your system:

* **Java**: `version 21` The project is developed in Java, and you will
need a compatible Java Runtime Environment (JRE) installed.
* **RabbitMQ**: `version 3.12.11` As the project utilizes RabbitMQ for 
messaging, make sure to install and configure RabbitMQ on your system.

###  Installation

1. Clone the repository to your local machine:

```sh
git clone https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git
```

2. Navigate to the project directory:

```sh
cd interoperability-of-cloud-monitoring-data
```

3. Build the project using your preferred build tool (Ant):

```sh
ant compile
```

### Configuration

Configure RabbitMQ:

1. Update the RabbitMQ connection details in the project configuration
files(`broker.properties` and `sender.properties`).


2. Run the RabbitMQ service on your local machine (Mac)

```sh
brew services start rabbitmq
```

### Running The Application

1. Run the `MsgRecieverMain.java` file to start listening for data being sent 
through RabbitMQ.

2. Run the `MsgSenderMain.java` file to start sending data through RabbitMQ.

### Usage
Once the application is running, you can:

Experiment with different data sets by providing input in various formats.
Monitor the serialization and deserialization processes over the cmd line.
Evaluate the efficiency of different serialization formats with the provided
data sets. Refer to the project documentation for detailed information on 
available commands, configuration options, and additional features.

###  Tests

To execute tests run the `DataInterchangeTester.java` file.

---

##  Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Submit Pull Requests](https://github/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git/discussions)**: Share your insights, provide feedback, or ask questions.
- **[Report Issues](https://github/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git/issues)**: Submit bugs found or log feature requests for Interoperability-of-cloud-monitoring-data.

<details closed>
    <summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your GitHub account.
2. **Clone Locally**: Clone the forked repository to your local machine using a Git client.
   ```sh
   git clone https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to GitHub**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.

Once your PR is reviewed and approved, it will be merged into the main branch.

</details>

---

## License

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

This project is licensed under the MIT License - see the [LICENSE](https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data/blob/main/LICENSE.md) file for details.

---

##  Acknowledgments
I would like to express my deepest appreciation to all those who provided me the possibility to complete this 
project and the accompanying report. A special gratitude I give to our final year project mentor Dr Vincent C.
Emeakaroha, whose contribution in stimulating suggestions and encouragement, helped me to coordinate my project.

Furthermore, I would also like to acknowledge with much appreciation the crucial role of the Systems Support
Staff of the UCC computer science department, who gave the permission to use all required equipment and the
necessary materials to complete the Interoperability of Cloud Monitoring Data project.
  
Finally, many thanks go to the supervisor of the project, Prof John Morrison who invested his full effort in
guiding me in achieving the goal of completing this project. I have to also appreciate the guidance and help given
by other lecturing staff with in the computer science department here in UCC, which was invaluable to the 
success of my final year project.

[**Return**](#-quick-links)

---
