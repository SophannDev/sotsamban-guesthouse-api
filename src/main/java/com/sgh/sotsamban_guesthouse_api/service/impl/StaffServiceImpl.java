package com.sgh.sotsamban_guesthouse_api.service.impl;

import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.domain.staff.Staff;
import com.sgh.sotsamban_guesthouse_api.domain.staff.StaffRepository;
import com.sgh.sotsamban_guesthouse_api.dto.request.staff.StaffRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.staff.StaffResponse;
import com.sgh.sotsamban_guesthouse_api.exception.BusinessException;
import com.sgh.sotsamban_guesthouse_api.service.StaffService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void createStaff(StaffRequest request) {

        // check email is exist
        var existsEmail = staffRepository.emailExists(request.getEmail());
        if (existsEmail) {
            throw new BusinessException(StatusCode.EMAIL_ALREADY_EXISTS);
        }

        Staff staff = new Staff();
        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setEmail(request.getEmail());
        staff.setPhone(request.getPhone());
        staff.setPosition(request.getPosition());
        staff.setSalary(request.getSalary());

        staffRepository.save(staff);
    }

    @Override
    public StaffResponse findStaffById(Long id) {
        var findStaffId = staffRepository.findStaffById(id)
                .orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));

        return StaffResponse.builder()
                .staffId(findStaffId.getStaffId())
                .firstName(findStaffId.getFirstName())
                .lastName(findStaffId.getLastName())
                .email(findStaffId.getEmail())
                .phone(findStaffId.getPhone())
                .position(findStaffId.getPosition())
                .salary(findStaffId.getSalary())
                .createdAt(findStaffId.getCreatedAt())
                .updatedAt(findStaffId.getUpdatedAt())
                .build();
    }
}
