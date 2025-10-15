--  Flights (비행기 노선 정보)
CREATE TABLE flights.Flights (
    flight_id BIGSERIAL PRIMARY KEY,          -- 항공편 ID
    flight_number VARCHAR(20) NOT NULL,       -- 항공편 번호
    "from" VARCHAR(50) NOT NULL,             -- 출발지
    "to" VARCHAR(50) NOT NULL,               -- 도착지
    departure_time TIMESTAMP NOT NULL,       -- 출발 시간
    arrival_time TIMESTAMP NOT NULL          -- 도착 시간
);

-- Airplane_Seats (항공편별 좌석 정보)
CREATE TABLE flights.Airplane_Seats (
    seat_id BIGSERIAL PRIMARY KEY,           -- 좌석 정보 ID
    flight_id BIGINT NOT NULL,               -- 항공편 ID
    class_type VARCHAR(20) NOT NULL,         -- 좌석 등급 (이코노미, 비즈니스 등)
    total_seats INT NOT NULL,                -- 총 좌석 수
    CONSTRAINT fk_flight FOREIGN KEY(flight_id) REFERENCES flights.Flights(flight_id)
);

-- Flight_Status (항공편별 운행 상태)
CREATE TABLE flights.Flight_Status (
    flight_id BIGINT PRIMARY KEY,            -- 항공편 ID
    status VARCHAR(20) NOT NULL,             -- 비행 상태 (scheduled, delayed, cancelled 등)
    delay_time INT DEFAULT 0,                -- 지연 시간 (분)
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 최종 업데이트 시간
    CONSTRAINT fk_flight_status FOREIGN KEY(flight_id) REFERENCES flights.Flights(flight_id)
);

-- Users (유저 테이블)
CREATE TABLE flights.Users (
    user_id BIGSERIAL PRIMARY KEY,           -- 유저 ID
    username VARCHAR(50) NOT NULL,           -- 사용자 이름/아이디
    password VARCHAR(255) NOT NULL,          -- 비밀번호
    email VARCHAR(100),                       -- 이메일
    phone VARCHAR(20)                         -- 전화번호
);


-- Reservations (항공편별 예약 정보)
CREATE TABLE flights.Reservations (
    reservation_id BIGSERIAL PRIMARY KEY,    -- 예약 ID
    flight_id BIGINT NOT NULL,               -- 항공편 ID
    user_id BIGINT NOT NULL,                 -- 유저 ID
    seat_class VARCHAR(20) NOT NULL,         -- 좌석 등급
    passenger_name VARCHAR(50) NOT NULL,     -- 승객 이름
    passenger_type VARCHAR(10) NOT NULL,     -- 승객 유형 (성인/소아/유아)
    status VARCHAR(20) NOT NULL,             -- 예약 상태 (예약완료/취소 등)
    CONSTRAINT fk_flight_res FOREIGN KEY(flight_id) REFERENCES flights.Flights(flight_id),
    CONSTRAINT fk_user_res FOREIGN KEY(user_id) REFERENCES flights.Users(user_id)
);