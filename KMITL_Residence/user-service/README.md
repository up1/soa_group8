# User Service
> POST /users/create
```
Request JSON
{
    "username": "staff001",
    "password": "passwordstaff001",
    "role": 1,
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
    "role": "Staff",
    "fullNameTh": "นายอดิศร ศรีภัคไพสิฐ",
    "fullNameEn": "Mr.Adisorn Sripakpaisit",
    "email": "example@example.com"
}
```
> DELETE /user/{username}/delete
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
