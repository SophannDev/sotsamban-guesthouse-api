package com.sgh.sotsamban_guesthouse_api.service;


import com.sgh.sotsamban_guesthouse_api.dto.request.staff.StaffRequest;
import com.sgh.sotsamban_guesthouse_api.dto.response.staff.StaffResponse;

public interface StaffService {

    void createStaff(StaffRequest request);

    StaffResponse findStaffById(Long id);

}
