<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "YuhanDB"; 

// 데이터베이스 연결 생성
$conn = new mysqli($servername, $username, $password, $dbname);

// 연결 확인
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// POST 요청에서 데이터 수신
$username = $_POST['username'];
$password = $_POST['password'];
$school = !empty($_POST['school']) ? "'".$_POST['school']."'" : "NULL";
$position = !empty($_POST['position']) ? "'".$_POST['position']."'" : "NULL";
$department = !empty($_POST['department']) ? "'".$_POST['department']."'" : "NULL";
$name = !empty($_POST['name']) ? "'".$_POST['name']."'" : "NULL";
$age = isset($_POST['age']) && $_POST['age'] !== "" ? intval($_POST['age']) : "NULL";
$grade = isset($_POST['grade']) && $_POST['grade'] !== "" ? intval($_POST['grade']) : "NULL";
$classNum = isset($_POST['classNum']) && $_POST['classNum'] !== "" ? intval($_POST['classNum']) : "NULL";
$studentId = isset($_POST['studentId']) && $_POST['studentId'] !== "" ? intval($_POST['studentId']) : "NULL";

// SQL 문 작성
$sql = "INSERT INTO Yuhan (id, pass, school, position, major, name, age, grade, class, studentId)
        VALUES ('$username', '$password', $school, $position, $department, $name, $age, $grade, $classNum, $studentId)";

// SQL 문 실행 및 결과 확인
if ($conn->query($sql) === TRUE) {
    echo "회원가입에 성공했습니다";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

// 연결 종료
$conn->close();
?>
