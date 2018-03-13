/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cms_test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-13 08:47:31
声明：此sql主要来源于开源的zheng项目(https://gitee.com/shuzheng/zheng)
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `topic_id` int(11) DEFAULT NULL COMMENT '所属专题',
  `title` varchar(200) NOT NULL COMMENT '文章标题',
  `author` varchar(50) DEFAULT NULL COMMENT '文章原作者',
  `fromurl` varchar(300) DEFAULT NULL COMMENT '转载来源网址',
  `image` varchar(300) DEFAULT NULL COMMENT '封面图',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键字',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `allowcomments` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否允许评论(0:不允许,1:允许)',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(-1:不通过,0未审核,1:通过)',
  `content` mediumtext COMMENT '内容',
  `user_id` int(10) unsigned NOT NULL COMMENT '发布人id',
  `readnumber` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '阅读数量',
  `top` int(11) NOT NULL DEFAULT '0' COMMENT '置顶等级',
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('21', null, '文章标题', 'leecp', 'http://localhost/a/', null, null, '就是文章的测试', '1', '0', '0', '文章内容', '0', '0', '0', null, '0', '0');

-- ----------------------------
-- Table structure for `article_category`
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `category_id` int(10) unsigned NOT NULL COMMENT '类目编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章类目关联表';

-- ----------------------------
-- Records of article_category
-- ----------------------------

-- ----------------------------
-- Table structure for `article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `article_tag`;
CREATE TABLE `article_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签关联表';

-- ----------------------------
-- Records of article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类目编号',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '上级编号',
  `level` tinyint(4) NOT NULL COMMENT '层级',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` tinyint(3) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(255) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='类目表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('5', null, '1', '经济', '经济类目', '', '1', 'economic', '1', '1489590733919', '1489590733919');
INSERT INTO `category` VALUES ('6', '5', '2', '中国经济', '中国经济类目', '', '1', 'chinaeconomic', '1', '1489590768989', '1489590768989');
INSERT INTO `category` VALUES ('7', '5', '2', '日本经济', '日本经济类目', '', '1', 'japaneconomic', '1', '1491636586316', '1491636586316');
INSERT INTO `category` VALUES ('8', null, '1', '人类', '人类问题', '', '1', 'people', '2', '1491636586317', '1491636586317');
INSERT INTO `category` VALUES ('9', null, '1', '技术', '技术博文', null, '1', 'technic', '3', '1491636586318', '1491636586318');
INSERT INTO `category` VALUES ('10', '6', '2', '中国正在腾飞', '这是一个基本测试', 'icon', '1', '沉睡的东方巨龙', '1', '0', '0');

-- ----------------------------
-- Table structure for `category_tag`
-- ----------------------------
DROP TABLE IF EXISTS `category_tag`;
CREATE TABLE `category_tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` int(10) unsigned NOT NULL COMMENT '类目编号',
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目标签关联表';

-- ----------------------------
-- Records of category_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(10) unsigned DEFAULT NULL COMMENT '回复楼中楼编号回复楼中楼编号',
  `article_id` int(10) unsigned NOT NULL COMMENT '文章编号',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户编号',
  `content` text NOT NULL COMMENT '评论内容',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态(-1:不通过,0:未审核,1:通过)',
  `ip` varchar(30) DEFAULT NULL COMMENT '评论人ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '评论人终端信息',
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', null, '8', '1', '1', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490535749413');
INSERT INTO `comment` VALUES ('2', null, '8', '1', '2', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536186447');
INSERT INTO `comment` VALUES ('3', null, '8', '1', '3', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536192205');
INSERT INTO `comment` VALUES ('4', null, '8', '1', '4', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536460544');
INSERT INTO `comment` VALUES ('5', null, '8', '1', '5', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536462182');
INSERT INTO `comment` VALUES ('6', null, '8', '1', '6', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536463614');
INSERT INTO `comment` VALUES ('7', null, '8', '1', '7', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536464870');
INSERT INTO `comment` VALUES ('8', null, '8', '1', '8', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536465926');
INSERT INTO `comment` VALUES ('9', null, '8', '1', '9', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536466853');
INSERT INTO `comment` VALUES ('10', null, '8', '1', '10', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '1', '1490536467821');
INSERT INTO `comment` VALUES ('11', null, '19', '1', '1', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737900448');
INSERT INTO `comment` VALUES ('12', null, '19', '1', '3', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737902517');
INSERT INTO `comment` VALUES ('13', null, '19', '1', '4', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737903420');
INSERT INTO `comment` VALUES ('14', null, '19', '1', '5', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737904428');
INSERT INTO `comment` VALUES ('15', null, '19', '1', '6', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737905236');
INSERT INTO `comment` VALUES ('16', null, '19', '1', '7', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Safari/537.36', '2', '1491737905980');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` int(11) DEFAULT NULL COMMENT '父菜单',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `target` varchar(10) DEFAULT NULL COMMENT '打开方式',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('7', '0', '博客', '/cms/blog', null, null);
INSERT INTO `menu` VALUES ('8', '0', '问答', '/cms/qa', null, null);
INSERT INTO `menu` VALUES ('9', '0', '资讯', '/cms/new', null, null);

-- ----------------------------
-- Table structure for `page`
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编码',
  `pid` int(10) DEFAULT NULL COMMENT '父页面',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `content` mediumtext COMMENT '页面内容',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键字',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面';

-- ----------------------------
-- Records of page
-- ----------------------------

-- ----------------------------
-- Table structure for `setting`
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(10) DEFAULT NULL,
  `setting_value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站配置';

-- ----------------------------
-- Records of setting
-- ----------------------------

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '系统名称',
  `code` varchar(20) DEFAULT NULL COMMENT '别名',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `orders` bigint(20) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='系统管理';

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES ('1', '资讯', 'news', '资讯', '1', '1');
INSERT INTO `system` VALUES ('2', '问答', 'qa', '问答', '2', '2');
INSERT INTO `system` VALUES ('3', '博客', 'blog', '博客', '3', '3');
INSERT INTO `system` VALUES ('4', '工作', 'job', '企业招聘及个人应聘', null, null);

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型(1:普通,2:热门...)',
  `alias` varchar(20) DEFAULT NULL COMMENT '别名',
  `system_id` int(11) DEFAULT NULL COMMENT '所属系统',
  `ctime` bigint(20) unsigned NOT NULL COMMENT '创建时间',
  `orders` bigint(20) unsigned NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'JAVA', 'java标签', '', '1', 'java', '1', '1489585694864', '1489585694864');
INSERT INTO `tag` VALUES ('2', 'Android', 'android标签', '', '1', 'android', '1', '1489585720382', '1489585720382');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `ctime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='专题';

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '日本地震专题', '日本经历灭国性地震，彻底沉入海底', '/topic/1', '1489843484448');
INSERT INTO `topic` VALUES ('2', '这是一个专题', '就是一个测试', '/api/v1/cms/topic', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码(MD5(密码+盐))',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别(0:未知,1:男,2:女)',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `create_ip` varchar(50) DEFAULT NULL COMMENT '注册IP地址',
  `last_login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('7', '123456', null, 'admin', '0', null, '2018-03-12 23:52:07', null, '2018-03-05 23:51:56', null);
