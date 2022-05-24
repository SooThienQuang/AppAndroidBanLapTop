<?php
    require 'dbconnect.php';
    class PRODUCTDETAIL{
        function __construct($a,$b,$c,$d,$e,$f,$g,$h){
            $this -> proID = $a;
            $this -> proName = $b;
            $this -> proPrice = $c;
            $this -> proDetail = $d;
            $this -> proDetailPhoto1 = $e;
            $this -> proDetailPhoto2 = $f;
            $this -> proDetailPhoto3 = $g;
            $this -> proDetailPhoto4 = $h;
        }
    }
    if (isset($_GET['id'])){
        $ma = $_GET['id'];
        $query = "select * from productdetail where proID='$ma'";
        $data = mysqli_query($connect,$query);
        $arraylist = array();

        while($row = mysqli_fetch_assoc($data)){
            // echo $row['TenChuDe'];
            array_push($arraylist, new PRODUCTDETAIL($row['proID'],$row['proName'],$row['proPrice'],$row['proDetail'],$row['proDetailPhoto1'],$row['proDetailPhoto2'],$row['proDetailPhoto3'],$row['proDetailPhoto4']));

        }
        echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
