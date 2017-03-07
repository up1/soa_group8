package room.model;

public class Room {
	private int room_id;
	private String room_detail;
	private int room_status;
	private int room_type_id;
	
	public Room (int room_id){
		this.room_id = room_id;
		this.room_detail = "sample detail";
		this.room_status = 0;
		this.room_type_id = 1;
	}
	
	public Room(){
		
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getRoom_detail() {
		return room_detail;
	}

	public void setRoom_detail(String room_detail) {
		this.room_detail = room_detail;
	}

	public int getRoom_status() {
		return room_status;
	}

	public void setRoom_status(int room_status) {
		this.room_status = room_status;
	}

	public int getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(int room_type_id) {
		this.room_type_id = room_type_id;
	}
	
}
