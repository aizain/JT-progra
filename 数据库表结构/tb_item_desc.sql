/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-16 21:53:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_item_desc`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc` (
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `item_desc` text COMMENT '商品描述',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`item_id`),
  KEY `item_id` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------
INSERT INTO `tb_item_desc` VALUES ('61', 'ssss', '2016-10-15 11:55:54', '2016-10-15 11:55:54');
INSERT INTO `tb_item_desc` VALUES ('67', 'ssssss12312312', '2016-10-15 12:02:10', '2016-10-15 12:02:10');
INSERT INTO `tb_item_desc` VALUES ('68', 'dasdasdaddada<img src=\"http://image.jt.com/images/116/9/6/1476539647926416.jpg\" width=\"1920\" height=\"1080\" alt=\"\" />', '2016-10-15 21:54:16', '2016-10-15 21:54:16');
INSERT INTO `tb_item_desc` VALUES ('69', 'aaa', '2016-10-16 20:17:37', '2016-10-16 20:17:37');
INSERT INTO `tb_item_desc` VALUES ('72', 'addsa', '2016-10-16 20:47:41', '2016-10-16 21:24:44');
INSERT INTO `tb_item_desc` VALUES ('74', '商品规格', '2016-10-16 21:29:32', '2016-10-16 21:29:50');
INSERT INTO `tb_item_desc` VALUES ('75', '主体', '2016-10-16 21:33:40', '2016-10-16 21:39:06');
