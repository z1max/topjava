package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles({"postgres","datajpa"})
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
