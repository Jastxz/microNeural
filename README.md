# MicroNeural

MicroNeural is a Spring Boot microservice designed to perform neural network calculations and provide optimal moves for various board games. It exposes a REST API that acts as a brain for game interfaces, calculating the best possible moves using advanced search algorithms and neural network evaluation.

## Suggesting Games

Currently, the service supports the following games:

- **Tic-Tac-Toe (Tres en raya)**
- **Cats vs Mouse (Gatos y Ratón)**
- **Checkers (Damas)**

## Technologies Used

- **Java 21**: The core programming language.
- **Spring Boot 4.0.1**: A framework for building the microservice.
- **Lombok**: Annotation library to reduce boilerplate code.
- **H2 Database**: In-memory database for potential data persistence needs.
- **Neural Lib**: A custom library (`org.javig:neural-lib`) used for the underlying neural network and game logic.
- **Maven**: Dependency management and build tool.

## Prerequisites

- Java Development Kit (JDK) 21 or higher.
- Maven 3.6 or higher.

## Installation and Execution

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/microNeural.git
    cd microNeural
    ```

2.  **Build the project:**

    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

The application will start on port `8080` (default).

## API Documentation

The API exposes endpoints to calculate optimal moves. All endpoints accept a generic `MundoRequest` and return a `TableroResponse` or an error.

### Base URL

`http://localhost:8080`

### Endpoints

#### 1. Tic-Tac-Toe

- **POST** `/v0/tresenraya`
- Calculates the next move for a Tic-Tac-Toe game.

#### 2. Cats vs Mouse

- **POST** `/v0/gatos`
- Calculates the next move for the Cats vs Mouse game.

#### 3. Checkers

- **POST** `/v0/damas`
- Calculates the next move for a Checkers game.

### Data Structures

#### Request Body (`MundoRequest`)

```json
{
  "data": [
    [0, 0, 0],
    [0, 1, 0],
    [2, 1, 0]
  ],
  "posicionFila": 1,
  "posicionColumna": 2,
  "marca": 1,
  "turno": 2,
  "dificultad": 2,
  "profundidad": 1
}
```

#### Response Body (`TableroResponse`)

```json
{
  "data": [
    [0, 0, 0],
    [0, 1, 0],
    [2, 1, 0]
  ],
  "fila": 1,
  "columna": 2
}
```

## Project Structure

The project follows a standard Spring Boot architecture:

- `src/main/java/org/javig/micro_neural`:
  - `config`: Application configuration files.
  - `controller`: REST controllers handling API requests.
  - `model`: Data models and DTOs.
  - `service`: Business logic (implied).
  - `MicroNeuralApplication.java`: Main entry point.
- `src/main/resources`:
  - `api.yml`: OpenAPI/Swagger definition.
  - `application.yml`: Main configuration file.

## Contact

For any questions or suggestions, please contact the development team.
