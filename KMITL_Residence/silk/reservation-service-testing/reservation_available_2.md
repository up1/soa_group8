# Get list of available reservation

This can be run with `silk -silk.url="http://128.199.122.210:9000"`

## `GET /reservation/availableSearch?checkin=2017-06-01&checkout=2017-06-03&adults=1&children=0`

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"roomType":1,"total":60},{"roomType":2,"total":5},{"roomType":3,"total":5}]
```
