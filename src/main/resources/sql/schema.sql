/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : tale

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 28/02/2020 10:28:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attach
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
                            `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                            `fname` varchar(100) NOT NULL DEFAULT '',
                            `ftype` varchar(50) DEFAULT '',
                            `fkey` varchar(100) NOT NULL DEFAULT '',
                            `author_id` int(10) DEFAULT NULL,
                            `created` int(10) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
                              `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `cid` int(10) unsigned DEFAULT '0',
                              `created` int(10) unsigned DEFAULT '0',
                              `author` varchar(200) DEFAULT NULL,
                              `author_id` int(10) unsigned DEFAULT '0',
                              `owner_id` int(10) unsigned DEFAULT '0',
                              `mail` varchar(200) DEFAULT NULL,
                              `url` varchar(200) DEFAULT NULL,
                              `ip` varchar(64) DEFAULT NULL,
                              `agent` varchar(200) DEFAULT NULL,
                              `content` text,
                              `type` varchar(16) DEFAULT 'comment',
                              `status` varchar(16) DEFAULT 'approved',
                              `parent` int(10) unsigned DEFAULT '0',
                              PRIMARY KEY (`coid`),
                              KEY `cid` (`cid`),
                              KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_contents
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents` (
                              `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
                              `title` varchar(200) DEFAULT NULL,
                              `slug` varchar(200) DEFAULT NULL,
                              `created` int(10) unsigned DEFAULT '0',
                              `modified` int(10) unsigned DEFAULT '0',
                              `content` text COMMENT 'å†…å®¹æ–‡å­—',
                              `author_id` int(10) unsigned DEFAULT '0',
                              `type` varchar(16) DEFAULT 'post',
                              `status` varchar(16) DEFAULT 'publish',
                              `tags` varchar(200) DEFAULT NULL,
                              `categories` varchar(200) DEFAULT NULL,
                              `hits` int(10) unsigned DEFAULT '0',
                              `comments_num` int(10) unsigned DEFAULT '0',
                              `allow_comment` tinyint(1) DEFAULT '1',
                              `allow_ping` tinyint(1) DEFAULT '1',
                              `allow_feed` tinyint(1) DEFAULT '1',
                              PRIMARY KEY (`cid`),
                              UNIQUE KEY `slug` (`slug`),
                              KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                          `action` varchar(100) DEFAULT NULL,
                          `data` varchar(2000) DEFAULT NULL,
                          `author_id` int(10) DEFAULT NULL,
                          `ip` varchar(20) DEFAULT NULL,
                          `created` int(10) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_metas
-- ----------------------------
DROP TABLE IF EXISTS `t_metas`;
CREATE TABLE `t_metas` (
                           `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `name` varchar(200) DEFAULT NULL,
                           `slug` varchar(200) DEFAULT NULL,
                           `type` varchar(32) NOT NULL DEFAULT '',
                           `description` varchar(200) DEFAULT NULL,
                           `sort` int(10) unsigned DEFAULT '0',
                           `parent` int(10) unsigned DEFAULT '0',
                           PRIMARY KEY (`mid`),
                           KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
                             `name` varchar(32) NOT NULL DEFAULT '',
                             `value` varchar(1000) DEFAULT '',
                             `description` varchar(200) DEFAULT NULL,
                             PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
                                   `cid` int(10) unsigned NOT NULL,
                                   `mid` int(10) unsigned NOT NULL,
                                   PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
                           `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
                           `username` varchar(32) DEFAULT NULL,
                           `password` varchar(64) DEFAULT NULL,
                           `email` varchar(200) DEFAULT NULL,
                           `home_url` varchar(200) DEFAULT NULL,
                           `screen_name` varchar(32) DEFAULT NULL,
                           `created` int(10) unsigned DEFAULT '0',
                           `activated` int(10) unsigned DEFAULT '0',
                           `logged` int(10) unsigned DEFAULT '0',
                           `group_name` varchar(16) DEFAULT 'visitor',
                           PRIMARY KEY (`uid`),
                           UNIQUE KEY `name` (`username`),
                           UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
