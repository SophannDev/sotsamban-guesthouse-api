package com.sgh.sotsamban_guesthouse_api.controller;

import com.sgh.sotsamban_guesthouse_api.dto.request.staff.StaffRequest;
import com.sgh.sotsamban_guesthouse_api.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@Slf4j
public class StaffController extends BaseRestController{

    private final StaffService staffService;

    @PostMapping
    public Object createStaff(@RequestBody @Valid StaffRequest request) {
        staffService.createStaff(request);
        return ok();
    }

    @GetMapping("/{id}")
    public Object findStaffById(@PathVariable Long id) {
        return ok(staffService.findStaffById(id));
    }

}
