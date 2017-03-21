# Reservation Service
> GET /reservation/serachAvailable?checkin=2017-01-01&checkout=2017-01-02&adults=1&children=0
```
Response
{
    "roomType": 1,
    "total": 1
}
```
> GET /reservation/{reservationId}
```
Response
{
    "id": 1,
    "checkIn" '2017-01-01',
    "checkOut": '2017-01-02',
    "total": 2,
    "roomType": 1,
    "status": "waiting|completed|cancel",
    "customer": {
        "titleName": "Mr.",
        "fullName": "Abc Def",
        "email": "example@example.com",
        "tel": "0123456789",
        "country": "Thailand",
        "nation": "Thai"
    }
}
```
> POST /reservation/add
```
Request JSON
{
    "checkIn": "2017-01-01",
    "checkOut": "2017-01-12",
    "adults": 1,
    "children": 1,
    "status": 1,
    "roomType": 2,
    "customer": {
	  	"titleName": "Mr.",
	  	"fullName": "Adisorn Sripakpaisit",
	  	"email": "example@example.com",
	  	"tel": "0890938473",
	  	"country": "Thailand",
	  	"nation": "Thai"
	},
    "creditCard": {
	  	"number": "1234567890",
		"type": "Visa",
	  	"expiredDate": "2018-01-01",
	  	"cvv": "1234"
	}
}
```
```
Response 
{
    "message": "Success"
}
```
> PUT /reservation/{reservationId}/confirm?id=123wwdw22dsfsea
```
Response
{
    "message": "Success"
}
```
> PUT /reservation/{reservationId}/cancel
```
Response
{
    "message": "Success"
}
```
> PUT /reservation/{reservationId}/partialCheckout
```
Response
{
    "message": "Success"
}
```
