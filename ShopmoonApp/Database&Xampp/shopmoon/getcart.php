<?php
    require 'dbconnect.php';
    class CART{
        function __construct($a,$b,$c,$d,$e,$f,$g,$h){
            $this -> cartID = $a;
            $this -> proID = $b;
            $this -> proPhoto = $c;
            $this -> proName = $d;
            $this -> cusID = $e;
            $this -> proPrice = $f;
            $this -> proQuantity = $g;
            $this -> proMoney = $h;
          
        }
    }
    $cusID = $_GET['cusID'];
    $query = "select * from cart where cusID='$cusID'";
    $data = mysqli_query($connect,$query);
    $arraylist = array();

    while($row = mysqli_fetch_assoc($data)){
        // echo $row['TenChuDe'];
        array_push($arraylist, new CART($row['cartID'],$row['proID'],$row['proPhoto'],$row['proName'],$row['cusID'],$row['proPrice'],$row['proQuantity'],$row['proMoney']));

    }
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>