<?php
    require 'dbconnect.php';
    class PRODUCERTYPE{
        function __construct($p,$a,$x){
            $this -> proProducer = $p;
            $this -> proProducerName = $a;
            $this -> proProducerPhoto = $x;
        }
    }
    $query = "select * from producertype";
    $data = mysqli_query($connect,$query);
    $arraylist = array();

    while($row = mysqli_fetch_assoc($data)){
        // echo $row['TenChuDe'];
        array_push($arraylist, new PRODUCERTYPE($row['proProducer'],$row['proProducerName'],$row['proProducerPhoto']));

    }
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>