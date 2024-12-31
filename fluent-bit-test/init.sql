USE log_test;

CREATE TABLE IF NOT EXISTS application_logs (
                                                id INT AUTO_INCREMENT PRIMARY KEY,
                                                log_level VARCHAR(10),
    logger_name VARCHAR(255),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
