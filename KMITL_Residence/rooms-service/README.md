# Rooms Service
> GET /rooms/type/{room_type_id}
```
Response
{
  "typeId": 1,
  "typeTotalRooms": 60,
  "typeAdultLimit": 4,
  "typeChildrenLimit": 4,
  "typeDetailsTh": "ข้อมูลประเภทห้องภาษาไทย",
  "typeDetailsEn": "room type details in english",
  "typePrice": 2800,
  "typeNameTh": "ห้องหรู",
  "typeNameEn": "Deluxe"
}
```
> /rooms/types?adults=1&children=0
```
[
    {
        "typeId": 1,
        "typeTotalRooms": 60,
        "typeAdultLimit": 4,
        "typeChildrenLimit": 4,
        "typeDetailsTh": "ข้อมูลประเภทห้องภาษาไทย",
        "typeDetailsEn": "room type details in english",
        "typePrice": 2800,
        "typeNameTh": "",
        "typeNameEn": "Deluxe"
    },
    {
        "typeId": 2,
        "typeTotalRooms": 5,
        "typeAdultLimit": 2,
        "typeChildrenLimit": 1,
        "typeDetailsTh": "ข้อมูลประเภทห้องภาษาไทย",
        "typeDetailsEn": "room type details in english",
        "typePrice": 4500,
        "typeNameTh": "",
        "typeNameEn": "Premiere"
    },
    {
        "typeId": 3,
        "typeTotalRooms": 5,
        "typeAdultLimit": 2,
        "typeChildrenLimit": 0,
        "typeDetailsTh": "ข้อมูลประเภทห้องภาษาไทย",
        "typeDetailsEn": "room type details in english",
        "typePrice": 5600,
        "typeNameTh": "",
        "typeNameEn": "Suite"
    }
]
```
> GET /rooms/{room_id}
```
Response
{
    "roomId": 101,
    "roomDetails": "",
    "roomTypeId": 1,
    "roomAvailability": 1
}
```
> GET /rooms/available/{room_type_id}
```
Response
[
    {
        "roomId": 131,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 132,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 133,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 134,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 135,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    }
]
```
> POST /rooms/{room_id}/checkin/{reservation_id}
```
HEADER
authenticate-token: some generate token
```
```
Response
{
    "message": "Success"
}
```
> PUT /rooms/checkout?id=2134
```
HEADER
authenticate-token: some generate token
```
```
Response
{
    "message": "Success"
}
```
> GET /rooms
```
[
    {
        "roomId": 131,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 132,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 133,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 134,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    },
    {
        "roomId": 135,
        "roomDetails": "",
        "roomTypeId": 2,
        "roomAvailability": 1
    }
]
```
> PUT /rooms/{roomId}/open
```
header
authenticate-token: 12e23
```
```
Response
{
    "message": "Success"
}
```
> PUT /rooms/{roomId}/close
```
header
authenticate-token: 12e23
```
```
Response
{
    "message": "Success"
}
```
> PUT /rooms/change?reservationId=1&roomId=1
```
header
authenticate-token: 12e23
```
```
Response
{
    "message": "Success"
}
```
> GET /checkinfo?id=1
```
header
authenticate-token: 12e23
```
```
Response
{
  "id": 1,
  "checkIn": "2017-05-11",
  "checkOut": "2017-05-15",
  "total": 1,
  "roomType": 1,
  "status": "completed",
  "customer": {
    "titleName": "Mr.",
    "fullName": "Adisorn Sripakpaisit",
    "email": "anst_7@hotmail.com",
    "tel": "0890938473",
    "country": "United State",
    "nation": "Thai"
  },
  "checkInStatus": "yes",
  "checkOutStatus": "yes",
  "roomId": 101
}
```


