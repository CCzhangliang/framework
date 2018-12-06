package com.zl.framework;

import com.zl.framework.entity.Dict;
import com.zl.framework.entity.Hobby;
import com.zl.framework.mapper.master.DictMapper;
import com.zl.framework.mapper.slave.HobbyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class FrameworkDatasourceApplication {

    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HobbyMapper hobbyMapper;

    public static void main(String[] args) {
        SpringApplication.run(FrameworkDatasourceApplication.class, args);
    }
    @RequestMapping("/list")
    @ResponseBody
    public String list(){
        List<Dict> list = dictMapper.listAllDict();
        return list.size()+"";
    }
    @RequestMapping("/update")
    @ResponseBody
    public int update(){
        Hobby hobby = hobbyMapper.listAllHobby().get(0);
        hobby.setCreateTime(new Date());
        return hobbyMapper.updateHobbyByPrimaryKey(hobby);
    }
}
