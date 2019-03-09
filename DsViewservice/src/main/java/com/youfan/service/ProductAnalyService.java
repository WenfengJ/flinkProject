package com.youfan.service;

import com.youfan.batch.analy.ProductAnaly;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/11/3 0003.
 */
@FeignClient(value = "DsInterfaceService")
public interface ProductAnalyService {

    @RequestMapping("/product/listProductAnaly")
    public ProductAnaly listProductAnaly(@RequestParam(value = "productid") long productid, @RequestParam(value = "timedate") String timedate);
}
