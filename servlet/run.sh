mvn clean install

docker build -t web .

#firefox --new-tab  http://localhost:8080/jeebox/

docker run -p 8080:8080 web

