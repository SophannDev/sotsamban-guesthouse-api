package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.guest.Guest;
import com.sgh.sotsamban_guesthouse_api.domain.guest.GuestRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.guest.GuestRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.guest.GuestMainResponse;
import com.sgh.sotsamban_guesthouse_api.dto.response.guest.GuestResponse;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
import com.sgh.sotsamban_guesthouse_api.mapper.GuestMapper;
import com.sgh.sotsamban_guesthouse_api.service.GuestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    @Override
    public void createGuest(GuestRequest guestRequest) {
        var guest = guestMapper.toEntity(guestRequest);

        guestRepository.save(guest);

    }

    @Override
    public GuestResponse getGuestById(Long id) {
        var guest = guestRepository.findById(id)
                .orElseThrow(() -> new BusinessException(StatusCode.SUCCESS));

        return guestMapper.toResponse(guest);
    }

    @Override
    public Object getAllGuests(String startDate, String endDate, String searchValue, Pageable pages) {
        Page<Guest> guestPage = guestRepository.findAllByStartDateBetweenAndEndDateBetween(startDate, endDate, searchValue, pages);
        List<GuestResponse> guestResponses = guestPage.getContent().stream()
                .map(guestMapper::toResponse)
                .toList();

        return GuestMainResponse.builder()
                .guests(guestResponses)
                .page(guestPage)
                .build();
    }

}
