<p align="center">
    <img src="https://elementsofai.s3.amazonaws.com/EoAI-IE-UCC-grey.png" width="500" alt="University College Cork Logo" />
</p>

<h1 align="center">
    Interoperability of Cloud Monitoring Data
</h1>

<p align="center">
    <em>
        In the dynamic landscape of cloud computing, effective monitoring is vital for real-time insights.
        However, the surge in diverse monitoring tools has led to incompatible data interchange formats. 
        This project addresses this challenge by systematically evaluating and selecting efficient 
        human-readable and binary serialization options, resulting in a novel data interchange format.
   </em>
</p>

<p align="center">
    <em>
        The project and its findings where also Published in: 2016 5th IEEE International Conference on Cloud Networking (Cloudnet)
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
    <img src="https://img.shields.io/github/languages/count/Martin-Bullman/interoperability-of-cloud-monitoring-Data" alt="repo-language-count">
    <img src="https://img.shields.io/github/issues/Martin-Bullman/interoperability-of-cloud-monitoring-Data" alt="repo-language-count">
<p>

<p align="center">
	<!-- default option, no dependency badges. -->
</p>
<hr>

##  Quick Links

> - [ Overview](#-overview)
> - [ Features](#-features)
> - [ Repository Structure](#-repository-structure)
> - [ Modules](#-modules)
> - [ Getting Started](#-getting-started)
>   - [ Installation](#-installation)
>   - [ Running interoperability-of-cloud-monitoring-Data](#-running-interoperability-of-cloud-monitoring-Data)
>   - [ Tests](#-tests)
> - [ Project Roadmap](#-project-roadmap)
> - [ Contributing](#-contributing)
> - [ License](#-license)
> - [ Acknowledgments](#-acknowledgments)

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

Error generating text for features: Client error '429 Too Many Requests' for url 'https://api.openai.com/v1/chat/completions'
For more information check: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/429

---

##  Repository Structure

```sh
└── interoperability-of-cloud-monitoring-Data/
    ├── config
    ├── datafiles
    ├── docs
    ├── lib
    └── src
        └── main
            ├── deserializers
            ├── formatters
            ├── formatters
            ├── helpers
            ├── message_receiver
            ├── message_sender
            └── serializers
```

##  Getting Started

***Requirements***

Ensure you have the following dependencies installed on your system:

* **Java**: `version x.y.z`

###  Installation

1. Clone the interoperability-of-cloud-monitoring-Data repository:

```sh
git clone https://github.com/Martin-Bullman/interoperability-of-cloud-monitoring-Data.git
```

2. Change to the project directory:

```sh
cd interoperability-of-cloud-monitoring-Data
```

3. Install the dependencies:

```sh
mvn clean install
```

###  Running interoperability-of-cloud-monitoring-Data

Use the following command to run interoperability-of-cloud-monitoring-Data:

```sh
java -jar target/myapp.jar
```

###  Tests

To execute tests, run:

```sh
mvn test
```

---

##  Project Roadmap

- [X] `► INSERT-TASK-1`
- [ ] `► INSERT-TASK-2`
- [ ] `► ...`

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

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

##  Acknowledgments
I would like to express my deepest appreciation to all those who provided me the possibility to complete this 
project and the accompanying report. A special gratitude I give to our final year project mentor Dr Vincent C.
Emeakaroha, whose contribution in stimulating suggestions and encouragement, helped me to coordinate my project 
especially in writing this report. 

Furthermore, I would also like to acknowledge with much appreciation the crucial role of the Systems Support
Staff of the UCC computer science department and my mentor Vincent, who gave the permission to use all required 
equipment and the necessary materials to complete the Interoperability of Cloud Monitoring Data project.
  
Finally, many thanks go to the supervisor of the project, Prof John Morrison who invested his full effort in
guiding me in achieving the goal of completing this project. I have to also appreciate the guidance and help given
by other lecturing staff with in the computer science department here in UCC, which was invaluable to the 
success of my final year project.

[**Return**](#-quick-links)

---
