git clone https://github.com/sharifhan/crunch.git
mvn clean package
java -jar target\crunch-test-1.0.jar
And then point your internet browser to: http://localhost:8080/calculate?email=test@email.com&taxyear=2014/15&gross=120000