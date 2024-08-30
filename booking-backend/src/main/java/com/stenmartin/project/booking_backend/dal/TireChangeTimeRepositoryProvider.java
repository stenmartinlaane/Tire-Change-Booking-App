package com.stenmartin.project.booking_backend.dal;

import com.stenmartin.project.booking_backend.domain.repository.TireChangeTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
                .filter(tireChangeTimeRepository -> {
                    try {
                        Field field = tireChangeTimeRepository.getClass().getDeclaredField("WORKSHOP_ID");
                        field.setAccessible(true);
                        String workshopId = (String) field.get(null);

                        return Objects.equals(workshopId, id);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }
}
