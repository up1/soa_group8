# Get available room type information by adult and children numbers

This can be run with `silk -silk.url="http://128.199.122.210:9001"`

## `GET /rooms/types?adults=2&children=1`

Perform a find room type information with adult `2` and childern `1`.

===

* `Status: 200`
* `Content-Type: "application/json;charset=UTF-8"`
```
[{"typeNameEn":"Deluxe Room","typeDetailsTh":"เตียงคิงไซส์กว้างขวางนุ่มสบายเป็นพิเศษ ผ้าคลุมเตียง,หมอนขนนก ของใช้ครบชุดในห้องน้ำหินอ่อน ฝักบัวแยกต่างหาก หนังสือพิมพ์ท้องถิ่นฟรี เก้าอี้อ่านหนังสือ พื้นที่ทำงาน ทีวี ไดร์เป่าผม ขอเตียงเสริมได้ 1 เตียง ผู้ใหญ่เข้าพักได้สูงสุด 4 คนและเด็ก 4 คน","typeDetailsEn":"Extra spacious king size bed, feather pillows, full set of toiletries in marble bathroom and separate shower, free local newspapers, reading chairs, work area, LED TV, hair dryer. It is suited for 1 family of up to 4 adults and 4 children + 1 extra bed.","typePrice":2800.0,"typeAdultLimit":4,"typeChildrenLimit":4,"typeTotalRooms":60,"typeId":1,"typeNameTh":"ห้องดีลักซ์"},{"typeNameEn":"Premiere Room","typeDetailsTh":"ประสบการณ์ที่ไม่เหมือนใครด้วยการผสานการผ่อนคลายและมีสมาธิ ห้องในชั้นพรีเมียร์จะทำให้คุณรู้สึกเหมือนบุคคลสำคัญ ไม่มีเตียงเสริม เข้าพักได้สูงสุดผู้ใหญ่ 2 และเด็ก 1 ไม่มีเตียงเสริม","typeDetailsEn":"A unique experience combining relaxation and meditation. This Premier room will make you feel like a king. Maximum occupancy is 2 adults and 1 child without extra bed.","typePrice":4500.0,"typeAdultLimit":2,"typeChildrenLimit":1,"typeTotalRooms":5,"typeId":2,"typeNameTh":"ห้องพรีเมียร์"}]
```
