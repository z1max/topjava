package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

@ActiveProfiles({"postgres","jpa"})
public class JpaUserServiceTest extends AbstractUserServiceTest {
}
