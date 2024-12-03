-- Create User table (base for Student and Instructor)
CREATE TABLE "User" (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL CHECK (type IN ('student', 'instructor'))
);

-- Create Student table
CREATE TABLE student (
    user_id INTEGER PRIMARY KEY REFERENCES "User"(user_id)
);

-- Create Admin table
CREATE TABLE admin (
    user_id INTEGER PRIMARY KEY REFERENCES "User"(user_id)
);


-- Create Instructor table
CREATE TABLE Instructor (
    user_id INTEGER PRIMARY KEY REFERENCES "User"(user_id)
);

-- Create course table
CREATE TABLE course (
    course_id SERIAL PRIMARY KEY,
    course_title VARCHAR(255) NOT NULL,
    description TEXT,
    available_spots INTEGER CHECK (available_spots >= 0),
    start_date DATE,
    end_date DATE,
    instructor_id INTEGER REFERENCES Instructor(user_id)
);

-- Create module table
CREATE TABLE module (
    module_id SERIAL PRIMARY KEY,
    module_title VARCHAR(255) NOT NULL,
    module_content TEXT
);

-- Create assignment table
CREATE TABLE assignment (
    assignment_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date TIMESTAMP WITH TIME ZONE,
    module_id INTEGER REFERENCES Module(module_id)
);

-- Create studentCourse table for many-to-many relationship
CREATE TABLE studentCourse (
    student_id INTEGER REFERENCES Student(user_id),
    course_id INTEGER REFERENCES Course(course_id),
    enrollment_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (student_id, course_id)
);

-- Create instructorCourse table for many-to-many relationship
CREATE TABLE instructorCourse (
    instructor_id INTEGER REFERENCES Instructor(user_id),
    course_id INTEGER REFERENCES Course(course_id),
    PRIMARY KEY (instructor_id, course_id)
);

-- Create CourseModule table for many-to-many relationship
CREATE TABLE courseModule (
    course_id INTEGER REFERENCES Course(course_id),
    module_id INTEGER REFERENCES Module(module_id),
    module_order INTEGER NOT NULL,
    PRIMARY KEY (course_id, module_id)
);

-- Create forum table
CREATE TABLE forum (
    forum_id SERIAL PRIMARY KEY,
    topic VARCHAR(255) NOT NULL,
    related_topics TEXT[]
);

CREATE TABLE message (
    message_id SERIAL PRIMARY KEY,
    message_content TEXT NOT NULL,
    sender_id INTEGER REFERENCES "User"(user_id),
    receiver_id INTEGER REFERENCES "User"(user_id),
    forum_id INTEGER REFERENCES Forum(forum_id)
);

CREATE TABLE quiz (
    quiz_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    contents TEXT NOT NULL,
    correct_answer INTEGER
);

CREATE TABLE moduleQuiz (
    module_id INTEGER REFERENCES Module(module_id),
    quiz_id INTEGER REFERENCES Quiz(quiz_id),
    PRIMARY KEY (module_id, quiz_id)
);

CREATE TABLE assignmentQuiz (
    assignment_id INTEGER REFERENCES Assignment(assignment_id),
    quiz_id INTEGER REFERENCES Quiz(quiz_id),
    PRIMARY KEY (assignment_id, quiz_id)
);


