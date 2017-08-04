# pagerank
## mysql init:
```
mysql> create database pagerank;
mysql> create user 'pagerank'@'localhost' identified by 'pagerank';
mysql> grant all on pagerank.* to 'pagerank'@'localhost';
```
Disable mysql's ssl if connection fails.
