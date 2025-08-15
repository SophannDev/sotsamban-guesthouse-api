package com.sgh.sotsamban_guesthouse_api.mapper;

import com.sgh.sotsamban_guesthouse_api.domain.room.Room;
import com.sgh.sotsamban_guesthouse_api.dto.request.room.RoomRequest;
import com.sgh.sotsamban_guesthouse_api.enums.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomRequest request) {
        if (request == null) {
            return null;
        }

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus() != null ? request.getStatus() : RoomStatus.AVAILABLE);
        room.setPricePerNight(request.getPricePerNight());
        return room;
    }


}
