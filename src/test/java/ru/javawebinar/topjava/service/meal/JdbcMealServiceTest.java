package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"postgres","jdbc"})
public class JdbcMealServiceTest extends AbstractMealServiceTest {
}
