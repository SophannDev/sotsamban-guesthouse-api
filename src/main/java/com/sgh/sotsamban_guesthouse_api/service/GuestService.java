package com.sgh.sotsamban_guesthouse_api.service;


import com.sgh.sotsamban_guesthouse_api.dto.request.guest.GuestRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.guest.GuestResponse;
import org.springframework.data.domain.Pageable;

public interface GuestService {

    void createGuest(GuestRequest guestRequest);

    GuestResponse getGuestById(Long id);

    Object getAllGuests(String startDate, String endDate, String searchValue, Pageable pages);

}
