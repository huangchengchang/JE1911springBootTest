package com.hqyj.springBootTest.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.springBootTest.modules.test.entity.City;
import com.hqyj.springBootTest.modules.test.entity.Country;
import com.hqyj.springBootTest.modules.test.service.CountryService;
import com.hqyj.springBootTest.modules.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private final static Logger LOGGER =LoggerFactory.getLogger(TestController.class);
	@Autowired
	private CountryService cs;
	
	@Value("${server.port}")
	private int port;
	@Value("${com.hqyj.name}")
	private String name;
	@Value("${com.hqyj.age}")
	private int age;
	@Value("${com.hqyj.desc}")
	private String desc;
	@Value("${com.hqyj.random}")
	private String random;
	@Autowired
	private ConfigBean configbean;
	
	@RequestMapping("/config")
	@ResponseBody
	public String configTest() {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(port).append("--").
			append(name).append("--").
			append(age).append("--").
			append(desc).append("--").
			append(random).append("</br>");
		sbBuffer.append(configbean.getName()).append("--").
			append(configbean.getAge()).append("--").
			append(configbean.getDesc()).append("--").
			append(configbean.getRandom()).append("</br>");
		return sbBuffer.toString();
		
	}
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		LOGGER.trace("trace");
		LOGGER.debug("debug");
		LOGGER.info("info");
		LOGGER.warn("warn");
		LOGGER.error("error");
		return "test logaaaaaaaaabbbbbbbbbaaaaaaaaa";
	}
	
	@RequestMapping("/demo1")
	@ResponseBody
	public String demoDesc(HttpServletRequest req,@RequestParam String key) {
		String parameter = req.getParameter("key");
		return "helloWorld!"+" "+parameter+"||"+key;
	}
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		List<City> cities = cs.queryCitiesByCountryId(522).stream().limit(10)
				.collect(Collectors.toList());
		City city = cs.queryCitiesByCountryId(522).get(0);
		Country country = cs.getCountryById(522);
		//modelMap.addAttribute("template", "test/index");
		modelMap.addAttribute("thymeleafTitle", "This is thymeleafTitle");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("currentNumber", 111);
		modelMap.addAttribute("baiduUrl", "http://www.baidu.com");
		modelMap.addAttribute("city", city);
		modelMap.addAttribute("shopLogo", "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%E5%94%AF%E7%BE%8E%20%E5%B0%8F%E6%B8%85%E6%96%B0&step_word=&hs=2&pn=0&spn=0&di=138490&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=771274045%2C3394569530&os=239854462%2C7471277&simid=4125716781%2C477255758&adpicid=0&lpn=0&ln=827&fr=&fmq=1389861203899_R&fm=&ic=0&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=%E5%9B%BE%E7%89%87%E5%94%AF%E7%BE%8E&objurl=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201512%2F04%2F20151204220456_UFwNJ.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Fks52AzdH3F%3Ft1%3D9ldcnbd8m&gsm=1&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("updateCityUri", "/cityUpdate");
		modelMap.addAttribute("cities", cities);
		
		return "index";
	}
	
	/*
	 * @PostMapping(value = "/upload",consumes = "multipart/form-data") public
	 * String uploadFile(@RequestParam MultipartFile file, RedirectAttributes
	 * redirectAttributes) { if (file.isEmpty()) {
	 * redirectAttributes.addFlashAttribute("message", "Select file."); return
	 * "redirect:/test/index"; } String fileName = file.getOriginalFilename(); File
	 * destFile = new File(String.format("E:\\upload\\%s", fileName)); try {
	 * file.transferTo(destFile); redirectAttributes.addFlashAttribute("message",
	 * "upload success."); } catch (IllegalStateException | IOException e) {
	 * e.printStackTrace(); LOGGER.debug(e.getMessage());
	 * redirectAttributes.addFlashAttribute("message", "upload failed."); } return
	 * "redirect:/test/index"; }
	 */
	@PostMapping(value="/upload", consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}
		
		String fileName = file.getOriginalFilename();
		
		File destFile = new File(String.format("E:\\upload\\%s", fileName));
		try {
			file.transferTo(destFile);
			redirectAttributes.addFlashAttribute("message", "upload success.");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "upload failed.");
		}
		
		return "redirect:/test/index";
	}
	@PostMapping(value="/uploadBatchFile", consumes="multipart/form-data")
	public String uploadBatchFile(@RequestParam MultipartFile[] files, 
			RedirectAttributes redirectAttributes) {
		
		boolean isEmpty = true;
		try {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
					continue;
				}
				
				String fileName = file.getOriginalFilename();
				File destFile = new File(String.format("E:\\upload\\%s", fileName));
				file.transferTo(destFile);
				
				isEmpty = false;
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "upload failed.");
			return "redirect:/test/index";
		}
		
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
		} else {
			redirectAttributes.addFlashAttribute("message", "upload success.");
		}
		
		
		return "redirect:/test/index";
	}
	
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(
					Paths.get(String.format("E:\\upload\\%s", fileName)).toUri());
			if (resource.exists() && resource.isReadable()) {
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
						.header(HttpHeaders.CONTENT_DISPOSITION, 
								String.format("attachment; filename=\"%s\"", resource.getFilename()))
						.body(resource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "E:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);
		
		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
					String.format("attachment; filename=\"%s\"", fileName));
			
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "E:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);
		
		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));
				
				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	

}
