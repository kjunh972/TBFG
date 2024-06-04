<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "YuhanDB"; // 데이터베이스 이름으로 변경

// 데이터베이스 연결 생성
$conn = new mysqli($servername, $username, $password, $dbname);

// 연결 확인
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// POST 요청에서 데이터 수신
$user_id = $_POST['user_id'];

// SQL 문 작성
$sql = "SELECT * FROM Yuhan WHERE id = '$user_id'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // 데이터가 있는 경우
    $row = $result->fetch_assoc();
    echo json_encode($row);
} else {
    echo json_encode(array("error" => "No user found"));
}

// 연결 종료
$conn->close();
?>
