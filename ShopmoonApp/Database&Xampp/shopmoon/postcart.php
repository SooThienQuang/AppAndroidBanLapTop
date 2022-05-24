<?php
    require 'dbconnect.php';
        $cartID=$_POST['cartID'];
        $proID=$_POST['proID'];
        $proPhoto=$_POST['proPhoto'];
        $proName=$_POST['proName'];
        $cusID=$_POST['cusID'];
        $proPrice=$_POST['proPrice'];
        $proQuantity=$_POST['proQuantity'];
        $proMoney=$_POST['proMoney'];
       if(mysqli_num_rows(mysqli_query($connect,"select * from cart where proID='$proID' and cusID='$cusID'"))>0)
       {
           echo "Sản phẩm đã có trong giỏ hàng";
       }
       else
       {
        $insert = "insert into cart values ('$cartID', '$proID', '$proPhoto', '$proName', '$cusID', '$proPrice', '$proQuantity', '$proMoney')";
        if(mysqli_query($connect,$insert))
        {
            echo "Thêm giỏ thành công";
        }
        else
        {
            echo "Thêm vào giỏ hàng thất bại";
        }
    }
       
           
        
?>
