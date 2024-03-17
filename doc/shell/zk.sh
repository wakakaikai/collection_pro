#!/bin/bash
if [ $# -eq 0 ]
then
    echo -e "请输入参数：\nstart  启动ZK集群；\nstop  关闭ZK集群；\nstatus  查看ZK状态"&&exit
fi
case $1 in
"start")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo " =================== $host 启动 zk集群 ==================="
        ssh $host "/opt/module/zookeeper/bin/zkServer.sh start"
      done
;;
"stop")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo " =================== $host 关闭 zk集群 ==================="
        ssh $host "/opt/module/zookeeper/bin/zkServer.sh stop"
      done
;;
"status")
    for host in hadoop102 hadoop103 hadoop104
      do
        echo " =================== $host 查看 zk状态 ==================="
        ssh $host "/opt/module/zookeeper/bin/zkServer.sh status"
      done
;;
*)
  echo -e "请输入参数：\nstart  启动ZK集群；\nstop  关闭ZK集群；\nstatus  查看ZK状态"
;;
esac
