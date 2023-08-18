package com.k66.hadoop.mr;

/**
 * 统计给定的文件中输出每一个单词出现的总次数
 * wc.data
 *   pk,pk,pk
 *   hello,hello
 *   world
 * ==>
 *   pk 3
 *   hello 2
 *   world 1
 *
 *   Mapper
 *      读入一行行的数据，按照分隔符对一行行的数据进行拆分
 *      按照<单词,1>格式的输出
 *   Reducer
 *      <单词,N>
 *   Driver
 */