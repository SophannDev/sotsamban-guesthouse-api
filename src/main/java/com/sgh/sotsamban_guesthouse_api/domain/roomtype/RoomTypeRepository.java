package com.sgh.sotsamban_guesthouse_api.domain.roomtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    @Query("SELECT CASE WHEN COUNT(rt) > 0 THEN true ELSE false END FROM RoomType rt WHERE rt.typeName = ?1")
    Boolean existsRoomTypeByTypeName(String typeName);

}
