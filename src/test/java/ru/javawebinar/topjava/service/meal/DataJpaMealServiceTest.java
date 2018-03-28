package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

@ActiveProfiles({"postgres","datajpa"})
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
}
