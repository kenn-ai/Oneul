package kr.co.oneul.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writeMaterial")
public class WriteMaterialController {

	private static final Logger logger = Logger.getLogger(WriteMaterialController.class);

	@RequestMapping(value = "/imageMaterial")
	public void naverSearchNews(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/xml;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		URLConnection conn = null;
		BufferedWriter bw = null;
		BufferedReader br = null;

		try {
			conn = new URL("http://photo.naver.com/rss/theme/travel").openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			bw = new BufferedWriter(response.getWriter());

			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping(value = "/newsMaterial")
	public void naverSearchNews2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/xml;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		URLConnection conn = null;
		BufferedWriter bw = null;
		BufferedReader br = null;

		try {
			conn = new URL("http://myhome.chosun.com/rss/www_section_rss.xml").openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			bw = new BufferedWriter(response.getWriter());

			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
