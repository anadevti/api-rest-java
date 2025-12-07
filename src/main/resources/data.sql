-- Usuários
INSERT INTO users (uuid, name, email, dob) VALUES
                                                 (gen_random_uuid(), 'Ana Silva',     'ana.silva@example.com',     DATE '1995-03-12'),
                                                 (gen_random_uuid(), 'Bruno Costa',   'bruno.costa@example.com',   DATE '1988-07-25'),
                                                 (gen_random_uuid(), 'Carla Souza',   'carla.souza@example.com',   DATE '2001-11-03');

-- Postagens
INSERT INTO post (uuid, title, body, createdDate, user_id) VALUES
                                                        (gen_random_uuid(), 'Primeiro post', 'Olá, este é meu primeiro post!',
                                                        TIMESTAMP '2025-01-10 14:23:00', 1),
                                                        (gen_random_uuid(), 'Hoje foi legal', 'Saí para caminhar e o dia estava ótimo.',
                                                        TIMESTAMP '2025-01-11 09:12:45', 2),
                                                        (gen_random_uuid(), 'Estudando SQL', 'Aprendendo a criar tabelas e relacionamentos.',
                                                        TIMESTAMP '2025-01-12 20:05:30', 1),
                                                        (gen_random_uuid(), 'Boa noite', 'Passando só pra desejar boa noite :)',
                                                        TIMESTAMP '2025-01-13 22:47:10', 3);