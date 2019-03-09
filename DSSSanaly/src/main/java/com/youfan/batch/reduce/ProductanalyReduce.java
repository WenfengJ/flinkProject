package com.youfan.batch.reduce;

import com.youfan.analy.PidaoXinXianDu;
import com.youfan.batch.analy.ProductAnaly;
import org.apache.flink.api.common.functions.ReduceFunction;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
public class ProductanalyReduce implements ReduceFunction<ProductAnaly> {
    @Override
    public ProductAnaly reduce(ProductAnaly value1, ProductAnaly value2) throws Exception {
        String datetime = value1.getDateString();
        long productid = value1.getProductid();
        long chengjiaovalue1 = value1.getChengjiaocount();
        long weichegnjiaovalue1 = value1.getWeichegnjiao();

        long chengjiaovalue2 = value1.getChengjiaocount();
        long weichegnjiaovalue2 = value1.getWeichegnjiao();

        ProductAnaly productAnaly = new ProductAnaly();
        productAnaly.setDateString(datetime);
        productAnaly.setProductid(productid);
        productAnaly.setChengjiaocount(chengjiaovalue1+chengjiaovalue2);
        productAnaly.setWeichegnjiao(weichegnjiaovalue1+weichegnjiaovalue2);
        return productAnaly;
    }
}
