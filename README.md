# Project: Blog Application

## Part 1: CRUD
### Technologies Used:
- Spring MVC
- Spring Data JPA
- Thymeleaf

### Use Cases for Non-Logged-In Users:
- Read a list of blog posts with title, excerpt, author, published DateTime, and tags.
- Read full blog posts with title, content, author, published DateTime, and tags.
- Filter blog posts by author, published DateTime, and tags.
- Sort blog posts by published DateTime.
- Search blog posts using full-text search on title, content, author, and tags.
- Navigate through paginated blog lists (each page with a maximum of 10 posts).
- Create, update, and delete blog posts.
- Comment on blog posts using a form containing name, email, and comment.
- Read, update, and delete comments.

### Database Schema:
#### User
- id
- name
- email
- password

#### Posts
- id
- title
- excerpt
- content
- author
- published_at
- is_published
- created_at
- updated_at

#### Tags
- id
- name
- created_at
- updated_at

#### Post_Tags
- post_id
- tag_id
- created_at
- updated_at

#### Comments
- id
- name
- email
- comment
- post_id
- created_at
- updated_at

### Steps to Build:
1. Create HTML and CSS.
2. Design and implement the database schema.
3. Integrate Thymeleaf with HTML/CSS.
4. Connect data with the frontend using Spring MVC and Spring Data JPA.

### Steps for M2:
- Implement functionality for authors and tags.
- Enable commenting.
- Implement pagination, filters, sorting, and searching.

### Steps for M3 (Future Development):
- Authentication
- Authorization

---

## Part 2: Authentication & Authorization
### Technologies Used:
- Spring Boot
- Spring Data JPA
- Spring Security
- Thymeleaf

### Use Cases (Non-Logged-In & Logged-In Users):
- Read, filter, sort, and search blog posts.
- Create, update, and delete blog posts (based on roles).
- Comment on blog posts and manage own comments.
- Admin privileges for creating, updating, and deleting posts.

### Steps to Implement:
1. Incorporate Spring Security for authentication and authorization.
2. Enable user roles for different permissions.
3. Implement CRUD operations based on user roles.

---

## Setup Instructions

### Database Configuration
This application uses PostgreSQL and requires environment variables for database connection.

**DO NOT commit your actual database credentials to GitHub!**

#### Setting Environment Variables:

**On Railway/Production:**
Set these environment variables in your Railway dashboard:
- `DATABASE_URL=jdbc:postgresql://postgres.railway.internal:5432/railway`
- `DATABASE_USERNAME=postgres`
- `DATABASE_PASSWORD=your_actual_password`

**On Local Development (Windows PowerShell):**
```powershell
$env:DATABASE_URL="jdbc:postgresql://localhost:5432/blog_db"
$env:DATABASE_USERNAME="postgres"
$env:DATABASE_PASSWORD="your_local_password"
```

**On Local Development (Linux/Mac):**
```bash
export DATABASE_URL="jdbc:postgresql://localhost:5432/blog_db"
export DATABASE_USERNAME="postgres"
export DATABASE_PASSWORD="your_local_password"
```

**Using IntelliJ IDEA:**
1. Go to Run → Edit Configurations
2. Select your Spring Boot application
3. Under Environment Variables, add:
   - `DATABASE_URL=jdbc:postgresql://localhost:5432/blog_db`
   - `DATABASE_USERNAME=postgres`
   - `DATABASE_PASSWORD=your_local_password`

### Running the Application
1. Set up your environment variables as described above
2. Run: `./mvnw spring-boot:run`
3. Access the application at `http://localhost:8080`

