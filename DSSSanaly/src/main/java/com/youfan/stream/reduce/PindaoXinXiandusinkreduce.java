package com.youfan.stream.reduce;

import com.youfan.analy.PidaoPvUv;
import com.youfan.analy.PidaoXinXianDu;
import com.youfan.util.HbaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/30 0030.
 */
public class PindaoXinXiandusinkreduce implements SinkFunction<PidaoXinXianDu> {
    @Override
    public void invoke(PidaoXinXianDu value, Context context) throws Exception {
        System.out.println( "recuducesinkd --pidaoPvUv=="+value);
        long pingdaoid = value.getPingdaoid();
        long newcount = value.getNewcount();
        long oldcount = value.getOldcount();
        String timestring = value.getTimestring();
        String newcountstring = HbaseUtil.getdata("pindaoinfo",pingdaoid+timestring,"info","xinxiandunewcount");
        String oldcountstring = HbaseUtil.getdata("pindaoinfo",pingdaoid+timestring,"info","xinxianduoldcount");
        if(StringUtils.isNotBlank(newcountstring)){
            newcount += newcount + Long.valueOf(newcountstring);
        }
        if(StringUtils.isNotBlank(oldcountstring)) {
            oldcount += oldcount + Long.valueOf(oldcountstring);
        }

        Map<String,String> datamap = new HashMap<String,String>();
        datamap.put("xinxiandunewcount",newcount+"");
        datamap.put("xinxianduoldcount",oldcount+"");
        System.out.println( "xinxiandu---- HbaseUtil.put(pindaoinfo+"+","+pingdaoid+timestring+",info"+datamap+")");
        HbaseUtil.put("pindaoinfo",pingdaoid+timestring,"info",datamap);
    }
}
