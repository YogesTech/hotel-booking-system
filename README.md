# Hotel Booking System

Hotel Booking System is a role-based web application built with Spring Boot, MySQL, Thymeleaf, and Spring Security. It allows users to browse hotels, book rooms, complete a payment flow, and manage bookings, while admins can manage hotel records and monitor booking activity.

## Features

- User registration and login
- Role-based access for admins and customers
- Hotel listing and hotel detail views
- Hotel booking workflow
- Admin dashboard
- Add, edit, and manage hotel records
- Booking management
- Payment flow
- MySQL-backed persistence using Spring Data JPA
- Deployment-ready configuration using environment variables

## Tech Stack

- **Backend:** Java, Spring Boot
- **Frontend:** Thymeleaf, HTML, CSS
- **Database:** MySQL
- **Security:** Spring Security
- **Build Tool:** Maven
- **Deployment:** Docker-ready configuration

## Project Structure

```text
src/main/java/com/yogesh/hotel_booking_system
├── config
├── controllers
├── models
├── repositories
├── security
└── services

src/main/resources
├── templates
├── data.sql
└── application.properties
```

## Environment Variables

Create the required database configuration using environment variables:

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/hotel_booking_system
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
```

## Local Setup

1. Clone the repository:

```bash
git clone https://github.com/YogesTech/hotel-booking-system.git
cd hotel-booking-system
```

2. Create a MySQL database:

```sql
CREATE DATABASE hotel_booking_system;
```

3. Configure the environment variables listed above.

4. Run the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

5. Open the app:

```text
http://localhost:8080
```

## Testing

Run the test suite:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## Future Improvements

- Add screenshots and live demo link
- Add room availability validation
- Add booking cancellation and booking history
- Add payment gateway integration
- Add admin analytics for bookings and revenue
- Add API documentation and stronger test coverage

## Author

**Yogeshwaran B**  
GitHub: https://github.com/YogesTech  
Portfolio: https://yogeshdeveloper.netlify.app
