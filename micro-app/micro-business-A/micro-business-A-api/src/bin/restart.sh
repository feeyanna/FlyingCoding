#! /bin/shell
#======================================================================
# 项目重启shell脚本
# 先调用shutdown.sh停服
# 然后调用startup.sh启动服务
#
# author: laifeiyang
# date: 2019-11-19
#======================================================================

# 项目名称
APPLICATION="micro-business-A-api"

# 停服
echo stop ${APPLICATION} Application...
sh shutdown.sh

# 启动服务
echo start ${APPLICATION} Application...
echo 请确认父模块micro-parent的pom中已进行zookeeper和nacos的切换。[Y:已确认,N：未确认]-默认为zookeeper
read -t 10 -p "请输入是否确认:" confirm
# 如果未确认，请从新打包
if [ "$confirm" = "Y" ]; then
    echo 感谢配合,继续执行中...
    sh startup.sh
elif [ "$confirm" = "y" ]; then
    echo 感谢配合,继续执行中...
	sh startup.sh
fi
echo 请在父模块micro-parent的pom中进行zookeeper和nacos的切换,并从新打包发布