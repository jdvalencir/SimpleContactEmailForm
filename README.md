# RevAIsor Tecnical Interview

**https://revaisortest.teleafit.tech/**

This project is a Java Spring Boot application designed to provide users with a personalized contact form for reaching out to RevAIsor. Whether you have questions, feedback, or inquiries, this contact form makes it easy for you to get in touch with RevAIsor 

## Setup Instructions

To set up and run the project locally, follow these steps:

### 1. Clone the repository:

```bash
git clone https://github.com/jdvalencir/RevAIsorTecnicalInterview.git
```

### 2. Navigate to the project directory

```bash
cd RevAIsorTecnicalInterview
```

### 3. Navigate to the backend folder
```bash
cd backend
```

### 4. Install dependencies
```bash
./gradlew build
```
### 5. Configure application properties

* Rename **aplication-example.properties** to **aplication.properties**

* Update the properties with yout configuration details

* You need to change the emailTo variable in the EmailService.java file

```java

    // Here you can change the emailTo variable to your email    
    private String emailTo = "info@revaisor.com";
```


![image](https://github.com/jdvalencir/RevAIsorTecnicalInterview/assets/88250984/e407eb46-67df-44bc-a556-a4b03fde0af1)

### 6. Run the application: 
```bash
./gradlew bootRun
```

The REST API will start in: **http://localhost:8080**

You must turn on the Spring Boot App in order to stablish a communication with the React Client

### 7. Navigate to the frontend folder
```bash
cd frontend
```

### 8. Install dependencies
```bash
npm install
```

### 9. Change the vite.config.js file

This change is necessary to run the app in localhost, please copy paste this:

```JS
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
 base: "/",
 plugins: [react()],

});
```

**This change is just if you want to run the React Client in localhost, we must not change it if we want to dockerize the app**


### 10. Change the axios Base

In the Form.jsx component you must change the line: 

```JS
import.meta.env.VITE_BASE_PROD
``` 

to

```JS
import.meta.env.VITE_BASE_LOCAL
``` 

**This is important for the correct connection between frontend and backend**


### Run the app with docker compose

Before start building the images and running the containers make sure you complete the **5 and 10 above steps**

#### 1. Building the image

```bash
sudo docker compose -f docker-compose-local.yml build
```

#### 2. Create and run the containers

```bash
sudo docker compose -f docker-compose-local.yml up
```

After the following steps you should see the following screen: 

![image](https://github.com/jdvalencir/RevAIsorTecnicalInterview/assets/88250984/a05c3d3d-8d02-41d6-90be-330d215b1fe2)

Now you can send emails to the selected recipient!

## Architecture Overview

The project is built using Java Spring Boot framework for the backend and React.js for the frontend. The backend handles API requests, including a personalized contact form that collects basic information like name, email, and message. Upon form submission, the backend sends an email notification to the specified email address using JavaMailSender.

The frontend is a single-page application (SPA) developed with React.js. It communicates with the backend API to submit the contact form and handle responses.

![image](https://github.com/jdvalencir/RevAIsorTecnicalInterview/assets/88250984/19dbf2a6-3b6c-43f0-be82-fbc3ccd1192a)


## Design Decisions

In designing the project, I aim to decouple the frontend from the backend primarily due to the simplicity that React offers for creating contact forms with data validation. While Spring Boot provides excellent templates, it lacks the flexibility and freedom that React offers.

Additionally, separating the frontend from the backend enhances scalability. Currently, with containers, we can run multiple instances of both the backend and frontend to efficiently handle a large volume of requests.


## Challenges Faced

A significant challenge I've encountered is Spring Boot. While I have some experience with this development tool, my understanding of it is not extensive. Consequently, I needed to strengthen my Java skills and refresh my knowledge of Spring Boot.

Another challenges during the development included integration with the Backend API due to Cors Problems and configuring email sending functionality.

##  Improvements

* Add extra data validation to improve app stability and troubleshooting.
* Add a automated integration test to ensure the system reliability.
* Another thing that could be included is a DB to save the contact form responses in order to analyse the data

## CI/CD Process (Additional Task)

I implemented a CI/CD process for this application using the following steps:

1. **Version Control with Git:**
   - I used Git to track the source code of the application. This allowed for collaboration, versioning, and tracking of changes over time.

2. **Dockerization:**
   - I containerized both the backend and frontend components of the application using Docker. This allowed for consistent deployment environments and simplified the deployment process.

3. **Docker Compose:**
   - I created a `docker-compose.yml` file to define and orchestrate the deployment of multiple Docker containers as a single service. This streamlined the deployment process by managing the inter-container communication and dependencies.

4. **GitHub Actions Workflow:**
   - I created a `.github/workflows/publish-deployment.yaml` file to define the CI/CD workflow using GitHub Actions. This workflow automated the build and deployment processes for the application. This configuration will connect by ssh to the GCP VM that we specify. By this moment, the configuration  

     ![image](https://github.com/jdvalencir/RevAIsorTecnicalInterview/assets/88250984/84a6edf4-e53b-4852-84a8-48fc9492718b)


5. **Deployment on Google Cloud Platform (GCP):**
   - I provisioned a virtual machine (VM) on Google Cloud Platform (GCP) to host the Docker containers. The VM provided a scalable and reliable infrastructure for running the application.


I use this domain for this application: 
**https://revaisortest.teleafit.tech/**

By following these steps, I established a CI/CD pipeline that automates the building and deployment of the application, ensuring continuous integration and delivery of new features and updates.


## Deployment Instructions


There are two ways to deploy the application:

### 1. CI/CD Deployment:

TThe CI/CD pipeline will automatically build the application, containerize it using Docker, and deploy it.

But you must have this secrets in your github repo: 

![image](https://github.com/jdvalencir/RevAIsorTecnicalInterview/assets/88250984/74dad201-5d47-48c1-81c2-dd394c180087)


This is an important section for the publish-deployment.yaml file. Ensure that each secret is written accurately.

if you have problems, you can follow this tutorial: 

[Tutorial](https://eugeniusmario.medium.com/automated-deployment-django-on-google-compute-engine-using-github-action-and-docker-662f84755c57)

### 2. Manual Deployment:

To deploy manually:

1. Connect to your virtual machine (VM) or server where you want to deploy the application.

2. Make sure you are opening the 80, 8080, 587, 22 ports

3. Clone the repository onto the VM:
```bash
git clone https://github.com/jdvalencir/RevAIsorTecnicalInterview.git
```

3. Navigate to the project directory
```bash
cd RevAIsorTecnicalInterview
```

4. Build and run the Docker containers using Docker Compose:


```bash
sudo docker compose -f docker-compose.yml up
```

This will start the application containers and make the application accessible on the specified ports.

--- 


Thank you very much!

Julián David Valencia Restrepo

