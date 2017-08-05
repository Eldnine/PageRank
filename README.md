# pagerank
A realization for the pagerank session of Google `http://infolab.stanford.edu/~backrub/google.html`
## Getting Started
### Prerequisites
* mysql server 5.5+
* java 8
### Installing:
#### mysql setup
```
mysql> create database pagerank;
mysql> create user 'pagerank'@'localhost' identified by 'pagerank';
mysql> grant all on pagerank.* to 'pagerank'@'localhost';
```
Disable ssl of mysql server if the connection fails.
#### run (linux)
```
chmod +x mvnw
./mvnw spring-boot:run
```
