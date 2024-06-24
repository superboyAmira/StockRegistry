-- для автоматической генерации UUID можно использовать расширение uuid-ossp

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

BEGIN;
DO $$
DECLARE issuer1_UUID UUID := uuid_generate_v4();
DECLARE issuer2_UUID UUID := uuid_generate_v4();
DECLARE issuer3_UUID UUID := uuid_generate_v4();

DECLARE registry_UUID UUID := uuid_generate_v4();

DECLARE subject1_UUID UUID := uuid_generate_v4();
DECLARE subject2_UUID UUID := uuid_generate_v4();
DECLARE subject3_UUID UUID := uuid_generate_v4();
DECLARE subject4_UUID UUID := uuid_generate_v4();
DECLARE subject5_UUID UUID := uuid_generate_v4();

DECLARE stock1_UUID UUID := uuid_generate_v4();
DECLARE stock2_UUID UUID := uuid_generate_v4();
DECLARE stock3_UUID UUID := uuid_generate_v4();
DECLARE stock4_UUID UUID := uuid_generate_v4();
DECLARE stock5_UUID UUID := uuid_generate_v4();
DECLARE stock6_UUID UUID := uuid_generate_v4();

BEGIN
INSERT INTO issuer (id, authorized_capital, company, citizenship) VALUES
(issuer1_UUID, 1000000, 'Yandex', 'RUS'),
(uuid_generate_v4(), 10, 'Poor Company Co', 'ENG'),
(issuer2_UUID, 100000000, 'Russian Federation', 'RUS'),
(issuer3_UUID, 1000000, 'Apple Co', 'USA');

INSERT INTO issuer_account (issuer_id, currency) VALUES 
(issuer1_UUID, 'RUB'),
(issuer2_UUID, 'RUB'),
(issuer3_UUID, 'USD');

INSERT INTO registry (id, name, owner) VALUES 
(registry_UUID, 'Russian Registry', 'Russian Federation');

INSERT INTO subject (id, contact_info, subject_type) VALUES 
(subject1_UUID, 'Ivan +7(900)100-10-10', 'Individual'),
(subject2_UUID, 'Misha +7(900)100-20-10', 'Individual'),
(subject3_UUID, 'Tinkoff tinkoff.ru', 'Corporate'),
(subject4_UUID, 'Yarik +7(900)120-20-10', 'Individual'),
(subject5_UUID, 'Rostelekom rostelekom.ru', 'Corporate');

INSERT INTO stock (id, registry_id, subject_id, issuer_account_id, quote, form) VALUES 
(stock1_UUID, registry_UUID, subject1_UUID, 1, 150, 'Stock'),
(stock2_UUID, registry_UUID, subject2_UUID, 3, 200, 'Stock'),
(stock3_UUID, registry_UUID, subject2_UUID, 1, 249, 'Stock');

INSERT INTO stock (id, registry_id, subject_id, issuer_account_id, quote, form, nominal_value) VALUES 
(stock4_UUID, registry_UUID, subject4_UUID, 2, 949, 'Bond', 1000),
(stock5_UUID, registry_UUID, subject5_UUID, 3, 100025, 'Futures', 100000),
(stock6_UUID, registry_UUID, subject3_UUID, 1, 2951, 'Bond', 3000);

INSERT INTO operation (seller_subject_id, buyer_subject_id, registry_id, stock_id) VALUES 
(subject5_UUID, subject3_UUID, registry_UUID, stock6_UUID),
(subject2_UUID, subject1_UUID, registry_UUID, stock1_UUID);

INSERT INTO payment (subject_id, issuer_id, stock_id, amount) VALUES 
(subject4_UUID, issuer2_UUID, stock4_UUID, 7.44),
(subject3_UUID, issuer1_UUID, stock6_UUID, 21.50);

END $$;


COMMIT;