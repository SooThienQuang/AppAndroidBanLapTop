<?php
    require 'dbconnect.php';
        $cusID=$_POST['cusID'];
        $cartID=$_POST['cartID'];
        $delete = "delete from cart where cartID='$cartID' and cusID='$cusID'";
        if(mysqli_query($connect,$delete))
        {
            echo "Xóa giỏ thành công";
        }
        else
        {
            echo "Thêm vào giỏ hàng thất bại";
        }    
?>
