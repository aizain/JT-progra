/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-16 21:53:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_item_param`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param`;
CREATE TABLE `tb_item_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_cat_id` bigint(20) NOT NULL COMMENT '商品分类ID',
  `param_data` text COMMENT '参数数据 格式为json',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `item_cat_id` (`item_cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_param
-- ----------------------------
INSERT INTO `tb_item_param` VALUES ('10', '163', '[{\"group\":\"主题\",\"params\":[\"CPU\",\"内存\"]},{\"group\":\"版本\",\"params\":[\"敞篷\"]}]', '2016-10-16 13:10:12', '2016-10-16 13:10:15');
INSERT INTO `tb_item_param` VALUES ('12', '6', '[{\"group\":\"主体\",\"params\":[\"型号\",\"颜色\",\"上市时间\"]},{\"group\":\"配置\",\"params\":[\"存储容量\",\"处理器\"]}]', '2016-10-16 18:46:53', '2016-10-16 18:46:53');
