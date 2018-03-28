package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles({"postgres","jdbc"})
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
