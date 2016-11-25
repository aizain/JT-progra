/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-11-25 10:57:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_content`
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` bigint(20) NOT NULL COMMENT '内容分类id',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) DEFAULT NULL COMMENT '子标题 例如：价格显示',
  `title_desc` varchar(500) DEFAULT NULL COMMENT '标题描述',
  `url` varchar(300) DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) DEFAULT NULL COMMENT '图片2',
  `content` text COMMENT '内容 活动页',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('1', '9', '内容1', '1', '1', '1', '1', '1', '1', '2016-11-13 09:49:05', '2016-11-13 09:49:07');
INSERT INTO `tb_content` VALUES ('2', '9', '真女人', '2', '2', '2', '2', '2', '2', '2016-11-13 09:49:24', '2016-11-13 09:49:26');
INSERT INTO `tb_content` VALUES ('3', '9', '3', '3', '3', '3', '3', '3', '3', '2016-11-25 09:48:32', '2016-11-25 09:48:34');
INSERT INTO `tb_content` VALUES ('4', '9', '4', '4', '4', '4', '4', '4', '4', '2016-11-25 09:48:13', '2016-11-25 09:48:15');
INSERT INTO `tb_content` VALUES ('5', '9', '5', '5', '5', '5', '5', '5', '5', '2016-11-25 09:47:54', '2016-11-25 09:47:57');
INSERT INTO `tb_content` VALUES ('6', '9', '6', '6', '6', '6', '6', '6', '6', '2016-11-25 09:47:36', '2016-11-25 09:47:38');
INSERT INTO `tb_content` VALUES ('7', '11', '7', '7', '7', '7', '7', '7', '7', '2016-11-25 09:47:11', '2016-11-25 09:47:08');
INSERT INTO `tb_content` VALUES ('8', '11', '8', '8', '8', '8', '8', '8', '8', '2016-11-25 09:47:01', '2016-11-25 09:47:03');
INSERT INTO `tb_content` VALUES ('9', '11', '开学季音像299减99', '9', '9', '9', '9', '9', '9', '2016-11-25 09:46:17', '2016-11-25 09:46:20');
INSERT INTO `tb_content` VALUES ('10', '11', '38女人节得3800理财金', '10', '10', '10', '10', '10', '10', '2016-11-13 09:49:52', '2016-11-13 09:49:55');
