
-- 유저 기능 관련 추가 구현
-- 유저테이블에 잠금 관련 상황 표기
ALTER TABLE flights.users
ADD COLUMN account_status VARCHAR(20) DEFAULT 'ACTIVE',   -- ACTIVE, LOCKED, SUSPENDED 등
ADD COLUMN failed_attempts INT DEFAULT 0,                 -- 로그인 실패 횟수
ADD COLUMN locked_at TIMESTAMP;

-- 로그표기
CREATE TABLE flights.user_account_status_log (
    log_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES flights.users(user_id),
    status VARCHAR(20) NOT NULL,             -- ACTIVE, LOCKED, SUSPENDED 등
    reason VARCHAR(255),                     -- 차단사유 (예: 로그인 실패 5회)
    changed_at TIMESTAMP DEFAULT now()
);