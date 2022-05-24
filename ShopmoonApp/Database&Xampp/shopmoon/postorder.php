<?php	
    require 'dbconnect.php';
        $orderID=$_POST['orderID'];
        $proID=$_POST['proID'];
        $proPhoto=$_POST['proPhoto'];
        $proName=$_POST['proName'];
        $cusID=$_POST['cusID'];
        $cusName=$_POST['cusName'];
        $cusPhone=$_POST['cusPhone'];
        $cusEmail=$_POST['cusEmail'];
        $cusAddress=$_POST['cusAddress'];
        $proPrice=$_POST['proPrice'];
        $proQuantity=$_POST['proQuantity'];
        $proMoney=$_POST['proMoney'];
        $orderStatus=$_POST['orderStatus'];
        if(mysqli_num_rows(mysqli_query($connect,"select * from orders where proID='$proID' and cusID='$cusID'"))>0)
        {
            echo "Sản phẩm đã có trong đơn hàng";
        }
        else
        {
        
        $insert = "insert into Orders values ('$orderID', '$proID', '$proPhoto', '$proName', '$cusID', '$cusName', '$cusPhone', '$cusEmail', '$cusAddress', '$proPrice', '$proQuantity', '$proMoney', '$orderStatus')";
        if(mysqli_query($connect,$insert))
        {
            echo "Đặt hàng thành công";
        }
        else
        {
            echo "Đặt hàng thất bại";
        }  
    }
?>
