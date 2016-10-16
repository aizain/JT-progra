/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-16 21:53:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_item_param_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param_item`;
CREATE TABLE `tb_item_param_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `param_data` text COMMENT '参数数据 格式为json',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `item_id` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_param_item
-- ----------------------------
INSERT INTO `tb_item_param_item` VALUES ('3', '75', '[{\"group\":\"主体\",\"params\":[{\"k\":\"型号\",\"v\":\"lenovo\"},{\"k\":\"颜色\",\"v\":\"主体\"},{\"k\":\"上市时间\",\"v\":\"主体\"}]},{\"group\":\"配置\",\"params\":[{\"k\":\"存储容量\",\"v\":\"主体\"},{\"k\":\"处理器\",\"v\":\"主体\"}]}]', '2016-10-16 21:33:40', '2016-10-16 21:39:06');
