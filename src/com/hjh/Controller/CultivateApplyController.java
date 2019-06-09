package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.CultivateApply;
import com.hjh.Bean.CultivateRecord;
import com.hjh.Service.CultivateApplyService;


@Controller
@RequestMapping(value="CultivateApplyController")
public class CultivateApplyController {
	@Autowired
	private CultivateApplyService cultivateApplyService;

	@Autowired
	private CultivateApply cultivateApply;
		

}
