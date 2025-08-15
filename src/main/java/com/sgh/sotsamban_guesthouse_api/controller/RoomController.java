package com.sgh.sotsamban_guesthouse_api.controller;

import com.sgh.sotsamban_guesthouse_api.common.MultiSortBuilder;
import com.sgh.sotsamban_guesthouse_api.dto.request.room.RoomRequest;
import com.sgh.sotsamban_guesthouse_api.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RoomController extends BaseRestController {

    private final RoomService roomService;

    @PostMapping
    public Object createRoom(@RequestBody @Valid RoomRequest roomRequest) {
        roomService.createRoom(roomRequest);
        return ok();
    }

    @GetMapping
    public Object getAllRooms(
            @RequestParam(required = false) String searchValue,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
                "created_at:asc"
        ).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize,Sort.by(sortBuilder));

        var guestsResponse = roomService.getAllRooms(searchValue, pages);
        return ok(guestsResponse);
    }

    @GetMapping("/{roomId}")
    public Object getRoomById(@PathVariable Long roomId) {
        var roomResponse = roomService.getRoomById(roomId);
        return ok(roomResponse);
    }

}
