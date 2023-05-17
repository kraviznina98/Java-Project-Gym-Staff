/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 10.4.21-MariaDB : Database - obavezeteretana
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`obavezeteretana` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `obavezeteretana`;

/*Table structure for table `obaveza` */

DROP TABLE IF EXISTS `obaveza`;

CREATE TABLE `obaveza` (
  `ObavezaID` bigint(50) unsigned NOT NULL AUTO_INCREMENT,
  `NazivObaveze` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PredvidjenoVremeIzvrsavanja` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TipObaveze` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ObavezaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `obaveza` */

insert  into `obaveza`(`ObavezaID`,`NazivObaveze`,`PredvidjenoVremeIzvrsavanja`,`TipObaveze`) values 
(1,'Obaveza','15 meseci','Dugog trajanja');

/*Table structure for table `opstina` */

DROP TABLE IF EXISTS `opstina`;

CREATE TABLE `opstina` (
  `OpstinaID` bigint(50) unsigned NOT NULL AUTO_INCREMENT,
  `PttBroj` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Naziv` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`OpstinaID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `opstina` */

insert  into `opstina`(`OpstinaID`,`PttBroj`,`Naziv`) values 
(1,'11000','Beograd'),
(2,'22330','Nova Pazova'),
(3,'11080','Zemun'),
(4,'11070','Novi Beograd'),
(5,'55222','Novi Sad'),
(6,'66333','Sarajevo'),
(7,'44333','Banja Luka'),
(8,'88111','Zagreb');

/*Table structure for table `promenaobaveze` */

DROP TABLE IF EXISTS `promenaobaveze`;

CREATE TABLE `promenaobaveze` (
  `PromenaID` bigint(50) unsigned NOT NULL AUTO_INCREMENT,
  `DatumPromene` date NOT NULL,
  `ZaposleniID` bigint(50) unsigned NOT NULL,
  `ObavezaID` bigint(50) unsigned NOT NULL,
  PRIMARY KEY (`PromenaID`),
  KEY `FK_ZAP` (`ZaposleniID`),
  KEY `FK_OBA` (`ObavezaID`),
  CONSTRAINT `FK_OBA` FOREIGN KEY (`ObavezaID`) REFERENCES `obaveza` (`ObavezaID`),
  CONSTRAINT `FK_ZAP` FOREIGN KEY (`ZaposleniID`) REFERENCES `zaposleni` (`ZaposleniID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `promenaobaveze` */

/*Table structure for table `stavkazahteva` */

DROP TABLE IF EXISTS `stavkazahteva`;

CREATE TABLE `stavkazahteva` (
  `Rb` int(50) unsigned NOT NULL,
  `ZahtevID` bigint(50) unsigned NOT NULL,
  `OpisStavke` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ObavezaID` bigint(50) unsigned NOT NULL,
  PRIMARY KEY (`Rb`,`ZahtevID`),
  KEY `FK_Zah` (`ZahtevID`),
  KEY `FK_OBA2` (`ObavezaID`),
  CONSTRAINT `FK_OBA2` FOREIGN KEY (`ObavezaID`) REFERENCES `obaveza` (`ObavezaID`),
  CONSTRAINT `FK_Zah` FOREIGN KEY (`ZahtevID`) REFERENCES `zahtev` (`ZahtevID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `stavkazahteva` */

/*Table structure for table `zahtev` */

DROP TABLE IF EXISTS `zahtev`;

CREATE TABLE `zahtev` (
  `ZahtevID` bigint(50) unsigned NOT NULL AUTO_INCREMENT,
  `Datum` date NOT NULL,
  `RokZaIzvrsenje` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ZaposleniKreatorID` bigint(50) unsigned NOT NULL,
  `ZaposleniIzvrsiteljID` bigint(50) unsigned NOT NULL,
  PRIMARY KEY (`ZahtevID`),
  KEY `FK_ZAP2` (`ZaposleniKreatorID`),
  KEY `FK_ZAP3` (`ZaposleniIzvrsiteljID`),
  CONSTRAINT `FK_ZAP2` FOREIGN KEY (`ZaposleniKreatorID`) REFERENCES `zaposleni` (`ZaposleniID`),
  CONSTRAINT `FK_ZAP3` FOREIGN KEY (`ZaposleniIzvrsiteljID`) REFERENCES `zaposleni` (`ZaposleniID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `zahtev` */

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `ZaposleniID` bigint(50) unsigned NOT NULL AUTO_INCREMENT,
  `ImeZaposlenog` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PrezimeZaposlenog` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `KorisnickoIme` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Lozinka` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `OpstinaID` bigint(50) unsigned NOT NULL,
  PRIMARY KEY (`ZaposleniID`),
  KEY `FK_OPSTINA` (`OpstinaID`),
  CONSTRAINT `FK_OPSTINA` FOREIGN KEY (`OpstinaID`) REFERENCES `opstina` (`OpstinaID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`ZaposleniID`,`ImeZaposlenog`,`PrezimeZaposlenog`,`KorisnickoIme`,`Lozinka`,`OpstinaID`) values 
(1,'Marko','Markovic','marko','marko123',1),
(2,'Nikola','Nikolic','nikola','nikola123',1),
(3,'Marija','Markovic','marija','marij123',1),
(4,'Jovan','Jovanovic','jovan','jovan123',1),
(5,'Edis','Skenderi','edis','edis12345',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
