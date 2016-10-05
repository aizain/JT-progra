/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : jtdb

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-10-05 18:52:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_cat`;
CREATE TABLE `tb_item_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父分类ID 父ID=0时，代表一级类目',
  `name` varchar(150) DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '状态 默认值为1 可选值: 1正常 2删除',
  `sort_order` int(4) NOT NULL COMMENT '排序号',
  `is_parent` tinyint(1) DEFAULT NULL COMMENT '是否为父',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_cat
-- ----------------------------
INSERT INTO `tb_item_cat` VALUES ('1', '0', '图书、音像、电子书刊', '1', '1', '1', '2016-10-04 14:40:53', '2016-10-04 14:50:44');
INSERT INTO `tb_item_cat` VALUES ('2', '1', '电子书刊', '1', '1', '1', '2016-10-04 14:40:53', '2016-10-04 15:25:06');
INSERT INTO `tb_item_cat` VALUES ('3', '2', '', '1', '1', '0', '2016-10-04 14:40:53', '2016-10-04 14:50:57');
INSERT INTO `tb_item_cat` VALUES ('4', '2', '网络原创', '1', '2', '0', '2016-10-04 14:40:53', '2016-10-04 14:51:07');
INSERT INTO `tb_item_cat` VALUES ('5', '2', '数字杂志', '1', '3', '0', '2016-10-04 14:40:53', '2016-10-04 14:51:14');
INSERT INTO `tb_item_cat` VALUES ('6', '2', '多媒体图书', '1', '4', '0', '2016-10-04 14:40:53', '2016-10-04 14:51:29');
INSERT INTO `tb_item_cat` VALUES ('7', '1', '音像', '1', '2', '1', '2016-10-04 14:40:53', '2016-10-04 14:55:59');
INSERT INTO `tb_item_cat` VALUES ('8', '1', '美文原版', '1', '3', '1', '2016-10-04 14:40:53', '2016-10-04 15:25:08');
INSERT INTO `tb_item_cat` VALUES ('9', '1', '文艺', '1', '4', '1', '2016-10-04 14:40:53', '2016-10-04 15:25:09');
INSERT INTO `tb_item_cat` VALUES ('10', '1', '少儿', '1', '5', '1', '2016-10-04 14:40:53', '2016-10-04 15:25:09');
INSERT INTO `tb_item_cat` VALUES ('11', '1', '人文社科', '1', '6', '1', '2016-10-04 14:40:53', '2016-10-04 15:25:09');
INSERT INTO `tb_item_cat` VALUES ('35', '30', '法律', '1', '5', '0', '2016-10-04 14:39:05', '2016-10-04 14:40:11');
INSERT INTO `tb_item_cat` VALUES ('36', '30', '宗教', '1', '6', '0', '2016-10-04 14:40:00', '2016-10-04 14:40:38');
INSERT INTO `tb_item_cat` VALUES ('37', '30', '心理学', '1', '7', '0', '2016-10-04 14:40:03', '2016-10-04 14:40:43');
INSERT INTO `tb_item_cat` VALUES ('38', '30', '文化', '1', '8', '0', '2016-10-04 14:40:53', '2016-10-04 14:42:02');
INSERT INTO `tb_item_cat` VALUES ('39', '30', '社会科学', '1', '9', '0', '2016-10-04 14:40:53', '2016-10-04 14:42:03');
INSERT INTO `tb_item_cat` VALUES ('40', '1', '经管励志', '1', '7', '1', '2016-10-04 14:40:53', '2016-10-04 14:42:05');
INSERT INTO `tb_item_cat` VALUES ('41', '40', '经济', '1', '1', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:27');
INSERT INTO `tb_item_cat` VALUES ('42', '40', '金融与投资', '1', '2', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:28');
INSERT INTO `tb_item_cat` VALUES ('43', '40', '管理', '1', '3', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:29');
INSERT INTO `tb_item_cat` VALUES ('44', '40', '励志与成功', '1', '4', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:30');
INSERT INTO `tb_item_cat` VALUES ('45', '1', '生活', '1', '8', '1', '2016-10-04 14:40:53', '2016-10-04 14:45:40');
INSERT INTO `tb_item_cat` VALUES ('46', '45', '生活', '1', '1', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:41');
INSERT INTO `tb_item_cat` VALUES ('47', '45', '健康与保健', '1', '2', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:17');
INSERT INTO `tb_item_cat` VALUES ('48', '45', '家庭与育儿', '1', '3', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:16');
INSERT INTO `tb_item_cat` VALUES ('49', '45', '旅游', '1', '4', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:15');
INSERT INTO `tb_item_cat` VALUES ('50', '45', '动漫/幽默', '1', '5', '0', '2016-10-04 14:40:53', '2016-10-04 14:45:15');
INSERT INTO `tb_item_cat` VALUES ('62', '0', '家用电气', '1', '2', '1', '2016-10-04 14:40:53', '2016-10-05 15:12:20');
INSERT INTO `tb_item_cat` VALUES ('63', '0', '电脑、办公', '1', '3', '1', '2016-10-04 14:40:53', '2016-10-05 15:12:31');
INSERT INTO `tb_item_cat` VALUES ('64', '0', '个护化妆', '1', '4', '1', '2016-10-04 14:40:53', '2016-10-05 15:12:47');
INSERT INTO `tb_item_cat` VALUES ('65', '0', '钟表', '1', '5', '1', '2016-10-04 14:40:53', '2016-10-05 15:12:54');
INSERT INTO `tb_item_cat` VALUES ('66', '0', '母婴', '1', '6', '1', '2016-10-04 14:40:53', '2016-10-05 15:12:58');
INSERT INTO `tb_item_cat` VALUES ('67', '0', '食品饮料、保健视频', '1', '7', '1', '2016-10-04 14:40:53', '2016-10-05 15:13:15');
INSERT INTO `tb_item_cat` VALUES ('68', '0', '汽车用品', '1', '8', '1', '2016-10-04 14:40:53', '2016-10-05 15:13:28');
INSERT INTO `tb_item_cat` VALUES ('69', '0', '玩具乐器', '1', '9', '1', '2016-10-04 14:40:53', '2016-10-05 15:13:35');
INSERT INTO `tb_item_cat` VALUES ('70', '0', '手机', '1', '10', '1', '2016-10-04 14:40:53', '2016-10-05 15:13:41');
INSERT INTO `tb_item_cat` VALUES ('71', '0', '数码', '1', '11', '1', '2016-10-04 14:40:53', '2016-10-05 15:13:46');
SET FOREIGN_KEY_CHECKS=1;
