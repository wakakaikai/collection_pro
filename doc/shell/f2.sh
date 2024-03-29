#! /bin/bash
# 1. 判断是否存在参数
if [ $# == 0 ];then
  echo -e "请输入参数：\nstart   启动日志消费flume；\nstop   关闭日志消费flume；"&&exit
fi

FLUME_HOME=/opt/module/flume-1.9.0

case $1 in
"start"){
      echo " --------启动 hadoop104 消费flume-------"
      ssh hadoop104 "nohup $FLUME_HOME/bin/flume-ng agent -n a1 -c $FLUME_HOME/conf/ -f $FLUME_HOME/job/flume-kafka-hdfs.conf -Dflume.root.logger=INFO,LOGFILE >$FLUME_HOME/logs/flume.log  2>&1 &"
};;

"stop"){
      echo "---------- 停止 hadoop104 上的 日志消费flume ----------"
      flume_count=$(xcall jps -ml | grep flume-kafka-hdfs|wc -l);
      if [ $flume_count != 0 ];then
          ssh hadoop104 "ps -ef | grep flume-kafka-hdfs | grep -v grep | awk '{print \$2}' | xargs -n1 kill -9"
      else
          echo " hadoop104 当前没有日志采集flume在运行"
      fi
  };;
esac
