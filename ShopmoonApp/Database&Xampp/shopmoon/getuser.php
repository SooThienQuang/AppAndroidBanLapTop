<?php
    require 'dbconnect.php';
    
    $cusPhone = $_POST['cusPhone'];
    $cusPassword = $_POST['cusPassword'];
    $query = "select * from customer where cusPhone='$cusPhone' and cusPassword='$cusPassword'";
        if(mysqli_num_rows(mysqli_query($connect,$query))>0)
        {
            echo "Đăng nhập thành công";
        }
        else
        {
            echo "Username hoặc Password chưa chính xác ! Vui lòng thử lại";
        }



   // $data = mysqli_query($connect,$query);
    // $arraylist = array();

    // while($row = mysqli_fetch_assoc($data)){
    //     // echo $row['TenChuDe'];
    //     array_push($arraylist, new USER($row['cusID'],$row['cusName'],$row['cusPhone'],$row['cusEmail'],$row['cusPassword'],$row['cusAddress'],$row['cusPhoto']));

    // }
    // echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>