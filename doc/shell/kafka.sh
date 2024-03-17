#!/bin/bash
if [ ! -n "$1" ]; then
  echo -e "请输入参数：\n start  启动kafka集群;\n stop  停止kafka集群;\n" && exit
fi

case $1 in
"start")
  for host in hadoop102 hadoop103 hadoop104
    do
      echo "---------- $1 $host 的kafka ----------"
      ssh $host "/opt/module/kafka/bin/kafka-server-start.sh -daemon /opt/module/kafka/config/server.properties"
    done
;;
"stop")
  for host in hadoop102 hadoop103 hadoop104
    do
      echo "---------- $1 $host 的kafka ----------"
      ssh $host "/opt/module/kafka/bin/kafka-server-stop.sh /opt/module/kafka/config/server.properties"
    done
;;
esac

