package ru.razzh.igor.scheduler;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.razzh.igor.repository.LimitRepository;

import java.math.BigDecimal;

@EnableScheduling
@AllArgsConstructor
@Component
public class UpdateLimitScheduler {
    private final LimitRepository limitRepository;

    private static BigDecimal dayLimit = BigDecimal.valueOf(10000);

    public static void setDayLimit(BigDecimal dayLimit) {
        UpdateLimitScheduler.dayLimit = dayLimit;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    public void updatelimitOnSchedule() {
        limitRepository.revertToDefaultLimit(dayLimit);
    }
}
