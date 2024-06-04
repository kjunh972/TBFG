<?php
$servername = "localhost";
$username = "root";  // MySQL 사용자 이름
$password = "";  // MySQL 비밀번호
$dbname = "YuhanDB";

// 데이터베이스 연결 생성
$conn = new mysqli($servername, $username, $password, $dbname);

// 연결 확인
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// 클라이언트로부터 받은 데이터를 변수에 저장
$user = $_POST['username'];
$pass = $_POST['password'];

// SQL 문 작성
$sql = "SELECT * FROM Yuhan WHERE id='$user' AND pass='$pass'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    echo "로그인 성공";
} else {
    echo "로그인 실패";
}

// 연결 종료
$conn->close();
?>
