package room.model;

public class Room {
	private int roomId;
	private String roomDetails;
	private int roomTypeId;
	private int roomAvailability;

	public Room(int roomId, String roomDetails, int roomTypeId, int roomAvailability) {
		this.roomId = roomId;
		this.roomDetails = roomDetails;
		this.roomTypeId = roomTypeId;
		this.roomAvailability = roomAvailability;
	}

	public Room(){

	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(String roomDetails) {
		this.roomDetails = roomDetails;
	}

	public int getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public int getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(int roomAvailability) {
		this.roomAvailability = roomAvailability;
	}
}
