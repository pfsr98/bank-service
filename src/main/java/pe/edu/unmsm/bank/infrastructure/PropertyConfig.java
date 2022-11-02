package pe.edu.unmsm.bank.infrastructure;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class PropertyConfig {

    @Value("${backend.diary-limit}")
    private Double diaryLimit;

}
