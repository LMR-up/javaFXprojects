/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : freshsell

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 31/08/2022 15:38:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `weight` double NULL DEFAULT NULL COMMENT '商品重量',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `addtime` datetime NULL DEFAULT NULL COMMENT '商品上架时间',
  `goodnum` int NULL DEFAULT NULL COMMENT '商品数量',
  `imagepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '鲨鱼', 20.5, 100.00, '2021-11-30 00:00:00', 944, 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg');
INSERT INTO `goods` VALUES (2, '刀鱼', 20.5, 100.00, '2019-11-01 00:00:00', 111, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg');
INSERT INTO `goods` VALUES (3, '鲢鱼', 10.5, 10.00, '2021-12-07 00:00:00', 1186, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg');
INSERT INTO `goods` VALUES (5, '草鱼', 20, 100.00, '2019-11-01 00:00:00', 90, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg');
INSERT INTO `goods` VALUES (6, '波龙', 20.5, 100.00, '2020-11-02 00:00:00', 1205, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg');
INSERT INTO `goods` VALUES (7, '三眼蟹', 20.5, 100.00, '2021-12-08 00:00:00', 118, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg');
INSERT INTO `goods` VALUES (8, '田鸡', 20.5, 100.00, '2021-12-08 00:00:00', 8, 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg');
INSERT INTO `goods` VALUES (9, '鲫鱼', 20.5, 100.00, '2022-08-21 21:26:04', 17, 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg');
INSERT INTO `goods` VALUES (10, '青鱼', 20.5, 100.00, '2022-08-21 21:21:04', 17, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg');
INSERT INTO `goods` VALUES (11, '黑鱼', 20.5, 100.00, '2022-08-21 21:21:04', 16, 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg');
INSERT INTO `goods` VALUES (12, '鳙鱼', 20.5, 100.00, '2021-12-08 00:00:00', 18, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (13, '白鲢', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg');
INSERT INTO `goods` VALUES (14, '河豚', 20.5, 100.00, '2022-08-21 21:21:04', 19, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpg');
INSERT INTO `goods` VALUES (15, '娃娃鱼', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (16, '三文鱼', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg');
INSERT INTO `goods` VALUES (17, '金枪', 20.5, 100.00, '2022-08-21 21:21:04', 19, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg');
INSERT INTO `goods` VALUES (18, '鲳鱼', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (20, '戴拿', 20.5, 100.00, '2022-08-21 21:21:04', 17, 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg');
INSERT INTO `goods` VALUES (21, '盖亚', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg');
INSERT INTO `goods` VALUES (22, '大马哈鱼', 20.5, 100.00, '2022-08-21 21:21:04', 18, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (23, '小丑鱼12', 20.5, 100.00, '2022-08-21 00:00:00', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (27, '明虾', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg');
INSERT INTO `goods` VALUES (28, '草虾', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg');
INSERT INTO `goods` VALUES (29, '小龙虾', 20.5, 100.00, '2022-08-21 21:21:04', 19, 'E:\\睿智\\第一阶段\\项目资源\\image\\xl.jpeg');
INSERT INTO `goods` VALUES (30, '九节虾', 20.5, 100.00, '2022-08-21 21:21:04', 19, 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai1.jpeg');
INSERT INTO `goods` VALUES (32, '黄金鲍', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (33, '一头鲍', 20.5, 100.00, '2022-08-21 21:21:04', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (34, 'test', 12, 21.00, '2021-12-08 00:00:00', 11, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');
INSERT INTO `goods` VALUES (35, '野生奥特曼', 1333, 23213.00, '2021-12-08 00:00:00', 10, 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg');
INSERT INTO `goods` VALUES (36, '小青龙', 2, 22.00, '2021-12-08 00:00:00', 20, 'E:\\睿智\\第一阶段\\项目资源\\image\\xq.jpeg');
INSERT INTO `goods` VALUES (42, '野生美女', 80, 10.00, '2022-08-26 00:00:00', 1, 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg');
INSERT INTO `goods` VALUES (44, '21', 12, 12.00, '2022-08-10 00:00:00', 12, 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg');

-- ----------------------------
-- Table structure for logininfo
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `logintime` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 512 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES (335, 'admin', '123456', '2022-08-24 20:01:42');
INSERT INTO `logininfo` VALUES (336, 'admin', '123456', '2022-08-24 20:04:01');
INSERT INTO `logininfo` VALUES (337, 'admin', '123456', '2022-08-24 20:06:35');
INSERT INTO `logininfo` VALUES (338, 'admin', '123456', '2022-08-24 20:13:55');
INSERT INTO `logininfo` VALUES (339, 'admin', '123456', '2022-08-24 20:16:00');
INSERT INTO `logininfo` VALUES (340, '1', '1', '2022-08-24 20:23:39');
INSERT INTO `logininfo` VALUES (341, '周杰伦', '123456', '2022-08-24 20:31:10');
INSERT INTO `logininfo` VALUES (342, 'admin', '123456', '2022-08-24 20:32:23');
INSERT INTO `logininfo` VALUES (343, 'admin', '123456', '2022-08-24 20:36:14');
INSERT INTO `logininfo` VALUES (344, 'admin', '123456', '2022-08-24 20:40:28');
INSERT INTO `logininfo` VALUES (345, 'admin', '123456', '2022-08-24 20:43:25');
INSERT INTO `logininfo` VALUES (346, 'admin', '123456', '2022-08-24 20:45:33');
INSERT INTO `logininfo` VALUES (347, 'admin', '123456', '2022-08-24 21:39:13');
INSERT INTO `logininfo` VALUES (348, '周杰伦', '123456', '2022-08-24 21:48:15');
INSERT INTO `logininfo` VALUES (349, 'admin', '123456', '2022-08-24 21:53:32');
INSERT INTO `logininfo` VALUES (350, 'admin', '123456', '2022-08-24 21:59:15');
INSERT INTO `logininfo` VALUES (351, 'admin', '123456', '2022-08-24 22:00:20');
INSERT INTO `logininfo` VALUES (352, '周杰伦', '123456', '2022-08-24 22:05:11');
INSERT INTO `logininfo` VALUES (353, '1', '1', '2022-08-24 22:10:02');
INSERT INTO `logininfo` VALUES (354, '1', '1', '2022-08-24 22:12:59');
INSERT INTO `logininfo` VALUES (355, '1', '1', '2022-08-24 22:19:15');
INSERT INTO `logininfo` VALUES (356, '2', '1', '2022-08-24 22:19:24');
INSERT INTO `logininfo` VALUES (358, 'admin', '123456', '2022-08-24 22:29:04');
INSERT INTO `logininfo` VALUES (359, 'admin', '123456', '2022-08-24 23:18:50');
INSERT INTO `logininfo` VALUES (360, 'admin', '123456', '2022-08-24 23:25:52');
INSERT INTO `logininfo` VALUES (361, 'admin', '123456', '2022-08-24 23:28:50');
INSERT INTO `logininfo` VALUES (362, 'admin', '123456', '2022-08-24 23:36:02');
INSERT INTO `logininfo` VALUES (363, 'admin', '123456', '2022-08-24 23:38:49');
INSERT INTO `logininfo` VALUES (364, 'admin', '123456', '2022-08-24 23:48:46');
INSERT INTO `logininfo` VALUES (365, 'admin', '123456', '2022-08-24 23:50:17');
INSERT INTO `logininfo` VALUES (366, 'admin', '123456', '2022-08-24 23:51:18');
INSERT INTO `logininfo` VALUES (367, 'admin', '123456', '2022-08-24 23:53:13');
INSERT INTO `logininfo` VALUES (368, 'admin', '123456', '2022-08-24 23:56:41');
INSERT INTO `logininfo` VALUES (369, 'admin', '123456', '2022-08-24 23:58:26');
INSERT INTO `logininfo` VALUES (370, 'admin', '123456', '2022-08-25 00:07:22');
INSERT INTO `logininfo` VALUES (371, '周杰伦', '123456', '2022-08-25 09:10:56');
INSERT INTO `logininfo` VALUES (372, '周杰伦', '123456', '2022-08-25 09:12:36');
INSERT INTO `logininfo` VALUES (373, '周杰伦', '123456', '2022-08-25 09:13:55');
INSERT INTO `logininfo` VALUES (374, '周杰伦', '123456', '2022-08-25 09:14:42');
INSERT INTO `logininfo` VALUES (375, '周杰伦', '123456', '2022-08-25 09:15:29');
INSERT INTO `logininfo` VALUES (376, '周杰伦', '123456', '2022-08-25 09:16:25');
INSERT INTO `logininfo` VALUES (377, '周杰伦', '123456', '2022-08-25 09:17:20');
INSERT INTO `logininfo` VALUES (378, '周杰伦', '123456', '2022-08-25 09:18:03');
INSERT INTO `logininfo` VALUES (379, '周杰伦', '123456', '2022-08-25 09:18:52');
INSERT INTO `logininfo` VALUES (380, '周杰伦', '123456', '2022-08-25 09:19:36');
INSERT INTO `logininfo` VALUES (381, 'admin', '123456', '2022-08-25 09:20:13');
INSERT INTO `logininfo` VALUES (382, 'admin', '123456', '2022-08-25 09:29:02');
INSERT INTO `logininfo` VALUES (383, 'admin', '123456', '2022-08-25 09:38:16');
INSERT INTO `logininfo` VALUES (384, 'admin', '123456', '2022-08-25 09:43:15');
INSERT INTO `logininfo` VALUES (385, 'admin', '123456', '2022-08-25 09:47:34');
INSERT INTO `logininfo` VALUES (386, 'admin', '123456', '2022-08-25 09:48:54');
INSERT INTO `logininfo` VALUES (387, 'admin', '123456', '2022-08-25 09:50:22');
INSERT INTO `logininfo` VALUES (388, 'admin', '123456', '2022-08-25 10:01:21');
INSERT INTO `logininfo` VALUES (389, 'admin', '123456', '2022-08-25 10:06:31');
INSERT INTO `logininfo` VALUES (390, '23ewrew', 'ewwerg', '2022-08-25 11:56:31');
INSERT INTO `logininfo` VALUES (391, '周杰伦', '123456', '2022-08-25 11:57:11');
INSERT INTO `logininfo` VALUES (392, 'admin', '123456', '2022-08-25 11:58:12');
INSERT INTO `logininfo` VALUES (393, '老王', '123456', '2022-08-25 11:58:56');
INSERT INTO `logininfo` VALUES (394, 'admin', '123456', '2022-08-25 17:08:36');
INSERT INTO `logininfo` VALUES (395, '', '', '2022-08-25 17:09:14');
INSERT INTO `logininfo` VALUES (396, 'admin', '123456', '2022-08-25 17:09:35');
INSERT INTO `logininfo` VALUES (397, '周杰伦', '123456', '2022-08-25 17:15:27');
INSERT INTO `logininfo` VALUES (398, 'admin', '123456', '2022-08-25 17:16:40');
INSERT INTO `logininfo` VALUES (399, 'admin', '123456', '2022-08-25 17:23:09');
INSERT INTO `logininfo` VALUES (400, 'admin', '123456', '2022-08-25 19:57:15');
INSERT INTO `logininfo` VALUES (401, 'admin', '123456', '2022-08-25 20:02:29');
INSERT INTO `logininfo` VALUES (402, 'admin', '123456', '2022-08-25 20:03:20');
INSERT INTO `logininfo` VALUES (403, 'admin', '123456', '2022-08-25 20:06:35');
INSERT INTO `logininfo` VALUES (404, '1', '1', '2022-08-25 20:08:40');
INSERT INTO `logininfo` VALUES (405, 'admin', '123456', '2022-08-25 20:10:04');
INSERT INTO `logininfo` VALUES (406, 'admin', '123456', '2022-08-25 20:48:13');
INSERT INTO `logininfo` VALUES (407, '1', '1', '2022-08-25 20:49:35');
INSERT INTO `logininfo` VALUES (408, '1', '1', '2022-08-25 20:53:04');
INSERT INTO `logininfo` VALUES (409, '1', '1', '2022-08-25 21:28:25');
INSERT INTO `logininfo` VALUES (410, '1', '1', '2022-08-25 21:36:50');
INSERT INTO `logininfo` VALUES (411, '1', '1', '2022-08-25 21:39:41');
INSERT INTO `logininfo` VALUES (412, '1', '1', '2022-08-25 21:42:44');
INSERT INTO `logininfo` VALUES (413, '1', '1', '2022-08-25 21:42:54');
INSERT INTO `logininfo` VALUES (414, '1', '1', '2022-08-25 21:43:05');
INSERT INTO `logininfo` VALUES (415, '1', '1', '2022-08-25 21:43:13');
INSERT INTO `logininfo` VALUES (416, '', '', '2022-08-25 21:43:31');
INSERT INTO `logininfo` VALUES (417, '', '', '2022-08-25 21:46:17');
INSERT INTO `logininfo` VALUES (418, '', '', '2022-08-25 21:46:49');
INSERT INTO `logininfo` VALUES (419, '1', '1', '2022-08-25 21:47:14');
INSERT INTO `logininfo` VALUES (420, '1', '1', '2022-08-25 21:47:30');
INSERT INTO `logininfo` VALUES (421, '1', '1', '2022-08-25 21:49:56');
INSERT INTO `logininfo` VALUES (422, '1', '1', '2022-08-25 21:50:05');
INSERT INTO `logininfo` VALUES (423, '1', '1', '2022-08-25 21:50:16');
INSERT INTO `logininfo` VALUES (424, '1', '1', '2022-08-25 21:51:49');
INSERT INTO `logininfo` VALUES (425, 'admin', '123456', '2022-08-25 22:15:07');
INSERT INTO `logininfo` VALUES (426, 'admin', '123456', '2022-08-25 22:15:17');
INSERT INTO `logininfo` VALUES (427, '1', '1', '2022-08-25 22:17:03');
INSERT INTO `logininfo` VALUES (428, '1', '1', '2022-08-25 22:17:13');
INSERT INTO `logininfo` VALUES (429, '1', '1', '2022-08-25 22:22:19');
INSERT INTO `logininfo` VALUES (430, '1', '1', '2022-08-25 23:07:01');
INSERT INTO `logininfo` VALUES (431, 'admin', '123456', '2022-08-26 10:21:39');
INSERT INTO `logininfo` VALUES (432, 'admin', '123456', '2022-08-26 10:21:48');
INSERT INTO `logininfo` VALUES (433, '1', '1', '2022-08-26 10:25:59');
INSERT INTO `logininfo` VALUES (434, '老王', '123456', '2022-08-26 10:27:22');
INSERT INTO `logininfo` VALUES (435, '老刘', '1', '2022-08-26 10:29:21');
INSERT INTO `logininfo` VALUES (436, '老刘', '1', '2022-08-26 10:29:26');
INSERT INTO `logininfo` VALUES (437, '老铁', '12', '2022-08-26 10:30:50');
INSERT INTO `logininfo` VALUES (438, '老铁', '1', '2022-08-26 10:31:00');
INSERT INTO `logininfo` VALUES (439, 'admin', '123456', '2022-08-26 10:33:07');
INSERT INTO `logininfo` VALUES (440, 'admin', '123456', '2022-08-26 10:33:12');
INSERT INTO `logininfo` VALUES (441, 'admin', '123456', '2022-08-26 10:36:44');
INSERT INTO `logininfo` VALUES (442, 'admin', '123456', '2022-08-26 10:39:24');
INSERT INTO `logininfo` VALUES (443, 'admin', '123456', '2022-08-26 10:40:35');
INSERT INTO `logininfo` VALUES (444, 'admin', '123456', '2022-08-26 10:43:55');
INSERT INTO `logininfo` VALUES (445, 'admin', '123456', '2022-08-26 10:45:48');
INSERT INTO `logininfo` VALUES (446, 'admin', '123456', '2022-08-26 10:58:08');
INSERT INTO `logininfo` VALUES (447, 'admin', '123456', '2022-08-26 11:01:19');
INSERT INTO `logininfo` VALUES (448, 'admin', '123456', '2022-08-26 11:16:23');
INSERT INTO `logininfo` VALUES (449, 'admin', '123456', '2022-08-26 11:18:32');
INSERT INTO `logininfo` VALUES (450, 'admin', '123456', '2022-08-26 11:21:18');
INSERT INTO `logininfo` VALUES (451, '1', '1', '2022-08-26 11:31:05');
INSERT INTO `logininfo` VALUES (452, 'admin', '123456', '2022-08-26 11:32:24');
INSERT INTO `logininfo` VALUES (453, 'admin', '123456', '2022-08-26 11:34:14');
INSERT INTO `logininfo` VALUES (454, 'admin', '123456', '2022-08-26 11:34:56');
INSERT INTO `logininfo` VALUES (455, 'admin', '123456', '2022-08-26 11:35:07');
INSERT INTO `logininfo` VALUES (456, 'admin', '123456', '2022-08-26 11:35:56');
INSERT INTO `logininfo` VALUES (457, 'admin', '123456', '2022-08-26 11:38:13');
INSERT INTO `logininfo` VALUES (458, '23', '32', '2022-08-26 11:42:22');
INSERT INTO `logininfo` VALUES (459, '1', '123456', '2022-08-26 11:42:42');
INSERT INTO `logininfo` VALUES (460, 'admin', '123456', '2022-08-26 11:43:22');
INSERT INTO `logininfo` VALUES (461, 'admin', '123456', '2022-08-26 11:43:30');
INSERT INTO `logininfo` VALUES (462, 'admin', '123456', '2022-08-26 12:00:54');
INSERT INTO `logininfo` VALUES (463, 'admin', '123456', '2022-08-26 12:12:31');
INSERT INTO `logininfo` VALUES (464, 'admin', '123456', '2022-08-26 12:28:51');
INSERT INTO `logininfo` VALUES (465, 'admin', '123456', '2022-08-26 12:36:03');
INSERT INTO `logininfo` VALUES (466, 'admin', '123456', '2022-08-26 14:11:40');
INSERT INTO `logininfo` VALUES (467, 'admin', '123456', '2022-08-26 14:14:26');
INSERT INTO `logininfo` VALUES (468, 'admin', '123456', '2022-08-26 14:22:24');
INSERT INTO `logininfo` VALUES (469, 'admin', '123456', '2022-08-26 14:23:31');
INSERT INTO `logininfo` VALUES (470, 'admin', '123456', '2022-08-26 14:24:13');
INSERT INTO `logininfo` VALUES (471, '1', '1', '2022-08-26 14:24:54');
INSERT INTO `logininfo` VALUES (472, '1', '123456', '2022-08-26 14:25:00');
INSERT INTO `logininfo` VALUES (473, 'admin', '123456', '2022-08-26 14:26:46');
INSERT INTO `logininfo` VALUES (474, 'admin', '123456', '2022-08-26 14:45:17');
INSERT INTO `logininfo` VALUES (475, 'admin', '123456', '2022-08-26 14:47:05');
INSERT INTO `logininfo` VALUES (476, 'admin', '123456', '2022-08-26 14:56:43');
INSERT INTO `logininfo` VALUES (477, 'admin', '123456', '2022-08-26 14:58:40');
INSERT INTO `logininfo` VALUES (478, '1', '123456', '2022-08-26 15:01:16');
INSERT INTO `logininfo` VALUES (479, 'admin', '1', '2022-08-26 15:06:09');
INSERT INTO `logininfo` VALUES (480, 'admin', '123456', '2022-08-26 15:06:22');
INSERT INTO `logininfo` VALUES (481, '1', '123456', '2022-08-26 15:12:42');
INSERT INTO `logininfo` VALUES (482, 'admin', '123456', '2022-08-26 15:19:09');
INSERT INTO `logininfo` VALUES (483, 'admin', '123456', '2022-08-26 15:28:51');
INSERT INTO `logininfo` VALUES (484, '', '', '2022-08-26 15:38:53');
INSERT INTO `logininfo` VALUES (485, '阿达民', '21', '2022-08-26 15:39:02');
INSERT INTO `logininfo` VALUES (486, 'admin', '1', '2022-08-26 15:39:10');
INSERT INTO `logininfo` VALUES (487, 'admin', '123456', '2022-08-26 15:39:26');
INSERT INTO `logininfo` VALUES (488, '1', '123456', '2022-08-26 15:42:33');
INSERT INTO `logininfo` VALUES (489, '小白', '123456', '2022-08-26 15:49:58');
INSERT INTO `logininfo` VALUES (490, 'admin', '123456', '2022-08-27 13:35:18');
INSERT INTO `logininfo` VALUES (491, 'admin', '123456', '2022-08-29 09:29:13');
INSERT INTO `logininfo` VALUES (492, '周杰伦', '123456', '2022-08-29 09:36:51');
INSERT INTO `logininfo` VALUES (493, 'admin', '123456', '2022-08-29 12:18:46');
INSERT INTO `logininfo` VALUES (494, '周杰伦', '123456', '2022-08-29 12:23:30');
INSERT INTO `logininfo` VALUES (495, 'admin', '12', '2022-08-29 14:09:25');
INSERT INTO `logininfo` VALUES (496, 'admin', '123456', '2022-08-29 14:09:42');
INSERT INTO `logininfo` VALUES (497, '123456', '123456', '2022-08-29 14:11:26');
INSERT INTO `logininfo` VALUES (498, 'admin', '123456', '2022-08-29 14:13:35');
INSERT INTO `logininfo` VALUES (499, 'admin', '123456', '2022-08-29 14:25:57');
INSERT INTO `logininfo` VALUES (500, 'admin', '12345', '2022-08-29 14:26:05');
INSERT INTO `logininfo` VALUES (501, '1234', '123456', '2022-08-29 14:27:01');
INSERT INTO `logininfo` VALUES (502, 'admin', '123456', '2022-08-29 14:28:32');
INSERT INTO `logininfo` VALUES (503, '周杰伦', '123456', '2022-08-29 19:09:08');
INSERT INTO `logininfo` VALUES (504, '老王', '123456', '2022-08-29 19:09:29');
INSERT INTO `logininfo` VALUES (505, 'admin', '123456', '2022-08-29 19:10:08');
INSERT INTO `logininfo` VALUES (506, '1', '123456', '2022-08-29 19:10:50');
INSERT INTO `logininfo` VALUES (507, 'admin', '123456', '2022-08-29 20:18:46');
INSERT INTO `logininfo` VALUES (508, '周杰伦', '123456', '2022-08-29 20:20:07');
INSERT INTO `logininfo` VALUES (509, 'admin', '123456', '2022-08-29 20:22:15');
INSERT INTO `logininfo` VALUES (510, 'admin', '123456', '2022-08-29 20:27:55');
INSERT INTO `logininfo` VALUES (511, 'admin', '123456', '2022-08-31 15:25:25');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `telenum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `createtime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '123456', '123456', '2019-11-04 00:00:00');
INSERT INTO `t_user` VALUES (35, '老王', '123456', '654321', '2011-10-12 00:00:00');
INSERT INTO `t_user` VALUES (36, '老李', '123456', '654321', '2019-10-29 00:00:00');
INSERT INTO `t_user` VALUES (37, '周杰伦', '123456', '123456', '2020-11-27 00:00:00');
INSERT INTO `t_user` VALUES (38, '11', '123456', '123456', '2018-11-02 00:00:00');
INSERT INTO `t_user` VALUES (45, '老刘', '123456', '654321', '2022-08-25 00:00:00');
INSERT INTO `t_user` VALUES (46, 'user', '123456', '123456', '2022-08-01 00:00:00');
INSERT INTO `t_user` VALUES (47, '老铁', '123456', '123456', '2022-08-25 00:00:00');
INSERT INTO `t_user` VALUES (48, '东方睿智', '123456', '123456', '2022-08-26 11:40:57');
INSERT INTO `t_user` VALUES (49, '小黑', '123456', '123456', '2022-08-26 15:45:02');
INSERT INTO `t_user` VALUES (50, '小白', '123456', '123456', '2022-08-26 15:49:47');
INSERT INTO `t_user` VALUES (51, '老6', '123456', '123456', '2022-08-09 00:00:00');
INSERT INTO `t_user` VALUES (52, '12345', '123456', '123456', '2022-08-29 00:00:00');
INSERT INTO `t_user` VALUES (53, '2', '1', '123456', '2022-08-09 00:00:00');
INSERT INTO `t_user` VALUES (54, '1234567', '123456', '123456', '2022-08-29 00:00:00');
INSERT INTO `t_user` VALUES (56, 'aa', '123456', '123456', '2022-08-29 20:19:17');
INSERT INTO `t_user` VALUES (57, 'cc', '123456', '123456', '2022-08-09 00:00:00');

-- ----------------------------
-- Table structure for tran
-- ----------------------------
DROP TABLE IF EXISTS `tran`;
CREATE TABLE `tran`  (
  `tid` int NOT NULL AUTO_INCREMENT COMMENT '交易信息ID',
  `oid` int NULL DEFAULT NULL COMMENT '订单id',
  `uid` int NULL DEFAULT NULL COMMENT '顾客id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '顾客名',
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `weight` double NULL DEFAULT NULL COMMENT '重量',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `addtime` datetime NULL DEFAULT NULL COMMENT '上架时间',
  `imagepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `ordertime` datetime NULL DEFAULT NULL COMMENT '订单时间',
  `num` int NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tran
