
# Class Registration - By André Pícoli

This application manages the relationship between students and courses.




## Run Locally

Clone the project

```bash
  git clone https://github.com/andreluispicoli/school.git
```

Go to the project directory

```bash
  cd school
```
Now it is time to let docker-compose make the magic...


## Deployment

Once you have docker-compose installed in your machine, you will be able to deploy it after cloning.

```bash
  docker-compose up -d
```

When docker-compose is ready, the application will be able to get requests.
## API Reference

Postman collection to import
https://github.com/andreluispicoli/school/blob/main/school.json

## Student

#### Get all Students

```http
  GET http://localhost:8080/students/
```

#### Get all Students without a course enrolled

```http
  GET http://localhost:8080/students/empty
```

#### Get all Students for a specific Course

```http
  GET http://localhost:8080/students/course/2
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of course to fetch |


#### Get Student by id

```http
  GET http://localhost:8080/students/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of student to fetch |

#### Create Student

```http
  POST http://localhost:8080/students/
```

body example
``` reponse
{
	"name": "Jorge"
}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`    | `string` | **Required**. name of student     |

#### Update Student

```http
  PATCH http://localhost:8080/students/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of student         |
| `name`    | `string` | **Required**. name of student     |

body example
``` reponse
{
	"name": "Charles"
}
```

#### Delete Student

```http
  DELETE http://localhost:8080/students/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of student         |

#### Register Student into Course

```http
  PUT http://localhost:8080/students/register/1/course/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of student         |
| `id`      | `long` | **Required**. Id of course          |

## Course

#### Get all Courses

```http
  GET http://localhost:8080/courses/
```

#### Get all Courses without a student enrolled

```http
  GET http://localhost:8080/courses/empty
```

#### Get all Courses for a specific Course

```http
  GET http://localhost:8080/course/student/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of student to fetch |

#### Get Course by id

```http
  GET http://localhost:8080/courses/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long` | **Required**. Id of course to fetch |

#### Create Student

```http
  POST http://localhost:8080/courses/
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. name of course   |

body example
``` reponse
{
	"name": "Arts"
}
```

#### Update Course

```http
  PATCH http://localhost:8080/courses/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long`   | **Required**. Id of course        |
| `name`    | `string` | **Required**. name of course      |

body example
``` reponse
{
	"name": "Biology"
}
```

#### Delete Student

```http
  DELETE http://localhost:8080/courses/1
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `long`   | **Required**. Id of course        |


## Tech Stack

**Client:** Spring, Gradle, Docker, JUnit, MySQL

**Server:** Java, Apache Tomcat

