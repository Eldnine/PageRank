# pagerank
A realization for the pagerank session of Google `http://infolab.stanford.edu/~backrub/google.html`
## mysql init:
```
mysql> create database pagerank;
mysql> create user 'pagerank'@'localhost' identified by 'pagerank';
mysql> grant all on pagerank.* to 'pagerank'@'localhost';
```
Disable mysql's ssl if connection fails.
