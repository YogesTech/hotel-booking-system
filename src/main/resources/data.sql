-- Insert demo users
INSERT INTO users (username, password, role) VALUES
('demoUser', '$2a$10$7N6jZ1q2GclY8WbV7CkP6u/Rm6P3.5H2RzOkdZ4g2TojM8Z0xOq2W', 'USER'), -- password: demo123
('adminUser', '$2a$10$7N6jZ1q2GclY8WbV7CkP6u/Rm6P3.5H2RzOkdZ4g2TojM8Z0xOq2W', 'ADMIN'); -- password: demo123

-- Insert demo hotels
INSERT INTO hotels (name, city, address, description, rating) VALUES
('Grand Palace', 'Chennai', '123 Marina Road', 'Luxury 5-star hotel near beach', 5),
('Budget Inn', 'Bangalore', '45 MG Road', 'Affordable and cozy stay', 3),
('Skyline Resort', 'Hyderabad', 'Airport Road', 'Resort with pool and spa', 4);

-- Insert demo bookings
INSERT INTO bookings (user_id, hotel_id, check_in, check_out, status) VALUES
(1, 1, '2025-09-05', '2025-09-07', 'CONFIRMED'),
(1, 2, '2025-09-10', '2025-09-12', 'PENDING');
