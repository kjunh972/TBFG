CREATE DATABASE IF NOT EXISTS YuhanDB;

USE YuhanDB;

DROP TABLE IF EXISTS FavoriteClassrooms;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Classrooms;
DROP TABLE IF EXISTS StuTimetable;
DROP TABLE IF EXISTS Yuhan;

CREATE TABLE Yuhan (
  id VARCHAR(20) PRIMARY KEY NOT NULL,
  pass VARCHAR(20) NOT NULL,
  school VARCHAR(20),
  position VARCHAR(10),
  major VARCHAR(255),
  name VARCHAR(10),
  age INT,
  grade INT,
  class INT,
  studentId INT
);

CREATE TABLE Classrooms (
  classroom_name VARCHAR(10) PRIMARY KEY NOT NULL
);

CREATE TABLE FavoriteClassrooms (
  user_id VARCHAR(20) NOT NULL,
  classroom_num VARCHAR(10) NOT NULL,
  PRIMARY KEY (user_id, classroom_num),
  FOREIGN KEY (user_id) REFERENCES Yuhan(id),
  FOREIGN KEY (classroom_num) REFERENCES Classrooms(classroom_name)
);

CREATE TABLE Reservation (
  user_id VARCHAR(20) NOT NULL,
  classroom_name varchar(20),
  reservSeat INT NOT NULL,
  reservNum INT PRIMARY KEY ,
  FOREIGN KEY (user_id) REFERENCES Yuhan(id),
  FOREIGN KEY (classroom_name) REFERENCES Classrooms(classroom_name)
);

CREATE TABLE StuTimetable (
    user_id VARCHAR(50) NOT NULL,
    day VARCHAR(10),
    start_hour INT,
    end_hour INT, 
    classroomName VARCHAR(10),
    subject VARCHAR(30),
    FOREIGN KEY (user_id) REFERENCES Yuhan(id),
    FOREIGN KEY (classroomName) REFERENCES Classrooms(classroom_name)
);

INSERT INTO `Yuhan` VALUES ('kjunh972','1234','Yuhan','student','computer','김준형',24,3,2,202007024);
INSERT INTO `Yuhan` VALUES ('jin156','1106','Yuhan','professor','computer','송유진',22,3,2,202007054);

INSERT INTO Classrooms (classroom_name) VALUES 
('5311'),
('5406'),
('7202'),
('7203'),
('7206'),
('7207');

INSERT INTO FavoriteClassrooms (user_id, classroom_num) VALUES ('kjunh972', '5406');
INSERT INTO FavoriteClassrooms (user_id, classroom_num) VALUES ('kjunh972', '7206');

INSERT INTO Reservation (user_id, classroom_name, reservSeat, reservNum) VALUES
('kjunh972', '5406', 1, 1),
('kjunh972', '5406', 5, 2),
('kjunh972', '5406', 10, 3),
('kjunh972', '5406', 27, 4),
('kjunh972', '5406', 33, 5),
('kjunh972', '5406', 35, 6),
('kjunh972', '5406', 36, 7),
('kjunh972', '5406', 40, 8);
 
INSERT INTO StuTimetable (user_id, day, start_hour, end_hour, subject, classroomName) VALUES
('kjunh972', '월요일', 1, 4, 'Java Framework', '7207'),
('kjunh972', '화요일', 9, 12, '모바일 프로그래밍', '7207'),
('kjunh972', '화요일', 1, 4, 'C#', '7206'),
('kjunh972', '수요일', 9, 10, '진로 탐색', '5406'),
('kjunh972', '수요일', 10, 11, '취창업', '5406'),
('kjunh972', '목요일', 9, 12, '소프트웨어 공학', '5311'),
('kjunh972', '목요일', 1, 4, '데이터베이스 프로그래밍', '7207');
 