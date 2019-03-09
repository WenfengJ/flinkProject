package com.youfan.control;

import com.youfan.batch.analy.ProductAnaly;
import com.youfan.service.ProductAnalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@RestController
@RequestMapping("productControl")
public class ProductControl {

    @Autowired
    private ProductAnalyService productAnalyService ;

    @RequestMapping(value = "searchproductanaly")
    public ProductAnaly searchproductanaly(long prdouctid, String datetime){
           return     productAnalyService.listProductAnaly(prdouctid,datetime);
    }

}
