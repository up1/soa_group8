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
> /rooms/types?adult=1&children=0
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
> /rooms/{room_id}/checkin/{reservation_id}
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
> /rooms/{room_id}/checkout/{reservation_id}
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
