package com.sgh.sotsamban_guesthouse_api.domain.room;

import com.sgh.sotsamban_guesthouse_api.config.ImageConfig;
import com.sgh.sotsamban_guesthouse_api.domain.BaseEntity;
import com.sgh.sotsamban_guesthouse_api.domain.roomtype.RoomType;
import com.sgh.sotsamban_guesthouse_api.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_room")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @NotBlank
    @Size(max = 10)
    @Column(name = "room_num", nullable = false, unique = true)
    private String roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Convert(converter = RoomStatus.Converter.class)
    @Column(name = "sts")
    private RoomStatus status = RoomStatus.AVAILABLE;

    @Column(name = "prce_per_ngt")
    private BigDecimal pricePerNight;

    @Convert(converter = ImageConfig.StringListConverter.class)
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> images = new ArrayList<>();

}
