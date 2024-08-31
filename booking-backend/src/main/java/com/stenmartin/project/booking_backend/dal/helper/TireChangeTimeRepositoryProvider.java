package com.stenmartin.project.booking_backend.dal.helper;

import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@Component
public class TireChangeTimeRepositoryProvider {

    private final ApplicationContext applicationContext;

    public TireChangeTimeRepositoryProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public TireChangeTimeRepository findTireChangeTimeRepositoryById(String id) {
        Map<String, TireChangeTimeRepository> beans = applicationContext.getBeansOfType(TireChangeTimeRepository.class);

        return beans.values().stream()
                .filter(tireChangeTimeRepository -> Objects.equals(tireChangeTimeRepository.getWORKSHOP_ID(), id))
                    .findFirst()
                    .orElse(null);
    }
}
