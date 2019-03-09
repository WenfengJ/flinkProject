package com.youfan.control;

import com.youfan.batch.analy.ProductAnaly;
import com.youfan.util.HbaseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@RestController
@RequestMapping("product")
public class ProductControl {

    @RequestMapping("listProductAnaly")
    public ProductAnaly listProductAnaly(long productid,String timedate){
        ProductAnaly productAnaly = new ProductAnaly();
        try {
            String chengjiaocount = HbaseUtil.getdata("productinfo",productid+"=="+timedate,"info","chengjiaocount");
            String weichegnjiao = HbaseUtil.getdata("productinfo",productid+"=="+timedate,"info","weichegnjiao");

            productAnaly.setProductid(productid);
            productAnaly.setDateString(timedate);
            productAnaly.setWeichegnjiao(Long.valueOf(weichegnjiao));
            productAnaly.setChengjiaocount(Long.valueOf(chengjiaocount));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productAnaly;
    }

}
