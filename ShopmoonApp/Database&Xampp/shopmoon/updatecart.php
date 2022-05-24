<?php
    require 'dbconnect.php';
        $cusID=$_POST['cusID'];
        $cartID=$_POST['cartID'];
        $proQuantity=$_POST['proQuantity'];
        $proMoney=$_POST['proMoney'];
        $delete = "update cart set proQuantity='$proQuantity',proMoney='$proMoney'  where cartID='$cartID' and cusID='$cusID'";
        if(mysqli_query($connect,$delete))
        {
            echo "Cập nhật giỏ hàng thành công";
        }
        else
        {
            echo "Cập nhật thất bại";
        }    
?>