package cn.mediinfo.grus.shujuzx.utils;

import cn.hutool.core.convert.Convert;
import cn.mediinfo.cyan.msf.core.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WenDangSCSJJUtils<T> {
    private Class<T> clazz;

    public   String  getXMLStr(T data,List<String> xmlList) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = data.getClass().getDeclaredFields();
        List<String> resList =new ArrayList<>();
        resList.addAll(xmlList);
        for (Field field : fields)
        {
            field.setAccessible(true);
            var shuXingMC=field.getName();
            var shuXingLX=field.getType();
            Class<?> dataClass = data.getClass();
            Field dataField = dataClass.getDeclaredField(shuXingMC);
            dataField.setAccessible(true);
            var shuXingZhi =  dataField.get(data);
            var columnname = shuXingMC.toUpperCase();

            if(shuXingLX== List.class){

                var infoList = Convert.toList(shuXingZhi);
                var num=infoList.size();
                //在指定位置添加节点
                if(num>1){
                    var itemField=infoList.get(0).getClass().getDeclaredFields();
                    List<String> jieDianMCList=new ArrayList<>();
                    for(Field itemInfo:itemField){
                        itemInfo.setAccessible(true);
                        var infoSXMC=itemInfo.getName();
                        var infoName=infoSXMC.toUpperCase();
                        jieDianMCList.add(infoName);
                    }
                    var index=0;
                    List<String> jieDianNRList=new ArrayList<>();
                    for(int i=0;i<resList.size();i++){
                        for(int m=0;m<jieDianMCList.size();m++){
                            var name=jieDianMCList.get(m);
                            var jiDian=jieDianNRList.stream().filter(o->o.contains("@" + name + "@")).findFirst();
                            if(jieDianNRList.size()==0||jiDian.isEmpty()||StringUtil.notHasText(jiDian.toString())){
                                if(resList.get(i).contains("@" + jieDianMCList.get(m) + "@"))
                                {
                                    if(index==0){
                                        index=i;
                                    }
                                    jieDianNRList.add(resList.get(i));
                                }
                            }
                        }

                    }
                    for(int i=0;i<num-1;i++){
                        resList.addAll(index,jieDianNRList);
                    }
                }
                for ( int n=0;n<infoList.size();n++){
                    var itemField=infoList.get(n).getClass().getDeclaredFields();
                    for(Field itemInfo:itemField){
                        itemInfo.setAccessible(true);
                        var infoSXMC=itemInfo.getName();
                        var infoName=infoSXMC.toUpperCase();
                        Class<?> infoClass = infoList.get(n).getClass();
                        Field infoDField= null;
                        try {
                            infoDField = infoClass.getDeclaredField(infoSXMC);
                            infoDField.setAccessible(true);
                            var infoValue =  infoDField.get(infoList.get(n));
                            var isChange=false;
                            for(int i=0;i<resList.size();i++){
                                if(resList.get(i).contains("@" + infoName + "@")&&!isChange)
                                {
                                    isChange=true;
                                    if(infoValue !=null){
                                        resList.set(i,resList.get(i).replace("@" + infoName + "@", FiledSpecialProceed(infoValue)));
                                    }
                                    else{
                                        resList.set(i,resList.get(i).replace("@" + infoName + "@", ""));
                                    }

                                }
                            }
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException(e);
                        }catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }else{

                for(int i=0;i<resList.size();i++){

                    if(resList.get(i).contains("@" + columnname + "@"))
                    {
                        if(shuXingZhi !=null){
                            resList.set(i,resList.get(i).replace("@" + columnname + "@", FiledSpecialProceed(shuXingZhi)));
                        }
                        else{
                            resList.set(i,resList.get(i).replace("@" + columnname + "@", ""));
                        }

                    }
                }
            }
        }
        StringBuilder xmlStr = new StringBuilder();
        if(resList!=null){
            resList.forEach(o->{
                xmlStr.append(o);
            });
        }
        String result = xmlStr.toString();
        return result;
    }

    /// <summary>
    /// 字段特殊处理，如替换XML文档对应的特殊，时间格式转换
    /// </summary>
    /// <param name="filedString">待处理的字段值</param>
    /// <returns>处理后的字符串</returns>
    private static String FiledSpecialProceed(Object filedString)
    {
        if (filedString != null && StringUtil.hasText(filedString.toString()))
        {
            // 特殊字符处理
            filedString = ConvertChrN(filedString.toString());
            filedString = filedString.toString().replace('<', '[').replace('>', ']').replace('&', '_').replace('"', '\'');

            if (filedString.toString().trim().length() >= 10)
            {
                // 日期格式处理
                Date time = new Date();
                try
                {
                    if (!filedString.toString().trim().contains("--") && isValidDate(filedString.toString()))
                    {
                        time= Convert.toDate(filedString);
                        if ((time.getHours() == 0 && time.getMinutes() == 0 && time.getSeconds() == 0))
                        {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            filedString=format.format(time);
                        }
                        else
                        {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            filedString=format.format(time);
                        }
                    }
                }
                catch(Exception ex)
                {
                    // 转换失败不处理
                }
            }

            return filedString.toString().trim();
        }

        return null;
    }

    //校验日期
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    /// <summary>
    /// 移除ASCII码值为Config中配置的值的字符
    /// </summary>
    /// <param name="src"></param>
    private static String ConvertChrN(String src)
    {
        String SpecialChar = "0,1,2,3,4,5,6,7,8,9,11,12,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,64,127";
        if (SpecialChar == null || StringUtil.notHasText(SpecialChar))
        {
            return src;
        }
        char[] b = src.toCharArray();
        char[] c = new char[b.length];
        int j = 0;
        for (int i = 0; i < b.length; i++)
        {
            if (SpecialChar.indexOf(Convert.toStr((int) b[i])) >= 0)
            {
                continue;
            }
            c[j++] = b[i];
        }
        return new String(c, 0, j);

    }

}
