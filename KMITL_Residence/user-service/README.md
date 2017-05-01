# User Service
> POST /users/create
```
HEADER
authenticate-token: token
```
```
Request JSON
{
    "username": "staff001",
    "password": "passwordstaff001",
    "role": 2,
    "titleNameEn": "Mr.",
    "fullNameEn": "Adisorn Sripakpaisit",
    "titleNameTh": "นาย",
    "fullNameTh": "อดิศร ศรีภัคไพสิฐ",
    "email": "example@example.com"
}
```
```
Response
{
    "username": "staff001",
    "message": "Successfully created"
}
```
> GET /user/{username}
```
HEADER
authenticate-token: token
```
```
Response
{
    "username": "staff001",
    "role": 2,
    "titleNameEn": "Mr.",
    "fullNameEn": "Adisorn Sripakpaisit",
    "titleNameTh": "นาย",
    "fullNameTh": "อดิศร ศรีภัคไพสิฐ",
    "email": "example@example.com"
}
```
> GET /users?item_per_page=2&page=1
```
HEADER
authenticate-token: token
```
```
Response
[
    {
        "username": "staff001",
        "role": 2,
        "titleNameEn": "Mr.",
        "fullNameEn": "Adisorn Sripakpaisit",
        "titleNameTh": "นาย",
        "fullNameTh": "อดิศร ศรีภัคไพสิฐ",
        "email": "example@example.com"
    },
    {
        "username": "staff002",
        "role": 2,
        "titleNameEn": "Mr.",
        "fullNameEn": "Somsak Rakter",
        "titleNameTh": "นาย",
        "fullNameTh": "สมศักดิ์ รักเธอ",
        "email": "somsak@rakter.com"
    }
]
```
> PUT /user/{username}
```
HEADER
authenticate-token: token
```
```
Request JSON
{
    "username": "staff001",
    "password": "newpassword",
    "role": 2,
    "titleNameEn": "Mr.",
    "fullNameEn": "Adisorn Sripakpaisit",
    "titleNameTh": "นาย",
    "fullNameTh": "อดิศร ศรีภัคไพสิฐ",
    "email": "new_example@example.com"
}
```
```
Response
{
    "username": "staff001",
    "message": "Successfully Updated"
}
```
> DELETE /user/{username}/delete
```
HEADER
authenticate-token: token
```
```
Response
{
    "username": "staff001",
    "message": "Successfully Deleted"
}
```
> POST /users/authentication
```
Request 
{
    "username": "staff001",
    "password": "123456"
}
```
```
Response
token-generate
```

> GET /users/validate?token=wksdjqio3eu
```
Response
{
    "username": "admin",
    "role": "admin"
}
```
