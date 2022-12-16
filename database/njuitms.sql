/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.28 : Database - njuitms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`njuitms` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `njuitms`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `dept_id` int NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) NOT NULL,
  `dept_loc` varchar(40) DEFAULT NULL,
  `dept_man` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

/*Data for the table `department` */

insert  into `department`(`dept_id`,`dept_name`,`dept_loc`,`dept_man`) values (1,'教学部','教学楼411',2),(2,'行政部','行政楼211',1),(3,'后勤部','行政楼218',4),(4,'就业部','双创楼321',5),(5,'保卫部','行政楼117',6),(6,'科技部','科技楼212',7);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sex` int DEFAULT '1',
  `dept` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`userName`),
  KEY `user_dept_key` (`dept`),
  CONSTRAINT `user_dept_key` FOREIGN KEY (`dept`) REFERENCES `department` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

/*Data for the table `user` */

insert  into `user`(`id`,`userName`,`pwd`,`name`,`sex`,`dept`) values (1,'admin','1234','张三丰',1,1),(2,'lucy','123321','李四',1,2),(3,'liuneng','lnnbclass','刘能',1,3),(4,'xiedajiao','xdj888','谢大脚',0,1),(5,'wanger','wr666','王二',1,2),(6,'haobro','12332','二龙湖浩哥',1,3),(7,'zhaosi','zs666','赵四',1,4),(16,'13152354581','#t*8zWVh&`.','查洋',1,1),(17,'13032839902','T&j!WDg!@`Y:Y4@J0','单彬',1,1),(18,'13339497598','#oIV(ee@','薄勇',1,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
