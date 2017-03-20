# Email Service
> POST /sendmail
```
Request JSON
{
    "emailType": 1,
    "destination": "example@example.com",
    "fullName": "Mr.Bug",
    "content": {
        "reservationId": 1,
        "roomType": 1,
        "total": 10000,
        "confirmationLink": "exmaple link"
    }
}
```