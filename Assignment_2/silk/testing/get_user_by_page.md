# Get all users information

This can be run with `silk -silk.url="http://localhost:9001"`

## `GET users`

Perform a find all users information.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"id":1,"firstname":"Sirirach","lastname":"Junta"},{"id":2,"firstname":"Akkapon","lastname":"Chainarong"},{"id":3,"firstname":"Adisorn","lastname":"Sripakpaisit"},{"id":4,"firstname":"Tytle","lastname":"Teerachart"},{"id":5,"firstname":"Patcharapon","lastname":"Sophon"},{"id":6,"firstname":"Donald","lastname":"Trump"},{"id":7,"firstname":"Barack","lastname":"Obama"},{"id":8,"firstname":"Hilary","lastname":"Clinton"},{"id":9,"firstname":"Bill","lastname":"Clinton"},{"id":10,"firstname":"Abraham","lastname":"Lincoln"}]
```

## `GET users?page=2&item_per_page=5`

Perform a find users information with page = `2` and item_per_page = `5`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"id":6,"firstname":"Donald","lastname":"Trump"},{"id":7,"firstname":"Barack","lastname":"Obama"},{"id":8,"firstname":"Hilary","lastname":"Clinton"},{"id":9,"firstname":"Bill","lastname":"Clinton"},{"id":10,"firstname":"Abraham","lastname":"Lincoln"}]
```

## `GET users?page=3&item_per_page=3`

Perform a find users information with page = `3` and item_per_page = `3`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"id":7,"firstname":"Barack","lastname":"Obama"},{"id":8,"firstname":"Hilary","lastname":"Clinton"},{"id":9,"firstname":"Bill","lastname":"Clinton"}]
```

## `GET users?page=9&item_per_page=1`

Perform a find users information with page = `9` and item_per_page = `1`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"id":9,"firstname":"Bill","lastname":"Clinton"}]
```
