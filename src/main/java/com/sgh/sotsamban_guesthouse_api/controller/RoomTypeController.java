package com.sgh.sotsamban_guesthouse_api.controller;

import com.sgh.sotsamban_guesthouse_api.dto.request.roomtype.RoomTypeRequest;
import com.sgh.sotsamban_guesthouse_api.service.RoomTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/room_type")
@RequiredArgsConstructor
@Slf4j
public class RoomTypeController extends BaseRestController{

     private final RoomTypeService roomTypeService;

     @PostMapping
     public Object createRoomType(@RequestBody @Valid RoomTypeRequest payload) {
         roomTypeService.createRoomType(payload);
         return ok();
     }

        @GetMapping
        public Object getRoomType() {
            return roomTypeService.getRoomType();
        }



}
