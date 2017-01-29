package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TaotaoResult;
import com.taotao.search.service.impl.ItemServiceImpl;

@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @RequestMapping("/importAll")
    @ResponseBody
    public TaotaoResult importAllItems() {
        return itemService.importAllItems();
    }
}
