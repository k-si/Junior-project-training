package run.autoium.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import run.autoium.common.DataCode.response.R;
import run.autoium.entity.po.ApiCaseSuite;
import run.autoium.entity.vo.ApiCaseSuiteVo;
import run.autoium.service.ApiCaseSuiteService;

import java.util.List;

/**
 * <p>
 * 用例模块 前端控制器
 * </p>
 *
 * @author Ks1r
 * @since 2020-11-28
 */
@RestController
@RequestMapping("/autoium/api-case-suite")
@CrossOrigin
public class ApiCaseSuiteController {

    @Autowired
    private ApiCaseSuiteService apiCaseSuiteService;

    /**
     * 条件分页查询数据
     * @param current 当前页
     * @param limit 每页大小
     * @param apiCaseSuiteVo 可选条件
     * @return
     */
    @PostMapping("/PageCaseCondition/{current}/{limit}")
    public R getCaseListPage(@PathVariable("current") Long current,
                             @PathVariable("limit") Long limit,
                             @RequestBody(required = false) ApiCaseSuiteVo apiCaseSuiteVo){
        //创建分页查询
        //创建页面
        Page<ApiCaseSuite> page = new Page<>(current , limit);
        //创建查询对象的对象
        QueryWrapper<ApiCaseSuite> wrapper = new QueryWrapper<>();

        //判断条件，如果非空再去加条件，动态查询
        if(apiCaseSuiteVo != null){

            String name = apiCaseSuiteVo.getName();

            String description = apiCaseSuiteVo.getDescription();

            String gmtCreateStart = apiCaseSuiteVo.getGmtCreateStart();

            String gmtCreateEnd = apiCaseSuiteVo.getGmtCreateEnd();


            if(!Strings.isEmpty(name)){
                wrapper.like("name" , name);
            }

            if(!Strings.isEmpty(description)){
                wrapper.like("description" , description);
            }

            if(!Strings.isEmpty(gmtCreateStart)){
                wrapper.ge("gmt_create" , gmtCreateStart);
            }

            if(!Strings.isEmpty(gmtCreateEnd)){
                wrapper.le("gmt_modified" , gmtCreateEnd);
            }
        }
        //排序
        wrapper.orderByAsc("id");

        //查询
        apiCaseSuiteService.page(page , wrapper);

        //获取数据
        long total = page.getTotal();
        List<ApiCaseSuite> list = page.getRecords();

        //返回
        return R.ok().data("list" , list).data("total" , total);
    }

    /**
     * 根据id值获取用例
     * @param id
     * @return
     */
    @GetMapping("/getCase/{id}")
    public R getCaseById(@PathVariable("id") String id){
        ApiCaseSuite apiCaseSuite = apiCaseSuiteService.getById(id);

        if(apiCaseSuite != null){
            return R.ok().data("case" , apiCaseSuite);
        }else{
            return R.error().message("查询失败");
        }
    }

    /**
     * 根据id删除用例
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteApiCaseSuiteById(@PathVariable("id") String id){

        boolean b = apiCaseSuiteService.removeById(id);

        if(b == true){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }

    /**
     * 添加一条用例
     * @param apiCaseSuite
     * @return
     */
    @PostMapping("/addCase")
    public R addCase(@RequestBody ApiCaseSuite apiCaseSuite){

        System.out.println(apiCaseSuite.getProjectId());
        boolean b = apiCaseSuiteService.save(apiCaseSuite);

        if(b == true){
            return R.ok().message("添加成功");
        }else{
            return R.error().message("添加失败");
        }
    }

    @PostMapping("/updateCase")
    public R updateCase(@RequestBody ApiCaseSuite testcase){
        boolean b = apiCaseSuiteService.updateById(testcase);


        if(b){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }
    }

}

