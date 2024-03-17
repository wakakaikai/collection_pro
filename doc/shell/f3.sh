#!/bin/bash
FLUME_HOME=/opt/module/flume-1.9.0
case $1 in
"start")
        echo " --------启动 hadoop104 业务数据flume-------"
        ssh hadoop104 "nohup $FLUME_HOME/bin/flume-ng agent -n a1 -c $FLUME_HOME/conf -f $FLUME_HOME/job/kafka_to_hdfs_db.conf >/dev/null 2>&1 &"
;;
"stop")

        echo " --------停止 hadoop104 业务数据flume-------"
        ssh hadoop104 "ps -ef | grep kafka_to_hdfs_db.conf | grep -v grep |awk '{print \$2}' | xargs -n1 kill"
;;
esac
