<?php
    require 'dbconnect.php';
    class PRODUCT{
        function __construct($a,$b,$c,$d,$e,$f){
            $this -> proID = $a;
            $this -> proName = $b;
            $this -> proPrice = $c;
            $this -> proPhoto = $d;
            $this -> proProducer = $e;
            $this -> proQuantity = $f;
        }
    }
    $query = "select * from product";
    $data = mysqli_query($connect,$query);
    $arraylist = array();

    while($row = mysqli_fetch_assoc($data)){
        // echo $row['TenChuDe'];
        array_push($arraylist, new PRODUCT($row['proID'],$row['proName'],$row['proPrice'],$row['proPhoto'],$row['proProducer'],$row['proQuantity']));

    }
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>