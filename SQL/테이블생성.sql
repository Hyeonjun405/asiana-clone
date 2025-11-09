-- aircrafts (항공기 종류 + 좌석 구성 포함)
CREATE TABLE flights.aircrafts (
    aircraft_id BIGSERIAL PRIMARY KEY,   -- 항공기 ID
    aircraft_code VARCHAR(20) UNIQUE,    -- 항공기 코드 (예: A321)
    model_name VARCHAR(50) NOT NULL,     -- 모델명
    manufacturer VARCHAR(50),            -- 제조사
    economy_seats INT NOT NULL,          -- 이코노미 좌석 수
    business_seats INT DEFAULT 0,        -- 비즈니스 좌석 수
    first_seats INT DEFAULT 0,           -- 퍼스트 클래스 좌석 수
    total_capacity INT GENERATED ALWAYS AS
        (economy_seats + business_seats + first_seats) STORED
);


-- flights (비행기 노선 정보)
CREATE TABLE flights.flights (
    flight_id BIGSERIAL PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    aircraft_id BIGINT NOT NULL,
    departure_airport VARCHAR(50) NOT NULL,
    arrival_airport VARCHAR(50) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_flight_aircraft FOREIGN KEY (aircraft_id)
        REFERENCES flights.aircrafts (aircraft_id)
        ON DELETE RESTRICT
);


-- flight_status (항공편 운항 상태)
CREATE TABLE flights.flight_status (
    flight_id BIGINT PRIMARY KEY,             -- 항공편 ID
    status VARCHAR(20) NOT NULL,              -- 운항 상태 (scheduled, delayed, cancelled 등)
    delay_time INT DEFAULT 0,                 -- 지연 시간 (분)
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 최종 업데이트 시간
    CONSTRAINT fk_status_flight FOREIGN KEY (flight_id)
        REFERENCES flights.flights (flight_id)
        ON DELETE CASCADE
);

-- users (유저 테이블)
CREATE TABLE flights.users (
    user_id BIGSERIAL PRIMARY KEY,            -- 유저 ID
    username VARCHAR(50) NOT NULL,            -- 사용자 이름/아이디
    password VARCHAR(255) NOT NULL,           -- 비밀번호
    email VARCHAR(100),                       -- 이메일
    phone VARCHAR(20)                         -- 전화번호
);

-- reservations (항공편별 예약 정보)
CREATE TABLE flights.reservations (
    reservation_id BIGSERIAL PRIMARY KEY,     -- 예약 ID
    flight_id BIGINT NOT NULL,                -- 항공편 ID
    user_id BIGINT NOT NULL,                  -- 유저 ID
    seat_class VARCHAR(20) NOT NULL,          -- 좌석 등급
    passenger_name VARCHAR(50) NOT NULL,      -- 승객 이름
    passenger_type VARCHAR(10) NOT NULL,      -- 승객 유형 (성인/소아/유아)
    status VARCHAR(20) NOT NULL,              -- 예약 상태 (예약완료/취소 등)
    CONSTRAINT fk_res_flight FOREIGN KEY (flight_id)
        REFERENCES flights.flights (flight_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_res_user FOREIGN KEY (user_id)
        REFERENCES flights.users (user_id)
        ON DELETE CASCADE
);

-- flight_price (항공편당 금액)
CREATE TABLE flights.flight_price (
    flight_id BIGINT PRIMARY KEY REFERENCES flights.flights(flight_id),
    first_price NUMERIC, -- 퍼스트
    business_price NUMERIC, -- 비즈니스
    economy_price NUMERIC NOT NULL, -- 이코노미 가격
    option_price NUMERIC -- 옵션가격
);