# How to run the user service ?

```
$mvn clean package
$java -jar ./target/user-service.jar
```

And go to URL http://localhost:9001/users
or add some parameter such as http://localhost:9001/users?page=1&item_per_page=10
(default value of parameters 'page' and 'item_per_page' are 1 and 10 by order)
