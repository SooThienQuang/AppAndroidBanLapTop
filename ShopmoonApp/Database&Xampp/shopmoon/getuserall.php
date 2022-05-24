<?php
    require 'dbconnect.php';
    class USER{
        function __construct($a,$b,$c,$d,$e,$f,$g){
            $this -> cusID = $a;
            $this -> cusName = $b;
            $this -> cusPhone = $c;
            $this -> cusEmail = $d;
            $this -> cusPassword = $e;
            $this -> cusAddress = $f;
            $this -> cusPhoto = $g;
        }
    }
    $cusPhone = $_GET['cusPhone'];
    $query = "select * from customer where cusPhone='$cusPhone'";
    $data = mysqli_query($connect,$query);
    $arraylist = array();

    while($row = mysqli_fetch_assoc($data)){
        // echo $row['TenChuDe'];
        array_push($arraylist, new USER($row['cusID'],$row['cusName'],$row['cusPhone'],$row['cusEmail'],$row['cusPassword'],$row['cusAddress'],$row['cusPhoto']));
    }
  echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

   
?>