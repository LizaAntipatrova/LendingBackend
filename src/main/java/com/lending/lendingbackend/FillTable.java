//package com.lending.lendingbackend;
//
//
//
//import com.lending.lendingbackend.auth.config.BCrypt.BCryptPasswordEncoder;
//import com.lending.lendingbackend.data.entity.CreditCategory;
//import com.lending.lendingbackend.data.entity.Manager;
//import com.lending.lendingbackend.data.entity.User;
//import com.lending.lendingbackend.data.entity.UserRole;
//import com.lending.lendingbackend.data.repository.CreditCategoryRepository;
//import com.lending.lendingbackend.data.repository.ManagerRepository;
//import com.lending.lendingbackend.data.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//@RequiredArgsConstructor
//// spring_profiles_active=test в edit configuration стратегия игнора: https://www.baeldung.com/spring-profiles
//public class FillTable {
//    private  final UserRepository userRepository;
//    private final ManagerRepository managerRepository;
//    private final BCryptPasswordEncoder encoder;
//    private final CreditCategoryRepository creditCategoryRepository;
//
//
//    /**
//     * при запуске приложения заполняет БД тестовыми данными
//     */
//    @EventListener(ApplicationReadyEvent.class)
//    public void fillRoleEntities() {
//
//
//
//        CreditCategory category1 = new CreditCategory();
//        category1.setText_name("Потребительский");
//        category1 = creditCategoryRepository.save(category1);
//
//        CreditCategory category2 = new CreditCategory();
//        category2.setText_name("Ипотека");
//        category2 = creditCategoryRepository.save(category2);
//
//        CreditCategory category3 = new CreditCategory();
//        category3.setText_name("Автокредит");
//        category3 = creditCategoryRepository.save(category3);
//
//        CreditCategory category4 = new CreditCategory();
//        category4.setText_name("Кредит с обеспечением");
//        category4 = creditCategoryRepository.save(category4);
//
//        CreditCategory category5 = new CreditCategory();
//        category5.setText_name("Рефинансирование");
//        category5 = creditCategoryRepository.save(category5);
//
//        CreditCategory category6 = new CreditCategory();
//        category6.setText_name("Реструктуризация");
//        category6 = creditCategoryRepository.save(category6);
//
//        CreditCategory category7 = new CreditCategory();
//        category7.setText_name("Кредитная карта");
//        category7 = creditCategoryRepository.save(category7);
//
//        User user = new User();
//        user.setRole(UserRole.MANAGER);
//        user.setLogin("22");
//        user.setPassword(encoder.encode("22"));
//        user.setRegistrationDate(LocalDateTime.now());
//        user = userRepository.save(user);
//
//        Manager manager1 = new Manager();
//        manager1.setLastName("Ухов");
//        manager1.setFirstName("Владимир");
//        manager1.setMiddleName("Менеджерович");
//        manager1.setPhone("89005353535");
//        manager1.setEmail("job@gmail.com");
//        manager1.setUser(user);
//        Set<CreditCategory> categories = new HashSet<>();
//        categories.add(category2);
//        categories.add(category5);
//        categories.add(category6);
//        manager1.setSpecializations(categories);
//        managerRepository.save(manager1);
//
//        User user1 = new User();
//        user1.setRole(UserRole.MANAGER);
//        user1.setLogin("33");
//        user1.setPassword(encoder.encode("33"));
//        user1.setRegistrationDate(LocalDateTime.now());
//        user1 = userRepository.save(user1);
//
//        Manager manager2 = new Manager();
//        manager2.setLastName("Филатов");
//        manager2.setFirstName("Пуп");
//        manager2.setMiddleName("Пупович");
//        manager2.setPhone("89005353535");
//        manager2.setEmail("job.pupupu@gmail.com");
//        manager2.setUser(user1);
//        Set<CreditCategory> categories2 = new HashSet<>();
//        categories2.add(category1);
//        manager2.setSpecializations(categories2);
//        managerRepository.save(manager2);
//
//        User userAdmin = new User();
//        userAdmin.setRole(UserRole.ADMINISTRATOR);
//        userAdmin.setLogin("admin");
//
//        userAdmin.setRegistrationDate(LocalDateTime.now());
//        userAdmin.setPassword(encoder.encode("admin"));
//        userAdmin = userRepository.save(userAdmin);
//
//
//
//
//
//
//
//    }
//}
