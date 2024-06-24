CREATE TABLE IF NOT EXISTS issuer (
    id UUID PRIMARY KEY,
    authorized_capital BIGINT,
    company VARCHAR(255),
    citizenship VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS registry (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    owner VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS subject (
    id UUID PRIMARY KEY,
    contact_info VARCHAR(255),
    subject_type VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS issuer_account (
    id BIGSERIAL PRIMARY KEY,
    issuer_id UUID,
    currency VARCHAR(10),
    FOREIGN KEY (issuer_id) REFERENCES issuer(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS stock (
    id UUID PRIMARY KEY,
    registry_id UUID,
    subject_id UUID,
    issuer_account_id BIGINT,
    quote BIGINT NOT NULL,
    form VARCHAR(100),
    nominal_value BIGINT,
    stock_quote_change BIGINT DEFAULT 0,
    FOREIGN KEY (registry_id) REFERENCES registry(id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE SET NULL,
    FOREIGN KEY (issuer_account_id) REFERENCES issuer_account(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS operation (
    id BIGSERIAL PRIMARY KEY,
    seller_subject_id UUID,
    buyer_subject_id UUID,
    registry_id UUID,
    stock_id UUID,
    operation_date TIMESTAMP DEFAULT current_timestamp,
    FOREIGN KEY (seller_subject_id) REFERENCES subject(id) ON DELETE SET NULL,
    FOREIGN KEY (buyer_subject_id) REFERENCES subject(id) ON DELETE SET NULL,
    FOREIGN KEY (registry_id) REFERENCES registry(id) ON DELETE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS payment (
    id BIGSERIAL PRIMARY KEY,
    subject_id UUID,
    issuer_id UUID,
    stock_id UUID,
    amount FLOAT,
    FOREIGN KEY (subject_id) REFERENCES subject(id) ON DELETE SET NULL,
    FOREIGN KEY (issuer_id) REFERENCES issuer(id) ON DELETE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_stock_owner()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE stock
    SET subject_id = NEW.buyer_subject_id
    WHERE id = NEW.stock_id;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_stock_owner_trigger
    AFTER INSERT ON operation
    FOR EACH ROW
EXECUTE FUNCTION update_stock_owner();