
-- https://www.phpmyadmin.net/



--
-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 16, 2018 at 09:18 AM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlbansach`
--
CREATE DATABASE IF NOT EXISTS `shopmoon` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shopmoon`;

-- --------------------------------------------------------

--
-- Table structure for table `chude`
--

DROP TABLE IF EXISTS `producertype`;
CREATE TABLE IF NOT EXISTS `producertype` (
  `proProducer` int(11) NOT NULL AUTO_INCREMENT,
  `proProducerName` varchar(50) NOT NULL,
  `proProducerPhoto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`proProducer`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chude`
--

INSERT INTO `producertype` (`proProducer`, `proProducerName`, `proProducerPhoto`) VALUES
(1,'Intel','intel.png'),
(2,'Macbook','apple.png'),
(3,'Asus','asus.png'),
(4,'HP','hp.png'),
(5,'Lenovo','lenovo.jpg'),
(6,'Acer','acer.png'),
(7,'Dell','dell.png'),
(8,'MSI','msi.png'),
(9,'Surface','microsoft.png'),
(10,'LG','lg.png');

-- --------------------------------------------------------

--
-- Table structure for table `sach`
--
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `proID` int(11) NOT NULL AUTO_INCREMENT,
  `proName` varchar(1000) NOT NULL,
  `proPrice` int(11) DEFAULT NULL,
  `proPhoto` varchar(100) DEFAULT NULL,
  `proProducer` int(11) DEFAULT NULL,
  `proQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`proID`),
  KEY `proProducer` (`proProducer`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


--
-- Dumping data for table `sach`
--

INSERT INTO `Product` (`proID`, `proName`, `proPrice`, `proPhoto`, `proProducer`, `proQuantity`) VALUES
(1,'Laptop Acer Nitro 5 Gaming ',23990000,'acer-nitro-5-gaming-an515-45-r6ev-r5-5600h-8gb-600x600.jpg',6,1000),
(2,'Laptop Apple MacBook Air M1 2020 ',33990000,'macbook-air-m1-2020-gray-600x600.jpg',2,1000),
(3,'Laptop Lenovo Yoga 9 ',34990000,'lenovo-yoga-9-14itl5-i7-82bg006evn-21-600x600.jpg',5,1000),
(4,'Laptop Intel NUC M15 Kit',27990000,'intel-nuc-m15-i7-bbc710bcuxbc1-thumb-1-600x600.jpg',1,1000),
(5,'Laptop Asus TUF Gaming FX506LH i5',17990000,'asus-tuf-gaming-fx506lh-i5-hn188w-120122-121947-600x600.jpg',3,1000),
(6,'Laptop Asus ROG Strix Gaming',22990000,'asus-rog-strix-gaming-g513ih-r7-4800h-8gb-512gb-4gb-600x600.jpg',3,1000),
(7,'Laptop MacBook Pro 14 M1 Pro 2021',22990000,'macbook-pro-14-inch-m1-pro-2021-8-core-cpu-thumb-1-600x600.jpg',2,1000),
(8,'Laptop Dell Gaming Alienware m15 R6 i7',66490000,'dell-gaming-alienware-m15-r6-i7-11800h-32gb-1tb-ssd-8gb-600x600.jpg',7,1000)
;
COMMIT;


DROP TABLE IF EXISTS `productdetail`;
CREATE TABLE `productdetail` (
  `proID` int(11) NOT NULL AUTO_INCREMENT,
  `proName` varchar(1000) NOT NULL,
   `proPrice` int(11) DEFAULT NULL,
  `proDetail` varchar(500) DEFAULT NULL,
  `proDetailPhoto1` varchar(100) DEFAULT NULL,
   `proDetailPhoto2` varchar(100) DEFAULT NULL,
    `proDetailPhoto3` varchar(100) DEFAULT NULL,
	 `proDetailPhoto4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`proID`),
  KEY `proID` (`proID`)
)  ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `productdetail` (`proID`, `proName`, `proPrice`, `proDetail`, `proDetailPhoto1`, `proDetailPhoto2`, `proDetailPhoto3`, `proDetailPhoto4`) VALUES
(1,'Laptop Acer Nitro 5 Gaming AN515 45 R6EV R5 5600H/8GB/512GB/144Hz/4GB GTX1650/Win11 (NH.QBMSV.006)',23900000,'Phá cách với diện mạo mạnh mẽ đến từ laptop Acer Nitro 5 Gaming AN515 45 R6EV R5 5600H (NH.QBMSV.006) mang đến cho người dùng hiệu năng ổn định, hỗ trợ bạn trong mọi tác vụ hằng ngày hay chiến những trận game cực căng một cách mượt mà','vi-vn-acer-nitro-5-gaming-an515-45-r6ev-r5-nhqbmsv006-1.jpg','vi-vn-acer-nitro-5-gaming-an515-45-r6ev-r5-nhqbmsv006-2.jpg','vi-vn-acer-nitro-5-gaming-an515-45-r6ev-r5-nhqbmsv006-4.jpg','vi-vn-acer-nitro-5-gaming-an515-45-r6ev-r5-nhqbmsv006-5.jpg'),
(2,'Laptop Apple MacBook Air M1 2020 16GB/256GB/7-core GPU (Z124000DE) ',33900000,'Laptop Apple MacBook Air M1 2020 có thiết kế đẹp, sang trọng với CPU M1 độc quyền từ Apple cho hiệu năng đồ họa cao, màn hình Retina hiển thị siêu nét cùng với hệ thống bảo mật tối ưu','vi-vn-apple-macbook-air-m1-2020-z124000de-1.jpg','vi-vn-apple-macbook-air-m1-2020-z124000de-2.jpg','apple-macbook-air-m1-2020-z124000de-4-1-1-1020x570.jpg','vi-vn-apple-macbook-air-m1-2020-z124000de-6.jpg'),
(3,'Laptop Lenovo Yoga 9 14ITL5 i7/1185G7/16GB/1TB SSD/Touch/Pen/Win10 (82BG006EVN)',34990000,'Sang trọng, đẳng cấp, mạnh mẽ là những mỹ từ tóm gọn giá trị mà laptop Lenovo Yoga 9 14ITL5 (82BG006EVN) mang lại cho bạn. Nếu đang tìm 1 chiếc laptop mỏng nhẹ, linh hoạt, cấu hình ấn tượng hỗ trợ các tác vụ nặng, trải nghiệm ngay phiên bản cao cấp này từ Lenovo','lenovo-yoga-9-14itl5-i7-82bg006evn-180821-0418370.jpg','lenovo-yoga-9-14itl5-i7-82bg006evn-120821-0317081.jpg','vi-vn-lenovo-yoga-9-14itl5-i7-82bg006evn-5.jpg','vi-vn-lenovo-yoga-9-14itl5-i7-82bg006evn-7.jpg'),
(4,'Laptop Intel NUC M15 Kit i7 1165G7/16GB/512GB/Touch/Win10 (BBC710BCUXBC1)',27990000,'Sở hữu vẻ ngoài cao cấp, độc đáo và sang trọng, ẩn bên trong đó là sức mạnh hiệu năng vượt trội đến từ con chip Intel Gen 11 tân tiến, Intel NUC M15 Kit i7 (LAPBC710) hứa hẹn sẽ mang đến cho bạn nhiều trải nghiệm hoàn hảo trong công việc lẫn giải trí cơ bản hàng ngày','vi-vn-intel-nuc-m15-kit-i7-bbc710ecuxbc1-1.jpg','vi-vn-intel-nuc-m15-kit-i7-bbc710ecuxbc1-4.jpg','vi-vn-intel-nuc-m15-kit-i7-bbc710ecuxbc1-5.jpg','vi-vn-intel-nuc-m15-kit-i7-bbc710ecuxbc1-6.jpg'),
(5,'Laptop Asus TUF Gaming FX506LH i5 10300H/8GB/512GB/4GB GTX1650/144Hz/Win11 (HN188W)',17990000,' Sở hữu chip Intel Core i5 Comet Lake 10300H và card GeForce GTX 1650 4 GB từ nhà NVIDIA xử lý hoàn hảo mọi nhu cầu từ văn phòng đến đồ họa nặng hay thậm chí hỗ trợ bạn chiến các tựa game hấp dẫn: CS:GO, Fifa Online 4, GTA V,...','asus-tuf-gaming-fx506lh-i5-hn188w-1-1.jpg','asus-tuf-gaming-fx506lh-i5-hn188w-2-1.jpg','asus-tuf-gaming-fx506lh-i5-hn188w-fix-1-1020x570.jpg','vi-vn-asus-tuf-gaming-fx506lh-i5-hn188w-2.jpg'),
(6,'Laptop Asus ROG Strix Gaming G513IH R7 4800H/8GB/512GB/4GB GTX1650/144Hz/Win11 (HN015W)',22990000,'Laptop Asus ROG Strix Gaming G513IH (HN015W) là một trợ thủ đắc lực cho mọi game thủ chuyên nghiệp với phong cách thiết kế vô cùng độc đáo, ấn tượng chuẩn gaming cùng bộ cấu hình đầy mạnh mẽ.','vi-vn-asus-rog-strix-gaming-g513ih-r7-hn015w-1.jpg','vi-vn-asus-rog-strix-gaming-g513ih-r7-hn015w-2.jpg','vi-vn-asus-rog-strix-gaming-g513ih-r7-hn015w-4.jpg','vi-vn-asus-rog-strix-gaming-g513ih-r7-hn015w-5.jpg'),
(7,'Laptop MacBook Pro 14 M1 Pro 2021 8-core CPU/32GB/512GB/14-core GPU (Z15G004SS) ',60990000,'Nếu là fan của nhà Táo, bạn không thể bỏ qua laptop Apple Macbook Pro 14 M1 Pro (Z15G004SS) là một sản phẩm hoàn hảo thách thức mọi giới hạn hiệu năng cùng thiết kế thanh lịch, tinh tế..','vi-vn-macbook-pro-14-inch-m1-pro-2021-8-core-cpu-2.jpg','vi-vn-macbook-pro-14-inch-m1-pro-2021-8-core-cpu-04.jpg','vi-vn-macbook-pro-14-inch-m1-pro-2021-8-core-cpu-5.jpg','vi-vn-macbook-pro-14-inch-m1-pro-2021-8-core-cpu-6.jpg'),
(8,'Laptop Dell Gaming Alienware m15 R6 i7 11800H/32GB/1TB SSD/8GB RTX3070/240Hz/OfficeHS/Win11 (70272633) ',66490000,'Với phong cách thiết kế độc đáo cùng cấu hình vượt trội, laptop Dell Gaming Alienware m15 R6 i7 11800H (70272633) sẵn sàng đáp ứng hoàn hảo mọi tác vụ của một chiếc laptop đồ họa - kỹ thuật như thiết kế hay chiến game.','dell-gaming-alienware-m15-r6-i7-70272633-fix-01-1020x570.jpg','vi-vn-dell-gaming-alienware-m15-r6-i7-70272633-02.jpg','vi-vn-dell-gaming-alienware-m15-r6-i7-70272633-03.jpg','vi-vn-dell-gaming-alienware-m15-r6-i7-70272633-04.jpg')


