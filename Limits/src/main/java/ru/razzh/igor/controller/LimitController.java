package ru.razzh.igor.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.razzh.igor.dto.LimitDto;
import ru.razzh.igor.dto.UpdateDayLimitRq;
import ru.razzh.igor.dto.UpdateLimitRq;
import ru.razzh.igor.dto.UpdateRs;
import ru.razzh.igor.dto.error.ErrorResponse;
import ru.razzh.igor.exception.LimitExceedException;
import ru.razzh.igor.scheduler.UpdateLimitScheduler;
import ru.razzh.igor.service.LimitService;

@AllArgsConstructor
@RestController
@RequestMapping("v1/limit")
public class LimitController {
    private final LimitService limitService;

    @GetMapping("/{id}")
    public LimitDto getLimit(@PathVariable long id) {
        return limitService.getLimit(id);
    }

    @PutMapping("/reduction")
    public LimitDto limitReductionAfterPayment(@RequestBody UpdateLimitRq body) {
        return limitService.lessLimit(body.sum(), body.userId());
    }
    @PutMapping("/revert")
    public UpdateRs revertLimit(@RequestBody UpdateLimitRq body) {
        return limitService.updateLimit(body.sum(), body.userId());
    }

    @PutMapping("/daylimit")
    public UpdateRs updateDayLimit(@RequestBody UpdateDayLimitRq body) {
        UpdateLimitScheduler.setDayLimit(body.limit());
        return new UpdateRs("Дневной лимит успешно изменён");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotFoundException(LimitExceedException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }
}
