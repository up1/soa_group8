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
