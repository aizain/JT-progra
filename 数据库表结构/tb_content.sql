/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-11-21 20:42:26
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
  `update` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('1', '9', '内容1', null, null, null, null, null, null, '2016-11-13 09:49:05', '2016-11-13 09:49:07');
INSERT INTO `tb_content` VALUES ('2', '9', '真女人', null, null, null, null, null, null, '2016-11-13 09:49:24', '2016-11-13 09:49:26');
INSERT INTO `tb_content` VALUES ('10', '11', '38女人节得3800理财金', null, null, null, null, null, null, '2016-11-13 09:49:52', '2016-11-13 09:49:55');
