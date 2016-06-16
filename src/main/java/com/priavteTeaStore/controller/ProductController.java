package com.priavteTeaStore.controller;

import com.priavteTeaStore.msg.AddProductMsg;
import com.priavteTeaStore.msg.ClientTeaStore;
import com.priavteTeaStore.msg.ListProductMsg;
import com.priavteTeaStore.msg.ReqProductCategoryMsg;
import com.priavteTeaStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Thoughtworks on 16/6/11.
 */
@RestController
public class ProductController extends BaseRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/addProductCategory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProductCategory(@RequestBody @Valid ReqProductCategoryMsg msg) {
        productService.addCategory(msg.getCategoryName());
    }

    @RequestMapping(value = "/addTeaStoreCard", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody @Valid AddProductMsg msg) {
        productService.addProduct(msg.getCategoryId(), msg.getShopId(), msg.getClientTeaStoreInfo());
    }


    //其中categoryId为0,表示全部
    @RequestMapping(value = "/listProduct/{categoryId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientTeaStore> listProduct(@PathVariable Long categoryId, @RequestBody @Valid ListProductMsg msg) {
        return productService.listProduct(categoryId, msg.getPageRequest());
    }

}
