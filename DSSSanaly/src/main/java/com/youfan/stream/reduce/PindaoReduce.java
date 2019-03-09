package com.youfan.stream.reduce;

import com.youfan.analy.PindaoRD;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Created by Administrator on 2018/10/28 0028.
 */
public class PindaoReduce implements ReduceFunction<PindaoRD> {

    @Override
    public PindaoRD reduce(PindaoRD value1, PindaoRD value2) throws Exception {
        PindaoRD pindaoRD = new PindaoRD();
        System.out.println("value1=="+value1);
        System.out.println("value2=="+value2);
        pindaoRD.setPingdaoid(value1.getPingdaoid());
        pindaoRD.setCount(value1.getCount()+value2.getCount());
        return  pindaoRD;
    }
}
