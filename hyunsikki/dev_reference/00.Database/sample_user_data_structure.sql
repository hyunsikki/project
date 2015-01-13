/*
SQLyog Community v10.4 Beta1
MySQL - 5.6.21 : Database - userdatabase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`userdatabase` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `userdatabase`;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `no` mediumint(10) NOT NULL AUTO_INCREMENT COMMENT 'no',
  `userId` varchar(50) NOT NULL COMMENT 'userId',
  `userPasswd` varchar(255) NOT NULL COMMENT 'userPasswd',
  `userName` varchar(50) NOT NULL COMMENT 'userName',
  `email` varchar(100) DEFAULT NULL COMMENT 'email',
  `part` varchar(50) DEFAULT NULL COMMENT 'part',
  `level` int(10) DEFAULT NULL COMMENT 'level',
  `regDate` timestamp NULL DEFAULT NULL COMMENT 'regDate',
  `updDate` timestamp NULL DEFAULT NULL COMMENT 'updDate',
  `lastDate` timestamp NULL DEFAULT NULL COMMENT 'lastDate',
  `loginCount` mediumint(10) DEFAULT NULL COMMENT 'loginCount',
  `useYn` varchar(10) DEFAULT NULL COMMENT 'useYn',
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='usertable';

/*Data for the table `t_user` */

LOCK TABLES `t_user` WRITE;

insert  into `t_user`(`no`,`userId`,`userPasswd`,`userName`,`email`,`part`,`level`,`regDate`,`updDate`,`lastDate`,`loginCount`,`useYn`) values (1,'admin','ae6edd66088d68243f7f3c32d35c8716','관리자','user@domain.co.kr','경영지원',50,'2013-09-28 22:02:27','2013-09-28 22:02:27','2013-09-28 22:02:27',1,'Y'),(2,'super','ae6edd66088d68243f7f3c32d35c8716','슈퍼관리자','user@domain.co.kr','경영지원',100,'2013-10-08 10:39:25','2013-10-08 10:39:25','2013-10-08 10:39:25',1,'Y'),(3,'partAdmin','ae6edd66088d68243f7f3c32d35c8716','부서관리자','user@domain.co.kr','경영지원',30,'2013-11-08 15:01:07','2013-11-08 15:01:07','2013-11-08 15:01:07',1,'Y'),(5,'partSubAdmin','ae6edd66088d68243f7f3c32d35c8716','부서부관리자','user@domain.co.kr','경영지원',40,'2014-02-04 19:40:46','2014-02-04 19:40:46','2014-02-04 19:40:46',1,'Y'),(6,'','ae6edd66088d68243f7f3c32d35c8716','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
