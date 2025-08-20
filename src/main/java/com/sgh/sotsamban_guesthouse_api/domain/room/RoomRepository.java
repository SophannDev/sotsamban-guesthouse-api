package com.sgh.sotsamban_guesthouse_api.domain.room;

import com.sgh.sotsamban_guesthouse_api.dto.response.room.IRoomResponse;
import com.sgh.sotsamban_guesthouse_api.enums.RoomStatus;
import com.sgh.sotsamban_guesthouse_api.enums.RoomTypeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByRoomNumber(String roomNumber);

    @Query(value = """
        SELECT r.room_id      AS room_id,
               r.room_num     AS room_number,
               r.sts          AS sts,
               r.room_type_id AS room_type_id,
               rt.type_nm     AS room_type_name,
               r.prce_per_ngt AS price_per_night,
               rt.base_price  AS base_price,
               r.image_url    AS image_url
        FROM tb_room r
                 LEFT JOIN tb_room_type rt ON r.room_type_id = rt.rt_id
    """, nativeQuery = true)
    Page<IRoomResponse> findAllRooms(Pageable pageable);

    @Query(value = """
        SELECT r.room_id      AS room_id,
               r.room_num     AS room_number,
               r.sts          AS sts,
               r.room_type_id AS room_type_id,
               rt.type_nm     AS room_type_name,
               r.prce_per_ngt AS price_per_night,
               rt.base_price  AS base_price,
               r.image_url    AS image_url
        FROM tb_room r
                 LEFT JOIN tb_room_type rt ON r.room_type_id = rt.rt_id
        WHERE r.sts = :status
          AND r.room_id IN :roomIds
    """, nativeQuery = true)
    String findAllRoomsByStatus(List<Long> roomIds, String status);

}
