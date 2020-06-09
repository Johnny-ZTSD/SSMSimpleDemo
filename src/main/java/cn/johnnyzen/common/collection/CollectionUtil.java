package cn.johnnyzen.common.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @project: SSMDemo2
 * @author: 千千寰宇
 * @date: 2020/6/6  22:54:43
 * @description: ...
 */

public class CollectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionUtil.class);

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * 将Collection集合对象转为数组
     * @param <T>
     * @return
     *  + null
     *  + array(size>0)
     */
    public static <T> List collectionToList(Collection<T> collection){
        if(collection==null || collection.size()<1){
            LOGGER.error("Fail to invoke!Because collection is null or its size<1!");
            return null;
        }
        Iterator<T> iterator = collection.iterator();
        List<T> list = new ArrayList<T>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
//        int size=collection.size();
//        T [] array = (T[]) new Object[size];//初始化泛型数组
//        for(int i=0;iterator.hasNext();i++){
//            array[i] = iterator.next();
//        }
        return list;
    }

    /**
     * 当前对象是否该列表里面的成员
     * @param list
     * @param item
     */
    public static <T> boolean isItemInList(List<T> list, T item){
        if(list==null){
            return false;
        }
        Iterator iterator = null;
        iterator = list.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * 求字符串差集
     *  注1:方向，仅支持从前往后求差集
     *  注2:必须满足 一个字符串是另一个字符串的子集，如果不是子集，结果将出错
     *  @param strA
     *  @param strB
     */
    public static String minus(String strA, String strB){
        if(strA == null){
            return null;
        }
        if(strB == null){
            return null;
        }
        int compare = strA.compareTo(strB);
        System.out.println("compare:" + (compare) + " <" + strA + " : " + strB + ">");
        int lenA = strA.length();
        int lenB = strB.length();
        if(compare == 0){ //相等
            return "";
        } else if(compare < 0){//A更小或者更短
            return strB.substring(lenB + compare);
        } else {//B更长或者更大
            return strA.substring(lenA - compare);
        }
    }
}
