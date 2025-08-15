package com.sgh.sotsamban_guesthouse_api.service;

import com.sgh.sotsamban_guesthouse_api.dto.request.room.RoomRequest;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    void createRoom(RoomRequest roomRequest);

    Object getAllRooms(String searchValue, Pageable pages);

    Object getRoomById(Long roomId);

}
