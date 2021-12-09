
##建表语句

CREATE TABLE `sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号码',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '性别，0未知，1男，2女',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用，0未启用，1启用，99冻结',
  `create_user` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` int(10) unsigned DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL COMMENT '后续通过sys_role表做关联',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';