package com.sgh.sotsamban_guesthouse_api.domain.roomtype;

import com.sgh.sotsamban_guesthouse_api.config.ImageConfig;
import com.sgh.sotsamban_guesthouse_api.domain.BaseEntity;
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
@Table(name = "tb_room_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rt_id")
    private Long roomTypeId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "type_nm", nullable = false, unique = true)
    private String typeName;

    @Column(name = "descr", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Convert(converter = ImageConfig.StringListConverter.class)
    @Column(name = "image_url", columnDefinition = "TEXT")
    private List<String> images = new ArrayList<>();
}
