package pe.edu.unmsm.bank.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unmsm.bank.infrastructure.Constants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<AccountStatement>> getAccountStatement(
            @PathVariable("customerId") Long customerId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = Constants.DATE_FORMAT) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = Constants.DATE_FORMAT) LocalDate endDate) {
        return ResponseEntity.ok(service.getAccountStatement(customerId, startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)));
    }

}
