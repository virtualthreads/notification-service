CREATE TABLE customer (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          customer_code VARCHAR(50) NOT NULL UNIQUE,
                          name VARCHAR(150) NOT NULL,
                          email VARCHAR(255),
                          phone_number VARCHAR(30),
                          email_enabled BOOLEAN NOT NULL DEFAULT TRUE,
                          whatsapp_enabled BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE notification_template (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       template_code VARCHAR(100) NOT NULL UNIQUE,
                                       event_type VARCHAR(100) NOT NULL,
                                       description VARCHAR(255),
                                       active BOOLEAN NOT NULL DEFAULT TRUE,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       INDEX idx_template_event_type (event_type)
);

CREATE TABLE notification_template_channel (
                                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                               template_id BIGINT NOT NULL,
                                               channel VARCHAR(30) NOT NULL,
                                               subject VARCHAR(500),
                                               body TEXT NOT NULL,
                                               active BOOLEAN NOT NULL DEFAULT TRUE,
                                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                               updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                               CONSTRAINT fk_template_channel_template FOREIGN KEY (template_id) REFERENCES notification_template(id),
                                               CONSTRAINT uk_template_channel UNIQUE (template_id, channel)
);

CREATE TABLE notification (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              event_id VARCHAR(100) NOT NULL UNIQUE,
                              event_type VARCHAR(100) NOT NULL,
                              customer_id BIGINT,
                              payload JSON NOT NULL,
                              status VARCHAR(30) NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              CONSTRAINT fk_notification_customer FOREIGN KEY (customer_id) REFERENCES customer(id),
                              INDEX idx_notification_status (status),
                              INDEX idx_notification_event_type (event_type)
);

CREATE TABLE notification_delivery (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       notification_id BIGINT NOT NULL,
                                       channel VARCHAR(30) NOT NULL,
                                       recipient VARCHAR(255) NOT NULL,
                                       template_id BIGINT NOT NULL,
                                       status VARCHAR(30) NOT NULL,
                                       attempt_count INT NOT NULL DEFAULT 0,
                                       last_error TEXT,
                                       scheduled_at TIMESTAMP NULL,
                                       sent_at TIMESTAMP NULL,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       CONSTRAINT fk_delivery_notification FOREIGN KEY (notification_id) REFERENCES notification(id),
                                       CONSTRAINT fk_delivery_template FOREIGN KEY (template_id) REFERENCES notification_template(id),
                                       INDEX idx_delivery_status_scheduled (status, scheduled_at),
                                       INDEX idx_delivery_notification (notification_id)
);