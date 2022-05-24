<?php
    require 'dbconnect.php';
        $cusID=$_POST['cusID'];
        $cusName=$_POST['cusName'];
        $cusPhone=$_POST['cusPhone'];
        $cusEmail=$_POST['cusEmail'];
        $cusPassword=$_POST['cusPassword'];
        $cusAddress=$_POST['cusAddress'];
        $cusPhoto=$_POST['cusPhoto'];
       if(mysqli_num_rows(mysqli_query($connect,"select * from customer where cusPhone='$cusPhone'"))>0)
       {
           echo "Số điện thoại đã tồn tại";
       }
       else
       {
        $insert = "insert into customer values ('$cusID', '$cusName', '$cusPhone', '$cusEmail', '$cusPassword', '$cusAddress', '$cusPhoto')";
        if(mysqli_query($connect,$insert))
        {
            echo "Đăng kí thành công";
        }
        else
        {
            echo "Đăng kí thất bại";
        }
    }
       
           
        
?>
