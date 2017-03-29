# Email Service
> POST /sendmail
```
Request JSON
{
    "emailType": 1,
    "destination": "example@example.com",
    "titleName": "Mr.",
    "fullName": "Software Mebug",
    "content": {
        "reservationId": 1,
        "roomType": 1,
        "checkinTime": "2017-03-30",
        "checkoutTime": "2017-03-31",
        "totalNight": 1,
        "totalPrice": 2000,
        "confirmationLink": "confirm link",
        "cancelLink": "cancel link";
    }
}
```