;
COMMIT;








DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `cusID` int(11) NOT NULL AUTO_INCREMENT,
  `cusName` varchar(1000) NOT NULL,
  `cusPhone` varchar(11) DEFAULT NULL,
  `cusEmail` varchar(100) DEFAULT NULL,
  `cusPassword` varchar(100) DEFAULT NULL,
  `cusAddress` varchar(100) DEFAULT NULL,
  `cusPhoto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cusID`),
  KEY `cusID` (`cusID`)
)  ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `Cart`;
CREATE TABLE `Cart` (
  `cartID` int(11) NOT NULL AUTO_INCREMENT,
  `proID` int NOT NULL,
`proPhoto` varchar(100) DEFAULT NULL,
 `proName` varchar(1000) NOT NULL,
  `cusID` int DEFAULT NULL,
  `proPrice` float DEFAULT NULL,
  `proQuantity` int DEFAULT NULL,
  `proMoney` int DEFAULT NULL,
  PRIMARY KEY (`cartID`),
  KEY `cusID` (`cartID`)
)  ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `proID` int NOT NULL,
  `proPhoto` varchar(100) DEFAULT NULL,
  `proName` varchar(1000) NOT NULL,
  `cusID` int DEFAULT NULL,
  `cusName` varchar(1000) NOT NULL,
  `cusPhone` varchar(11) DEFAULT NULL,
  `cusEmail` varchar(100) DEFAULT NULL,
  `cusAddress` varchar(100) DEFAULT NULL,
  `proPrice` float DEFAULT NULL,
  `proQuantity` int DEFAULT NULL,
  `proMoney` int DEFAULT NULL,
`orderStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `orderID` (`orderID`)
)  ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;




-- --------------------------------------------------------


