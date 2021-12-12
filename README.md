"# telenor-test" 


Following are the commands that are needed to run from project root directory

    $ mvn clean package
    $ docker build -t springio/gs-spring-boot-docker .
    $ docker run -p 8080:8080 -t springio/gs-spring-boot-docker

You can use URL for Ex:
http://localhost:8080/getdata?property:gb_limit_min=5