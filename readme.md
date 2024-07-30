## Spring Boot Blog Application with CRUD Operations and Image Upload

This repository contains a Spring Boot application for a blog that implements CRUD (Create, Read, Update, Delete) operations and image uploading functionality. The application uses MySQL as its database and provides a RESTful API for interacting with blog posts.

### Features

- **Create, Read, Update, and Delete blog posts:**
    - Create new blog posts with title, content, and image.
    - Retrieve existing blog posts.
    - Update existing blog posts.
    - Delete existing blog posts.
- **Image Upload:**
    - Upload images for blog posts.
    - Store images in a specified directory.
- **RESTful API:**
    - Expose endpoints for performing CRUD operations on blog posts.
    - Use Postman to interact with the API.

### Prerequisites

- Java 8 or higher
- Maven
- MySQL database
- Postman (for testing the API)

### Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/spring-boot-blog-app.git
   ```

2. **Configure database connection:**
   - Update the `application.properties` file with your MySQL database credentials.

3. **Build the application:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Test the API using Postman:**
   - Use the provided Postman collection to test the various API endpoints.

### Usage

- **Create a new blog post:**
   - Send a POST request to the `/posts` endpoint with the following data:
     ```json
     {
       "title": "My First Blog Post",
       "content": "This is my first blog post.",
       "image": "your-image-file.jpg" // optional
     }
     ```
- **Retrieve all blog posts:**
   - Send a GET request to the `/posts` endpoint.
- **Retrieve a specific blog post:**
   - Send a GET request to the `/posts/{id}` endpoint, replacing `{id}` with the blog post ID.
- **Update a blog post:**
   - Send a PUT request to the `/posts/{id}` endpoint with the updated data.
- **Delete a blog post:**
   - Send a DELETE request to the `/posts/{id}` endpoint.

### Technologies Used

- Spring Boot
- Spring Data JPA
- MySQL
- Java
- Maven
- Postman