-- ----------------------------
INSERT INTO `tran` VALUES (38, 320, 29, '1', '鲨鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 16:57:39', 1);
INSERT INTO `tran` VALUES (39, 321, 29, '1', '鲨鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 17:33:53', 1);
INSERT INTO `tran` VALUES (41, 323, 33, '周杰伦', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-24 17:38:44', 1);
INSERT INTO `tran` VALUES (42, 331, 37, '周杰伦', '刀鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-24 21:48:55', 1);
INSERT INTO `tran` VALUES (43, 332, 41, '1', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:13:16', 1);
INSERT INTO `tran` VALUES (44, 333, 41, '1', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:14:58', 1);
INSERT INTO `tran` VALUES (45, 334, 41, '2', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-24 22:19:35', 1);
INSERT INTO `tran` VALUES (46, 335, 41, '2', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:20:54', 1);
INSERT INTO `tran` VALUES (47, 336, 41, '1', '鲢鱼', 10.5, 10, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-24 22:26:53', 1);
INSERT INTO `tran` VALUES (48, 337, 41, '2', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:27:50', 1);
INSERT INTO `tran` VALUES (49, 338, 37, '周杰伦', '鲢鱼', 10.5, 10, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 09:11:07', 1);
INSERT INTO `tran` VALUES (50, 341, 35, '老王', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-25 11:59:01', 1);
INSERT INTO `tran` VALUES (51, 342, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 11:59:02', 1);
INSERT INTO `tran` VALUES (52, 339, 37, '周杰伦', '小龙虾', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xl.jpeg', '2022-08-25 11:57:28', 1);
INSERT INTO `tran` VALUES (53, 345, 37, '周杰伦', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-25 17:15:51', 1);
INSERT INTO `tran` VALUES (54, 352, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:17:58', 1);
INSERT INTO `tran` VALUES (55, 353, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:18:00', 1);
INSERT INTO `tran` VALUES (56, 356, 38, '1', '金枪', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:26:12', 1);
INSERT INTO `tran` VALUES (57, 354, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:18:02', 1);
INSERT INTO `tran` VALUES (58, 361, 38, '1', '小青龙', 2, 22, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\xq.jpeg', '2022-08-26 10:26:29', 1);
INSERT INTO `tran` VALUES (59, 357, 38, '1', '黑鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg', '2022-08-26 10:26:14', 1);
INSERT INTO `tran` VALUES (60, 358, 38, '1', '鳙鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:26:16', 1);
INSERT INTO `tran` VALUES (61, 359, 38, '1', '大马哈鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:26:21', 1);
INSERT INTO `tran` VALUES (62, 355, 38, '1', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:26:09', 1);
INSERT INTO `tran` VALUES (63, 344, 35, '老王', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-25 11:59:05', 1);
INSERT INTO `tran` VALUES (64, 364, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:29', 1);
INSERT INTO `tran` VALUES (65, 377, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:48', 1);
INSERT INTO `tran` VALUES (66, 385, 35, '老王', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:27:59', 1);
INSERT INTO `tran` VALUES (67, 390, 45, '老刘', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:29:45', 1);
INSERT INTO `tran` VALUES (68, 392, 45, '老刘', '大马哈鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:29:51', 1);
INSERT INTO `tran` VALUES (69, 387, 45, '老刘', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:29:37', 1);
INSERT INTO `tran` VALUES (70, 388, 45, '老刘', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 10:29:40', 1);
INSERT INTO `tran` VALUES (71, 394, 45, '老刘', '黑鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg', '2022-08-26 10:29:58', 1);
INSERT INTO `tran` VALUES (72, 401, 47, '老铁', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:32:10', 1);
INSERT INTO `tran` VALUES (73, 398, 47, '老铁', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:32:06', 1);
INSERT INTO `tran` VALUES (74, 397, 47, '老铁', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 10:32:04', 1);
INSERT INTO `tran` VALUES (75, 400, 47, '老铁', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:32:08', 1);
INSERT INTO `tran` VALUES (76, 396, 47, '老铁', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 10:32:03', 1);
INSERT INTO `tran` VALUES (77, 402, 47, '老铁', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:32:11', 1);
INSERT INTO `tran` VALUES (78, 403, 47, '老铁', '鲫鱼', 20.5, 100, '2022-08-21 21:26:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg', '2022-08-26 10:32:12', 1);
INSERT INTO `tran` VALUES (79, 404, 47, '老铁', '青鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 10:32:14', 1);
INSERT INTO `tran` VALUES (80, 346, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:17:41', 1);
INSERT INTO `tran` VALUES (81, 407, 38, '1', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 11:31:22', 1);
INSERT INTO `tran` VALUES (82, 409, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 11:31:26', 1);
INSERT INTO `tran` VALUES (83, 362, 38, '1', '鲫鱼', 20.5, 100, '2022-08-21 21:26:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg', '2022-08-26 10:26:31', 1);
INSERT INTO `tran` VALUES (84, 408, 38, '1', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 11:31:24', 1);
INSERT INTO `tran` VALUES (85, 410, 38, '1', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 11:31:31', 1);
INSERT INTO `tran` VALUES (86, 413, 38, '1', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 11:43:01', 1);
INSERT INTO `tran` VALUES (87, 416, 50, '小白', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 15:50:03', 1);
INSERT INTO `tran` VALUES (88, 417, 50, '小白', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 15:50:05', 1);
INSERT INTO `tran` VALUES (89, 419, 50, '小白', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 15:50:09', 1);
INSERT INTO `tran` VALUES (90, 340, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-25 11:57:34', 1);
INSERT INTO `tran` VALUES (91, 425, 52, '12345', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-29 14:12:29', 1);
INSERT INTO `tran` VALUES (92, 426, 54, '1234567', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-29 14:27:34', 1);
INSERT INTO `tran` VALUES (93, 427, 54, '1234567', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 14:27:38', 1);
INSERT INTO `tran` VALUES (94, 423, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 09:37:00', 1);
INSERT INTO `tran` VALUES (95, 431, 37, '周杰伦', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-29 20:20:59', 1);
INSERT INTO `tran` VALUES (96, 430, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 20:20:57', 1);

-- ----------------------------
-- Table structure for u_order
-- ----------------------------
DROP TABLE IF EXISTS `u_order`;
CREATE TABLE `u_order`  (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `userid` int NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `weight` double NULL DEFAULT NULL COMMENT '商品重量',
  `price` double NULL DEFAULT NULL COMMENT '商品价格',
  `addtime` datetime NULL DEFAULT NULL COMMENT '商品上架时间',
  `imagepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片路径',
  `ordertime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单时间',
  `num` int NULL DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 431 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_order
-- ----------------------------
INSERT INTO `u_order` VALUES (360, 38, '1', '戴拿', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg', '2022-08-26 10:26:24', 1);
INSERT INTO `u_order` VALUES (363, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:28', 1);
INSERT INTO `u_order` VALUES (375, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:45', 1);
INSERT INTO `u_order` VALUES (376, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:46', 1);
INSERT INTO `u_order` VALUES (378, 35, '老王', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:27:49', 1);
INSERT INTO `u_order` VALUES (386, 35, '老王', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:28:05', 1);
INSERT INTO `u_order` VALUES (389, 45, '老刘', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:29:43', 1);
INSERT INTO `u_order` VALUES (391, 45, '老刘', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:29:47', 1);
INSERT INTO `u_order` VALUES (393, 45, '老刘', '戴拿', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg', '2022-08-26 10:29:54', 1);
INSERT INTO `u_order` VALUES (395, 45, '老刘', '九节虾', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai1.jpeg', '2022-08-26 10:30:04', 1);
INSERT INTO `u_order` VALUES (399, 47, '老铁', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 10:32:07', 1);
INSERT INTO `u_order` VALUES (405, 47, '老铁', '黑鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg', '2022-08-26 10:32:15', 1);
INSERT INTO `u_order` VALUES (406, 47, '老铁', '鳙鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:32:17', 1);
INSERT INTO `u_order` VALUES (411, 38, '1', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 11:31:33', 1);
INSERT INTO `u_order` VALUES (412, 38, '1', '戴拿', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\daina.jpeg', '2022-08-26 11:31:37', 1);
INSERT INTO `u_order` VALUES (415, 38, '1', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 15:13:00', 1);
INSERT INTO `u_order` VALUES (418, 50, '小白', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 15:50:07', 1);
INSERT INTO `u_order` VALUES (420, 50, '小白', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 15:50:11', 1);
INSERT INTO `u_order` VALUES (421, 50, '小白', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 15:50:13', 1);
INSERT INTO `u_order` VALUES (422, 50, '小白', '青鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 15:50:15', 1);

-- ----------------------------
-- Table structure for u_tran
-- ----------------------------
DROP TABLE IF EXISTS `u_tran`;
CREATE TABLE `u_tran`  (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int NULL DEFAULT NULL COMMENT '顾客id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `addtime` datetime NULL DEFAULT NULL,
  `imagepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` datetime NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 431 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of u_tran
-- ----------------------------
INSERT INTO `u_tran` VALUES (320, 29, '1', '鲨鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 16:57:39', 1);
INSERT INTO `u_tran` VALUES (321, 29, '1', '鲨鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 17:33:53', 1);
INSERT INTO `u_tran` VALUES (322, 29, '1', '鲨鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 17:33:55', 1);
INSERT INTO `u_tran` VALUES (323, 33, '周杰伦', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-24 17:38:44', 1);
INSERT INTO `u_tran` VALUES (332, 41, '1', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:13:16', 1);
INSERT INTO `u_tran` VALUES (333, 41, '1', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:14:58', 1);
INSERT INTO `u_tran` VALUES (334, 41, '2', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-24 22:19:35', 1);
INSERT INTO `u_tran` VALUES (335, 41, '2', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:20:54', 1);
INSERT INTO `u_tran` VALUES (336, 41, '1', '鲢鱼', 10.5, 10, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-24 22:26:53', 1);
INSERT INTO `u_tran` VALUES (337, 41, '2', '鲨鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-24 22:27:50', 1);
INSERT INTO `u_tran` VALUES (338, 37, '周杰伦', '鲢鱼', 10.5, 10, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 09:11:07', 1);
INSERT INTO `u_tran` VALUES (340, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-25 11:57:34', 1);
INSERT INTO `u_tran` VALUES (341, 35, '老王', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-25 11:59:01', 1);
INSERT INTO `u_tran` VALUES (342, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 11:59:02', 1);
INSERT INTO `u_tran` VALUES (344, 35, '老王', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-25 11:59:05', 1);
INSERT INTO `u_tran` VALUES (345, 37, '周杰伦', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-25 17:15:51', 1);
INSERT INTO `u_tran` VALUES (346, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:17:41', 1);
INSERT INTO `u_tran` VALUES (354, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-25 22:18:02', 1);
INSERT INTO `u_tran` VALUES (355, 38, '1', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:26:09', 1);
INSERT INTO `u_tran` VALUES (356, 38, '1', '金枪', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:26:12', 1);
INSERT INTO `u_tran` VALUES (357, 38, '1', '黑鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg', '2022-08-26 10:26:14', 1);
INSERT INTO `u_tran` VALUES (358, 38, '1', '鳙鱼', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:26:16', 1);
INSERT INTO `u_tran` VALUES (359, 38, '1', '大马哈鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:26:21', 1);
INSERT INTO `u_tran` VALUES (361, 38, '1', '小青龙', 2, 22, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\xq.jpeg', '2022-08-26 10:26:29', 1);
INSERT INTO `u_tran` VALUES (362, 38, '1', '鲫鱼', 20.5, 100, '2022-08-21 21:26:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg', '2022-08-26 10:26:31', 1);
INSERT INTO `u_tran` VALUES (364, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:29', 1);
INSERT INTO `u_tran` VALUES (377, 35, '老王', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:27:48', 1);
INSERT INTO `u_tran` VALUES (385, 35, '老王', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:27:59', 1);
INSERT INTO `u_tran` VALUES (387, 45, '老刘', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:29:37', 1);
INSERT INTO `u_tran` VALUES (388, 45, '老刘', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 10:29:40', 1);
INSERT INTO `u_tran` VALUES (390, 45, '老刘', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:29:45', 1);
INSERT INTO `u_tran` VALUES (392, 45, '老刘', '大马哈鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\baoyu.jpeg', '2022-08-26 10:29:51', 1);
INSERT INTO `u_tran` VALUES (394, 45, '老刘', '黑鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\xia.jpeg', '2022-08-26 10:29:58', 1);
INSERT INTO `u_tran` VALUES (396, 47, '老铁', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 10:32:03', 1);
INSERT INTO `u_tran` VALUES (397, 47, '老铁', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 10:32:04', 1);
INSERT INTO `u_tran` VALUES (398, 47, '老铁', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 10:32:06', 1);
INSERT INTO `u_tran` VALUES (400, 47, '老铁', '波龙', 20.5, 100, '2020-11-02 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:32:08', 1);
INSERT INTO `u_tran` VALUES (401, 47, '老铁', '三眼蟹', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish3.jpeg', '2022-08-26 10:32:10', 1);
INSERT INTO `u_tran` VALUES (402, 47, '老铁', '田鸡', 20.5, 100, '2021-12-08 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpeg', '2022-08-26 10:32:11', 1);
INSERT INTO `u_tran` VALUES (403, 47, '老铁', '鲫鱼', 20.5, 100, '2022-08-21 21:26:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\penhai.jpg', '2022-08-26 10:32:12', 1);
INSERT INTO `u_tran` VALUES (404, 47, '老铁', '青鱼', 20.5, 100, '2022-08-21 21:21:04', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 10:32:14', 1);
INSERT INTO `u_tran` VALUES (407, 38, '1', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 11:31:22', 1);
INSERT INTO `u_tran` VALUES (408, 38, '1', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 11:31:24', 1);
INSERT INTO `u_tran` VALUES (409, 38, '1', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-26 11:31:26', 1);
INSERT INTO `u_tran` VALUES (410, 38, '1', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 11:31:31', 1);
INSERT INTO `u_tran` VALUES (413, 38, '1', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 11:43:01', 1);
INSERT INTO `u_tran` VALUES (416, 50, '小白', '鲨鱼', 20.5, 100, '2021-11-30 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\sh.jpeg', '2022-08-26 15:50:03', 1);
INSERT INTO `u_tran` VALUES (417, 50, '小白', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-26 15:50:05', 1);
INSERT INTO `u_tran` VALUES (419, 50, '小白', '草鱼', 20, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpg', '2022-08-26 15:50:09', 1);
INSERT INTO `u_tran` VALUES (423, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 09:37:00', 1);
INSERT INTO `u_tran` VALUES (427, 54, '1234567', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 14:27:38', 1);
INSERT INTO `u_tran` VALUES (430, 37, '周杰伦', '刀鱼', 20.5, 100, '2019-11-01 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish1.jpeg', '2022-08-29 20:20:57', 1);
INSERT INTO `u_tran` VALUES (431, 37, '周杰伦', '鲢鱼', 10.5, 10, '2021-12-07 00:00:00', 'E:\\睿智\\第一阶段\\项目资源\\image\\fish2.jpeg', '2022-08-29 20:20:59', 1);

SET FOREIGN_KEY_CHECKS = 1;
