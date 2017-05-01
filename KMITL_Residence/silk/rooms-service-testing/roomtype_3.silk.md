# Get room type information by room_type_id

This can be run with `silk -silk.url="http://128.199.122.210:9001"`

## `GET /rooms/type/3`

Perform a find room type information with id `3`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
{"typeNameEn":"Suite Room","typeDetailsTh":"ประสบการณ์ที่ยากจะลืมเลือน! ห้องสวีทขนาดใหญ่ที่ออกแบบอย่างยอดเยี่ยม มีห้องรับรองและห้องน้ำแยกต่างหาก พร้อมด้วยสิ่งอำนวยความสะดวกอันทันสมัย เช่น โซฟาและโซฟา (เรามีโซฟาให้ 2 ตัวน่ะ) พักได้สูงสุดผู้ใหญ่ 2 ไม่มีเตียงเสริม","typeDetailsEn":"An unforgettable experience! Large and greatly designed suite room. There is a separate lounge and bathroom. Equipped with modern amenities such as sofa (2 sofas). Maximum occupancy is 2 adults, no extra bed.","typePrice":5600.0,"typeAdultLimit":2,"typeChildrenLimit":0,"typeTotalRooms":5,"typeId":3,"typeNameTh":"ห้องสวีท"}
```
