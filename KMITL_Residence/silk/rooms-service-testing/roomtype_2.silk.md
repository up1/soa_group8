# Get room type information by room_type_id

This can be run with `silk -silk.url="http://128.199.122.210:9001"`

## `GET /rooms/type/2`

Perform a find room type information with id `2`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
{"typeNameEn":"Premiere Room","typeDetailsTh":"ประสบการณ์ที่ไม่เหมือนใครด้วยการผสานการผ่อนคลายและมีสมาธิ ห้องในชั้นพรีเมียร์จะทำให้คุณรู้สึกเหมือนบุคคลสำคัญ ไม่มีเตียงเสริม เข้าพักได้สูงสุดผู้ใหญ่ 2 และเด็ก 1 ไม่มีเตียงเสริม","typeDetailsEn":"A unique experience combining relaxation and meditation. This Premier room will make you feel like a king. Maximum occupancy is 2 adults and 1 child without extra bed.","typePrice":4500.0,"typeAdultLimit":2,"typeChildrenLimit":1,"typeTotalRooms":5,"typeId":2,"typeNameTh":"ห้องพรีเมียร์"}
```
