# 🧪 Lab Experiment Logbook

A full-stack web application for managing laboratory experiments with Spring Boot backend and React frontend.

## 🚀 Features

- **User Authentication**: Secure login system with JWT tokens
- **Experiment Management**: Create, read, update, and delete experiments
- **Experiment Status Tracking**: Track experiments from draft to completion
- **Search Functionality**: Search experiments by title and objective
- **Responsive Design**: Modern UI with Bootstrap styling
- **Real-time Notifications**: Toast notifications for user feedback

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.2.0**: Main framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database operations
- **MySQL**: Database
- **JWT**: Token-based authentication
- **Maven**: Build tool

### Frontend
- **React 18**: UI framework
- **React Router**: Navigation
- **React Bootstrap**: UI components
- **Axios**: HTTP client
- **React Toastify**: Notifications
- **React Hook Form**: Form handling

## 📋 Prerequisites

- Java 17 or higher
- Node.js 16 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## 🗄️ Database Setup

1. Install MySQL if not already installed
2. Create a database named `emis` (or update the configuration)
3. Update database credentials in `backend/src/main/resources/application.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 🚀 Quick Start

### 1. Clone and Setup

```bash
# Navigate to project directory
cd SBproject

# Install frontend dependencies
cd frontend
npm install

# Return to root
cd ..
```

### 2. Start Backend

```bash
# Navigate to backend directory
cd backend

# Run Spring Boot application
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Start Frontend

```bash
# Open new terminal and navigate to frontend
cd frontend

# Start React development server
npm start
```

The frontend will start on `http://localhost:3000`

## 🔐 Authentication

For demo purposes, you can use any username and password to login. The system will automatically create a user account if it doesn't exist.

## 📁 Project Structure

```
SBproject/
├── backend/                 # Spring Boot Backend
│   ├── src/main/java/
│   │   └── com/lablogbook/backend/
│   │       ├── config/      # Security configuration
│   │       ├── controller/  # REST controllers
│   │       ├── dto/         # Data transfer objects
│   │       ├── entity/      # JPA entities
│   │       ├── repository/  # Data access layer
│   │       ├── service/     # Business logic
│   │       └── util/        # Utilities (JWT)
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/                # React Frontend
│   ├── src/
│   │   ├── components/      # Reusable components
│   │   ├── context/         # React context
│   │   ├── pages/           # Page components
│   │   └── services/        # API services
│   ├── public/
│   └── package.json
└── README.md
```

## 🔧 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Experiments
- `GET /api/experiments` - Get all experiments
- `GET /api/experiments/{id}` - Get experiment by ID
- `POST /api/experiments` - Create new experiment
- `PUT /api/experiments/{id}` - Update experiment
- `DELETE /api/experiments/{id}` - Delete experiment
- `GET /api/experiments/search` - Search experiments

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## 🎯 Usage

1. **Login**: Use any username and password to access the system
2. **Dashboard**: View all your experiments with search functionality
3. **Create Experiment**: Click "New Experiment" to create a new experiment
4. **Edit Experiment**: Click "Edit" on any experiment to modify it
5. **View Details**: Click "View Details" to see full experiment information
6. **Status Management**: Update experiment status as you progress

## 🐛 Troubleshooting

### Backend Issues
- Ensure MySQL is running and accessible
- Check database credentials in `application.properties`
- Verify Java 17+ is installed: `java -version`

### Frontend Issues
- Clear node_modules and reinstall: `rm -rf node_modules && npm install`
- Check if port 3000 is available
- Ensure backend is running on port 8080

### Database Issues
- Verify MySQL service is running
- Check database connection settings
- Ensure database `emis` exists

## 📝 License

This project is for educational purposes.

## 🤝 Contributing

Feel free to submit issues and enhancement requests! 