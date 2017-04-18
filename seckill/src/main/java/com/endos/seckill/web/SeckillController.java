package com.endos.seckill.web;

import com.endos.seckill.dto.Exposer;
import com.endos.seckill.dto.SeckillExecution;
import com.endos.seckill.dto.SeckillResult;
import com.endos.seckill.entity.Seckill;
import com.endos.seckill.enums.SeckillStateEnum;
import com.endos.seckill.exception.RepeatKillException;
import com.endos.seckill.exception.SeckillCloseException;
import com.endos.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Endos on 2017/04/17.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 首页 /WEB-INF/jsp/list.jsp
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    /**
     * 详情页
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 暴露秒杀地址URL请求
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀请求
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(
            @PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
            @CookieValue(value = "killPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "请注册！");
        }
        SeckillResult<SeckillExecution> seckillResult;
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (Exception e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            seckillResult = new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
        return seckillResult;
    }

    /**
     * 获取系统时间
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> now(){
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
