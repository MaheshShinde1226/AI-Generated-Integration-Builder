-- ===============================
-- Integration Application Table
-- ===============================
CREATE TABLE integration_app (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    base_url VARCHAR(255) NOT NULL,
    auth_type VARCHAR(50) NOT NULL
);

-- ===============================
-- Integration Endpoint Table
-- ===============================
CREATE TABLE integration_endpoint (
    id BIGINT PRIMARY KEY,
    path VARCHAR(255) NOT NULL,
    http_method VARCHAR(10) NOT NULL,

    -- Pagination configuration
    pagination_enabled BOOLEAN DEFAULT FALSE,
    pagination_type VARCHAR(20),        -- CURSOR / OFFSET
    next_page_field VARCHAR(255),        -- e.g. pagination.next_page

    app_id BIGINT NOT NULL,
    CONSTRAINT fk_endpoint_app
        FOREIGN KEY (app_id) REFERENCES integration_app(id)
);

-- ===============================
-- Field Mapping Table
-- ===============================
CREATE TABLE field_mapping (
    id BIGINT PRIMARY KEY,
    source_path VARCHAR(255) NOT NULL,   -- e.g. resource.email
    target_field VARCHAR(100) NOT NULL,  -- e.g. email
    endpoint_id BIGINT NOT NULL,

    CONSTRAINT fk_mapping_endpoint
        FOREIGN KEY (endpoint_id) REFERENCES integration_endpoint(id)
);

-- ===============================
-- Temporary User Storage
-- ===============================
CREATE TABLE temp_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    source_app VARCHAR(100)
);

