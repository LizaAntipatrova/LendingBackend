//package com.lending.lendingbackend;
//
//
//
//import com.lending.lendingbackend.auth.config.BCrypt.BCryptPasswordEncoder;
//import com.lending.lendingbackend.data.entity.*;
//import com.lending.lendingbackend.data.repository.CreditCategoryRepository;
//import com.lending.lendingbackend.data.repository.CreditProductRepository;
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
//    private final CreditProductRepository creditProductRepository;
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
//        // Потребительский кредит
//        CreditProduct product1 = new CreditProduct();
//        product1.setName("Потребительский кредит наличными");
//        product1.setInterestRate(12.5);
//        product1.setMinAmount(50000.0);
//        product1.setMaxAmount(3000000.0);
//        product1.setMinTerm(6);
//        product1.setMaxTerm(84);
//        product1.setDescription("Кредит на любые цели без обеспечения");
//        product1.setCategory(category1);
//        creditProductRepository.save(product1);
//
//
//// Ипотека
//        CreditProduct product2 = new CreditProduct();
//        product2.setName("Ипотека на новостройку");
//        product2.setInterestRate(8.9);
//        product2.setMinAmount(1000000.0);
//        product2.setMaxAmount(30000000.0);
//        product2.setMinTerm(12);
//        product2.setMaxTerm(360);
//        product2.setMinDownPayment(15.0);
//        product2.setDescription("Ипотечный кредит на покупку квартиры в новостройке");
//        product2.setCategory(category2);
//        creditProductRepository.save(product2);
//
//// Автокредит
//        CreditProduct product3 = new CreditProduct();
//        product3.setName("Автокредит на новый автомобиль");
//        product3.setInterestRate(7.5);
//        product3.setMinAmount(300000.0);
//        product3.setMaxAmount(10000000.0);
//        product3.setMinTerm(12);
//        product3.setMaxTerm(84);
//        product3.setMinDownPayment(20.0);
//        product3.setDescription("Кредит на покупку нового автомобиля");
//        product3.setCategory(category3);
//        creditProductRepository.save(product3);
//
//// Кредит с обеспечением
//        CreditProduct product4 = new CreditProduct();
//        product4.setName("Кредит под залог недвижимости");
//        product4.setInterestRate(10.0);
//        product4.setMinAmount(500000.0);
//        product4.setMaxAmount(15000000.0);
//        product4.setMinTerm(6);
//        product4.setMaxTerm(120);
//        product4.setDescription("Кредит под залог имеющейся недвижимости");
//        product4.setCategory(category4);
//        creditProductRepository.save(product4);
//
//// Рефинансирование
//        CreditProduct product5 = new CreditProduct();
//        product5.setName("Рефинансирование потребительских кредитов");
//        product5.setInterestRate(11.0);
//        product5.setMinAmount(100000.0);
//        product5.setMaxAmount(5000000.0);
//        product5.setMinTerm(12);
//        product5.setMaxTerm(84);
//        product5.setDescription("Объединение нескольких кредитов в один с пониженной ставкой");
//        product5.setCategory(category5);
//        creditProductRepository.save(product5);
//
//// Реструктуризация
//        CreditProduct product6 = new CreditProduct();
//        product6.setName("Реструктуризация задолженности");
//        product6.setInterestRate(0.0); // может быть 0 для некоторых программ
//        product6.setMinAmount(0.0);
//        product6.setMaxAmount(10000000.0);
//        product6.setMinTerm(1);
//        product6.setMaxTerm(120);
//        product6.setDescription("Изменение условий кредитного договора для заемщиков в трудной ситуации");
//        product6.setCategory(category6);
//        creditProductRepository.save(product6);
//
//// Кредитная карта
//        CreditProduct product7 = new CreditProduct();
//        product7.setName("Кредитная карта с льготным периодом");
//        product7.setInterestRate(23.9);
//        product7.setMinAmount(0.0);
//        product7.setMaxAmount(500000.0);
//        product7.setMinTerm(1);
//        product7.setMaxTerm(60);
//        product7.setDescription("Кредитная карта с льготным периодом до 100 дней");
//        product7.setCategory(category7);
//        creditProductRepository.save(product7);
//        // Дополнительный потребительский кредит
//        CreditProduct product8 = new CreditProduct();
//        product8.setName("Экспресс-кредит");
//        product8.setInterestRate(18.5);
//        product8.setMinAmount(10000.0);
//        product8.setMaxAmount(300000.0);
//        product8.setMinTerm(3);
//        product8.setMaxTerm(24);
//        product8.setDescription("Быстрый кредит наличными без справок");
//        product8.setCategory(category1);
//        creditProductRepository.save(product8);
//
//// Дополнительная ипотека
//        CreditProduct product9 = new CreditProduct();
//        product9.setName("Ипотека на вторичное жилье");
//        product9.setInterestRate(9.2);
//        product9.setMinAmount(1000000.0);
//        product9.setMaxAmount(25000000.0);
//        product9.setMinTerm(12);
//        product9.setMaxTerm(360);
//        product9.setMinDownPayment(20.0);
//        product9.setDescription("Ипотечный кредит на покупку квартиры на вторичном рынке");
//        product9.setCategory(category2);
//        creditProductRepository.save(product9);
//
//// Дополнительный автокредит
//        CreditProduct product10 = new CreditProduct();
//        product10.setName("Автокредит на подержанный автомобиль");
//        product10.setInterestRate(12.0);
//        product10.setMinAmount(100000.0);
//        product10.setMaxAmount(5000000.0);
//        product10.setMinTerm(12);
//        product10.setMaxTerm(60);
//        product10.setMinDownPayment(30.0);
//        product10.setDescription("Кредит на покупку автомобиля с пробегом");
//        product10.setCategory(category3);
//        creditProductRepository.save(product10);
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
