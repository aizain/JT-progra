/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-16 12:11:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '商品ID，也是商品编号',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
  `price` bigint(20) DEFAULT NULL COMMENT '价格 单位为：分',
  `num` int(10) DEFAULT NULL COMMENT '库存数量',
  `barcode` varchar(30) CHARACTER SET latin1 DEFAULT NULL COMMENT '条形码',
  `image` varchar(500) CHARACTER SET latin1 DEFAULT NULL COMMENT '图片 最多5张图片',
  `cid` bigint(10) DEFAULT NULL COMMENT '所属分类',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态 默认值为1，可选值： 1正常，2下架，3删除',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `id` (`id`),
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `tb_item_cat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '2016-10-05 19:33:14', '2016-10-05 19:54:46');
INSERT INTO `tb_item` VALUES ('2', '2', '2', '2', '2', '2', '2', '2', '1', '2016-10-05 19:33:19', '2016-10-05 19:54:50');
INSERT INTO `tb_item` VALUES ('3', '3', '3', '3', '3', '3', '3', '3', '1', '2016-10-05 19:34:00', '2016-10-05 19:54:53');
INSERT INTO `tb_item` VALUES ('4', '4', '4', '4', '4', '4', '4', '4', '1', '2016-10-05 19:34:01', '2016-10-05 19:54:56');
INSERT INTO `tb_item` VALUES ('5', '5', '5', '5', '5', '5', '5', '5', '1', '2016-10-05 19:34:06', '2016-10-05 19:55:03');
INSERT INTO `tb_item` VALUES ('7', null, null, null, null, null, null, null, '1', '2016-10-07 12:10:59', '2016-10-07 12:10:59');
INSERT INTO `tb_item` VALUES ('8', null, null, null, null, null, null, null, '1', '2016-10-07 12:15:16', '2016-10-07 12:15:16');
INSERT INTO `tb_item` VALUES ('9', null, null, null, null, null, null, null, '1', '2016-10-07 12:15:39', '2016-10-07 12:15:39');
INSERT INTO `tb_item` VALUES ('10', null, null, null, null, null, null, null, '1', '2016-10-07 12:19:09', '2016-10-07 12:19:09');
INSERT INTO `tb_item` VALUES ('11', null, null, null, null, null, null, null, '1', '2016-10-07 12:40:15', '2016-10-07 12:40:15');
INSERT INTO `tb_item` VALUES ('12', '', null, '1111100', null, '', '', null, '1', '2016-10-07 12:41:43', '2016-10-07 12:41:43');
INSERT INTO `tb_item` VALUES ('13', '', null, '100', null, '', '', null, '1', '2016-10-07 12:42:11', '2016-10-07 12:42:11');
INSERT INTO `tb_item` VALUES ('14', '1', null, '1', '1', '', '', null, '1', '2016-10-07 13:06:48', '2016-10-07 13:06:48');
INSERT INTO `tb_item` VALUES ('15', '121', null, '123', '13', '', '', null, '1', '2016-10-07 13:08:47', '2016-10-07 13:08:47');
INSERT INTO `tb_item` VALUES ('16', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:42', '2016-10-07 20:00:42');
INSERT INTO `tb_item` VALUES ('17', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:42', '2016-10-07 20:00:42');
INSERT INTO `tb_item` VALUES ('18', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:42', '2016-10-07 20:00:42');
INSERT INTO `tb_item` VALUES ('19', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:43', '2016-10-07 20:00:43');
INSERT INTO `tb_item` VALUES ('20', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:43', '2016-10-07 20:00:43');
INSERT INTO `tb_item` VALUES ('21', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:43', '2016-10-07 20:00:43');
INSERT INTO `tb_item` VALUES ('22', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:43', '2016-10-07 20:00:43');
INSERT INTO `tb_item` VALUES ('23', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:43', '2016-10-07 20:00:43');
INSERT INTO `tb_item` VALUES ('24', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('25', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('26', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('27', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('28', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('29', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:44', '2016-10-07 20:00:44');
INSERT INTO `tb_item` VALUES ('31', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:45', '2016-10-07 20:00:45');
INSERT INTO `tb_item` VALUES ('32', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:45', '2016-10-07 20:00:45');
INSERT INTO `tb_item` VALUES ('33', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:45', '2016-10-07 20:00:45');
INSERT INTO `tb_item` VALUES ('34', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:45', '2016-10-07 20:00:45');
INSERT INTO `tb_item` VALUES ('35', null, null, null, null, null, null, null, '1', '2016-10-07 20:00:45', '2016-10-07 20:00:45');
INSERT INTO `tb_item` VALUES ('47', 'sadsa', null, '1100', '111', '', '', null, '1', '2016-10-10 21:29:12', '2016-10-10 21:29:12');
INSERT INTO `tb_item` VALUES ('48', 'sadsa', null, '1100', '111', '', '', null, '1', '2016-10-10 21:29:16', '2016-10-10 21:29:16');
INSERT INTO `tb_item` VALUES ('49', '111', null, '121200', '232323', '', '', null, '1', '2016-10-10 21:43:08', '2016-10-10 21:43:08');
INSERT INTO `tb_item` VALUES ('50', '111', null, '121200', '232323', '', '', null, '1', '2016-10-10 21:44:00', '2016-10-10 21:44:00');
INSERT INTO `tb_item` VALUES ('51', '111', null, '121200', '232323', '', '', null, '1', '2016-10-10 21:45:21', '2016-10-10 21:45:21');
INSERT INTO `tb_item` VALUES ('52', '111', null, '121200', '232323', '', '', null, '1', '2016-10-10 21:53:14', '2016-10-10 21:53:14');
INSERT INTO `tb_item` VALUES ('53', '111', null, '121200', '232323', '', '', null, '1', '2016-10-10 21:54:33', '2016-10-10 21:54:33');
INSERT INTO `tb_item` VALUES ('58', '1112123', 'ddsa爱爱爱cads', '121222', '991231', '2132313', '', '4', '1', '2016-10-10 22:05:41', '2016-10-12 21:38:36');
INSERT INTO `tb_item` VALUES ('59', '2e2e', 'dasdada', '21301', '21313', '132312adda', '', '3', '1', '2016-10-12 21:38:55', '2016-10-12 21:38:55');
INSERT INTO `tb_item` VALUES ('67', 'dasddadsd', 'adsdadasd', '11100', '111', 'asdsadasdasd', '', '1', '1', '2016-10-15 12:02:10', '2016-10-15 12:02:10');
INSERT INTO `tb_item` VALUES ('68', 'dasda1212121', 'dadadasdasd', '212100', '21212', '', 'http://image.jt.com/images/116/9/6/1476539551611598.jpg', null, '1', '2016-10-15 21:54:16', '2016-10-15 21:54:16');
