<br />
<div align="center">
<h3 align="center">Instructions for launching the application</h3>
</div>

1. Unzip the application package:
    - Unpack the ZIP archive with your application to a convenient location on your computer.

2. Launch the backend (Spring Boot) application:
    - Find the JAR file of your backend application in the unpacked archive.
    - Create an external application.yml that will replace your database access settings
      from internal application.yml such as:
      * url of your database
      * username
      * password
        Your external application.yml should look something like this:
        spring:
          datasource:
            url: jdbc:mysql://real_database_host:real_database_port/real_database_name
            username: real_database_username
            password: real_database_password
    - Open a terminal (command line) and go to the directory with the JAR file.
    - Launch your backend application by entering the command:
      ```
      java -jar user-storage-0.0.1-SNAPSHOT.jar --spring.config.location=application.yml
      ```

3. Launching front-end (React.js) application:
    - Find the folder with the collected static files for the front-end application in the unpacked archive.
    - If required, make sure Node.js is installed on your computer to run the React.js application.
    - Open a new terminal window, go to the folder with the React.js application.
    - Start a local server for the front-end application, for example using serve:
      ```
      npx serve -s build
      ```
    - the front-end application will be available at the address that the server will display in the terminal.