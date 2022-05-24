<?php
    require 'dbconnect.php';
    class ORDERS{
        function __construct($a,$b,$c,$d,$e,$f,$g,$h,$aa,$ab,$ac,$ad,$ae){
            $this -> orderID = $a;
            $this -> proID = $b;
            $this -> proPhoto = $c;
            $this -> proName = $d;
            $this -> cusID = $e;
            $this -> cusName = $f;
            $this -> cusPhone = $g;
            $this -> cusEmail = $h;
            $this -> cusAddress = $aa;
            $this -> proPrice = $ab;
            $this -> proQuantity = $ac;
            $this -> proMoney = $ad;
            $this -> orderStatus = $ae;
          
        }
    }
    $cusID = $_GET['cusID'];
    $query = "select * from orders where cusID='$cusID'";
    $data = mysqli_query($connect,$query);
    $arraylist = array();

    while($row = mysqli_fetch_assoc($data)){
        // echo $row['TenChuDe'];
        array_push($arraylist, new ORDERS($row['orderID'],$row['proID'],$row['proPhoto'],$row['proName'],$row['cusID'],$row['cusName'],$row['cusPhone'],$row['cusEmail'],$row['cusAddress'],$row['proPrice'],$row['proQuantity'],$row['proMoney'],$row['orderStatus']));

    }
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>