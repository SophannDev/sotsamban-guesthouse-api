package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.roomtype.RoomType;
import com.sgh.sotsamban_guesthouse_api.domain.roomtype.RoomTypeRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.roomtype.RoomTypeRequest;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
import com.sgh.sotsamban_guesthouse_api.service.RoomTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void createRoomType(RoomTypeRequest payload) {

        var existTypeName = roomTypeRepository.existsRoomTypeByTypeName(payload.getTypeName().getValue());
        if (existTypeName) {
            throw new BusinessException(StatusCode.ROOM_TYPE_ALREADY_EXISTS);
        }

        RoomType roomType = new RoomType();
        roomType.setTypeName(payload.getTypeName().getValue());
        roomType.setDescription(payload.getDescription());
        roomType.setBasePrice(payload.getBasePrice());

        roomTypeRepository.save(roomType);

    }
}
