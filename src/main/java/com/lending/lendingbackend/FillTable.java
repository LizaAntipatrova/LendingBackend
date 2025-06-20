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
//import java.math.BigDecimal;
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
//        product1.setInterestRate(new BigDecimal(12.5));
//        product1.setMinAmount(new BigDecimal(50000.0));
//        product1.setMaxAmount(new BigDecimal(3000000.0));
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
//        product2.setInterestRate(new BigDecimal(8.9));
//        product2.setMinAmount(new BigDecimal(1000000.0));
//        product2.setMaxAmount(new BigDecimal(30000000.0));
//        product2.setMinTerm(12);
//        product2.setMaxTerm(360);
//        product2.setMinDownPayment(new BigDecimal(15.0));
//        product2.setDescription("Ипотечный кредит на покупку квартиры в новостройке");
//        product2.setCategory(category2);
//        creditProductRepository.save(product2);
//
//// Автокредит
//        CreditProduct product3 = new CreditProduct();
//        product3.setName("Автокредит на новый автомобиль");
//        product3.setInterestRate(new BigDecimal(7.5));
//        product3.setMinAmount(new BigDecimal(300000.0));
//        product3.setMaxAmount(new BigDecimal(10000000.0));
//        product3.setMinTerm(12);
//        product3.setMaxTerm(84);
//        product3.setMinDownPayment(new BigDecimal(20.0));
//        product3.setDescription("Кредит на покупку нового автомобиля");
//        product3.setCategory(category3);
//        creditProductRepository.save(product3);
//
//// Кредит с обеспечением
//        CreditProduct product4 = new CreditProduct();
//        product4.setName("Кредит под залог недвижимости");
//        product4.setInterestRate(new BigDecimal(10.0));
//        product4.setMinAmount(new BigDecimal(500000.0));
//        product4.setMaxAmount(new BigDecimal(15000000.0));
//        product4.setMinTerm(6);
//        product4.setMaxTerm(120);
//        product4.setDescription("Кредит под залог имеющейся недвижимости");
//        product4.setCategory(category4);
//        creditProductRepository.save(product4);
//
//// Рефинансирование
//        CreditProduct product5 = new CreditProduct();
//        product5.setName("Рефинансирование потребительских кредитов");
//        product5.setInterestRate(new BigDecimal(11.0));
//        product5.setMinAmount(new BigDecimal(100000.0));
//        product5.setMaxAmount(new BigDecimal(5000000.0));
//        product5.setMinTerm(12);
//        product5.setMaxTerm(84);
//        product5.setDescription("Объединение нескольких кредитов в один с пониженной ставкой");
//        product5.setCategory(category5);
//        creditProductRepository.save(product5);
//
//// Реструктуризация
//        CreditProduct product6 = new CreditProduct();
//        product6.setName("Реструктуризация задолженности");
//        product6.setInterestRate(new BigDecimal(0.0)); // может быть 0 для некоторых программ
//        product6.setMinAmount(new BigDecimal(0.0));
//        product6.setMaxAmount(new BigDecimal(10000000.0));
//        product6.setMinTerm(1);
//        product6.setMaxTerm(120);
//        product6.setDescription("Изменение условий кредитного договора для заемщиков в трудной ситуации");
//        product6.setCategory(category6);
//        creditProductRepository.save(product6);
//
//// Кредитная карта
//        CreditProduct product7 = new CreditProduct();
//        product7.setName("Кредитная карта с льготным периодом");
//        product7.setInterestRate(new BigDecimal(23.9));
//        product7.setMinAmount(new BigDecimal(0.0));
//        product7.setMaxAmount(new BigDecimal(500000.0));
//        product7.setMinTerm(1);
//        product7.setMaxTerm(60);
//        product7.setDescription("Кредитная карта с льготным периодом до 100 дней");
//        product7.setCategory(category7);
//        creditProductRepository.save(product7);
//        // Дополнительный потребительский кредит
//        CreditProduct product8 = new CreditProduct();
//        product8.setName("Экспресс-кредит");
//        product8.setInterestRate(new BigDecimal(18.5));
//        product8.setMinAmount(new BigDecimal(10000.0));
//        product8.setMaxAmount(new BigDecimal(300000.0));
//        product8.setMinTerm(3);
//        product8.setMaxTerm(24);
//        product8.setDescription("Быстрый кредит наличными без справок");
//        product8.setCategory(category1);
//        creditProductRepository.save(product8);
//
//        // Кредит под залог имущества
//        CreditProduct product9 = new CreditProduct();
//        product3.setName("Кредит под залог недвижимости");
//        product3.setInterestRate(new BigDecimal(9.5));
//        product3.setMinAmount(new BigDecimal(500000.0));
//        product3.setMaxAmount(new BigDecimal(10000000.0));
//        product3.setMinTerm(12);
//        product3.setMaxTerm(120);
//        product3.setDescription("Кредит на любые цели под залог квартиры, дома или коммерческой недвижимости");
//        product3.setCategory(category1);
//        creditProductRepository.save(product3);
//
//        // Образовательный кредит
//        CreditProduct product10 = new CreditProduct();
//        product4.setName("Образовательный кредит");
//        product4.setInterestRate(new BigDecimal(7.9));
//        product4.setMinAmount(new BigDecimal(50000.0));
//        product4.setMaxAmount(new BigDecimal(2000000.0));
//        product4.setMinTerm(12);
//        product4.setMaxTerm(60);
//        product4.setDescription("Кредит на оплату обучения с льготным периодом погашения");
//        product4.setCategory(category1);
//        creditProductRepository.save(product4);
//
//
//// Дополнительная ипотека
//        CreditProduct product11 = new CreditProduct();
//        product9.setName("Ипотека на вторичное жилье");
//        product9.setInterestRate(new BigDecimal(9.2));
//        product9.setMinAmount(new BigDecimal(1000000.0));
//        product9.setMaxAmount(new BigDecimal(25000000.0));
//        product9.setMinTerm(12);
//        product9.setMaxTerm(360);
//        product9.setMinDownPayment(new BigDecimal(20.0));
//        product9.setDescription("Ипотечный кредит на покупку квартиры на вторичном рынке");
//        product9.setCategory(category2);
//        creditProductRepository.save(product9);
//
//// Дополнительный автокредит
//        CreditProduct product12 = new CreditProduct();
//        product10.setName("Автокредит на подержанный автомобиль");
//        product10.setInterestRate(new BigDecimal(12.0));
//        product10.setMinAmount(new BigDecimal(100000.0));
//        product10.setMaxAmount(new BigDecimal(5000000.0));
//        product10.setMinTerm(12);
//        product10.setMaxTerm(60);
//        product10.setMinDownPayment(new BigDecimal(30.0));
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
//        manager1.setLastName("Смирнов");
//        manager1.setFirstName("Владимир");
//        manager1.setMiddleName("Михайлович");
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
//        manager2.setLastName("Кузнецов");
//        manager2.setFirstName("Алексей");
//        manager2.setMiddleName("Александрович");
//        manager2.setPhone("89005353535");
//        manager2.setEmail("job.pupupu@gmail.com");
//        manager2.setUser(user1);
//        Set<CreditCategory> categories2 = new HashSet<>();
//        categories2.add(category1);
//        manager2.setSpecializations(categories2);
//        managerRepository.save(manager2);
//
//        User user3 = new User();
//        user3.setRole(UserRole.MANAGER);
//        user3.setLogin("44");
//        user3.setPassword(encoder.encode("44"));
//        user3.setRegistrationDate(LocalDateTime.now());
//        user3 = userRepository.save(user3);
//
//        Manager manager3 = new Manager();
//        manager3.setLastName("Сафронова");
//        manager3.setFirstName("Екатерина");
//        manager3.setMiddleName("Ивановна");
//        manager3.setPhone("89005373535");
//        manager3.setEmail("job3.pupupu@gmail.com");
//        manager3.setUser(user3);
//        manager3.setSpecializations(categories2);
//        managerRepository.save(manager3);
//
//
//
//
//
//    }
//}
