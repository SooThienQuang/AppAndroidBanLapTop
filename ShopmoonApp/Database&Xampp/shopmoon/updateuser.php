<?php
    require 'dbconnect.php';
        $cusPhone=$_POST['cusPhone'];
        $cusPassword=$_POST['cusPassword'];
  
        $insert = "update customer set cusPassword='$cusPassword' where cusPhone='$cusPhone'";
        if(mysqli_query($connect,$insert))
        {
            echo "Đổi mật khẩu thành công";
        }
        else
        {
            echo "Đổi mật khẩu thất bại";
        }
?>
