INSERT INTO user_roles (id, role_name) VALUES
                                           (1, 'Клиент'),
                                           (2, 'Менеджер'),
                                           (3, 'Администратор')
    ON CONFLICT (id) DO NOTHING;