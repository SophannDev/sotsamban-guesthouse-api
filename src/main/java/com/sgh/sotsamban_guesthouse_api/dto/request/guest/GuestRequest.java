package com.sgh.sotsamban_guesthouse_api.dto.request.guest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GuestRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String idDocumentType; // PASSPORT, DRIVING_LICENSE, NATIONAL_ID, OTHER

    private String idDocumentNumber;
}
