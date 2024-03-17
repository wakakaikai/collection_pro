#!/bin/bash
# 1. 判断参数个数
if [ $# == 0 ];then
    echo "请输入需要分发的文件\目录"&&exit
fi

# 2. 同步文件
for host in hadoop102 hadoop103 hadoop104
  do
    echo "========== ${host} =========="
    # 3. 遍历文件/目录
    for file in $@
      do
        # 4. 判断是否是文件/目录
        if [ -e $file ];then
            # 5. 获取父目录
            pdir=${cd -P $(dirname $file);pwd}
            # 6. 获取文件名
            echo $pdir
            # 7. 创建父目录

            # 8. 同步文件
        else
          echo "文件/目录不存在！"
        fi
      done
  done
