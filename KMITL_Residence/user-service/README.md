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
> GET /users/{username}
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
> DELETE /users/{username}/delete
```
Response
{
    "username": "staff001",
    "message": "Successfully Deleted"
}
```
> PUT /users/{username}/password
```
{
    "username": "staff001",
    "message": "Successfully changed"
}
```
