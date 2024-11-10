package ru.razzh.igor.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.razzh.igor.dto.LimitDto;
import ru.razzh.igor.dto.UpdateRs;
import ru.razzh.igor.entity.Limit;
import ru.razzh.igor.exception.LimitExceedException;
import ru.razzh.igor.repository.LimitRepository;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class LimitService {
    private final static BigDecimal LIMIT = BigDecimal.valueOf(10000);
    LimitRepository limitRepository;

    @Transactional
    public LimitDto getLimit(long userId) {
        Limit limit;
        try {
           limit = limitRepository.findByUserId(userId).orElseThrow(EntityExistsException::new);
        } catch (EntityExistsException e) {
            Limit limitAdd = new Limit();
            limitAdd.setLimit(LIMIT);
            limitAdd.setUserId(userId);
            limitRepository.saveLimit(limitAdd.getLimit(), limitAdd.getId());
            limit = limitAdd;
        }

        return limitMapToDto(limit);
    }
    @Transactional
    public LimitDto lessLimit(BigDecimal sum, long userId) {
        BigDecimal limit = getLimit(userId).limit();
        if (limit.compareTo(sum) < 0) {
            throw new LimitExceedException("Превышел лимит по карте");
        }
        limit = limit.subtract(sum);
        limitRepository.updateLimit(limit, userId);
        return new LimitDto(userId, limit);
    }

    @Transactional
    public UpdateRs updateLimit(BigDecimal limit, long userId) {
        limitRepository.updateLimit(limit, userId);
        return new UpdateRs("Возврат лимита после отмененной оплаты прошёл успешно");
    }

    public LimitDto limitMapToDto(Limit limit) {
        return new LimitDto(limit.getUserId(), limit.getLimit());
    }
}
